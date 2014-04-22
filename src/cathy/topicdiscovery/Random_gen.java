package cathy.topicdiscovery;

import java.util.*;
import java.io.*;
import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;


public class Random_gen {
	
	public static float[][] generator(int rows, int cols){
		float edgefraction[][] = new float[rows][cols];
		
		
		//Edge fraction (random numbers between 0-1)
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				Random rnd = new Random();
				edgefraction[i][j] = rnd.nextFloat();
			}
		}
		
		return edgefraction;
		
	}
}
