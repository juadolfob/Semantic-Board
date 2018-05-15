package logic;

public class Logic {

	final static boolean ALPHA = true;
	final static boolean BETA = false;
	final static boolean DEFAULT = false;

	public Expression toSemanticBoardForm(Expression logicExpression) {
		return logicExpression;
	}

	static boolean isFinalSemanticBoardLineForm(SemanticBoardLine SemanticBoardLine) { 
		for (int ExpressionIndex = 0; ExpressionIndex < SemanticBoardLine.size(); ExpressionIndex++) { 
			for (int SubExpressionIndex = 0; SubExpressionIndex < SemanticBoardLine.size(ExpressionIndex); SubExpressionIndex++) {  
				if (!SemanticBoardLine.get(ExpressionIndex, SubExpressionIndex).hasSingleVariable()) { 

					System.out.println("isNOTFinalSemanticBoardLineForm");   
					return false;
				}
			} 
		}
		System.out.println("isFinalSemanticBoardLineForm");  
		return true;
	}

	public static SemanticBoardLine toNormalConjuntiveForm(SemanticBoardLine SemanticBoardLine) {
		System.out.println("CONVERTION------------------start---"+SemanticBoardLine.toPrintableString());
		Expression F1 = null;
		Expression F2 = null; 
		SemanticBoardLine modelSemanticLine = new SemanticBoardLine();
		boolean ExpressionForm = DEFAULT;

		 
		for (int ExpressionIndex = 0; ExpressionIndex < SemanticBoardLine.size(); ExpressionIndex++) {  
			int subExpDisplace=0;
			int subAlphaExpDisplace=0;
			for (int SubExpressionIndex = 0; SubExpressionIndex < SemanticBoardLine.size(ExpressionIndex); SubExpressionIndex++) {
				
				Expression modelExpression = SemanticBoardLine.get(ExpressionIndex, SubExpressionIndex);
				if(modelExpression.hasMultipleVariables()) {
				if (modelExpression.getTruthValue() == true) {
					switch (modelExpression.getOperator()) {
					case '∧':
						F1 = new Expression(modelExpression.getLeft());
						F2 = new Expression(modelExpression.getRight());
						ExpressionForm = ALPHA;
						break;
					case '↔':
						F1 = new Expression("(" + modelExpression.getLeft() + ")→(" + modelExpression.getRight() + ")");
						F2 = new Expression("(" + modelExpression.getRight() + ")→(" + modelExpression.getLeft() + ")");
						ExpressionForm = ALPHA;
						break;
					case '∨':
						F1 = new Expression(modelExpression.getLeft());
						F2 = new Expression(modelExpression.getRight());
						ExpressionForm = BETA;
						break;
					case '→':
						F1 = new Expression(modelExpression.getLeft(), false);
						F2 = new Expression(modelExpression.getRight());
						ExpressionForm = BETA;
						break;

					default:
						System.out.println("NO EXPRESSION CASE FOUND");
					}
				} else if (modelExpression.getTruthValue() == false) {
					switch (modelExpression.getOperator()) {
					case '∧':
						F1 = new Expression(modelExpression.getLeft(), false);
						F2 = new Expression(modelExpression.getRight(), false);
						ExpressionForm = BETA;
						break;
					case '↔':
						F1 = new Expression(
								"¬((" + modelExpression.getLeft() + ")→(" + modelExpression.getRight() + "))");
						F2 = new Expression(
								"¬((" + modelExpression.getRight() + ")→(" + modelExpression.getLeft() + "))");
						ExpressionForm = BETA;
						break;
					case '∨':
						F1 = new Expression(modelExpression.getLeft(), false);
						F2 = new Expression(modelExpression.getRight(), false);
						ExpressionForm = ALPHA;
						break;
					case '→':
						F1 = new Expression(modelExpression.getLeft());
						F2 = new Expression(modelExpression.getRight(), false);
						ExpressionForm = ALPHA;
						break;

					default:
						System.out.println("*****NO CASE FOUND*****");
					} 
				} 
				if (ExpressionForm == BETA) { // o
					System.out.println("----------------------BETAstart");
					 
 

 
					System.out.println("-----------------------------rpeprepre "+SemanticBoardLine.size(ExpressionIndex)+"   -   "+SemanticBoardLine.toString());

					
					
					for (int i = subExpDisplace+subAlphaExpDisplace; i < SemanticBoardLine.size(ExpressionIndex); i++) {    
						if(i==SubExpressionIndex) {
							modelSemanticLine.add(F1, ExpressionIndex );
						}else { 
							modelSemanticLine.add(SemanticBoardLine.get(ExpressionIndex,i), ExpressionIndex );
					}
					}
					for (int i = 0; i < modelSemanticLine.size(ExpressionIndex); i++) {   
						System.out.println("	"
								+ "						rpeprepre"); 
					if(i==SubExpressionIndex+subAlphaExpDisplace) {
						modelSemanticLine.add(F2, ExpressionIndex+ 1  );
					}else { 
						modelSemanticLine.add(modelSemanticLine.get(ExpressionIndex,i), ExpressionIndex+1 );
					}
					} 
					
					for (int exp = ExpressionIndex+1; exp < SemanticBoardLine.size(); exp++)
					for (int subexp = 0; subexp < SemanticBoardLine.size(exp); subexp++) {    
						modelSemanticLine.add(SemanticBoardLine.get(exp,subexp), exp+1);
					}
					 
					System.out.println("----------------------BETAend");
					return modelSemanticLine; 
				} else if (ExpressionForm == ALPHA) { // y
 
					System.out.println("----ALPHAstart");
					modelSemanticLine.add(F1, ExpressionIndex  );
					modelSemanticLine.add(F2, ExpressionIndex  );
					System.out.println("----ALPHAend");

					subAlphaExpDisplace++;
				}else {
					System.out.println("*****NO CASE FOUND 2*****");
				}
				}else if(modelExpression.hasSingleVariable()) { 

					System.out.println("-------displace: "+ ""+"");
					modelSemanticLine.add(modelExpression, ExpressionIndex );

					subExpDisplace++;
				}
			}
		}
		System.out.println("CONVERTION--------------end----"+modelSemanticLine.toString());
		return modelSemanticLine;

	} 

}
