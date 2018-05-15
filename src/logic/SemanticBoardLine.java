package logic;

import java.lang.reflect.Array;

import main.Final;

public class SemanticBoardLine {
	
	final static int ROOT_EXPRESSION=-1,DEFAULT=0;
	
	int ExpressionArrayForm=DEFAULT;
	Expression ExpressionArray[][]= new Expression[Final.StringArrayMaxSize][Final.StringArrayMaxSize]; 
	int addcounter=0; 
	public int ExpressionCounter[]=new int[Final.StringArrayMaxSize];
	
	public SemanticBoardLine() {
		for(int i=0;i<Final.StringArrayMaxSize;i++) {
			ExpressionCounter[i]=0;
		}
	}
	/*
	public void add(Expression[] logicExpression){  

		System.out.println("F11111111111111111111111");
		ExpressionArray[addcounter++]=logicExpression;
	}
	*/
	public void add(Expression logicExpression,int ExpressionIndex){

		
		if(ExpressionIndex==ROOT_EXPRESSION) { 
			System.out.println("ROOT_EXPRESSION  "+logicExpression.toPrintableString());
			ExpressionArray[0][0]=logicExpression;
			addcounter++;
			ExpressionCounter[0]++;
		}else 
			if(ExpressionIndex==0||(ExpressionIndex>0 && this.size(ExpressionIndex-1)>0)){  
			if(ExpressionCounter[ExpressionIndex]==0) {
				addcounter++;
			}
			System.out.println("add "+logicExpression.toPrintableString()+" to "+"["+ExpressionIndex+","+ExpressionCounter[ExpressionIndex]+"] : "+this.toPrintableString()); 
			ExpressionArray[ExpressionIndex][ExpressionCounter[ExpressionIndex]]=logicExpression; 
			ExpressionCounter[ExpressionIndex]++;
		}else {
			System.out.println("INVALID INDEX ADD line44"); 
				System.exit(1000); 
		}
	} 
	/*
	 public void add(Expression[] logicExpression,int ExpressionIndex){ 
			ExpressionArray[ExpressionIndex]=logicExpression; 
			ExpressionCounter[ExpressionIndex]+=logicExpression.length;
	}
	public void set(Expression logicExpression,int ExpressionIndex,int SubExpressionIndex){  
			ExpressionArray[ExpressionIndex][SubExpressionIndex]=logicExpression;  
			ExpressionCounter[ExpressionIndex]++;
	} ¨
	*/
	public int size(){
		return addcounter; 
	}
	public int size(int index){
		return ExpressionCounter[index];   
	}
	public Expression[] get(int ExpressionIndex){
		return ExpressionArray[ExpressionIndex];   
	} 
	public Expression get(int ExpressionIndex,int SubExpressionIndex){
		return ExpressionArray[ExpressionIndex][SubExpressionIndex];   
	}
	public Expression getHeadExpression(){  
		return ExpressionArray[0][0];
	}
	public String toString(){
		String SemanticBoardLineString="";
		for(int exp=0;exp<size();exp++) {
			for(int subexp=0;subexp<size(exp);subexp++) { 
			SemanticBoardLineString=SemanticBoardLineString+this.get(exp,subexp).toString();
			SemanticBoardLineString=SemanticBoardLineString+",";
			}
			if(SemanticBoardLineString.length()>0)
			SemanticBoardLineString=SemanticBoardLineString.substring(0,SemanticBoardLineString.length()-1)+"  ";
	}
		return SemanticBoardLineString;
}
	public String toString(int Expressionindex){
		String SemanticBoardLineString=""; 
			for(int subexp=0;subexp<size(Expressionindex);subexp++) { 
			SemanticBoardLineString=SemanticBoardLineString+this.get(Expressionindex,subexp).toString();
			SemanticBoardLineString=SemanticBoardLineString+",";
			}
			if(SemanticBoardLineString.length()>0)
			SemanticBoardLineString=SemanticBoardLineString.substring(0,SemanticBoardLineString.length()-1)+"  "; 
		return SemanticBoardLineString;
}
	public String toPrintableString(){
		String SemanticBoardLineString="";
		for(int exp=0;exp<size();exp++) {
			for(int subexp=0;subexp<size(exp);subexp++) { 
			SemanticBoardLineString=SemanticBoardLineString+this.get(exp,subexp).toPrintableString();
			SemanticBoardLineString=SemanticBoardLineString+",";
			}
			if(SemanticBoardLineString.length()>0)
			SemanticBoardLineString=SemanticBoardLineString.substring(0,SemanticBoardLineString.length()-1)+"  ";
	}
		return SemanticBoardLineString;
}
}


