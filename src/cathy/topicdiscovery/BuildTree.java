package cathy.topicdiscovery;

public class BuildTree {

	public void BuildTree(Node root, String options){
//	function tree = BuildTree(root, options)
//	% build the ontology tree rooted at `root', recursively
//	% root.edge - the term-term network
//	% root.idterm - dictionary of id to name
//	% root.np - number of documents that contain this topic
//	% root.prefix - the prefix of the output file name
//	% root.depth - the depth of the root node in the entire tree
//	% every topic is named as [root.prefix '.' z]
//	maxdepth = options.maxdepth;
//	if ~isfield(options,'recompute')
//	    recompute = 0;  % don't recompute if the node exists in the tree
//	else
//	    recompute = options.recompute;
//	end
//	if ~isfield(options,'Iter')
//	    Iter = 5; % number of random initializations
//	else
//	    Iter = options.Iter;
//	end
//
//	%% clustering
//	pos = find(root.prefix=='/',1,'last');
//	if isempty(pos) pos=0; end
//	name = root.prefix(pos+1:end);    
//	name(name=='.')='/';
//	name = ['o' name];
//	tree = node([], [], [], [], name);
//	if root.depth<maxdepth 
//	  if recompute || ~exist('k','var')
//	      % select number of topics
//	      k = SelectK(root, options)
//	  end
//	  if ~isfield(root,'k') || root.k ~= k
//	      root.k = k;
//	  end
//	  if recompute || ~exist([root.prefix '_' int2str(k) '.mat'],'file') 
//	    % topic discovery step
//	    maxll = -inf;    
//	    rt = zeros(Iter,1); % running time
//	    edge = root.edge;
//	    for iter = 1:Iter
//	        start = tic;
//	        [theta,rho] = Init(edge, k);
//	        [theta,rho, q,ll]=EM(options,...
//	                edge,k,theta,rho);
//	        rt(iter) = toc(start);
//	        if ll>maxll
//	            maxll = ll;
//	            besttheta = theta;
//	            bestw = rho;
//	            bestq = q;
//	        end
//	    end
//	    [maxll mean(rt)]
//	    theta = besttheta;
//	    rho = bestw;
//	    q = bestq;    
//	  else
//	    load([root.prefix '_' int2str(k) '.mat'],'-mat'); 
//	  end
//	    tree.alpha1 = rho';
//	    tree.time = rt;
//	    tree.children = cell(1,k);
//	end
//	%     tree.map = root.map;
//
//	%% go down - the recursion
//	if root.depth<maxdepth
//	    prefix = [root.prefix '.'];
//	    subnet = ReduceNet(q,root.edge);
//	    for z = 1:k
//	%         if isempty(subnet{z}) continue; end
//	        newroot.prefix = [prefix int2str(z)];
//	        newroot.depth = root.depth + 1;
//	        newroot.range = root.range;
//	% renumber the nodes to be 1 to N
//	        [new2old,newroot.edge]=Renumber(subnet(z));
//	        newroot.map = root.map(new2old);
//	        child = BuildTree(newroot,options);
//	        child.wordval = theta{1}(:,z);
//	        child.wordidx = root.map(1:size(theta{1},1));
//	        child.parent = tree;
//	        tree.children{z}=child;
//	    end
//	end


	}

	
	
	

}
