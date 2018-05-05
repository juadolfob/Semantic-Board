package logic;

import java.util.ArrayList;

public class Expression {

	final public boolean DO_NOT_SEND_WARNINGS = false;
	final public boolean SEND_WARNINGS = true;

	final public boolean FOR_EXPRESSION = true;
	final public boolean FOR_SUBEXPRESSION = false;

	final public boolean RIGHTSIDE = true;
	final public boolean LEFTSIDE = false;

	public boolean ExpressionV = true;
	public boolean leftV = true;
	public boolean rightV = true;
	public String left = "";
	public String right = "";
	public String Operator = "";
	public ArrayList<String> Warnings = new ArrayList<String>();

	public Expression(String Expression) {
		Warnings.clear();
		Expression = Expression.replaceAll("\\s", "");
		if (hasValidParentheses(Expression, SEND_WARNINGS)) {
			if (hasValidOperators(Expression)) {
				if (hasValidNegations(Expression)) {
					if (hasValidChars(Expression)) {
						if (hasValidVariables(Expression)) {
							InitiateExpression(Expression);
						}
					}
				}
			}
		} else {
			clearExpression();
			System.out.println("Expresion is not right");
		}
	}

	public void clearExpression() {
		ExpressionV = true;
		leftV = true;
		rightV = true;
		left = "";
		right = "";
		Operator = "";
	}

	public void InitiateExpression(String Expression) {

		System.out.println(" Expression " + Expression); 
		Expression=removeDoubleNegations(Expression);
		Expression=removeUnnecessaryParentheses(Expression);
		Expression=removeExpressionGlobalParentheses(Expression);
		int OperatorIndex = PrincipalOperatorIndex(Expression);

		Operator = Character.toString(Expression.charAt(OperatorIndex));

		left = Expression.substring(0, OperatorIndex);
		right = Expression.substring(OperatorIndex + 1, Expression.length());
		System.out.println(" l " + left + Operator + " r " + right);

		left = removeSubExpressionGlobalParentheses(left, LEFTSIDE);
		right = removeSubExpressionGlobalParentheses(right, RIGHTSIDE);
		if (SubExpressions(left) >= 2) {
			left = '(' + left + ')';
		} 
		if (SubExpressions(right) >= 2) {
			right = '(' + right + ')';
		}  
		left=removeUnnecessaryParentheses(left);
		right=removeUnnecessaryParentheses(right);
		Warnings.add("Done.");
	}

	public String getExpression() {
		String leftVchar = "";
		String rightVchar = "";
		if (leftV == false) {
			leftVchar = "¬";
		}
		if (rightV == false) {
			rightVchar = "¬";
		}
		if (ExpressionV == false) {

			System.out.println("FIXED");
			return "¬(" + leftVchar + left + Operator + rightVchar + right + ")";
		}

		return leftVchar + left + Operator + rightVchar + right;

	}

	public String addGlobalParentheses(String Expression) {
		return "(" + Expression + ")";
	}

	public String removeSubExpressionGlobalParentheses(String Expression, boolean EXPRESSIONSIDE) {

		for (int i = 0; i < 55; i++) {
			System.out.println("subex preneg" + (i + 1) + "Expression " + Expression);
			if (hasGlobalNegation(Expression)) {
				if (EXPRESSIONSIDE == RIGHTSIDE) {
					Expression = removeGlobalNegation(Expression);
					negateRight();
				}
				if (EXPRESSIONSIDE == LEFTSIDE) {
					Expression = removeGlobalNegation(Expression);
					negateLeft();
				}
			}

			System.out.println("subex posneg" + (i + 1) + " Expression " + Expression);
			if (hasGlobalParentheses(Expression)) {
				Expression = removeGlobalParentheses(Expression);
			} else {
				break;
			}
		}
		return Expression;
	}

	public String removeExpressionGlobalParentheses(String Expression) {

		for (int i = 0; i < 55; i++) {
			System.out.println("preneg" + (i + 1) + "Expression " + Expression);
			if (hasGlobalNegation(Expression)) {
				Expression = removeGlobalNegation(Expression);
				negateExpression();
			}

			System.out.println("posneg" + (i + 1) + " Expression " + Expression);
			if (hasGlobalParentheses(Expression)) {
				Expression = removeGlobalParentheses(Expression);
			} else {
				break;
			}
		}
		return Expression;
	}

	public String replaceDoubleParentheses(String Expression) { 
		for (int k = 0; k < 55; k++) {
			for (int i = 0; i < Expression.length() - 1; i++) {
				if (Expression.charAt(i) == '(' && Expression.charAt(i+1)  == '(') { 
					for (int j = i + 2; j < Expression.length() - 1; j++) {
						
						if (Expression.charAt(j) == '(') {
							System.out.println("BREAKS");
							break;
						}
						if (Expression.charAt(j) == ')' && Expression.charAt(j+1) == ')') {
							Expression = Expression.substring(0, i) + Expression.substring(i + 1, j)
									+ Expression.substring(j + 1, Expression.length());
 
							break;
						}
					}

				}
			}
		} 
		return Expression;
	}

