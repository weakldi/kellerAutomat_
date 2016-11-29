package automat;

import java.util.ArrayList;

public class Loader2 {
	public void load(String data){
		data = data.replace("[\t]*", "").replaceAll("[ ]*", "");
		ArrayList<String> tokens = new ArrayList<>();
		for(String line:data.split("\n")){
			//Zustaende
			if(line.matches("Z\\{\\s*([A-Za-z0-9]*\\s*,\\s*)*[A-Za-z0-9]{1,}\\s*\\}")){
				
			}else if(line.matches("E\\{[A-Za-z,]*[A-Za-z]\\}")){
				
			}else if(line.matches("A\\{[A-Za-z0-9,]{1}*[A-Za-z0-9]{1}\\}")){
				
			}else if(line.matches("[A-Za-z0-9]*,[A-Za-z0-9]{1},[A-Za-z0-9#]*->push[A-Za-z0-9]*,[A-Za-z]*")){
				
			}
		}
	}
}
