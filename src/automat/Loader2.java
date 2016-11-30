package automat;

import java.util.ArrayList;
import java.util.HashMap;

public class Loader2 {
	public Kellerautomat load(String data){
//		data = data.replace("[\t]*", "").replaceAll("[ ]*", "");
		ArrayList<String> tokens = new ArrayList<>();
		HashMap<String, Zustand> z = new HashMap<>();
		ArrayList<String> end = new ArrayList<>();
		EingabeAlphabeth ea = new EingabeAlphabeth();
		String start = null;
		Stack s = new Stack("#");
		for(String line:data.split("[\n\r]")){
			line = line.replaceAll(";.*", "");//Kommentare entfehrnen
			//Zustaende
			if(line.matches("Z\\{\\s*([A-Za-z0-9]*\\s*,\\s*)*[A-Za-z0-9]{1,}\\s*\\}")){
				line = line.replaceAll("Z\\{", "").replaceAll("\\}", "").replaceAll("\\s", "");
				String[] zustände = line.split(",");
				for (String name : zustände) {
					if(!z.containsKey(name))
						z.put(name, new Zustand(s, name));
				}	
			}
			//Endzustände
			else if(line.matches("E\\{\\s*([A-Za-z0-9]*\\s*,\\s*)*[A-Za-z0-9]{1,}\\s*\\}")){
				line = line.replaceAll("E\\{", "").replaceAll("\\}", "").replaceAll("\\s", "");
				String[] zustände = line.split(",");
				for (String name : zustände) {
					if(!z.containsKey(name)){
						z.put(name, new Zustand(s, name));
					}
					end.add(name);
					
				}
			}
			//Alphabeth
			else if(line.matches("A\\{\\s*(\\w{1}\\s*,\\s*)*\\w{1}\\s*\\}")){
				ea.setAlphabeth(line.replaceAll("Z\\{", "").replaceAll("\\}", "").replaceAll("\\s", "").replaceAll(",", ""));
			}
			//start
			if(line.matches("S=\\s*\\w{1,}\\s*")){
				start = line.replaceAll("S=\\s*", "").replaceAll("\\s*", "");
				
			}
			//push
			else if(line.matches("\\s*\\w{1,}\\s{1,}[\\p{Alnum}\\p{Punct}]{1}\\s{1,}[\\p{Alnum}\\p{Punct}]{1,}\\s*->\\s*push\\s{1,}[\\p{Alnum}\\p{Punct}]{1,}\\s{1,}\\w{1,}\\s*")){
				if(line.matches("\\s{1,}.*")){
					line = line.replaceFirst("\\s*", "");
				}
				String[] ruleP = line.replaceAll("->", " ").split("\\s{1,}");
				for (String t : ruleP) {
					tokens.add(t);
				}
			}//pop
			else if(line.matches("\\s*\\w{1,}\\s{1,}[\\p{Alnum}\\p{Punct}]{1}\\s{1,}[\\p{Alnum}\\p{Punct}]{1,}\\s*->\\s*pop\\s{1,}\\w{1,}\\s*")){
				
				if(line.matches("\\s{1,}.*")){
					line = line.replaceFirst("\\s*", "");
				}
				String[] ruleP = line.replaceAll("->", " ").split("\\s{1,}");
				for (String t : ruleP) {
					tokens.add(t);
				}
			}//nop
			else if(line.matches("\\s*\\w{1,}\\s{1,}[\\p{Alnum}\\p{Punct}]{1}\\s{1,}[\\p{Alnum}\\p{Punct}]{1,}\\s*->\\s*nop\\s{1,}\\w{1,}\\s*")){
				if(line.matches("\\s{1,}.*")){
					line = line.replaceFirst("\\s*", "");
				}
				String[] ruleP = line.replaceAll("->", " ").split("\\s{1,}");
				for (String t : ruleP) {
					tokens.add(t);
				}
			}//err
			else if(line.matches("\\s*\\w{1,}\\s{1,}[\\p{Alnum}\\p{Punct}]{1}\\s{1,}[\\p{Alnum}\\p{Punct}]{1,}\\s*->\\s*err\\s{1,}\\w{1,}\\s*")){
				if(line.matches("\\s{1,}.*")){
					line = line.replaceFirst("\\s*", "");
				}
				String[] ruleP = line.replaceAll("->", " ").split("\\s{1,}");
				for (String t : ruleP) {
					tokens.add(t);
				}
			}
			
			
		}
		for(int i = 0; i < tokens.size();){
			String startZustand     = 		tokens.get(i++);
			char inputValue 	= 		tokens.get(i++).charAt(0);
			String valueOnStack     = 		tokens.get(i++);
			byte operation 		= getOP(	tokens.get(i++));
			String opValue 		= operation==0b0000001?tokens.get(i++):null;
			String nextZustand	= tokens.get(i++);
			System.out.println("Start=" + startZustand + " I=" + inputValue + " S=" + valueOnStack + " OP=" + operation + " opValue=" + opValue + " N=" + nextZustand );
			
			Rule rule = new Rule(z.get(nextZustand), valueOnStack, operation, opValue, inputValue);
			z.get(startZustand).addRule(rule);
		
		}
		Zustand[] e = new Zustand[end.size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = z.get(end.get(i));
		}
		Zustand startZ = z.get(start);
		Kellerautomat kellerautomat = new Kellerautomat(ea, s, startZ, e);
		return kellerautomat;
	}
	
	public static byte getOP(String op){
		byte opCode = 0b1111100;
		switch (op) {
		case "push":
			opCode = 0b0000001;
			break;
		case "pop":
			opCode = 0b0000010;
			break;
		case "nop":
			opCode = 0b0000000;
			break;
		case "err":
			opCode = 0b1111100;
			break;
		default:
			break;
		}
		return opCode;
	}
}
