package cathy.matrix;

public class Matrix {
	private Integer paperid;
	private Integer wordid;
	public Matrix(Integer pid, Integer tid){
		this.paperid = pid;
		this.wordid = tid;
	}
	
	public Integer getPaperid(){
		return paperid;
	}
	
	public Integer getWordid(){
		return wordid;
	}
}
