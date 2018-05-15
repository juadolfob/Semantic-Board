package logic;

import java.util.ArrayList;

public class Expression {

	final public boolean DO_NOT_SEND_WARNINGS = false;
	final public boolean SEND_WARNINGS = true;

	final public boolean FOR_EXPRESSION = true;
	final public boolean FOR_SUBEXPRESSION = false;

	final public boolean RIGHTSIDE = true;
	final public boolean LEFTSIDE = false;

	 
	final public boolean DOES_NOT_EXIST = false;
	final public boolean EXIST = true;

	final public boolean MULTIPLE_VARIABLE = true;
	final public boolean SINGLE_VARIABLE = false;

	public boolean expressionForm = MULTIPLE_VARIABLE;

	public boolean hasOperator = false;

	public boolean expressiontruthvalue = true;
	public boolean lefttruthvalue = true;
	public boolean righttruthvalue = true;
	
	public String left = "";
	public String right = "";
	public String Operator = "";
	public ArrayList<String> Warnings = new ArrayList<String>();

	// GETERS

	public boolean getTruthValue() {
		return expressiontruthvalue;
	}

	public char getOperator() {
		return Operator.charAt(0);
	}

	public String toString() { 
		if (hasMultipleVariables()) {
			String leftVchar = "";
			String rightVchar = "";
			if (lefttruthvalue == false) {
				leftVchar = "¬";
			}
			if (righttruthvalue == false) {
				rightVchar = "¬";
			}
			if (expressiontruthvalue == false) {

				return "¬(" + leftVchar + left + Operator + rightVchar + right + ")";
			}

			return leftVchar + left + Operator + rightVchar + right;
		} else if (hasSingleVariable()) {
			return left;
		}
		return "SOMETHINGS NOT RIGHT";
	}
	
	public String toPrintableString() { 
		if (hasMultipleVariables()) {
			String leftVchar = "";
			String rightVchar = "";
			if (lefttruthvalue == false) {
				leftVchar = "¬";
			}
			if (righttruthvalue == false) {
				rightVchar = "¬";
			}
			if (expressiontruthvalue == false) {

				return toPrintableOperator("¬(" + leftVchar + left + Operator + rightVchar + right + ")");
			}

			return toPrintableOperator(leftVchar + left + Operator + rightVchar + right);
		} else if (hasSingleVariable()) {
			return left;
		}
		return "SOMETHINGS NOT RIGHT";
	}
	public String toPrintableOperator(String expression) {
		expression=expression.replace("→", "->");
		expression=expression.replace("↔", "<->");
		expression=expression.replace("∧", "^");
		expression=expression.replace("∨", "v");
		return expression;
	}
	public String getRight() {
		if (righttruthvalue == false) {
			return "¬(" + right + ")";
		} else {
			return right;
		}

	}

	public String getLeft() {
		if (lefttruthvalue == false) {
			return "¬(" + left + ")";
		} else {
			return left;
		}

	}
	// BINARY GETTERS
	 
	boolean isValidOperatorIndex(int index) {
		if (index >= 0) {
			return EXIST;
		} else {
			return DOES_NOT_EXIST;
		}
	}

	public boolean hasMultipleVariables() {
		if (expressionForm == MULTIPLE_VARIABLE) {
			return true;
		} else
			return false;
	}

