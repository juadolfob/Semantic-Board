package logic; 
public class SemanticBoard { 

	public static Expression EXP; 
	public SemanticBoard() { 
	}
	public static void setHeadExpression(String Expression) { 
		EXP=new Expression(Expression);
		System.out.println(EXP.getExpression());
		System.out.println(LogicTranslator.NormalConjunctiveFormStep(EXP));
	}
	 
	
}
