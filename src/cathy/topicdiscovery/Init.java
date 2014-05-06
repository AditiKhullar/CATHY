package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;
import java.util.Timer;

public class Init {
	
	public static String Folder = "/Users/aditi_khullar/Documents/workspace/CATHY_JAVA/data/20conf/";
	
	public static void main (String[] args){
	    int topics = 4;
		int depth = 3;
		int iter = 10000;
		
		
		//Creating the main edge data structure
		HashMap<SparseMatrix, Integer> edge_freq = new HashMap<SparseMatrix, Integer>();
		
		long startTime = new Date().getTime();
		edge_freq = LoadAndRead.ReadEdge( Folder + "pt.txt");
		long endTime = new Date().getTime();
		System.out.println("Time take to create the initial edge structure(seconds)" + ((endTime - startTime)/1000));
		
		
		
		// For iterating over the edge set
		/*
		Iterator<SparseMatrix> it = edge_freq.keySet().iterator(); 
		int i = 0;
		while(it.hasNext()){
			SparseMatrix temp = it.next();
			System.out.print(temp.getwordid1() + " ");
			System.out.print(temp.getwordid2());
			System.out.println();
			i++;
			if(i==500){
			break;
			}
		}*/
		
		//Set of edges which is constant across all topics
		ArrayList<SparseMatrix> edgeset = new ArrayList<SparseMatrix>();
		for(Map.Entry<SparseMatrix,Integer> edgearr : edge_freq.entrySet()){
			edgeset.add(edgearr.getKey());

		}
		System.out.print("Number of edges formed: ");
		System.out.println(edgeset.size());
		
		
		//Edge Multiplier (Initial Value is set to the frequency of each edge)
		ArrayList<Float> freq = new ArrayList<Float>();
		Iterator<SparseMatrix> it2 = edgeset.iterator();
		while(it2.hasNext()){
			freq.add((float)edge_freq.get(it2.next()));
		}
		
		System.out.print("Number of edges frequencies found: ");
		System.out.println(freq.size());
		
		
		
		//Creating the word dictionary
		HashMap<Integer, String> udict = new HashMap<Integer, String>();
		udict = LoadAndRead.ReadName( Folder + "term.txt" );
		
		System.out.print("Total number of unique terms found: ");
		System.out.println(udict.size());
		
		
		//Generate Random topic distribution
		int numberofedges = edge_freq.size();
		float[][] edgeweight1 = Random_gen.generator(numberofedges, topics);
		
		//Create topic and its children
		Topic Maintopic = new Topic(edgeset, freq, edgeweight1, topics);
		Maintopic.Set_edgeweight();
		Maintopic.Set_depth(0);
		
		List<String> addedList = new ArrayList<String>();
		addedList.add(Maintopic.Get_name());
	    BuildTree.addTopicsLevelsRec(Maintopic, depth, topics, iter, addedList);
	    
		//Find rhoi and theta 1
	    
	    //This call is to be made in the BuildTree's recurrsive function
		//BuildTree.EM(Maintopic); 	
		
		//Add children finding eij for each
		//Run for each children
		//Find eij
		
		
		
		
		
		System.out.println("The edge data structure and dictionary created");
		
	}
	
	
	
	
}
