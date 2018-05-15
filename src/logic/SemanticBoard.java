package logic;

import main.Final;
import logic.Logic;

public class SemanticBoard extends Logic{
	SemanticBoardLine SemanticLine[]= new SemanticBoardLine[Final.StringArrayMaxSize];
	public Expression rootExpression;  
	boolean hasOneCloseExpression=false;
	public int currentline=0;
	String satisfactibility[] =new String[3];
	public SemanticBoard() {
		debug();
	}

	private void debug() {

	}

	public void setHeadExpression(String expression) {
		System.out.println("SETGEAD");
		rootExpression = new Expression(expression);
		System.out.println(rootExpression.toString());
		SemanticLine[0] = new SemanticBoardLine();
		SemanticLine[0].add(rootExpression,SemanticBoardLine.ROOT_EXPRESSION);
	}
	public void setHeadExpression(Expression expression) {
		rootExpression = expression;
		System.out.println(rootExpression.toString());
		SemanticLine[0] = new SemanticBoardLine();
		SemanticLine[0].add(rootExpression,SemanticBoardLine.ROOT_EXPRESSION); 
	}
	public void buildSemanticTree(Expression expression) {  
		clear();
		setHeadExpression(expression);

			System.out.println("______________________________________________________while start");
		while(!Logic.isFinalSemanticBoardLineForm(SemanticLine[currentline])) {   
			System.out.println("______________________________________________________LINE: "+currentline);
			SemanticLine[currentline+1]=Logic.toNormalConjuntiveForm(SemanticLine[currentline]); 
			currentline++; 
		}
		analizesatisfactibility(SemanticLine[currentline]);
	}
	private void analizesatisfactibility(SemanticBoardLine semanticBoardLine) {
		satisfactibility[0]=semanticBoardLine.toString();

		
		for(int exp=0;exp<semanticBoardLine.size();exp++) {
			boolean thisexphascloseexp=false;
			for(int subexp=0;subexp<semanticBoardLine.size(exp);subexp++) { 
				for(int restsubexp=subexp;restsubexp<semanticBoardLine.size(exp);restsubexp++) { 
					if(((semanticBoardLine.get(exp, subexp).toString()).equals("¬"+semanticBoardLine.get(exp, restsubexp).toString()))||("¬"+semanticBoardLine.get(exp, subexp).toString()).equals(semanticBoardLine.get(exp, restsubexp).toString())) {
						hasOneCloseExpression=true;
						thisexphascloseexp=true; 
						break;
					}
				} 
				int spaces=semanticBoardLine.toString(exp).length();
				if(!thisexphascloseexp){  
					satisfactibility[1]=satisfactibility[1]+"O";
					for(int i=0;i<spaces-1;i++) {
						satisfactibility[1]=satisfactibility[1]+" "; 
					}  
					break;
				}else {   
					satisfactibility[1]=satisfactibility[1]+"X";
					for(int i=0;i<spaces-1;i++) {
						satisfactibility[1]=satisfactibility[1]+" ";

					}  
					break;
				}
			}
			
		}
		if(hasOneCloseExpression) {
			satisfactibility[2]="Unsatisfiable";
		}else {
			satisfactibility[2]="Satisfiable";
		} 
	}

	public String getWarnings() {
		return rootExpression.getWarning(0); 
	}
	public int size() {
		return currentline;
	} 
	public String getHeadExpression() {
		return SemanticLine[0].getHeadExpression().toString();
	}   
	public String ToDocument(){
		String SemanticBoardDocument="";
		for(int line=0;line<size();line++) {
			SemanticBoardDocument=SemanticBoardDocument+SemanticLine[line].toString();
			SemanticBoardDocument=SemanticBoardDocument+"\n";
		}
		for(int line=0;line<3;line++) {
			SemanticBoardDocument=SemanticBoardDocument+satisfactibility[line];
			SemanticBoardDocument=SemanticBoardDocument+"\n";
		}	 
		return SemanticBoardDocument;
	}
	public void clear() {
		currentline=0;
		for(int line=0;line<3;line++) {
			satisfactibility[line]="";
		}	
	}
}
