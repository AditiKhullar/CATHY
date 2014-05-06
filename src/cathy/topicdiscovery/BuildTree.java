package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class BuildTree {

	/**
	 * Builds the tree structure with child topics until the depth for the tree is reached. 
	 * @param root
	 * @param level
	 * @param n_topics
	 * @param iter
	 * @param addedList
	 */
	
	
	// Depth of the main topic root is 0
	public static void addTopicsLevelsRec(Topic root, int level, int n_topics,
			int iter, List<String> addedList) {
		int d = root.Get_depth();
		if (d < level) {
			preEM(root);// k ArrayList<Float>
			EM(root, iter);
			ArrayList<ArrayList<Float>> edgeMul_children = root.ArraytoArrayList(root.Get_edgeweight());
			float[][] edgeweight1 = Random_gen.generator(root.Get_edgesetSize(), n_topics);
			for (int i = 0; i < n_topics; i++) {
				Topic child = new Topic(root.Get_edgeset(),edgeMul_children.get(i), edgeweight1, n_topics); // use topic constructor with value fetched after the EM run
				root.Set_Child(child);
				child.Set_depth(root.Get_depth() + 1);
				child.Set_edgeweight();
				addedList.add(child.Get_name());
				addTopicsLevelsRec(child, level, n_topics, iter, addedList);
			}

		}

	}

	/**
	 * Initial E step initializations
	 * 
	 * @param root
	 * @return
	 */
	public static void preEM(Topic root) {
		// Fetching the edge set for the topic
		ArrayList<SparseMatrix> getset = root.Get_edgeset();

		// Fetching the Max word it of the topic
		Integer MaxWordId = root.Get_Maxwordid();

		// Fetching the edge weight across sub topics(children)
		float[][] edgeweight = root.Get_edgeweight();
		int edgeweight_i = root.Get_edgeweight().length;
		int edgeweight_j = root.Get_edgeweight()[0].length; // Number of columns
															// or topics

		// Fetching original rho value (initially set to 0.0)
		float[] rho = root.Get_rho_z();
		for (int j = 0; j < edgeweight_j; j++) {
			for (int i = 0; i < edgeweight_i; i++) {
				rho[j] = rho[j] + edgeweight[i][j];
			}

		}

		// Setting the newly calculated rho values for each subtopic
		root.Set_rho_zM(rho);

		// printing edge weight
		// for(int i = 0; i < edgeweight_i; i++){
		// for(int j = 0 ; j < edgeweight_j; j++){
		// System.out.print(edgeweight[i][j] + " ");
		// }
		// System.out.println();
		// }

		// printing rho array for the present topic's children
		// for(int f = 0 ; f < rho.length; f++){
		// System.out.print(rho[f] + " ");
		// }
		//
		// Calculate theta i for everyword in each subtopic
		float[][] sumthetai = root.clone2df(root.Get_thetai());
		// System.out.println("rows: " + sumthetai.length);
		// System.out.println("cols: " + sumthetai[0].length);
		//
		Iterator<SparseMatrix> it = getset.iterator();
		int ew = 0;
		while (it.hasNext()) {
			SparseMatrix current = it.next();
			for (int t = 0; t < root.Get_Totalsubtopics(); t++) {
				sumthetai[current.getwordid1() - 1][t] = sumthetai[current.getwordid1() - 1][t] + edgeweight[ew][t];
				sumthetai[current.getwordid2() - 1][t] = sumthetai[current.getwordid2() - 1][t] + edgeweight[ew][t];
			}
			ew++;
		}

		// For printing the theta value of each word across the topics
		// int n = sumthetai.length;
		// int m = sumthetai[0].length;
		//
		// for(int i = 0; i < n ; i++){
		// for(int j = 0; j < m; j++){
		// System.out.print(sumthetai[i][j] + " ");
		// }
		// System.out.println("");
		// }
		//

		float[][] thetai = new float[sumthetai.length][sumthetai[0].length];
		for (int i = 0; i < sumthetai.length; i++) {
			for (int j = 0; j < sumthetai[0].length; j++) {
				thetai[i][j] = sumthetai[i][j] / rho[j];
			}
		}
		
		root.Set_thetai(thetai);
		
		// Creating a Thetai in array format to return to the Build Tree
		// function
		
	}
	
	/**
	 * Runs the EM algorithm for the specified number of iterations
	 * @param root
	 * @param iter
	 */

	public static void EM(Topic root, int iter) {
		int iterations = iter;
		while (true) {
			if (iterations == 0) {
				break;
			}
			E(root);
			M(root);
			iterations--;
		}
	}
	
	/**
	 * Takes the root and sets the estimated edge weight
	 * @param root
	 */
	
	public static void E(Topic root) {
		ArrayList<SparseMatrix> edges = root.Get_edgeset();
		float[][] prior_eijk = root.Get_edgeweight();
		int ii = prior_eijk.length; // number of edges
		int jj = prior_eijk[0].length; // number of topics

		float[] rho_z = root.Get_rho_z();
		float[][] thetai = root.Get_thetai();

		// Calculating sum of each edge weight across all topics
		float[] sum_eij = new float[prior_eijk.length];
		Arrays.fill(sum_eij, 0);
		for (int i = 0; i < ii; i++) {
			for (int j = 0; j < jj; j++) {
				sum_eij[i] = sum_eij[i] + prior_eijk[i][j];
			}
		}

		Iterator<SparseMatrix> it = edges.iterator();
		float[][] new_eij = new float[prior_eijk.length][prior_eijk[0].length];
		// TODO fill the above matrix with zero initials

		// i is the loop for each edge
		// j is the loop for each topic

		float[] den_Estep = new float[prior_eijk.length];
		Arrays.fill(den_Estep, 0);

		SparseMatrix curredge = it.next();

		for (int i = 0; i < ii; i++) {
			for (int j = 0; j < jj; j++) {
				den_Estep[i] = den_Estep[i]
						+ (rho_z[j] * thetai[curredge.getwordid1() - 1][j] * thetai[curredge
								.getwordid2() - 1][j]);
			}
			curredge = it.next();
		}

		Iterator<SparseMatrix> it2 = edges.iterator();
		for (int i = 0; i < ii; i++) {
			for (int j = 0; j < jj; j++) {
				new_eij[i][j] = sum_eij[i]
						* ((rho_z[j] * thetai[curredge.getwordid1() - 1][j] * thetai[curredge
								.getwordid2() - 1][j]) / den_Estep[i]);
			}
			curredge = it2.next();
		}

		root.Set_edgewieghtE(new_eij);

	}
	
	/**
	 * Takes the root and sets the theta i and rho values for each topics and word
	 * @param root
	 */
	public static void M(Topic root) {
		ArrayList<SparseMatrix> edges = root.Get_edgeset();
		
		float[][] eijk = root.clone2df(root.Get_edgeweight());
		int ii = eijk.length; // number of edges
		int jj = eijk[0].length; // number of topics
		
		//float[] rho_z_M = root.clone1df(root.Get_rho_z());
		float[] rho_z_M = new float[eijk[0].length];
		Arrays.fill(rho_z_M, 0);
		
		for(int j = 0; j< jj; j++){
			for(int i = 0; i< ii; i ++){
				rho_z_M[j] = rho_z_M[j] + eijk[i][j];
			}
		}
		
		root.Set_rho_zM(rho_z_M);
		
		/**
		 * Setting the theta values for each word across topics
		 */
		float[][] sumthetai = new float[root.Get_thetai().length][root.Get_thetai()[0].length];
		
		for(int i = 0; i< sumthetai[0].length; i++){
			Arrays.fill(sumthetai[i], 0);
		}
		
		Iterator<SparseMatrix> it = edges.iterator();
		int ew = 0;
		while (it.hasNext()) {
			SparseMatrix current = it.next();
			for (int t = 0; t < root.Get_Totalsubtopics(); t++) {
				sumthetai[current.getwordid1() - 1][t] = sumthetai[current.getwordid1() - 1][t] + eijk[ew][t];
				sumthetai[current.getwordid2() - 1][t] = sumthetai[current.getwordid2() - 1][t] + eijk[ew][t];
			}
			ew++;
		}
		
		float[][] thetai = new float[sumthetai.length][sumthetai[0].length];
		for (int i = 0; i < sumthetai.length; i++) {
			for (int j = 0; j < sumthetai[0].length; j++) {
				thetai[i][j] = sumthetai[i][j] / rho_z_M[j];
			}
		}
		
		
		
		


	}



}