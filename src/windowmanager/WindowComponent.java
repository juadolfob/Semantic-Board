package windowmanager;

import java.awt.Color;
import java.awt.Font; 
import java.awt.Graphics; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.SwingUtilities; 
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
 

import logic.SemanticBoard;
import media.font; 

public class WindowComponent {

	public Main menu = new Main();
	
	class Main extends JPanel {
		
		boolean refressemanticboard=false;
		
		class PaintPanel extends JPanel {  private static final long serialVersionUID = 4748952077810439508L;
			
			 
			public PaintPanel() {
				setBorder(BorderFactory.createLineBorder(Color.black));
			}

			public void paintComponent(Graphics g) { 
				super.paintComponent(g);
				// Draw Text
				int value = 60;
				Color myColour = new Color(255, 255, 255, value);
				g.setColor(myColour);
				g.fillRect(0, 100, 960, 490);
				
				 
				if(refressemanticboard)RePaintSemanticBoard();
				 
			}

			private void RePaintSemanticBoard() {
				//------TEXT
				
				JLabel label2; 
				
		        label2 			=	new JLabel(SemanticBoard.EXP.GetExpression());  
			    label2.setBounds(40,40,1920/2,1920/2); 
				label2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
				refressemanticboard=false;
				add(label2);
			}
			 
		}
		
		 

		// ------VARIABLES

		private static final long serialVersionUID = -3196083404766354569L;
		final String MENU = "MENU";

		String SHOW = MENU;

