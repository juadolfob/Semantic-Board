package logic; 
public class SemanticBoard { 

	public static Expression EXP;
	public SemanticBoard(String Expression) { 
		EXP=new Expression(Expression);
		System.out.println(EXP.GetExpression());
	}

}
