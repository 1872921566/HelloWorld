package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.*;


public class CardMainPanel extends JPanel{
	//申明组件与变量申明
	public boolean flag = false;//状态  （开关）
	JLabel l1;JTextField t1;JLabel l2;JTextField t2;
	JLabel l3;JTextField t3;JLabel l4;JTextField t4;
	JLabel l5;JTextField t5;JLabel l6;JTextField t6;
	JLabel l7;JTextField t7;JButton badd,bsave,bdel;
	JPanel p1,p2,p3;  CardQueryPanel cardquery;
	//无参构造函数
	public CardMainPanel(){
		init();
	}
	//delCard方法
	public void delCard() {
		flag = false;
		PersonInfo p = cardquery.getCurrentPerson();
		String sname = p.getSname();
		if(p==null){
			JOptionPane.showMessageDialog(null,"请在列表中选择要修改的联系人");
			return ;
		}
		if(new persons().delete(p)){
			JOptionPane.showMessageDialog(null,"成功删除了"+sname+"的名片信息");
			cardquery.query("");
			cardquery.setCurrentPerson(0);
		}else {
			JOptionPane.showMessageDialog(null,"删除"+sname+"失败");
		}

	}
	//newCard方法
	public void newCard() {
		String sname = t1.getText();
		if(sname.length()<1) {
			JOptionPane.showMessageDialog(null,"联系人姓名不能为空");
			return;
		}
		String sex = t2.getText();
		String company = t3.getText();
		String zhiwei = t4.getText();
		String phone = t5.getText();
		String pQ = t6.getText();
		String weixin = t7.getText();
		PersonInfo t = new PersonInfo("0",sname,sex,company,zhiwei,phone,pQ,weixin);
		if(new persons().add(t)) {
			JOptionPane.showMessageDialog(null,"成功添加了"+sname+"的名片信息");
			cardquery.query("");
			cardquery.setCurrentPerson(-1);
		}
		else {
			JOptionPane.showMessageDialog(null,"联系人"+sname+"的名片信息添加失败");
		}
	}
	//UpdateCard方法  修改方法
	public void UpdateCard() {
		flag = false;
		PersonInfo p = cardquery.getCurrentPerson();
		if(p==null) {
			JOptionPane.showMessageDialog(null,"请在列表中选择要修改的联系人");
			return ;
		}
		if(p.getSid()=="0") {
			JOptionPane.showMessageDialog(null,"名片夹已经空了");
			return ;
		}
		String sname = t1.getText();
		if(sname.length()<1) {
			JOptionPane.showMessageDialog(null,"联系人姓名不能为空");
			return ;
		}
		p.setSname(sname);
		if(t2.getText().trim().length()>1)p.setSex(t2.getText());
		if(t3.getText().trim().length()>1)p.setCompany(t3.getText());
		if(t4.getText().trim().length()>1)p.setZhiwei(t4.getText());
		if(t5.getText().trim().length()>1)p.setPhone(t5.getText());
		if(t6.getText().trim().length()>1)p.setpQ(t6.getText());
		if(t7.getText().trim().length()>1)p.setWeixin(t7.getText());
		if(new persons().change(p)) {
			JOptionPane.showMessageDialog(null,"成功修改了"+sname+"的名片信息");
		}
		else {
			JOptionPane.showMessageDialog(null,"联系人"+sname+"的名片信息修改失败");
		}
	}
	//准备添加    即当按下添加按钮时
	public void prepareAdd() {
		flag = true;
		t1.setText("");t2.setText("");t3.setText("");t4.setText("");
		t5.setText("");t6.setText("");t7.setText("");	
	}
	public void init(){
		l1 = new JLabel("姓名",JLabel.CENTER);t1 = new JTextField(20);
		l2 = new JLabel("性别",JLabel.CENTER);t2 = new JTextField(20);
		l3 = new JLabel("QQ",JLabel.CENTER);t3 = new JTextField(20);
		l4 = new JLabel("公司",JLabel.CENTER);t4 = new JTextField(20);
		l5 = new JLabel("电话",JLabel.CENTER);t5 = new JTextField(20);
		l6 = new JLabel("微信",JLabel.CENTER);t6 = new JTextField(20);
		l7 = new JLabel("职位",JLabel.CENTER);t7 = new JTextField(20);
		badd = new JButton("添加");bsave = new JButton("保存");bdel = new JButton("删除");
		cardquery = new CardQueryPanel(this);
		p1 = new JPanel(new GridLayout(7,2)); 
		p1.add(l1);p1.add(t1);p1.add(l2);p1.add(t2);p1.add(l3);p1.add(t3);p1.add(l4);p1.add(t4);
		p1.add(l5);p1.add(t5);p1.add(l6);p1.add(t6);p1.add(l7);p1.add(t7);
		p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3 = new JPanel(new BorderLayout());
		p3.add("Center",p1);p3.add("South",p2);
		p2.add(badd);p2.add(bsave);p2.add(bdel);
		this.setLayout(new BorderLayout());
		this.add("Center",p3);this.add("East",cardquery);
		//---------添加按钮的事件处理
		badd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				prepareAdd();
			}
			
		});
		//---------修改按钮的事件处理
		bsave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				if(flag==true)newCard();
				else UpdateCard();	
			}
			
		});
		//---------删除按钮的事件处理
		bdel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 自动生成的方法存根
				delCard();
			}
			
		});
		
		
		
	}
	public void showCard(PersonInfo p){
		if(p!=null){
			t1.setText(p.getSname());
			t2.setText(p.getSex());
			t3.setText(p.getpQ());
			t4.setText(p.getCompany());
			t5.setText(p.getPhone());
			t6.setText(p.getWeixin());
			t7.setText(p.getZhiwei());		
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("名片管理");
		f.getContentPane().add(new CardMainPanel());
		f.setSize(600,450);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter(){
			public void windouClosing(WindowEvent e){
				f.dispose();			
			}
		});
		
	}

}
