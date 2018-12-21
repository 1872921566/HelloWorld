package gui;
import java.awt.*;
import db.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import db.PersonInfo;
import db.persons;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CardQueryPanel extends JPanel{
	//�������
	
	CardMainPanel mainPanel ;
	JTextField txt;
	JButton b1;
	JPanel p1;
	//JPanel p2;
	JList<DefaultListModel<PersonInfo>> resultlist;
	JScrollPane sp;
	DefaultListModel<PersonInfo>  lisemode;
	//-------����
	public CardQueryPanel(CardMainPanel p) {
		this.mainPanel = p;
		init();
		// TODO �Զ����ɵĹ��캯�����
	}
	//-------����
	public CardQueryPanel(){
		init();
	}
	//�õ���ǰѡ����ϵ�˵���Ϣ
		public PersonInfo getCurrentPerson() {
			int index = resultlist.getSelectedIndex();
			PersonInfo p = null;
			if(index>0) {
				p = lisemode.getElementAt(index);
			}
			return p;
		}
	public void init(){
		lisemode = new DefaultListModel<PersonInfo>();
		txt = new JTextField(15);
		b1 = new JButton("��ѯ");
		p1 = new JPanel();
		//p2=new JPanel();
		resultlist =new JList(lisemode);
		sp =new JScrollPane();
		sp.setViewportView(resultlist);
		//-----����
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		//p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(txt);
		p1.add(b1);
		//p2.add(b2);
		//p2.add(b3);
		//p2.add(b4);
		this.setLayout(new BorderLayout());
		this.add("North",p1);
		this.add("Center",sp);
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				query(txt.getText().trim());
			}
		});
		//�����б��ĵ����¼�����
		resultlist.addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent e) {
				// TODO �Զ����ɵķ������
				int index = resultlist.getSelectedIndex();
				if(index>0) {
					PersonInfo p= lisemode.getElementAt(index);
					mainPanel.showCard(p);
				}
			}
			
		});
		
	}
	public void query(String txt){
		persons p = new persons();
		ArrayList<PersonInfo> bag = p.getPerson(txt);
		lisemode.clear();
		if(bag ==null){
			PersonInfo temp  =new PersonInfo();
			temp.setSname("û�з�������������");
			return;
		}
		Iterator<PersonInfo>i= bag.iterator();
		while(i.hasNext())
		{
			PersonInfo t =i.next();
			lisemode.addElement(t);	
		}	
	}
	public void setCurrentPerson(int index) {
		if(index>0||index>lisemode.size()-1) {
			resultlist.setSelectedIndex(lisemode.size()-1);
		}
		else {
			resultlist.setSelectedIndex(index);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //	CardQueryPanel temp =new CardQueryPanel(new CardMainPanel());
		/*
		 * JFrame f = new JFrame("��Ƭ��ѯ");
		
		CardQueryPanel cqp = new CardQueryPanel(new CardMainPanel());
		f.getContentPane().add(cqp);
		f.setSize(450,450);
		f.setVisible(true);
		 */
	}
	

}
