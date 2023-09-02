import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator {
	
	static double calcNum = 0;
	static double result = 0;
	static boolean proceedCalc = false;
    static JLabel[] a = new JLabel[5];
    static int hisCount = 0;
    static boolean isChain = false;
    static String operator;

	public static void main(String[] args) {
		
		String[][] layout = {
				{"C", 	"%", "⌫", "/"},
				{"7", 	"8", "9", 	"*"},
				{"4", 	"5", "6", 	"-"},
				{"1", 	"2", "3", 	"+"},
				{"+/-", "0", ".", 	"="}
		};
		
		String[] calcs = new String[5];
		
		Color c1 = new Color(38, 38, 38);
		Color c2 = new Color(67, 67, 67);
		Color c3 = new Color(189, 126, 62);
		
		Font f1 = new Font(null, Font.BOLD, 20);
		
		JFrame frame = new JFrame("Calculator");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	    frame.setSize(290,390);
	    frame.getContentPane().setBackground(c1);
	    
	    JLabel history = new JLabel("History");
	    history.setBounds(300, 100, 70, 20);
	    history.setForeground(Color.white);
	    history.setFont(f1);
	    frame.add(history);
	    
	    int ay = 120;
	    for(int i = 0 ; i < 5; i++) {
	    	a[i] = new JLabel();
	    	a[i].setBounds(250, ay, 170, 40);
	    	a[i].setBackground(c1);
	    	a[i].setForeground(Color.lightGray);
	    	a[i].setBorder(null);
	    	a[i].setVisible(false);
	    	frame.add(a[i]);
	    	ay += 40;
	    }
	    
	    JButton b = new JButton("Show History");
	    b.setBounds(0, 0, 100, 20);
	    b.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(frame.getWidth() == 290) {
					frame.setSize(490, 390);
					for(int i = 0; i < 5; i++)
						a[i].setVisible(true);
				}
				else {
					frame.setSize(290, 390);
					for(int i = 0; i < 5; i++)
						a[i].setVisible(false);	
				}
			}
		});
	    frame.add(b);
	    
	    JTextField textField = new JTextField();
	    textField.setBounds(20, 20, 200, 50);
	    textField.setFont(new Font("Arial", Font.PLAIN, 24));
	    textField.setBackground(c2);
	    textField.setForeground(Color.white);
	    frame.add(textField);
	    
	    JLabel selOperator = new JLabel("()");
	    selOperator.setBounds(230, 35, 30, 20);
	    selOperator.setFont(new Font("Arial", Font.PLAIN, 20));
	    selOperator.setForeground(Color.WHITE);
	    frame.add(selOperator);
	    
	    int y = 140;
	    for(int i = 1; i < 4; i++) {
	    	int x = 20;
	    	for(int j = 0; j < 3; j++) {
	    		JButton btn = new JButton(layout[i][j]);
	    		btn.setBounds(x, y, 50, 50);
	    		btn.setBackground(c1);
	    		btn.setForeground(Color.white);
	    		btn.setFont(f1);
	    		btn.setBorder(null);
	    		btn.addActionListener(Calculator.action(textField));
	    	    frame.add(btn);
	    	    x += 50;
	    	}
	    	y += 50;
	    }
	    
	    y = 90;
	    for(int i = 0; i < 4; i++) {
	    	JButton btn = new JButton(layout[i][3]);
	    	btn.setBounds(170, y, 50, 50);
	    	btn.setBackground(c1);
	    	btn.setForeground(c3);
	    	btn.setFont(f1);
	    	btn.setBorder(null);
	    	btn.addActionListener(action2(textField, selOperator));
		    frame.add(btn);
		    y += 50;
	    }
	    
	    /* row - 1 */
	    JButton b_0_0 = new JButton(layout[0][0]);
	    b_0_0.setBounds(20, 90, 50, 50);
	    b_0_0.setBackground(c1);
	    b_0_0.setForeground(Color.white);
	    b_0_0.setFont(f1);
	    b_0_0.setBorder(null);
	    b_0_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				selOperator.setText("()");
				calcNum = 0;
				result = 0;
				proceedCalc = false;
			}
		});
	    frame.add(b_0_0);
	    
	    JButton b_0_1 = new JButton(layout[0][1]);
	    b_0_1.setBounds(70, 90, 50, 50);
	    b_0_1.setBackground(c1);
	    b_0_1.setForeground(Color.white);
	    b_0_1.setFont(f1);
	    b_0_1.setBorder(null);
	    b_0_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				calculate(textField, "%");
				textField.setText(Double.toString(result));
				calcNum = 0;
				result = 0;
				operator = "";
				hisCount++;
				proceedCalc = false;
			}
		});
	    frame.add(b_0_1);
	    
	    JButton b_0_2 = new JButton(layout[0][2]);
	    b_0_2.setBounds(120, 90, 50, 50);
	    b_0_2.setBackground(c1);
	    b_0_2.setForeground(c3);
	    b_0_2.setFont(f1);
	    b_0_2.setBorder(null);
	    b_0_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")) {
					textField.setText(textField.getText().substring(0, textField.getText().length() - 1));			
				}
			}
		});
	    frame.add(b_0_2);
	    
	    /* row - 5 */
	    JButton b_4_0 = new JButton(layout[4][0]);
	    b_4_0.setBounds(20, 290, 50, 50);
	    b_4_0.setBackground(c1);
	    b_4_0.setForeground(Color.white);
	    b_4_0.setFont(f1);
	    b_4_0.setBorder(null);
	    b_4_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals(""))
				{
					double x = Double.parseDouble(textField.getText());
					x *= -1;
					textField.setText(Double.toString(x));
				}
			}
		});
	    frame.add(b_4_0);
	    
	    JButton b_4_1 = new JButton(layout[4][1]);
	    b_4_1.setBounds(70, 290, 50, 50);
	    b_4_1.setBackground(c1);
	    b_4_1.setForeground(Color.white);
	    b_4_1.setFont(f1);
	    b_4_1.setBorder(null);
	    b_4_1.addActionListener(Calculator.action(textField));
	    frame.add(b_4_1);
	    
	    JButton b_4_2 = new JButton(layout[4][2]);
	    b_4_2.setBounds(120, 290, 50, 50);
	    b_4_2.setBackground(c1);
	    b_4_2.setForeground(Color.white);
	    b_4_2.setFont(f1);
	    b_4_2.setBorder(null);
	    b_4_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().contains("."))
					textField.setText(textField.getText() + ".");				
			}
		});
	    frame.add(b_4_2);
	    
	    JButton b_4_3 = new JButton(layout[4][3]);
	    b_4_3.setBounds(170, 290, 50, 50);
	    b_4_3.setBackground(c3);
	    b_4_3.setForeground(Color.white);
	    b_4_3.setFont(f1);
	    b_4_3.setBorder(null);
	    b_4_3.addActionListener(Calculator.action3(textField));
	    frame.add(b_4_3);

	    frame.setVisible(true);
	}
	
	public static ActionListener action(JTextField txt) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				if(!txt.getText().equals(null) && !proceedCalc && !txt.getText().equals(".")) {
					txt.setText(null);
				}
				txt.setText(txt.getText() + btn.getText());
				proceedCalc = true;
			}
		};
		
	}
	
	public static ActionListener action2(JTextField txt, JLabel lbl) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				operator = btn.getText();
				lbl.setText("(" + operator + ")");
				if(hisCount > 4 || a[hisCount].getText().equals(""))
					addToHistory(txt.getText());

				if(proceedCalc) {					
					if(result != 0) {
						isChain = true;
						calcNum = Double.parseDouble(txt.getText());
						calculate(txt, operator);
						txt.setText(Double.toString(result));
					}
					else {
						result = Double.parseDouble(txt.getText());
					}					
					proceedCalc = false;					
				}
			}
		};		
	}
	
	public static ActionListener action3(JTextField txt) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt.getText().equals(null)) {
					if(isChain) {
						calcNum = Double.parseDouble(txt.getText());
					}
					else {
						if(calcNum != 0) {
							result = calcNum;
						}
						else {
							calcNum = Double.parseDouble(txt.getText());
						}
					}
					calculate(txt, operator);
					addToHistory(" = " + result);
					txt.setText(Double.toString(result));
					calcNum = 0;
					result = 0;
					operator = "";
					proceedCalc = false;
					isChain = false;
					hisCount++;
				}				
			}
		};		
	}
	
	public static void calculate(JTextField input, String op)
	{
		switch(op) {
			case "%":				
				calcNum = Double.parseDouble(input.getText()) / 100;
				input.setText(Double.toString(calcNum));
				calculate(input, operator);
				addToHistory(" = " + result);
				break;
			case "/":
				result /= Double.parseDouble(input.getText());
				addToHistory("/" + calcNum);
				break;
			case "*":
				result *= Double.parseDouble(input.getText());
				addToHistory("*" + calcNum);
				break;
			case "-":
				result -= Double.parseDouble(input.getText());
				addToHistory("-" + calcNum);
				break;
			case "+":
				result += Double.parseDouble(input.getText());
				addToHistory("+" + calcNum);
				break;
		}
	}
	
	public static void addToHistory(String s) {
		if(hisCount > 4) {
			a[0].setText(a[1].getText());
			a[1].setText(a[2].getText());
			a[2].setText(a[3].getText());
			a[3].setText(a[4].getText());
			a[4].setText(s);
			hisCount = 4;
		}
		else {
			a[hisCount].setText(a[hisCount].getText() + s);
		}
	}
}
