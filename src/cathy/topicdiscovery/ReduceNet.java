package cathy.topicdiscovery;

import cathy.matrix.Matrix;
import cathy.matrix.SparseMatrix;

public class ReduceNet {

	public void reduceNet(Matrix q, SparseMatrix edge){ //*****data type of q and edge not known

	}
		
	public void createSubNetwork(){
		
	}
		
	public void graphConnComp(){ //find a replacement for matlab funtion graphconncomp
		
		
	}
//	function subnet = ReduceNet(q,edge)
//	% partition edge into subnetworks
//	% q - k*ni
//	% subnet - k*1 cells, each is the subnetwork of topic i
//	k = size(q,1);
//	subnet = cell(k,1);
//	zfreq = bsxfun(@times,q',edge{1}(:,3));
//	for z=1:k
//	    ind = zfreq(:,z)>1;
//	    if any(ind)
//	        subnet{z} = [edge{1}(ind,1:2) zfreq(ind,z)];
//	        G=sparse([subnet{z}(:,1);subnet{z}(:,2)],...
//	            [subnet{z}(:,2);subnet{z}(:,1)], ...
//	            [subnet{z}(:,3); subnet{z}(:,3)]);        
//	        % find the largest connected component
//	        [S,C]=graphconncomp(G);
//	        count=hist(C,unique(C));
//	        [m,ind] = max(count);
//	        subnet{z}(C(subnet{z}(:,1))~=ind,:)=[];
//
//	    end
//	end
//
	
	
}