	public String removeDoubleNegations(String Expression) {
		return Expression.replace("¬¬", "");
	}

	private boolean hasValidNegations(String Expression) {

		for (int i = 0; i < Expression.length(); i++) {
			if (Expression.charAt(i) == '¬') {
				// check before Negations
				if (i > 0) {
					if (Expression.charAt(i - 1) == ')') {
						Warnings.add("There can not be a negation after a subexpression");
						return false;
					}
				}
				// check after Negations
				if (i < Expression.length() - 1) {
					if (isOperator(Expression.charAt(i + 1))) {
						Warnings.add("An operator cant be negated");
						return false;
					}
				}
				// check Negations
			}
		}

		// check extras
		if (Expression.charAt(Expression.length() - 1) == '¬') { // Check if Expression start with operator
			Warnings.add("Negation need to negate something");
			return false;
		}
		return true;
	}

	boolean hasGlobalNegation(String Expression) {

		if (Expression.charAt(0) == '¬') {

			System.out.println("hasGlobalNegation " + Expression);
			String testExpressionNegation = Expression.substring(1, Expression.length());
			System.out.println("testnegationExpression   " + testExpressionNegation);
			if (hasGlobalParentheses(testExpressionNegation)) {
				return true;
			}
		}
		return false;
	}

	String removeGlobalNegation(String Expression) {
		System.out.println("removeGlobalNegation " + Expression);
		return Expression = Expression.substring(1, Expression.length());
	}

	boolean hasGlobalParentheses(String Expression) {

		String testExpressionWithouGlobalBraquets = Expression;
		if ((Expression.charAt(0) == '(') && (Expression.charAt(Expression.length() - 1) == ')')) {
			testExpressionWithouGlobalBraquets = testExpressionWithouGlobalBraquets.substring(1,
					testExpressionWithouGlobalBraquets.length() - 1);
			if (hasValidParentheses(testExpressionWithouGlobalBraquets, DO_NOT_SEND_WARNINGS)) {
				return true;
			}
		}
		return false;
	}

	String removeGlobalParentheses(String Expression) {

		System.out.println("removeGlobalBracket ( \" " + Expression + "\" )");
		if (hasValidParentheses(Expression.substring(1, Expression.length() - 1), DO_NOT_SEND_WARNINGS)) {
			return Expression.substring(1, Expression.length() - 1);
		}
		return Expression;
	}
	String removeUnnecessaryParentheses(String Expression) {

		System.out.println("removeUnnecessaryParentheses 1  " + Expression);
		for (int i = 0; i < Expression.length()-1; i++) {
			if (Character.toString(Expression.charAt(i)).matches("[a-zA-z]{1}")){
				System.out.println("Expression.charAt(i)  " + Expression.charAt(i));
				// check before 
				if (i > 0) {
					
				}
				// check after 
				if (i < Expression.length() - 1) {
					
				}
				// check Position
				if (i > 0 && i < (Expression.length() - 1)) {
				if (Expression.charAt(i - 1) == '(' && Expression.charAt(i + 1) == ')') { 
					Expression=Expression.substring(0, i - 1)+Expression.charAt(i)+Expression.substring(i+2, Expression.length());
				}
				}
			}
		}

		System.out.println("removeUnnecessaryParentheses 2  " + Expression);
		Expression=replaceDoubleParentheses(Expression);

		System.out.println("removeUnnecessaryParentheses 3  " + Expression);
		return Expression;
	}

	public void negateExpression() {
		if (ExpressionV == true) {
			ExpressionV = false;
		} else {
			ExpressionV = true;
		}
	}

	public void negateRight() {
		if (rightV == true) {
			rightV = false;
		} else {
			rightV = true;
		}
	}

	public void negateLeft() {
		if (leftV == true) {
			leftV = false;
		} else {
			leftV = true;
		}
	}

	public int SubExpressions(String Expression) {
		int openParentheses = 0;
		int SubExpressions = 0;

		if (Expression != null && !Expression.trim().isEmpty())
			for (int i = 0; i < Expression.length(); i++) {

				if (openParentheses == 0 && isOperator(Expression.charAt(i))) {
					SubExpressions++;
				}
				if (Expression.charAt(i) == '(') {
					openParentheses++;
				} else if (Expression.charAt(i) == ')') {
					openParentheses--;
				}

			}

		return SubExpressions + 1;
	}

