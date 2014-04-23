package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;


public class Init {
	
	public static String Folder = "/Users/aditi_khullar/Documents/workspace/CATHY_JAVA/data/20conf/";
	
	public static void main (String[] args){
	    int topics = 4;
		int depth = 3;
		
		
		//Creating the main edge data structure
		HashMap<SparseMatrix, Integer> edge_freq = new HashMap<SparseMatrix, Integer>();
		edge_freq = LoadAndRead.ReadEdge( Folder + "pt.txt");
		
		//Set of edges which is constant across all topics
		Set<SparseMatrix> edgeset = edge_freq.keySet();
		
		//Edge Multiplier (Initial Value is set to the frequency of each edge)
		ArrayList<Float> freq = new ArrayList<Float>();
		Iterator it = edgeset.iterator();
		while(it.hasNext()){
			freq.add((float)edge_freq.get(it.next()));
		}
		
		//Creating the word dictionary
		HashMap<Integer, String> udict = new HashMap<Integer, String>();
		udict = LoadAndRead.ReadName( Folder + "term.txt" );
		
		//Generate Random topic distribution
		int numberofedges = edge_freq.size();
		float[][] edgeweight1 = Random_gen.generator(numberofedges, topics);
		
		//Create topic and its children
		Topic Maintopic = new Topic(edgeset, freq, edgeweight1, topics);
		Maintopic.Set_edgeweight();
		
		
//		float[][] test = Maintopic.Get_edgeweight();
//		int ni = test.length;
//		int nj = test[0].length;
//		for (int i = 0 ; i <100; i++){
//			for(int j = 0; j <nj; j++){
//				System.out.print(test[i][j] + "--");
//			}
//			System.out.println("");
//		}
		
		
		
		
		
		
		
		//Find rhoi and theta 1
		//Find eij
		
		
		
		
		
		System.out.println("The edge data structure and dictionary created");
		
	}
	
	
	
	
}
