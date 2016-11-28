package automat;

public class EingabeAlphabeth {
	public String alphabeth;
	
	public String getAlphabeth() {
		return alphabeth;
	}

	public void setAlphabeth(String alphabeth) {
		this.alphabeth = alphabeth;
	}

	public boolean containsChar(char c){
		return true;//alphabeth.contains(c+"");
	}
}
