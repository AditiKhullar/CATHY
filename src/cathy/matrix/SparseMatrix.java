package cathy.matrix;


public class SparseMatrix  {
	
	private Integer wordid1;
	private Integer wordid2;
	//private Integer documentid;
	//ArrayList<SparseMatrix> sparse = new ArrayList<SparseMatrix>();
	public SparseMatrix(Integer w1, Integer w2){
		this.wordid1 = w1;
		this.wordid2 = w2;
		//this.documentid = d;
	}
	
	public Integer getwordid1(){
		return wordid1;
	}

	public Integer getwordid2(){
		return wordid2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wordid1 == null) ? 0 : wordid1.hashCode());
		result = prime * result + ((wordid2 == null) ? 0 : wordid2.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SparseMatrix)) {
			return false;
		}
		SparseMatrix other = (SparseMatrix) obj;
		if (wordid1 == null) {
			if (other.wordid1 != null) {
				return false;
			}
		} else if (!wordid1.equals(other.wordid1)) {
			return false;
		}
		if (wordid2 == null) {
			if (other.wordid2 != null) {
				return false;
			}
		} else if (!wordid2.equals(other.wordid2)) {
			return false;
		}
		return true;
	}

	//public Integer getdocumentid(){
		//return documentid;
	//}




	

}
