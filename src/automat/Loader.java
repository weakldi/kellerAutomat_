package automat;

import java.util.ArrayList;
import java.util.List;


public class Loader {
	public Kellerautomat load(String data){
		String[] lines = data.split("\n");
		List<Zustand> zustaende = new ArrayList<>();
		List<Zustand> end = new ArrayList<>();
		Zustand start = null;
		Stack stack = new Stack("#");
		
	
		for (String line : lines) {
			System.out.println("L= " + line);
			System.out.println(zustaende);
			System.out.println("S=" + start);
			if(line.startsWith("Z{") || line.startsWith("Zustaende{") || line.startsWith("Zustände{")){
				String[] zustaendeS = line.replaceAll(" ", "").split("\\{")[1].split("\\}")[0].split(",");
				for (String z : zustaendeS) {
					//Optimierung moeglich
					zustaende.add(new Zustand(stack, z));
				}
			}else if(line.startsWith("S=") || line.startsWith("Start=")){
				String startS = line.split("=")[1].replaceAll(" ", "");
				//Optimierung moeglich
				for(int i = 0; i < zustaende.size(); i++) {
					System.out.println(startS);
					if(zustaende.get(i).getName().equalsIgnoreCase(startS)){
						start = zustaende.get(i);
						break;
					}	
				}
			}else if(line.startsWith("E{") || line.startsWith("End{")){
				//Optimierung moeglich
				String[] zustaendeS = line.replaceAll(" ", "").split("\\{")[1].split("\\}")[0].split(",");
				A:for (String z : zustaendeS) {
					for(int i = 0; i < zustaende.size(); i++) {
						if(zustaende.get(i).getName().equals(z)){
							end.add(zustaende.get(i));
							continue A; // macht hier das selbe wie break
						}	
					}
				}
			}else{
				//Z input stack -> op opValue target
				String[] split = line.split("->");
				String[] condition = split[0].split(" ");
				String[] operation = split[1].split(" ");
				Zustand current = null;
				for(int i = 0; i < zustaende.size(); i++) {
					System.out.println(condition[0]);
					if(zustaende.get(i).getName().equals(condition[0])){
						current = zustaende.get(i);
						break;
					}	
				}
				char inputValue = condition[1].charAt(0);
				String stackValue = condition[2];
				String op = operation [1];
				String opV = "";
				String target = "";
				if(op.equalsIgnoreCase("push")){
					opV = operation[2];
					target = operation[3];
				}else{
					target = operation[2];
				}
				Zustand next = null;
				for(int i = 0; i < zustaende.size(); i++) {
					if(zustaende.get(i).getName().equals(target)){
						next = zustaende.get(i);
						break;
					}	
				}
				System.out.println("OP =" + op);
				byte opCode = 0b000000;
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
				Rule r = new Rule(next, stackValue, opCode, opV, inputValue);
				current.addRule(r);
			}
		}
		Zustand[] e = new Zustand[end.size()];
		for (int i = 0; i < e.length; i++) {
			e[i] = end.get(i);
		}
		return new Kellerautomat(new EingabeAlphabeth(), stack, start, e);
	}
}
