package automat;

public class Main {
	public static void main(String[] args) {
		String s = "Z{1,2,f}\n"
				+ "E{2}\n"
				+ "A{(,)}\n"
				+ "S=1\n"
				+ "1 ( # -> push ( 1\n"
				+ "1 ( ( -> push ( 1\n"
				+ "1 ) ( -> pop 2\n"
				+ "2 ) ( -> pop 2\n"
				+ "2 ( ( -> push ( 1\n"
				+ "2 ( # -> push ( 1\n"
				+ "2 ) # -> err f\n"
			;
		 
		Kellerautomat k = new Loader2().load(s);
		System.out.println(k.check("((()))"));
		
	}
}
