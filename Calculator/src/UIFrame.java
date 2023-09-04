import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UIFrame extends JFrame{
	
    static JLabel[] his = new JLabel[5];
	
	public UIFrame(String windowTitle) {
		
		super(windowTitle);
	}
	
	public void createUI() {
		
		String[][] layout = {
				{"C", 	"%", "⌫", "÷"},
				{"7", 	"8", "9", 	"×"},
				{"4", 	"5", "6", 	"-"},
				{"1", 	"2", "3", 	"+"},
				{"+/-", "0", ".", 	"="}
		};
				
		Color c1 = new Color(38, 38, 38);
		Color c2 = new Color(67, 67, 67);
		Color c3 = new Color(189, 126, 62);
		
		Font f1 = new Font(null, Font.BOLD, 20);
		
		UIManager.put("Button.select", new Color(0.38f, 0.38f, 0.38f, .2f ));
		
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	    this.setSize(290,415);
	    this.getContentPane().setBackground(c1);
	    Image img = Toolkit.getDefaultToolkit().getImage("C:\\Dvlp2\\NSU\\NSU-Java\\Arman\\src\\Calculator-Icon.png");
	    this.setIconImage(img);
	    Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    p.x -= 145;
	    p.y -= 205;
	    this.setLocation(p);
	    
	    JLabel history = new JLabel("History");
	    history.setBounds(300, 100, 70, 20);
	    history.setForeground(Color.white);
	    history.setFont(f1);
	    this.add(history);
	    
	    int ay = 120;
	    for(int i = 0 ; i < 5; i++) {
	    	his[i] = new JLabel();
	    	his[i].setBounds(250, ay, 170, 40);
	    	his[i].setBackground(c1);
	    	his[i].setForeground(Color.lightGray);
	    	his[i].setBorder(null);
	    	his[i].setVisible(false);
	    	this.add(his[i]);
	    	ay += 40;
	    }
	    
	    JTextField textField = new JTextField();
	    textField.setBounds(20, 20, 200, 50);
	    textField.setFont(new Font("Arial", Font.PLAIN, 24));
	    textField.setBackground(c2);
	    textField.setForeground(Color.white);
	    this.add(textField);
	    
	    JLabel selOperator = new JLabel("()");
	    selOperator.setBounds(230, 35, 30, 20);
	    selOperator.setFont(new Font("Arial", Font.PLAIN, 20));
	    selOperator.setForeground(Color.WHITE);
	    this.add(selOperator);
	    
	    JButton b = new JButton("Show History");
	    b.setBounds(90, 80, 135, 30);
	    b.setFont(new Font(null, Font.BOLD, 15));
		b.setBackground(c1);
		b.setForeground(Color.lightGray);
		b.setBorder(null);
		b.setFocusPainted(false);
	    b.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(UIFrame.this.getWidth() == 290) {
					UIFrame.this.setSize(490, 415);
					for(int i = 0; i < 5; i++)
						his[i].setVisible(true);
				}
				else {
					UIFrame.this.setSize(290, 415);
					for(int i = 0; i < 5; i++)
						his[i].setVisible(false);	
				}
			}
		});
	    this.add(b);
	    
	    int y = 165;
	    for(int i = 1; i < 4; i++) {
	    	int x = 20;
	    	for(int j = 0; j < 3; j++) {
	    		JButton btn = new JButton(layout[i][j]);
	    		btn.setBounds(x, y, 50, 50);
	    		btn.setBackground(c1);
	    		btn.setForeground(Color.white);
	    		btn.setFont(f1);
	    		btn.setBorder(null);
	    		btn.setFocusPainted(false);
	    		btn.addActionListener(BtnActions.action(textField));
	    	    this.add(btn);
	    	    x += 50;
	    	}
	    	y += 50;
	    }
	    
	    y = 115;
	    for(int i = 0; i < 4; i++) {
	    	JButton btn = new JButton(layout[i][3]);
	    	btn.setBounds(170, y, 50, 50);
	    	btn.setBackground(c1);
	    	btn.setForeground(c3);
	    	btn.setFont(f1);
	    	btn.setBorder(null);
	    	btn.setFocusPainted(false);
	    	btn.addActionListener(BtnActions.action2(textField, selOperator));
		    this.add(btn);
		    y += 50;
	    }
	    
	    /* row - 1 */
	    JButton b_0_0 = new JButton(layout[0][0]);
	    b_0_0.setBounds(20, 115, 50, 50);
	    b_0_0.setBackground(c1);
	    b_0_0.setForeground(Color.white);
	    b_0_0.setFont(f1);
	    b_0_0.setBorder(null);
	    b_0_0.setFocusPainted(false);
	    b_0_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				selOperator.setText("()");
				Calc.calcNum = 0;
				Calc.result = 0;
				Calc.proceedCalc = false;
				Calc.hisCount = 0;
				for(int i = 0; i < 5; i++)
				{
					his[i].setText("");
				}
			}
		});
	    this.add(b_0_0);
	    
	    JButton b_0_1 = new JButton(layout[0][1]);
	    b_0_1.setBounds(70, 115, 50, 50);
	    b_0_1.setBackground(c1);
	    b_0_1.setForeground(Color.white);
	    b_0_1.setFont(f1);
	    b_0_1.setBorder(null);
	    b_0_1.setFocusPainted(false);
	    b_0_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				Calc.calculate(textField, "%");
				textField.setText(Double.toString(Calc.result));
				Calc.calcNum = 0;
				Calc.result = 0;
				Calc.operator = "";
				Calc.hisCount++;
				Calc.proceedCalc = false;
			}
		});
	    this.add(b_0_1);
	    
	    JButton b_0_2 = new JButton(layout[0][2]);
	    b_0_2.setBounds(120, 115, 50, 50);
	    b_0_2.setBackground(c1);
	    b_0_2.setForeground(c3);
	    b_0_2.setFont(f1);
	    b_0_2.setBorder(null);
	    b_0_2.setFocusPainted(false);
	    b_0_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")) {
					textField.setText(textField.getText().substring(0, textField.getText().length() - 1));			
				}
			}
		});
	    this.add(b_0_2);
	    
	    /* row - 5 */
	    JButton b_4_0 = new JButton(layout[4][0]);
	    b_4_0.setBounds(20, 315, 50, 50);
	    b_4_0.setBackground(c1);
	    b_4_0.setForeground(Color.white);
	    b_4_0.setFont(f1);
	    b_4_0.setBorder(null);
	    b_4_0.setFocusPainted(false);
	    b_4_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals(""))
				{
					double x = Double.parseDouble(textField.getText());
					x *= -1;
					textField.setText(Double.toString(x));
					Calc.proceedCalc = true;
				}
			}
		});
	    this.add(b_4_0);
	    
	    JButton b_4_1 = new JButton(layout[4][1]);
	    b_4_1.setBounds(70, 315, 50, 50);
	    b_4_1.setBackground(c1);
	    b_4_1.setForeground(Color.white);
	    b_4_1.setFont(f1);
	    b_4_1.setBorder(null);
	    b_4_1.setFocusPainted(false);
	    b_4_1.addActionListener(BtnActions.action(textField));
	    this.add(b_4_1);
	    
	    JButton b_4_2 = new JButton(layout[4][2]);
	    b_4_2.setBounds(120, 315, 50, 50);
	    b_4_2.setBackground(c1);
	    b_4_2.setForeground(Color.white);
	    b_4_2.setFont(f1);
	    b_4_2.setBorder(null);
	    b_4_2.setFocusPainted(false);
	    b_4_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().contains("."))
					textField.setText(textField.getText() + ".");				
			}
		});
	    this.add(b_4_2);
	    
	    JButton b_4_3 = new JButton(layout[4][3]);
	    b_4_3.setBounds(170, 315, 50, 50);
	    b_4_3.setBackground(c3);
	    b_4_3.setForeground(Color.white);
	    b_4_3.setFont(f1);
	    b_4_3.setBorder(null);
	    b_4_3.setFocusPainted(false);
	    b_4_3.addActionListener(BtnActions.action3(textField, selOperator));
	    this.add(b_4_3);

	    this.setVisible(true);
	}
}