		public Main() {

			 
	       
	        	
			SwingUtilities.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
					
 
//------------------------FINALS
		      final   Color SubBarColor1=Color.WHITE;
		      final   Color SubBarColor2=Color.BLACK;
		      final   Color SubBarColor3=Color.BLACK;	
//------------------------CREATE
				
				
//------GLPANE
					
			 
					
//------TEXT
					
			JLabel label2; 
			
	        label2 			=	new JLabel("INFO HER here HERE here Here Lore lore LORE lore");  
		    label2.setBounds(40,40,500,500); 
				label2.setFont	(new font().ExpressionboxText); 
		    
//------TEXTFIELD	    
				

			    int BelowBarY=1080-150; 
			    int ExpressionBoxLenght= 1920-400;
			    int BelowBarHeight = 50;
			    int ExpressionBoxFontsize=30;
			    
			    JTextField ExpressionBox=new  JTextField(); 
			    ExpressionBox.setFont(new Font(Font.MONOSPACED, Font.PLAIN, ExpressionBoxFontsize));
			    ExpressionBox.setBounds(10, BelowBarY ,ExpressionBoxLenght ,BelowBarHeight);    
			    ExpressionBox.setBackground(SubBarColor1);
			    ExpressionBox.setForeground(SubBarColor2);
			    ExpressionBox.setBorder(BorderFactory.createBevelBorder(0)); 
			    ExpressionBox.addKeyListener(new KeyAdapter() {
			        public void keyTyped(KeyEvent e) { 
			            if (ExpressionBox.getText().length() >= 116 ) 
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
					public void update(){
						int size=30;
					if(ExpressionBox.getText().length()>=84) { 
						size=29;
						if(ExpressionBox.getText().length()>=87) {
							size=28;
							if(ExpressionBox.getText().length()>=89) {
								size=27;
								if(ExpressionBox.getText().length()>=93) { 
									size=26;
									if(ExpressionBox.getText().length()>=94) { 
										size=25;
										if(ExpressionBox.getText().length()>=101) { 
											size=24;
											if(ExpressionBox.getText().length()>=107) { 
												size=23;
												if(ExpressionBox.getText().length()>=108) { 
													size=22;
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
//------BUTTONS 
			    
			     int SubBarY=BelowBarY-40;
		         int SubBarXstart=10;
		         int SubBarHeight=40;
		         int SubBarButtonsWidth=100;
		         
			String  implication="→",conjunction="∧",disjunction="∨",doubleimplication="↔",negation="¬";
	        JButton b_check ,b_implication,b_conjunction,b_disjunction,b_doubleimplication,b_negation;
			b_check=new JButton("Check");
	        b_check.addActionListener(new ActionListener(){  
	        public void actionPerformed(ActionEvent e){   
	        		SemanticBoard pan=new SemanticBoard(ExpressionBox.getText());
	        		refressemanticboard=true;
	            }});  
	         b_check.setBounds(ExpressionBoxLenght+30,BelowBarY,1920-ExpressionBoxLenght-50,BelowBarHeight); 
	         b_check.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));    
	         b_check.setBackground(SubBarColor1);
	         b_check.setForeground(SubBarColor2);
	         b_check.setBorder(BorderFactory.createBevelBorder(0)); 
	         
	         b_implication=new JButton(implication);
	         b_implication.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	            	if((ExpressionBox.getText().length()+1)<=116) {
	            		int CaretPosition = 0;
	            		CaretPosition=ExpressionBox.getCaretPosition();
	            		
	            		ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition()) + implication + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
	            		ExpressionBox.setCaretPosition(CaretPosition+1);
	            	}
	            }});  
	         b_implication.setBounds(SubBarXstart+(SubBarButtonsWidth*0), SubBarY,SubBarButtonsWidth,SubBarHeight); 
	         b_implication.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
	         b_implication.setBackground(SubBarColor1);
	         b_implication.setForeground(SubBarColor2);
	         b_implication.setBorder(BorderFactory.createBevelBorder(0)); 
	         b_implication.setFocusable(false);
	         
	         b_conjunction=new JButton(conjunction);
	         b_conjunction.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	            	if((ExpressionBox.getText().length()+1)<=116) {
	            		int CaretPosition = 0;
	            		CaretPosition=ExpressionBox.getCaretPosition();
	            		
	            		ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition()) + conjunction + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
	            		ExpressionBox.setCaretPosition(CaretPosition+1);
	            	}
	            }});  
	         b_conjunction.setBounds(SubBarXstart+(SubBarButtonsWidth*1), SubBarY,SubBarButtonsWidth,SubBarHeight); 
	         b_conjunction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
	         b_conjunction.setBackground(SubBarColor1);
	         b_conjunction.setForeground(SubBarColor2);
	         b_conjunction.setBorder(BorderFactory.createBevelBorder(0)); 
	         b_conjunction.setFocusable(false);
	         
	         b_disjunction=new JButton(disjunction);
	         b_disjunction.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	            	if((ExpressionBox.getText().length()+1)<=116) {
	            		int CaretPosition = 0;
	            		CaretPosition=ExpressionBox.getCaretPosition();
	            		
	            		ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition()) + disjunction + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
	            		ExpressionBox.setCaretPosition(CaretPosition+1);
	            	}
	            }});  
	         b_disjunction.setBounds(SubBarXstart+(SubBarButtonsWidth*2), SubBarY,SubBarButtonsWidth,SubBarHeight); 
	         b_disjunction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
	         b_disjunction.setBackground(SubBarColor1);
	         b_disjunction.setForeground(SubBarColor2);
	         b_disjunction.setBorder(BorderFactory.createBevelBorder(0)); 
	         b_disjunction.setFocusable(false);
	         
	         
	         b_doubleimplication=new JButton(doubleimplication);
	         b_doubleimplication.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	            	if((ExpressionBox.getText().length()+1)<=116)
	            		if((ExpressionBox.getText().length()+1)<=116) {
		            		int CaretPosition = 0;
		            		CaretPosition=ExpressionBox.getCaretPosition();
		            		
		            		ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition()) + doubleimplication + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
		            		ExpressionBox.setCaretPosition(CaretPosition+1);
		            	}
	            }});  
	         b_doubleimplication.setBounds(SubBarXstart+(SubBarButtonsWidth*3), SubBarY,SubBarButtonsWidth,SubBarHeight); 
	         b_doubleimplication.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39)); 
	         b_doubleimplication.setBackground(SubBarColor1);
	         b_doubleimplication.setForeground(SubBarColor2);
	         b_doubleimplication.setBorder(BorderFactory.createBevelBorder(0)); 
	         b_doubleimplication.setFocusable(false);
	         
	         
	         b_negation=new JButton(negation);
	         b_negation.addActionListener(new ActionListener(){  
	            public void actionPerformed(ActionEvent e){  
	            	if((ExpressionBox.getText().length()+1)<=116)
	            		if((ExpressionBox.getText().length()+1)<=116) {
		            		int CaretPosition = 0;
		            		CaretPosition=ExpressionBox.getCaretPosition();
		            		
		            		ExpressionBox.setText(ExpressionBox.getText().substring(0, ExpressionBox.getCaretPosition()) + negation + ExpressionBox.getText().substring(ExpressionBox.getCaretPosition(), ExpressionBox.getText().length()));
		            		ExpressionBox.setCaretPosition(CaretPosition+1);
		            	}
	            }});  
	         b_negation.setBounds(SubBarXstart+(SubBarButtonsWidth*4), SubBarY,SubBarButtonsWidth,SubBarHeight); 
	         b_negation.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 39));
	         b_negation.setBackground(SubBarColor1);
	         b_negation.setForeground(SubBarColor2);
	         b_negation.setBorder(BorderFactory.createBevelBorder(0)); 
	         b_negation.setFocusable(false);
//------PAINT
	         
	         PaintPanel paint=new PaintPanel();   
	         paint.setBounds(0,0,1920,1080);
	         paint.setOpaque(false); 
	         
//------------------------ADD  
	         
	          
	        	System.out.println("ShowMenu"); 
	        	add(b_implication);
				add(b_conjunction);
 				add(b_disjunction);
 				add(b_doubleimplication);
 				add(b_negation);
 
		        add(paint);  
		        add(ExpressionBox);
		        add(label2);  
		        add(b_check);   
		        add(b_implication);  
		        setLayout(null);    
	        	 
	 		   
//------ANIMATOR
				 
	 		 
	 		  
				}});}

		 

	}

	/*----------------------------------------------------------------MENU--------------------------------------------------------------------------*/

}
