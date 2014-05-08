package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import javax.swing.Box.Filler;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class EM {
	
	
	/**
	 * Initial E step initializations
	 * 
	 * @param root
	 * @return
	 */
	public static void preE(Topic root) {
		
		
		// Fetching the edge set for the topic
		ArrayList<SparseMatrix> getset = root.Get_edgeset();
		
		//System.out.println("Edge size" + getset.size());

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

		// ROOT SETTING RHO : Setting the newly calculated rho values for each subtopic
		root.Set_rho_zM(rho);
		//System.out.println("[EM] rho set");
		// printing edge weight
		// for(int i = 0; i < edgeweight_i; i++){
		// for(int j = 0 ; j < edgeweight_j; j++){
		// System.out.print(edgeweight[i][j] + " ");
		// }
		// System.out.println();
		// }

		 //printing rho array for the present topic's children
//		 for(int f = 0 ; f < rho.length; f++){
//		 System.out.print(rho[f] + " ");
//		 }
		//
		// Calculate theta i for everyword in each subtopic
		float[][] sumthetai = new float[root.Get_thetai().length][root.Get_thetai()[0].length];
		for(int i = 0; i< root.Get_thetai()[0].length; i++ ){
			Arrays.fill(sumthetai[i], 0);
		}
		
		
		
		// System.out.println("rows: " + sumthetai.length);
		// System.out.println("cols: " + sumthetai[0].length);
		//
		Iterator<SparseMatrix> it = getset.iterator();
		int ew = 0;
		
		while (it.hasNext()) {
			SparseMatrix current = it.next();
			
			for (int t = 0; t < sumthetai[0].length; t++) {
				sumthetai[current.getwordid1() - 1][t] = sumthetai[current.getwordid1() - 1][t] + edgeweight[ew][t];
				sumthetai[current.getwordid2() - 1][t] = sumthetai[current.getwordid2() - 1][t] + edgeweight[ew][t];
			}
			ew++;
		}

		// For printing the theta value of each word across the topics
		 int aa = sumthetai.length;
		 int bb = sumthetai[0].length;
		
		 
//		 System.out.println("-------sum theta>>>>>>>-----");
//		 for(int a = 0; a < aa ; a++){
//		 for(int b = 0; b < bb; b++){
//		 System.out.print(sumthetai[a][b] + " ");
//		 }
//		 System.out.println("");
//		 }
		//

		float[][] thetaiEM = new float[sumthetai.length][sumthetai[0].length];
		for (int i = 0; i < sumthetai.length; i++) {
			for (int j = 0; j < sumthetai[0].length; j++) {
				thetaiEM[i][j] = sumthetai[i][j] / rho[j];
			}
		}
		
		
		//System.out.println("Calling thetai set to set the preEM values of theta i");
		root.Set_thetai(thetaiEM);
		
//		int nn = thetaiEM.length;
//		 int mm = thetaiEM[0].length;
//		 System.out.println("-------Theta of each word in each topic-----");
//		 for(int i = 0; i < nn ; i++){
//		 for(int j = 0; j < mm; j++){
//		 System.out.print(thetaiEM[i][j] + " ");
//		 }
//		 System.out.println("");
//		 }
		
		// Creating a Thetai in array format to return to the Build Tree
		// function
		
		//System.out.println("thetai set");
		
		
	}
	
	
	
	
	public static void EM_run(Topic root, int iter) {
		//System.out.println("Running EM for root level:" + root.Get_depth());
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
		float[][] prior_eijk = root.clone2df(root.Get_edgeweight());

		
		int ii = prior_eijk.length; // number of edges
		int jj = prior_eijk[0].length; // number of topics

		float[] rho_z = root.clone1df(root.Get_rho_z());
		float[][] thetai = root.clone2df(root.Get_thetai());
		
		// Calculating sum of each edge weight across all topics
		
		
		float[] sum_eij = new float[prior_eijk.length];
		Arrays.fill(sum_eij, 0);
		for (int i = 0; i < ii; i++) {
			for (int j = 0; j < jj; j++) {
				sum_eij[i] = sum_eij[i] + prior_eijk[i][j];
			}
		}
		

		Iterator<SparseMatrix> it = edges.iterator();
		float[] den_Estep = new float[prior_eijk.length];
		
		
		for (int i = 0; i < ii; i++) {
			SparseMatrix curredge = it.next();
			for (int j = 0; j < jj; j++) {
				
				den_Estep[i] = den_Estep[i]
						+ (rho_z[j] * thetai[curredge.getwordid1() - 1][j] * thetai[curredge.getwordid2() - 1][j]);
			}
			//curredge = it.next();
		}

		
		
//		System.out.println("DENOMINATOR"); //Working fine
//		for(int i = 0; i< den_Estep.length; i++){
//			if(den_Estep[i]!=0){
//			System.out.println(den_Estep[i]);
//			}
//			else{
//				System.out.println("WARNING");
//			}
//		}
		
		
		float[][] new_eij = new float[prior_eijk.length][prior_eijk[0].length];
		Iterator<SparseMatrix> it2 = edges.iterator();
		for (int i = 0; i < ii; i++) {
			SparseMatrix curredge = it2.next();
			for (int j = 0; j < jj; j++) {
				new_eij[i][j] = sum_eij[i]
						* ((rho_z[j] * thetai[curredge.getwordid1() - 1][j] * thetai[curredge
								.getwordid2() - 1][j]) / den_Estep[i]);
			}
			
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
		 * Setting the thetai values for each word across topics
		 */
		float[][] sumthetai = new float[root.Get_thetai().length][root.Get_thetai()[0].length];
		
		for(int i = 0; i< sumthetai[0].length; i++){
			Arrays.fill(sumthetai[i], 0);
		}
		
		Iterator<SparseMatrix> it = edges.iterator();
		int ew = 0;
		while (it.hasNext()) {
			SparseMatrix current = it.next();
			for (int t = 0; t < sumthetai[0].length; t++) {
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