	public boolean hasSingleVariable() {
		if (expressionForm == SINGLE_VARIABLE) {
			return true;
		} else
			return false;
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

	// CONSTRUCTORS

	public Expression(String expression) { 

			Warnings.clear();  
		Warnings.clear();

		defineExpressionForm(expression);
		expression = expression.replaceAll("\\s", "");
		if (hasValidParentheses(expression, SEND_WARNINGS)) {
			if (hasValidOperators(expression)) {
				if (hasValidNegations(expression)) {
					if (hasValidChars(expression)) {
						if (hasValidVariables(expression)) {
							initiateExpression(expression);
						}
					}
				}
			}
		} else {
			clearExpression(); 
		}
		} 

	public Expression(String expression, boolean ExpressionV) {
		Warnings.clear();
		if (ExpressionV == false) {
			expression="¬("+expression+")";
		}
		expression = expression.replaceAll("\\s", "");

		defineExpressionForm(expression);
		if (hasValidParentheses(expression, SEND_WARNINGS)) {
			if (hasValidOperators(expression)) {
				if (hasValidNegations(expression)) {
					if (hasValidChars(expression)) {
						if (hasValidVariables(expression)) {
							initiateExpression(expression);
						}
					}
				}
			}
		} else {
			clearExpression(); 
		}
	}

	// CLEARS

	public void clearExpression() {
		expressiontruthvalue = true;
		lefttruthvalue = true;
		righttruthvalue = true;
		left = "";
		right = "";
		Operator = "";
	}

	// INITIALIZERS

	public void initiateExpression(String expression) {  

		if (hasMultipleVariables()) {

			expression = removeDoubleNegations(expression);
			expression = removeUnnecessaryParentheses(expression);
			expression = removeExpressionGlobalParentheses(expression);
			int OperatorIndex = PrincipalOperatorIndex(expression);

			Operator = Character.toString(expression.charAt(OperatorIndex));

			left = expression.substring(0, OperatorIndex);
			right = expression.substring(OperatorIndex + 1, expression.length());
			System.out.println(left + Operator + right);

			left = removeSubExpressionGlobalParentheses(left, LEFTSIDE);
			right = removeSubExpressionGlobalParentheses(right, RIGHTSIDE);
			if (SubExpressions(left) >= 2) {
				left = '(' + left + ')';
			}
			if (SubExpressions(right) >= 2) {
				right = '(' + right + ')';
			}
			left = removeUnnecessaryParentheses(left);
			right = removeUnnecessaryParentheses(right);
			Warnings.add("Done.");
		} else if (hasSingleVariable()) {

			left = removeAllParentheses(expression);
			left = removeDoubleNegations(left);
		}
	}

	public void defineExpressionForm(String expression) {
		if (countVariables(expression) > 1) {
			expressionForm = MULTIPLE_VARIABLE; 
		} else if (countVariables(expression) == 1) {
			expressionForm = SINGLE_VARIABLE; 
		}
	}

	public int countVariables(String expression) {
		int numberofvariables = 0;

		for (int i = 0; i < expression.length(); i++) {
			if (String.valueOf(expression.charAt(i)).matches("[a-zA-Z]{1}")) { // check if expression has numbers
				numberofvariables++;
			}
		} 
		return numberofvariables;
	}

	public String addGlobalParentheses(String Expression) {
		return "(" + Expression + ")";
	}

	public String removeSubExpressionGlobalParentheses(String expression, boolean EXPRESSIONSIDE) { 
		for (int i = 0; i < 55; i++) { 
			if (hasGlobalNegation(expression)) {
				if (EXPRESSIONSIDE == RIGHTSIDE) {
					expression = removeGlobalNegation(expression);
					negateRight();
				}
				if (EXPRESSIONSIDE == LEFTSIDE) {
					expression = removeGlobalNegation(expression);
					negateLeft();
				}
			}
 
			if (hasGlobalParentheses(expression)) {
				expression = removeGlobalParentheses(expression);
			} else {
				break;
			}
		}
		return expression;
	}

	public String removeExpressionGlobalParentheses(String Expression) {

		for (int i = 0; i < 55; i++) { 
			if (hasGlobalNegation(Expression)) {
				Expression = removeGlobalNegation(Expression);
				negateExpression();
			}
 
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
				if (Expression.charAt(i) == '(' && Expression.charAt(i + 1) == '(') {
					for (int j = i + 2; j < Expression.length() - 1; j++) {

						if (Expression.charAt(j) == '(') { 
							break;
						}
						if (Expression.charAt(j) == ')' && Expression.charAt(j + 1) == ')') {
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

	boolean hasGlobalNegation(String expression) {

		if (expression.charAt(0) == '¬') { 
			String testExpressionNegation = expression.substring(1, expression.length()); 
			if (hasGlobalParentheses(testExpressionNegation)) {
				return true;
			}
		}
		return false;
	}

	String removeGlobalNegation(String Expression) { 
		return Expression = Expression.substring(1, Expression.length());
	}

	String removeGlobalParentheses(String Expression) {
 
		if (hasValidParentheses(Expression.substring(1, Expression.length() - 1), DO_NOT_SEND_WARNINGS)) {
			return Expression.substring(1, Expression.length() - 1);
		}
		return Expression;
	}

	String removeUnnecessaryParentheses(String expression) {
 
		for (int i = 0; i < expression.length() - 1; i++) {
			if (Character.toString(expression.charAt(i)).matches("[a-zA-z]{1}")) { 
				// check before
				if (i > 0) {

				}
				// check after
				if (i < expression.length() - 1) {

				}
				// check Position
				if (i > 0 && i < (expression.length() - 1)) { // double parenthesis
					if (expression.charAt(i - 1) == '(' && expression.charAt(i + 1) == ')') {
						expression = expression.substring(0, i - 1) + expression.charAt(i)
								+ expression.substring(i + 2, expression.length());
					}
				}
			}
		}
		return expression;
	}

	public String removeAllParentheses(String expression) {
 
		for (int i = 0; i < expression.length() - 1; i++) {
			expression=expression.replace("(",""); 
			expression=expression.replace(")","");
		}
 
		return expression;
	}

	public boolean isVariable(char symbol) {
		return String.valueOf(symbol).matches("[a-zA-z]{1}");
	}

	public void negateExpression() {
		if (expressiontruthvalue == true) {
			expressiontruthvalue = false;
		} else {
			expressiontruthvalue = true;
		}
	}

	public void negateRight() {
		if (righttruthvalue == true) {
			righttruthvalue = false;
		} else {
			righttruthvalue = true;
		}
	}

	public void negateLeft() {
		if (lefttruthvalue == true) {
			lefttruthvalue = false;
		} else {
			lefttruthvalue = true;
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
 
		int openParentheses = 0;
		if (SubExpressions(Expression) >= 2) {
			for (int i = 0; i < Expression.length(); i++) {

				if (Expression.charAt(i) == '(') {
					openParentheses++;
				} else if (Expression.charAt(i) == ')') {
					openParentheses--;
				}
				if (openParentheses == 0 && isOperator(Expression.charAt(i))) {
					if (Expression.charAt(i) == '↔') {
						hasOperator = true; 
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
					if (Expression.charAt(i) == '→') {
						hasOperator = true; 
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
					if (Expression.charAt(i) == '∨') {
						hasOperator = true; 
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
					if (Expression.charAt(i) == '∧') {
						hasOperator = true; 
						return i;
					}
				}
			}

		}
		if (countVariables(Expression) > 1) {
			Warnings.add("Cannot Find Principal Operator Index");
		}
		hasOperator = false; 
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
		if (!hasAtLeastOneOperator && countVariables(Expression) > 1) {
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

	// WARNINGS

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
		return "Done.";
	}
}
