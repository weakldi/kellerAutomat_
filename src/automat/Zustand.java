package automat;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class Zustand {
	private List<Rule> rules;
	private Stack s;
	private String name;
	
	public Zustand(Stack s, String name) {
		super();
		this.s = s;
		this.name = name;
		rules = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rule> getRules() {
		return rules;
	}

        public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public Stack getS() {
		return s;
	}

	public void setS(Stack s) {
		this.s = s;
	}

	public Zustand applyRules(char c){
		
		for (Rule rule : rules) {
			if(rule.getStackVaue().equals(s.getTop())&&rule.getInputC()==c){
				switch (rule.getAction()) {
				//push
				case 0b0000001:
					s.push(rule.getActionValue());
					return rule.getNext();
				//pop
				case 0b0000010:
					try {
						s.pop();
						return rule.getNext();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				//NOP
				case 0b0000000:
					return rule.getNext();
				//Fehler
				case 0b1111100:
					return null;
				default:
					break;
				}
			}
		}
		return null;
	}
        private String[] ruleS;
        public Zustand applyRulesUI(char c,JTextArea src,int clockSpeed){
		String srcText = ";Zustand " + name + "\n";
                if(ruleS==null){
                    ruleS = new String[rules.size()];
                    for (int i = 0; i < ruleS.length;i++){
                        Rule rule = rules.get(i);
                        ruleS[i] = name + " " + rule.getInputC() + " " + rule.getStackVaue() + " -> " + 
                                (rule.getAction()==1?"push " + rule.getStackVaue() + " " + rule.getNext().getName()
                                : (rule.getAction()==2?"pop":
                                (rule.getAction()==0?"nop":"err")) 
                                +
                                " " + rule.getNext().getName());
                    }
                }
                src.setText(srcText + genText(ruleS));
		for (Rule rule : rules) {
			if(rule.getStackVaue().equals(s.getTop())&&rule.getInputC()==c){
				switch (rule.getAction()) {
				//push
				case 0b0000001:
					s.push(rule.getActionValue());
					return rule.getNext();
				//pop
				case 0b0000010:
					try {
						s.pop();
						return rule.getNext();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				//NOP
				case 0b0000000:
					return rule.getNext();
				//Fehler
				case 0b1111100:
					return null;
				default:
					break;
				}
			}
		}
		return null;
	}
        
        private String genText(String[] s){
            String text = "";
            for (int i = 0; i < s.length; i++) {
                text+="   " + s[i]+"\n";
                
            }
            return text;
        }
	
	public void addRule(Rule r){
		rules.add(r);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " " + name;
	}
}
