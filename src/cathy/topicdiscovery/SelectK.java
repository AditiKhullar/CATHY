package cathy.topicdiscovery;

public class SelectK {

	public SelectK(Node root, String options){
		
	}
//	
//	function [k,bicscore] =SelectK(root, options)
//	% select the number of clusters for root
//	% options.selk = bic - BIC score
//	% options.selk = load - load from stored data
//	% options.selk = dblp - for top level, use given value; for lower level,
//	% use a fixed number 4
//	if ~isfield(root,'range')
//	    range =3:6;
//	else
//	    range = root.range;
//	end
//	if ~isfield(options,'selk')
//	    k = ceil((range(1)+range(end))/2);
//	elseif strcmp(options.selk,'BIC')
//	    if length(range)==1
//	        k = range;
//	    else
//	        bicscore = zeros(length(range),4);
//	        for i=1:length(range)
//	            k = range(i);
//	            [theta0,rho0] = init(root.edge, k);
//	            [theta,~,~,ll] = EM(options,root.edge,k,theta0,rho0);
//	            np = numel(theta{1}); 
//	            % rho and the last row of phi are called even         
//	            nd = size(theta{1},1);
//	            bicscore(i,:) = [ll,np,log(nd),-2*ll+np*log(nd)];
//	        end
//	        [m,ind] = min(bicscore(:,4));
//	        k = range(ind);
//	    end
//	elseif strcmp(options.selk,'load')
//	    load([root.prefix '_0.mat' ],'-mat');
//	    k = root.k;
//	elseif strcmp(options.selk,'dblp')
//	    if ~root.depth
//	        k = range;
//	    else
//	        k = 4;
//	    end
//	elseif strcmp(options.selk,'vldb')
//	    if ~root.depth
//	        k = 5;
//	    else
//	        k = 3;
//	    end
//	end

}
