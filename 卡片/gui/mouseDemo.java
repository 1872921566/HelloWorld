package gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class mouseDemo extends JFrame{
	JTextField t = new JTextField(30);
	public mouseDemo(){
		this.setSize(300,300);
		this.setVisible(true);
		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(t);
		t.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				changebg(Color.BLUE);
			}
			public void mouseExited(MouseEvent e){
				changebg(Color.CYAN);
			}
		});
		
	}
	public void changebg(Color c){
		t.setBackground(c);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mouseDemo();
	}

}
