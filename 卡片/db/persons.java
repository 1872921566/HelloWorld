package db;
import java.io.*;
//���������û���ҵ�񣺵�¼��ע��
import java.sql.*;
import java.util.ArrayList;


public class persons  extends DB{
	public int  usermenu(){ //
		BufferedReader br=new BufferedReader(
    	    		 new InputStreamReader(System.in));  
		try{
		    System.out.println("******************");
		     System.out.println("1��¼����Ƭ");
		     System.out.println("2���޸���Ƭ");			      
		     System.out.println("3�� ɾ����Ƭ");
		     System.out.println("4�� ��ѯ��Ƭ");
		     String r=br.readLine();
		     //��֤�û�ֻ������1-4
		     int result=Integer.parseInt(r);
		     if(result<1 ||result>4){
		    	 return 0;//����������ֵ ����0
		     }
		     return result;//������ȷ��ѡ��
		}catch(Exception ex){
			//ex.printStackTrace();
			return 0;//������� ����0
		}		     
 }	 
   
	//-----------¼����Ƭ

	public boolean add(PersonInfo temp){
		   String sql="insert into person (sid,sname,sex,company,zhiwei,phone,pQ,weixin)values('"+temp.getSid()+"','"
				   +temp.getSname()+"','"
				   +temp.getSex()+"','"
				   +temp.getCompany()+"','"
				   +temp.getZhiwei()+"','"
				   +temp.getPhone()+"','"
				   +temp.getpQ()+"','"
				   +temp.getWeixin()+"');";	                                 ;
		   
		   return super.executeUpdate(sql)  ;
	   }
	//-----------�޸���Ƭ
	public boolean change(PersonInfo temp) {
		  String sql="update person set sex='"+temp.getSex()+"',"+
				 " sname='"+temp.getSname()+"',"+
				  "company='"+temp.getCompany()+"',"+
				  "zhiwei='"+temp.getZhiwei()+"',"+
				  "phone='"+temp.getPhone()+"',"+
				  "pQ='"+temp.getpQ()+"',"+
				  "weixin='"+temp.getWeixin()+"';";	  			
		  
		   return super.executeUpdate(sql) ;
	}
	//-----------ɾ����Ƭ
	public boolean delete(PersonInfo temp){
		  String sql="DELETE FROM person WHERE sid = '"+temp.getSid()+"';";		
			  
			   return super.executeUpdate(sql) ;
	}
	//-----------��ѯ��Ƭ
	  	public ArrayList<PersonInfo> getPerson(String tj){
	  		String sql="select * from person";
	  		if(tj.trim().length()>0)
	  			sql=sql+" where sname like '%"+tj+"%';";
	  		ArrayList<PersonInfo> bag = null;
	  		try{
	  			ResultSet rs= super.executeQuery(sql);
	  			if(rs.next())
	  			{
	  				bag = new ArrayList<PersonInfo>();
	  				do{
	  					String  sid = rs.getString("sid");
	  					String sname=rs.getString("sname");
	  					String sex = rs.getString("sex");
	  					String company = rs.getString("company");
	  					String zhiwei = rs.getString("zhiwei");
	  					String phone = rs.getString("phone");
	  					String zhiwei1 = rs.getString("zhiwei");
	  					String pQ = rs.getString("pQ");
	  					String weixin = rs.getString("weixin");
	  					PersonInfo temp = new PersonInfo(sid,sname,sex,company,zhiwei1,phone,pQ,weixin);
	  					bag.add(temp);
	  				}while(rs.next());
	  			}
	  			return bag;
	  		}
	  		catch(Exception ex){
	  			ex.printStackTrace();
	  		}
			return bag;
	
	  		
	  	}
	//-----------��ѯ��Ƭ
		public PersonInfo getPersons(String sid){
			//����sql���
			String sql="select * from person where sid='"+sid+"';";
			System.out.println(sql);
			PersonInfo temp=null;
			try{
				ResultSet rs=super.executeQuery(sql);//ִ�в�ѯ�õ����
				//����鵽�ˣ�˵����¼�ɹ���ȡ���û���ȫ����Ϣ�洢��һ��UserInfo������ ����
				if(rs.next()){
				    int id=rs.getInt("sid"); 
				    String sname=rs.getString("sname");
				    String sex =rs.getString("sex");
				    String phone =rs.getString("phone");
				    temp=new PersonInfo();
				    temp.setSname(sname);
				    temp.setSex(sex);
				    temp.setPhone(phone);			   
				}
				return temp;
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}		
		}
	
	public static void main(String args[]){
		persons m=new persons();//������һ������ 	
	    	 while(true){
		    	  int result=m.usermenu()  ;
		    	  if(result==0){
		    		  System.out.println("������������������ѡ��");
		    	   }
		    	  else if(result==4){
		    		  BufferedReader br=new BufferedReader(
			     	    		 new InputStreamReader(System.in));  
					 		try{
					 		     System.out.println("��������Ҫ��ѯ��id");			 	
					 		     String sid=br.readLine();
					 		    PersonInfo temp=m.getPersons(sid);
					 		     System.out.println(temp);
					 		}catch(Exception ex){
					 			ex.printStackTrace();				 		
					 		}	
		    	  }
		    	  else if(result==3){
		    			BufferedReader br=new BufferedReader(
			     	    		 new InputStreamReader(System.in));  
					 		try{
					 		     System.out.println("��������Ҫɾ����id");			 	
					 		     String sid=br.readLine();  
					 		     PersonInfo temp = new PersonInfo(); 
					 		     temp.setSid(sid); 
					 		     m.delete(temp);
					 		}catch(Exception ex){
					 			ex.printStackTrace();				 		
					 		}	
		    		  
		    	  }else if(result==1){ 		
		    		  //ʵ��ע��==�����û������Ҫע�����Ϣ������Ϊ��������ɢ����Ϣת��һ���û����󣬵���register����ʵ��ע��
		    		  	BufferedReader br=new BufferedReader(
		     	    		 new InputStreamReader(System.in));  
				 		try{
				 		     System.out.println("���������id");				 	
				 		     String sid=br.readLine();
				 		     System.out.println("�������������");				 	
				 		     String sname=br.readLine();
				 		     System.out.println("����������Ա�");				 	
				 		     String sex=br.readLine();
				 		     PersonInfo temp=new PersonInfo();
				 		     temp.setSid(sid);
				 		     temp.setSname(sname);
				 		     temp.setSex(sex);
				 		     if(m.add(temp)){
				 		    	System.out.println("ע��ɹ�");
				 		     }else{
				 		    	System.out.println("ע��ʧ��");
				 		     }
				 		}catch(Exception ex){
				 			ex.printStackTrace();				 		
				 		}					 		
		    	  }else if(result==2){
		    		//ʵ�ֵ�¼ ==�����û�������˺ź����룬����login����ʵ�ֵ�¼
		    		  	BufferedReader br=new BufferedReader(
		     	    		 new InputStreamReader(System.in));  
				 		try{
				 		     System.out.println("��������Ҫ�޸ĵ�id");			 	
				 		     String sid=br.readLine();  
				 		     System.out.println("�����޸�Ϊ��");				 	
				 		     String sname=br.readLine();
				 		     PersonInfo temp = new PersonInfo(); 
				 		     temp.setSid(sid);
				 		     temp.setSname(sname);
				 		     m.change(temp);
				 		}catch(Exception ex){
				 			ex.printStackTrace();				 		
				 		}	
		    	  }
	    	   }    
	    	
	   
	 }
}
