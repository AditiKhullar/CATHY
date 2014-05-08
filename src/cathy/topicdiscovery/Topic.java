package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class Topic {

	private ArrayList<SparseMatrix> t_edgeset; // set of constant edges across all
											// topics
	private ArrayList<Float> t_edgemultiplier; // eij - edge weight from prior
	private float[][] t_partialedgeweight; // n*k Matrix ; n = number of edges
											// and k = topics
	private float[][] t_edgeweight; // Calculated for every topic until
									// converges
	private List<Topic> t_children; // Contains the subtopics for each topic
	private Topic t_parent; //Contains the parent of the topic. For root is it null
	private boolean t_isprocessed;
	private int depth;
	private float[] rho_z;//one dimensional array with length equal to the total number of topics
	private float[][] thetai;//two dimensional array with length equal to the total number of unique words in topic * total number of topics 
	private String name; //May be an ID by concatenating with the depth
	//private HashMap<Integer, Float> word_distribution;//distribution of each word in the topic Key=wordid and value is the word distribution

	public Topic(ArrayList<SparseMatrix> edgeset, ArrayList<Float> edgemultiplier,
			float[][] partialedgeweight, int topics) {
		this.t_edgeset = edgeset;
		this.t_edgemultiplier = edgemultiplier;
		this.t_partialedgeweight = partialedgeweight;
		this.t_children = new ArrayList<Topic>();
		t_edgeweight = new float[t_partialedgeweight.length][t_partialedgeweight[0].length];
		rho_z = new float[topics];
		Arrays.fill(rho_z, 0);
		thetai = new float[Get_Maxwordid()][topics];
		Fillzero2D(thetai);
		this.name = new String();
		
	}

	public void Set_edgeweight() {
		int ni = t_partialedgeweight.length;
		int nj = t_partialedgeweight[0].length;

		for (int i = 0; i < ni; i++) {
			for (int j = 0; j < nj; j++) {
				Float temp = t_edgemultiplier.get(i);
				t_edgeweight[i][j] = t_partialedgeweight[i][j] * temp;
			}
		}

	}
	
	public void Set_edgewieghtE(float[][] temp){
		int ni = temp.length;
		int nj = temp[0].length;

		for (int i = 0; i < ni; i++) {
			for (int j = 0; j < nj; j++) {
				t_edgeweight[i][j] = temp[i][j];
			}
		}
	}

	public float[][] Get_edgeweight() {
		return t_edgeweight;

	}
	
	public ArrayList<SparseMatrix> Get_edgeset(){
		return t_edgeset;
	}
	
	public Integer Get_edgesetSize(){
		return t_edgeset.size();
	}
	
	public Integer Get_Maxwordid(){
		Integer MaxwordId = new Integer(1);
		Integer size = new Integer(Get_edgesetSize());
		Iterator<SparseMatrix> it = t_edgeset.iterator();
		while(it.hasNext()){
			SparseMatrix current = it.next();
			int currentw1 = current.getwordid1();
			int currentw2 = current.getwordid2();
			if (currentw1 > MaxwordId){
				MaxwordId = currentw1;
			}
			if (currentw2 > MaxwordId){
				MaxwordId = currentw2;
			}	
		}
		return MaxwordId;
		
	}
	
	public void Set_depth(int d){
		depth = d;
	}
	
	public int Get_depth(){
		return depth;
	}
	
	
	public String Get_name(){
		return name;
	}
	
//	public void Set_rho_z(float[] temprho){
//		int sizerho = temprho.length; //sizerho should be same as the number of child topics
//		for(int i = 0; i < sizerho; i++){
//			rho_z[i] = temprho[i];
//		}
//	}
	
	public int Get_Totalsubtopics(){
		return t_children.size();
	}
	
	
	public void Set_rho_zM(float[] rho){
		for(int i = 0; i < rho.length; i++){
			rho_z[i] = rho[i];
		}
	}
	
	public float[] Get_rho_z(){
		return rho_z;
	}
	
	
	public void Set_thetai(float[][] temp){
		
		for(int i = 0; i< temp.length; i++){
			for(int j = 0; j < temp[0].length; j ++){
				thetai[i][j] = temp[i][j];
			}
			
		}
			
	}
	
	public float[][] Get_thetai(){
		return thetai;
	}
	
	public void Set_parent(Topic parent){
		this.t_parent = parent;
	}
	
	public void Set_Child(Topic newChild) {
        t_children.add(newChild);
        newChild.Set_parent(this);
    }
	
	public float[] clone1df(float[] orig){
		int ii = orig.length;
		float[] temp = new float[ii];
		for (int i = 0; i<ii; i++){
			temp[i] = orig[i];
		}
		return temp;
	}
	
	/**
	 * This function allocates a new 2 dimensional array and send the clone of the given array back
	 * @param orig
	 * @return
	 */
	

	public float[][] clone2df(float[][] orig){
		int ii = orig.length;
		int jj = orig[0].length;
		float[][] temp = new float[ii][jj];
		for (int i = 0; i<ii; i++){
			for(int j = 0; j < jj; j++){
				temp[i][j] = orig[i][j];
			}
		}
		return temp;
	}
	
	
	/**
	 * This function creates an Array List of Array List from a given 2 dimensional Float array
	 * @param arr
	 * @return
	 */
	public ArrayList<ArrayList<Float>> ArraytoArrayList(float[][] arr){
		ArrayList<ArrayList<Float>> arrayL = new ArrayList<ArrayList<Float>>();
		
		for (int j = 0; j < arr[0].length; j++) {
			ArrayList<Float> tempL = new ArrayList<Float>();
			for (int i = 0; i < arr.length; i++) {
				tempL.add(arr[i][j]);
			}
			arrayL.add(tempL);
		}

		return arrayL;
	}
	
	
	/**
	 * This functions fills a given 2 Dimensional array with zeros
	 * @param fill
	 * @return
	 */
	
	public float[][] Fillzero2D(float[][] fill){
		int ii = fill.length;
		int jj = fill[0].length;
		for (int i= 0; i< ii; i ++){
			for (int j=0; j<jj; j++){
				fill[i][j] = 0;
			}
		}
		
		return fill;
	}
	
	/**
	 * TODO: a function to check if the given 2 D arrays has zeroes in it, and hence can or can not be used for division
	 */
	
	
	
}
