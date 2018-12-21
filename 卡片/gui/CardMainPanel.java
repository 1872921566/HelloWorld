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
	//����������������
	public boolean flag = false;//״̬  �����أ�
	JLabel l1;JTextField t1;JLabel l2;JTextField t2;
	JLabel l3;JTextField t3;JLabel l4;JTextField t4;
	JLabel l5;JTextField t5;JLabel l6;JTextField t6;
	JLabel l7;JTextField t7;JButton badd,bsave,bdel;
	JPanel p1,p2,p3;  CardQueryPanel cardquery;
	//�޲ι��캯��
	public CardMainPanel(){
		init();
	}
	//delCard����
	public void delCard() {
		flag = false;
		PersonInfo p = cardquery.getCurrentPerson();
		String sname = p.getSname();
		if(p==null){
			JOptionPane.showMessageDialog(null,"�����б���ѡ��Ҫ�޸ĵ���ϵ��");
			return ;
		}
		if(new persons().delete(p)){
			JOptionPane.showMessageDialog(null,"�ɹ�ɾ����"+sname+"����Ƭ��Ϣ");
			cardquery.query("");
			cardquery.setCurrentPerson(0);
		}else {
			JOptionPane.showMessageDialog(null,"ɾ��"+sname+"ʧ��");
		}

	}
	//newCard����
	public void newCard() {
		String sname = t1.getText();
		if(sname.length()<1) {
			JOptionPane.showMessageDialog(null,"��ϵ����������Ϊ��");
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
			JOptionPane.showMessageDialog(null,"�ɹ������"+sname+"����Ƭ��Ϣ");
			cardquery.query("");
			cardquery.setCurrentPerson(-1);
		}
		else {
			JOptionPane.showMessageDialog(null,"��ϵ��"+sname+"����Ƭ��Ϣ���ʧ��");
		}
	}
	//UpdateCard����  �޸ķ���
	public void UpdateCard() {
		flag = false;
		PersonInfo p = cardquery.getCurrentPerson();
		if(p==null) {
			JOptionPane.showMessageDialog(null,"�����б���ѡ��Ҫ�޸ĵ���ϵ��");
			return ;
		}
		if(p.getSid()=="0") {
			JOptionPane.showMessageDialog(null,"��Ƭ���Ѿ�����");
			return ;
		}
		String sname = t1.getText();
		if(sname.length()<1) {
			JOptionPane.showMessageDialog(null,"��ϵ����������Ϊ��");
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
			JOptionPane.showMessageDialog(null,"�ɹ��޸���"+sname+"����Ƭ��Ϣ");
		}
		else {
			JOptionPane.showMessageDialog(null,"��ϵ��"+sname+"����Ƭ��Ϣ�޸�ʧ��");
		}
	}
	//׼�����    ����������Ӱ�ťʱ
	public void prepareAdd() {
		flag = true;
		t1.setText("");t2.setText("");t3.setText("");t4.setText("");
		t5.setText("");t6.setText("");t7.setText("");	
	}
	public void init(){
		l1 = new JLabel("����",JLabel.CENTER);t1 = new JTextField(20);
		l2 = new JLabel("�Ա�",JLabel.CENTER);t2 = new JTextField(20);
		l3 = new JLabel("QQ",JLabel.CENTER);t3 = new JTextField(20);
		l4 = new JLabel("��˾",JLabel.CENTER);t4 = new JTextField(20);
		l5 = new JLabel("�绰",JLabel.CENTER);t5 = new JTextField(20);
		l6 = new JLabel("΢��",JLabel.CENTER);t6 = new JTextField(20);
		l7 = new JLabel("ְλ",JLabel.CENTER);t7 = new JTextField(20);
		badd = new JButton("���");bsave = new JButton("����");bdel = new JButton("ɾ��");
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
		//---------��Ӱ�ť���¼�����
		badd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				prepareAdd();
			}
			
		});
		//---------�޸İ�ť���¼�����
		bsave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if(flag==true)newCard();
				else UpdateCard();	
			}
			
		});
		//---------ɾ����ť���¼�����
		bdel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
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
		JFrame f = new JFrame("��Ƭ����");
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
