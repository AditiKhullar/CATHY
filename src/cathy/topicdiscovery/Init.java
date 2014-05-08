package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;
import java.util.Timer;

public class Init {
	
	public static String Folder = "/Users/aditi_khullar/Documents/workspace/CATHY_JAVA/data/20conf/";
	
	/**
	 * Main file which creates edge data
	 * @param args
	 */
	
	public static void main (String[] args){
	    int topics = 4;
		int depth = 2;
		int iter = 100;
		
		
		//Creating the main edge data structure
		HashMap<SparseMatrix, Integer> edge_freq = new HashMap<SparseMatrix, Integer>();
		
		long startTime = new Date().getTime();
		//edge_freq = LoadAndRead.ReadEdge( Folder + "title-term.txt");
		edge_freq = LoadAndRead.ReadEdge( Folder + "pt.txt");
		long endTime = new Date().getTime();
		System.out.println("Time taken to create the initial edge structure(miliseconds): " + ((endTime - startTime)));
		
		
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
		long startime = new Date().getTime();
	    BuildTree.addTopicsLevelsRec(Maintopic, depth, topics, iter, addedList);
	    long endtime = new Date().getTime();
	    		
		
		System.out.println("Topic:" + topics);
		System.out.println("Depth:" + depth );
		System.out.println("TIME:" + (endtime - startime));
		
		
		
	}
	
	
	
	
}