	public int PrincipalOperatorIndex(String Expression) {

		System.out.println("PrincipalOperatorIndex( " + Expression + " )");
		int openParentheses = 0;

		if (SubExpressions(Expression) >= 2) {
			for (int i = 0; i < Expression.length(); i++) {

				if (Expression.charAt(i) == '(') {
					openParentheses++;
				} else if (Expression.charAt(i) == ')') {
					openParentheses--;
				}
				if (openParentheses == 0 && isOperator(Expression.charAt(i))) {
					if (Expression.charAt(i) == '→' || Expression.charAt(i) == '↔') {
						return i;
					}
				}

			}
			for (int i = 0; i < Expression.length(); i++) {

				if (Expression.charAt(i) == '(') {
					openParentheses++;
				} else if (Expression.charAt(i) == ')') {
					openParentheses--;
				}
				if (openParentheses == 0 && isOperator(Expression.charAt(i))) {
					if (Expression.charAt(i) == '∧' || Expression.charAt(i) == '∨') {
						return i;
					}
				}
			}

		}

		Warnings.add("Cannot Return PrincipalOperatorIndex");
		return -1;
	}

	static boolean isOperator(char logic) {
		if (logic == '→' || logic == '∧' || logic == '∨' || logic == '↔') {
			return true;
		} else
			return false;
	}

	public boolean hasValidParentheses(String Expression, boolean sendWarnings) {

		int openbraquets = 0;

		for (int i = 0; i < Expression.length(); i++) {

			if (Expression.charAt(i) == '(') {
				openbraquets++;
				if (i == Expression.length() - 1) {
					if (sendWarnings)
						Warnings.add("End with open braquet");
					return false;
				}
			} else if (Expression.charAt(i) == ')') {
				openbraquets--;
				if (i == 0) {
					if (sendWarnings)
						Warnings.add("Start with close braquet");
					return false;
				}
			}
			if (openbraquets < 0) {
				if (sendWarnings)
					Warnings.add("Too many close braquets");
				return false;
			}
		}
		if (openbraquets > 0) {
			if (sendWarnings)
				Warnings.add("Braquets still open");
			return false;
		}

		for (int i = 0; i < Expression.length(); i++) {
			if (Expression.charAt(i) == '¬') {
				// check before Parentheses
				if (i > 0) {
					if (Expression.charAt(i - 1) == ')') {
						Warnings.add("There can not be a negation after a subexpression");
						return false;
					}
				}
				// check after Parentheses
				if (i < Expression.length() - 1) {
					if (isOperator(Expression.charAt(i + 1))) {
						Warnings.add("An operator cant be negated");
						return false;
					}
				}
				// check paracentesis
			}
		}

		// check extras
		if (Expression.charAt(Expression.length() - 1) == '¬') { // Check if Expression start with operator
			Warnings.add("Negation need to negate something");
			return false;
		}

		return true;
	}

	public boolean hasValidOperators(String Expression) {

		boolean hasAtLeastOneOperator = false;
		if (isOperator(Expression.charAt(0))) { // Check if Expression start with operator
			Warnings.add("Expression cant star with operator");
			return false;
		}

		for (int i = 0; i < Expression.length(); i++) {
			if (isOperator(Expression.charAt(i))) {
				hasAtLeastOneOperator = true;
				// check before operator
				if (i > 0) {
					if (isOperator(Expression.charAt(i - 1))) {
						Warnings.add("Double Operator ");
						return false;
					}
					if (Expression.charAt(i - 1) == '(') {
						Warnings.add("Subexpression cant start with operator ");
						return false;
					}
					if (Expression.charAt(i - 1) == '¬') {
						Warnings.add("Operator cant be negated ");
						return false;
					}
				}
				// check after operator
				if (i < Expression.length() - 1) {
					if (isOperator(Expression.charAt(i + 1))) {
						Warnings.add("Double Operator ");
						return false;
					}
					if (Expression.charAt(i + 1) == ')') {
						Warnings.add("Subexpression cant end with operator");
						return false;
					}
				}
				// check operator

			}
		}
		if (!hasAtLeastOneOperator) {
			Warnings.add("Expression doesnt have any operator");
			return false;
		}
		if (isOperator(Expression.charAt(0))) {
			Warnings.add("Expression cant start with operator");
			return false;
		}
		if (isOperator(Expression.charAt(Expression.length() - 1))) {
			Warnings.add("Expression cant end with operator");
			return false;
		}
		return true;
	}

	public boolean hasValidVariables(String Expression) {

		for (int i = 0; i < Expression.length(); i++) {
			if (Expression.matches(".*[a-zA-Z]{2,}+.*")) { // check if expression has numbers
				Warnings.add("Variables cant be together ");
				return false;
			}
		}
		return true;
	}

	public boolean hasValidChars(String Expression) {

		for (int i = 0; i < Expression.length(); i++) {
			if (Expression.matches(".*\\d+.*")) { // check if expression has numbers
				Warnings.add("Expression cant have numbers ");
				return false;
			}
			if (Expression.matches(".*}.*") || Expression.matches(".*}.*")) { // check if expression has numbers
				Warnings.add("Expression cant have \"{ or }\" ");
				return false;
			}
		}
		return true;
	}

	public ArrayList<String> getWarnings() {
		return Warnings;
	}

	public void clearWarnings() {
		Warnings.clear();
	}

	public String getWarning(int i) {
		if (Warnings.size() > 0) {
			return Warnings.get(i);
		}
		return "";
	}
}
