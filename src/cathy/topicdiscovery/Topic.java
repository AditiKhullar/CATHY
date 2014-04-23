package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class Topic {

	private Set<SparseMatrix> t_edgeset; // set of constant edges across all
											// topics
	private ArrayList<Float> t_edgemultiplier; // eij - edge weight from prior
	private float[][] t_partialedgeweight; // n*k Matrix ; n = number of edges
											// and k = topics
	private float[][] t_edgeweight; // Calculated for every topic until
									// converges
	private Topic[] t_children; // Contains the subtopics for each topic
	private Topic Parent;

	public Topic(Set<SparseMatrix> edgeset, ArrayList<Float> edgemultiplier,
			float[][] partialedgeweight, int topics) {
		this.t_edgeset = edgeset;
		this.t_edgemultiplier = edgemultiplier;
		this.t_partialedgeweight = partialedgeweight;
		this.t_children = new Topic[topics];
		t_edgeweight = new float[t_partialedgeweight.length][t_partialedgeweight[0].length];
	}

	public void Set_edgeweight() {
		int ni = t_partialedgeweight.length;
		int nj = t_partialedgeweight[0].length;
//		System.out.println(t_partialedgeweight.length);
//		System.out.println(t_edgemultiplier.size());
		
		
//		int sizemultiplier = 
		for (int i = 0; i < ni; i++) {
			for (int j = 0; j < nj; j++) {
				Float temp = t_edgemultiplier.get(i);
				t_edgeweight[i][j] = t_partialedgeweight[i][j] * temp;
			}
		}

	}

	public float[][] Get_edgeweight() {
		return t_edgeweight;

	}

}
