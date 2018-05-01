package logic;
public class Expression{
	final boolean RIGHTSTEP=true;
	final boolean LEFTSTEP=false; 
	 
	public String left=null; 
	public String right=null;
	public String Operator=null;
	public Expression(){
		
	}
 public Expression(String Expression){ 
	 removeglobalbrackets(Expression);		
	 int OperatorIndex=PrincipalOperatorIndex(Expression); 
	 Operator=Character.toString(Expression.charAt(OperatorIndex));	 
	 left=Expression.substring(0 , OperatorIndex);
	 right=Expression.substring(OperatorIndex+1, Expression.length());
	 if(SubExpressions(left)>=2) { 
			left='('+left+')';
		
	 }
	 if(SubExpressions(right)>=2) { 
			right='('+right+')';
		}
	 } 
 public String GetExpression( ){  
	 return left+" "+Operator+" "+right;
 	}
 public String addglobalbrackets(String Expression){ 
	 int openbraquets=0; 
	 for(int j=0; j<58; j++) { 
		 String newExpression=Expression; 
		 newExpression=newExpression.substring(newExpression.indexOf('(')   , newExpression.lastIndexOf(')'));
	 for(int i=0;i<Expression.length();i++) { 
			if(Expression.charAt(i)=='('){
				openbraquets++;  
			}else if(Expression.charAt(i)==')'){ 
				openbraquets--; 
			}   
			} 
	 	if(openbraquets==0) {
			Expression=newExpression;
		}else { 
			 return Expression; 
		} 
	 }  
	 return Expression; 
 }
 public String removeglobalbrackets(String Expression){ 
	 int openbraquets=0; 
	 for(int j=0; j<58; j++) { 
		 String newExpression=Expression; 
		 newExpression=newExpression.substring(newExpression.indexOf('(')   , newExpression.lastIndexOf(')'));
	 for(int i=0;i<Expression.length();i++) { 
			if(Expression.charAt(i)=='('){
				openbraquets++;  
			}else if(Expression.charAt(i)==')'){ 
				openbraquets--; 
			}   
			} 
	 	if(openbraquets==0) {
			Expression=newExpression;
		}else { 
			 return Expression; 
		} 
	 }  
	 return Expression; 
 }
 public int SubExpressions(String Expression) {
	 int openbraquets=0;
	 int SubExpressions=0;

	 if(Expression != null && !Expression.trim().isEmpty())
	 for(int i=0;i<Expression.length();i++) {
	 	 
		 if(openbraquets==0 && CharisOperator(Expression.charAt(i))){
				SubExpressions++;
			}
			if(Expression.charAt(i)=='('){
				openbraquets++;  
			}else if(Expression.charAt(i)==')'){ 
				openbraquets--; 
			}  
			
		} 
	 System.out.println("SUBEXPRESIONS  "+ SubExpressions);
	 return SubExpressions+1; 
 }
 public int PrincipalOperatorIndex(String Expression) {
	 int openbraquets=0; 
		 for(int i=0;i<Expression.length();i++) { 
			
			if(Expression.charAt(i)=='('){
				openbraquets++;  
			}else if(Expression.charAt(i)==')'){ 
				openbraquets--; 
			}  
			if(openbraquets==0 && CharisOperator(Expression.charAt(i))){
				if(Expression.charAt(i)=='→'||Expression.charAt(i)=='↔'){

					 System.out.println(" Principal Operator"+i);
					return i;
				}
				}
			}
	 for(int i=0;i<Expression.length();i++) {
	 	 
			
			if(Expression.charAt(i)=='('){
				openbraquets++;  
			}else if(Expression.charAt(i)==')'){ 
				openbraquets--; 
			}  
			if(openbraquets==0 && CharisOperator(Expression.charAt(i))){
				if(Expression.charAt(i)=='∧'||Expression.charAt(i)=='∨'){

					 System.out.println("COULDNT FIND Principal Operator"+i);
					return i;
				}
				}
			}
	 System.out.println("COULDNT FIND Principal Operator");
	return -1; 
 }
 
 	 
 	static boolean CharisOperator(char logic){
 		if(logic=='→'||logic=='∧'||logic=='∨'||logic=='↔'){
 			return true;
 		}else
		return false;
 	} 
}