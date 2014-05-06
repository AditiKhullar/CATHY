package cathy.topicdiscovery;

import java.util.*;
import java.io.*;

import cathy.matrix.SparseMatrix;
import cathy.matrix.Matrix;

public class EM {
	
	
	
	
	
	
	
	
	
	
	
	// /Basic EM algorithm, matrix implementation, larger memory, faster runtime
	//function [theta,w,q,ll] = EM(options,A,k,theta,w)
	//A(edges), theta, w, k need be input
	//*_o are output variables, returned with the object of type EM
	//
//	double theta_o[][];
//	double w_o[][];
//	double q_o[][];
//	double ll_o[][];
//	
	//*_i are input variables, accepted with the call of function EM_calculations
//	public void EM_calculation(String options_i, int[][] A_i, int k_i, double[][] theta_i, double [][] w_i ){
//	
//	}
	
//	public void InitFunction(SparseMatrix[] edgeCell, int k){
//		// chi: edgeCell is an array of sparse matrices
//		function [theta,rho] = Init(edgeCell, k)
//				% initialization of model parameters
//				% k: [k_1,k_2,...,k_t] number of groups for each type - currently only
//				% support a single k for all types
//				% method: random by default
//				% edgeCell must be a cell of one matrix
//				    if ~exist('options','var') || ...
//				            ~isfield(options,'method') || strcmp(options.method,'random')
//				        E = edgeCell{1};
//				        n = max(max(E(:,1:2)));
//				        % make sure E is triu, and E(i,j) = #edges between (i,j)
//				        % if E is symmetric, and E(i,j) = #edges between (i,j), then E(i,i)
//				        % should be 2#edges(i,i); if E(i,j)=1/2#edges between (i,j), then
//				        % E(i,i) = #edges betweeen (i,i)
//				        theta = cell(1);
//				        rho = zeros(1,k);
//				        theta{1} = zeros(n,k);
//				        m = size(E,1);
//				        q = rand(m,k);
//				        q = bsxfun(@rdivide,q,sum(q,2));
//				        for z=1:k
//				            Emat = sparse(E(:,1),E(:,2),E(:,3).*q(:,z),n,n);
//				            theta{1}(:,z) = sum(Emat,2)+(sum(Emat,1))';
//				            rho(z) = sum(Emat(:));
//				        end
//				        
//				        for z=1:k
//				            theta{1}(:,z) = theta{1}(:,z) / sum(theta{1}(:,z));
//				        end
//				    end
//				     
//				end

//	}
	
}
