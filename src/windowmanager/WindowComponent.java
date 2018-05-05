package windowmanager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import logic.SemanticBoard;
import media.font;

public class WindowComponent {

	public Main menu = new Main();

	class Main extends JPanel implements ActionListener {
		private static final long serialVersionUID = -3196083404766354569L;
		boolean refressemanticboard = false;

		// ------VARIABLES

		final String MENU = "MENU";

		String SHOW = MENU;
		JLabel Expressionhead;
		JLabel Warningpane = new JLabel(); 
		public Main() {

			// ------------------------FINALS
			final Color SubBarColor1 = Color.WHITE;
			final Color SubBarColor2 = Color.BLACK;
			final Color SubBarColor3 = Color.BLACK;
			// ------------------------CREATE

			// ------PAINT
			setBounds(0, 0, 1920, 1080);
			setOpaque(false);

			// ------TEXT

			

			// ------TEXTFIELD

			int BelowBarY = 1080 - 150;
			int ExpressionBoxLenght = 1920 - 400;
			int BelowBarHeight = 50;
			int ExpressionBoxFontsize = 30; 
			JTextField ExpressionBox = new JTextField();
			ExpressionBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, ExpressionBoxFontsize));
			ExpressionBox.setBounds(10, BelowBarY, ExpressionBoxLenght, BelowBarHeight);
			ExpressionBox.setBackground(SubBarColor1);
			ExpressionBox.setForeground(SubBarColor2);
			ExpressionBox.setBorder(BorderFactory.createBevelBorder(0));
			ExpressionBox.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (ExpressionBox.getText().length() >= 116)
						e.consume();
				}
			});
			ExpressionBox.getDocument().addDocumentListener(new DocumentListener() {

				public void changedUpdate(DocumentEvent e) {
					update();
				}

				public void insertUpdate(DocumentEvent e) {
					update();
				}

				public void removeUpdate(DocumentEvent e) {
					update();
				}

				public void update() {
					int size = 30;
					if (ExpressionBox.getText().length() >= 84) {
						size = 29;
						if (ExpressionBox.getText().length() >= 87) {
							size = 28;
							if (ExpressionBox.getText().length() >= 89) {
								size = 27;
								if (ExpressionBox.getText().length() >= 93) {
									size = 26;
									if (ExpressionBox.getText().length() >= 94) {
										size = 25;
										if (ExpressionBox.getText().length() >= 101) {
											size = 24;
											if (ExpressionBox.getText().length() >= 107) {
												size = 23;
												if (ExpressionBox.getText().length() >= 108) {
													size = 22;
												}
											}
										}
									}
								}
							}
						}
					}
					System.out.println(size);
					ExpressionBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, size));
				}
			});
			// ------BUTTONS

			int SubBarY = BelowBarY - 40;
			int SubBarXstart = 10;
			int SubBarHeight = 40;
			int SubBarButtonsWidth = 100;
			
			String implication = "→", conjunction = "∧", disjunction = "∨", doubleimplication = "↔", negation = "¬";
			JButton b_check, b_implication, b_conjunction, b_disjunction, b_doubleimplication, b_negation;
			b_check = new JButton("Check");
			b_check.addActionListener(new ActionListener() {
				boolean hasNewExpression=false;
				public void actionPerformed(ActionEvent e) { 
					if(hasNewExpression) {
						SemanticBoard.EXP.Warnings.clear();   
						remove(Warningpane);
						Warningpane.setText(""); 
					}
					SemanticBoard.setHeadExpression(ExpressionBox.getText()); 
					refressemanticboard = true;  
					Warnings();
					WindowManager.Main.Refresh();
					hasNewExpression=true;
				}
			});
			b_check.setBounds(ExpressionBoxLenght + 30, BelowBarY, 1920 - ExpressionBoxLenght - 50, BelowBarHeight);
			b_check.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_check.setBackground(SubBarColor1);
			b_check.setForeground(SubBarColor2);
			b_check.setBorder(BorderFactory.createBevelBorder(0));

			b_implication = new JButton(implication);
			b_implication.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((ExpressionBox.getText().length() + 1) <= 116) {
						int CaretPosition = 0;
						CaretPosition = ExpressionBox.getCaretPosition();

						ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition())
								+ implication + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(),
										ExpressionBox.getText().length()));
						ExpressionBox.setCaretPosition(CaretPosition + 1);
					}
				}
			});
			b_implication.setBounds(SubBarXstart + (SubBarButtonsWidth * 0), SubBarY, SubBarButtonsWidth, SubBarHeight);
			b_implication.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_implication.setBackground(SubBarColor1);
			b_implication.setForeground(SubBarColor2);
			b_implication.setBorder(BorderFactory.createBevelBorder(0));
			b_implication.setFocusable(false);

			b_conjunction = new JButton(conjunction);
			b_conjunction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((ExpressionBox.getText().length() + 1) <= 116) {
						int CaretPosition = 0;
						CaretPosition = ExpressionBox.getCaretPosition();

						ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition())
								+ conjunction + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(),
										ExpressionBox.getText().length()));
						ExpressionBox.setCaretPosition(CaretPosition + 1);
					}
				}
			});
			b_conjunction.setBounds(SubBarXstart + (SubBarButtonsWidth * 1), SubBarY, SubBarButtonsWidth, SubBarHeight);
			b_conjunction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_conjunction.setBackground(SubBarColor1);
			b_conjunction.setForeground(SubBarColor2);
			b_conjunction.setBorder(BorderFactory.createBevelBorder(0));
			b_conjunction.setFocusable(false);

			b_disjunction = new JButton(disjunction);
			b_disjunction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((ExpressionBox.getText().length() + 1) <= 116) {
						int CaretPosition = 0;
						CaretPosition = ExpressionBox.getCaretPosition();

						ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition())
								+ disjunction + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(),
										ExpressionBox.getText().length()));
						ExpressionBox.setCaretPosition(CaretPosition + 1);
					}
				}
			});
			b_disjunction.setBounds(SubBarXstart + (SubBarButtonsWidth * 2), SubBarY, SubBarButtonsWidth, SubBarHeight);
			b_disjunction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_disjunction.setBackground(SubBarColor1);
			b_disjunction.setForeground(SubBarColor2);
			b_disjunction.setBorder(BorderFactory.createBevelBorder(0));
			b_disjunction.setFocusable(false);

			b_doubleimplication = new JButton(doubleimplication);
			b_doubleimplication.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((ExpressionBox.getText().length() + 1) <= 116)
						if ((ExpressionBox.getText().length() + 1) <= 116) {
							int CaretPosition = 0;
							CaretPosition = ExpressionBox.getCaretPosition();

							ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition())
									+ doubleimplication + ExpressionBox.getText().substring(
											ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
							ExpressionBox.setCaretPosition(CaretPosition + 1);
						}
				}
			});
			b_doubleimplication.setBounds(SubBarXstart + (SubBarButtonsWidth * 3), SubBarY, SubBarButtonsWidth,
					SubBarHeight);
			b_doubleimplication.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_doubleimplication.setBackground(SubBarColor1);
			b_doubleimplication.setForeground(SubBarColor2);
			b_doubleimplication.setBorder(BorderFactory.createBevelBorder(0));
			b_doubleimplication.setFocusable(false);

			b_negation = new JButton(negation);
			b_negation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((ExpressionBox.getText().length() + 1) <= 116)
						if ((ExpressionBox.getText().length() + 1) <= 116) {
							int CaretPosition = 0;
							CaretPosition = ExpressionBox.getCaretPosition();

							ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition())
									+ negation + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(),
											ExpressionBox.getText().length()));
							ExpressionBox.setCaretPosition(CaretPosition + 1);
						}
				}
			});
			b_negation.setBounds(SubBarXstart + (SubBarButtonsWidth * 4), SubBarY, SubBarButtonsWidth, SubBarHeight);
			b_negation.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
			b_negation.setBackground(SubBarColor1);
			b_negation.setForeground(SubBarColor2);
			b_negation.setBorder(BorderFactory.createBevelBorder(0));
			b_negation.setFocusable(false);

			// ------------------------ADD

			add(b_implication);
			add(b_conjunction);
			add(b_disjunction);
			add(b_doubleimplication);
			add(b_negation);
			add(ExpressionBox); 
			add(b_check);
			add(b_implication);
			setLayout(null);

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

		boolean showinghead = false;

		public void RePaintSemanticBoard() {
			if (showinghead)
				remove(Expressionhead);
			Expressionhead = new JLabel(SemanticBoard.EXP.getExpression());
			Expressionhead.setBounds(70, 50, 1900, 50);
			Expressionhead.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
			repaint();
			WindowManager.Main.Refresh();
			add(Expressionhead); 
			showinghead = true;
		}

		boolean showingwarnings = false;

		

		public void Warnings(){  
			if (showingwarnings)
				remove(Warningpane);   
			Warningpane.setForeground(new Color(50,0,0));
			Warningpane.setBounds(10 + (100 * 5)+10,880 , 1480, 50);
			Warningpane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40)); 
			Warningpane.setText(SemanticBoard.EXP.getWarning(0)); 
			repaint();
			add(Warningpane);
			showingwarnings = true; 
			WindowManager.Main.Refresh();
		}
 

		public void paint(Graphics g) {
			super.paint(g);
			int value = 60;
			Color myColour = new Color(255, 255, 255, value);
			g.setColor(myColour);
			g.fillRect(0, 100, 960, 490);

			if (refressemanticboard) {
				RePaintSemanticBoard();
			}
		}

		public void actionPerformed(ActionEvent e) {
			repaint();
			WindowManager.Main.Refresh();
		}
	}

}
