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
			
			//System.out.println("At Level: " + root.Get_depth());
			EM.preE(root);// k ArrayList<Float>
			
			float[][] edgeweight = root.Get_edgeweight();

			
			//float[][] thetai = root.clone2df(root.Get_thetai());
			//System.out.println("Theta i in build tree now" );
			
				
			long startTime = new Date().getTime();
			EM.EM_run(root, iter);
			long endTime = new Date().getTime();
			
			//root.Set_name();
			
			//-----Printing the edge weight for each sub topics for root at Level 0----//
			if (d ==0 ){
				System.out.println("Time taken to Run the EM at the root level(milliseconds): " + ((endTime - startTime)));
//				
//				System.out.print("       " + "  	");
//				for(int k = 0; k< edgeweight[0].length; k++){
//					System.out.print( "Topic " + (k+1) + "		");
//				}
//				System.out.println("");
//				List<SparseMatrix> edges = root.Get_edgeset();
//				Iterator<SparseMatrix> it = edges.iterator();
//				
//				for(int i = 0; i< edgeweight.length; i++){
//				SparseMatrix curredge = it.next();
//				System.out.print(curredge.getwordid1());
//				System.out.print(" ~~ " + curredge.getwordid2() + "  	");
//				//System.out.println("");
//				int n=0;
//				while(n<20){
//					for(int j = 0; j< edgeweight[0].length; j++){
//						System.out.print(edgeweight[i][j] + "	");
//					} System.out.println("");
//				n++;
//				}
//				}
//			
			}
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

	


}