package cathy.topicdiscovery;

import java.util.ArrayList;


/**
 * The tree structure for each topics hierarchy
 * @author aditi_khullar
 *
 */


public class Tree {

	private Topic root;
	
	public Tree(Topic root){
		this.root = root;
	}
	
	public boolean isEmpty(){
		return (root == null)? true : false;
	}
	
	public Topic Get_root(){
		return root;
	}
	
	public Topic Set_root(Topic root){
		return this.root = root;
	}
	
//	public boolean exists(Topic key){
//		return find(root, key);
//	}
	
	public int Get_numberofnodes(Topic root){
		return Get_numberOfDescendants(root) + 1;
	}
	
	public int Get_numberOfDescendants(Topic node) {
		Integer n = node.Get_Totalsubtopics();
		return n;

	}
	
	//TODO: get top 100 unigrams of each level
	//TODO: get names of each topics
//	public String Get_topicAtLevel(Topic root){
//		
//	}
	
	
	
	
	
}
