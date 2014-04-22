package cathy.topicdiscovery;
import java.util.*;


public class Node {

	//Vocabulary Map
	Integer wordidx; 
	//Vocabulary distribution given current topic, p(w|z), w is the words, z is the topic, normalized
	HashMap<Integer, Integer> wordval = new HashMap<Integer, Integer>(); 
	//Parent Node
	Node parent;
	//Children Nodes
	ArrayList<Node> children = new ArrayList<Node>();
	double denominator;
	double alpha0;
	//childnum *1 array, topic prior distribution p(child=z|current topic), normalized
	ArrayList<Double> alpha1 = new ArrayList<Double>();
	double d1;
	double time;
	double nnz;
	double twmati;
	String name = new String();
	//Phrase list (N*4, length, ID, Freq, Ranking)
	ArrayList<ArrayList<Integer>> phrases = new ArrayList<ArrayList<Integer>>();
	//Constructor
	public Node(Integer idx, HashMap<Integer, Integer> val, Double de, Double ti, String n){
		this.wordidx = idx;
		this.wordval = val;
		this.denominator = de;
		this.twmati = ti;
		this.name = n;
		
	}
	
	
	
	

	
	
	
	
	

}
