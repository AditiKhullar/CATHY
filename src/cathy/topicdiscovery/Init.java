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
		
		//Creating the word dictionary
		HashMap<Integer, String> udict = new HashMap<Integer, String>();
		udict = LoadAndRead.ReadName( Folder + "term.txt" );
		
		
		//Generate Random topic distribution
		int numberofedges = edge_freq.size();
		System.out.println(numberofedges);
		
		//float[][] edgeweight1 = Random_gen.generator(numberofedges, numberofedges);
/*		
		for (int i = 0; i < numberofedges; i++){
			for (int j = 0; j < numberofedges; j++){
				//Random rnd = new Random();
				System.out.print(edgeweight1[i][j]);
			}
			
			System.out.println("");
			
	}*/
		
		
		
		
		//Create children
		//Find rhoi and theta 1
		//Find eij
		
		
		
		
		
		System.out.println("The edge data structure and dictionary created");
		
	}
	
	
	
	
}
