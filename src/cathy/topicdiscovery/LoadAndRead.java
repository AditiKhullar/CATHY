package cathy.topicdiscovery;

import java.util.*;
import java.io.*;
import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class LoadAndRead {
	public static String Folder = "/Users/aditi_khullar/Documents/workspace/CATHY_JAVA/data/20conf/";
	
	public static void LoadData(String paperterm, String term ){
		
		//HashMap of the Vocabulary Terms
		HashMap<Integer, String> udict = new HashMap<Integer, String>();
		udict = ReadName( Folder + "term.txt" );
		//Array List of all the eldges ( term id, term id, document id)
		HashMap<SparseMatrix, Integer> uup = new HashMap<SparseMatrix, Integer>();
		uup = ReadEdge( Folder + "pt.txt");
		
		//To check the structure of uup un-comment these lines and change the value of i to increase sample size
		System.out.println("\nSample Edges:");
		System.out.println("WordId1" + "\t" + "WordId2 "+ "\t" +  "DocId");
		/*for(int i = 0; i < 100; i ++){
			
			System.out.println(uup.get(i).getwordid1() + "\t " + uup.get(i).getwordid2() + "\t\t " +  uup.get(i).getdocumentid());
		}*/
		
		Iterator<SparseMatrix> iterator = uup.keySet().iterator();
		while(iterator.hasNext()){
			SparseMatrix next = iterator.next();
			String key1 = next.getwordid1().toString();
			String key2 = next.getwordid2().toString();
			Integer value = uup.get(next);  
			   
			//System.out.println(key + " " + value);
			System.out.println(key1 + " " + key2 + " " + value.toString());
			
		}
		

	}

	public static HashMap<SparseMatrix, Integer> ReadEdge(String edgeFile){
		//Reads the edge from the file pt.txt
		HashMap<SparseMatrix, Integer> readuup = new HashMap<SparseMatrix, Integer>();
		ArrayList<Matrix> paperterm = new ArrayList<Matrix>();
		
		try{
			FileInputStream fstreampt = new FileInputStream(edgeFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstreampt));
			//Read pt.txt file and store in a HashMap
			for (String line; (line = br.readLine()) != null;){
				String[] parts = line.split("\\t", -1);
				paperterm.add(new Matrix(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
			}
			
			br.close();
				
		}
		catch (Exception e){
			System.err.println("FileNotFoundException:" + e.getMessage());
		}
		
		//The first integer on the last row of the pt.txt file is assumed to be the highest doc id number
		int totaldocs = paperterm.get(paperterm.size()-1).getPaperid();
		int papertermsize = paperterm.size();
		System.out.println("Total Documents found:" + totaldocs);
		int docid = 1;
		
		System.out.println("Creating Edge data structure...");
		while (docid <= totaldocs){
  			ArrayList<Integer> wordsperdoc = new ArrayList<Integer>();
			
  			for(int j = 0; j < papertermsize; j++)
			{	
				 if(paperterm.get(j).getPaperid() == docid){
					wordsperdoc.add(paperterm.get(j).getWordid());
   				}
				
			}
  			
  			int size = wordsperdoc.size();

  			for(int a = 0; a < size; a++){
  				for(int b = a+1; b < size; b++){
  					SparseMatrix temp = new SparseMatrix(wordsperdoc.get(a),wordsperdoc.get(b));
  					if (readuup.containsKey(temp)){
  						Integer count = readuup.get(temp);
  						count += 1;
  						readuup.put(temp, count);
  					}
  					
  					else{
  						readuup.put(temp, 1);
  					}
  					//readuup.add(new SparseMatrix(wordsperdoc.get(a),wordsperdoc.get(b),docid));
  				}
  			}
  			
  			docid++;
  			
		}
		System.out.println("Creation complete");
		return readuup;

	}

	
	public static HashMap<Integer, String> ReadName(String filename){
		
		
		HashMap<Integer, String> udict = new HashMap<Integer, String>();
		//Read the input file stream
		try{
		FileInputStream fstream = new FileInputStream(filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
			for(String line; (line = br.readLine()) != null;){
				String parts[] = line.split("\\t", -1);
				udict.put(Integer.parseInt(parts[0]), parts[1]);				
			}
			
		br.close();
		
		}
		catch (Exception e){
			System.err.println("FileNotFoundException: " + e.getMessage());
		}
		
		return udict;

		
	}
	
	public static void main (String[] args){
		LoadData( Folder + "pt.txt", Folder + "term.txt");
	}
	
}
