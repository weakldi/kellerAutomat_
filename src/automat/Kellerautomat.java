package automat;

public class Kellerautomat {
	private EingabeAlphabeth a;
	private Stack s;
	private Zustand current;
	private Zustand[] end;
	
	
	public Kellerautomat(EingabeAlphabeth a, Stack s, Zustand start,Zustand[] end) {
		super();
		this.a = a;
		this.s = s;
		this.current = start;
		this.end = end;
	}
	
	public boolean check(String word){
		for(int i = 0; i < word.length();i++){
			if(a.containsChar(word.charAt(i))){
                            System.out.println(current);
                                    
				current = current.applyRules(word.charAt(i));
				if(current == null){
					System.out.println("Fehler");
					break;
				}
			}else{
				System.out.println("Der Buchstabe " + word.charAt(i) + "ist nicht im Alphabeth enthalten!");
				break;
			}
		}
		for (Zustand zustand : end) {
			if(zustand == current)
				return true;
		}
		return false;
	}
	
	public EingabeAlphabeth getA() {
		return a;
	}
	
	public void setA(EingabeAlphabeth a) {
		this.a = a;
	}
	
	public Stack getS() {
		return s;
	}
	
	public void setS(Stack s) {
		this.s = s;
	}
	
	public Zustand getCurrent() {
		return current;
	}
	
	public void setCurrent(Zustand current) {
		this.current = current;
	}
	
	
}
