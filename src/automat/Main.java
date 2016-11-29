package automat;

public class Main {
	public static void main(String[] args) {
		String s = "Z{1,2,f}\n"
				+ "E{2}\n"
				+ "S=1\n"
				+ "1 ( # -> push ( 1\n"
				+ "1 ( ( -> push ( 1\n"
				+ "1 ) ( -> pop 2\n"
				+ "2 ) ( -> pop 2\n"
				+ "2 ( ( -> push ( 1\n"
				+ "2 ( # -> push ( 1\n"
				+ "2 ) # -> err f\n"
			;
		 
		Kellerautomat k = new Loader().load(s);
		System.out.println(k.check("((()))"));
		System.out.println("Z{   aaa32144235dfsdfdgf}"
				.matches("Z\\{\\s*([A-Za-z0-9]*\\s*,\\s*)*[A-Za-z0-9]{1,}\\s*\\}")
				);
	}
}
