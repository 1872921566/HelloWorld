package db;
import java.io.*;
//用来处理用户的业务：登录和注册
import java.sql.*;
import java.util.ArrayList;


public class persons  extends DB{
	public int  usermenu(){ //
		BufferedReader br=new BufferedReader(
    	    		 new InputStreamReader(System.in));  
		try{
		    System.out.println("******************");
		     System.out.println("1、录入名片");
		     System.out.println("2、修改名片");			      
		     System.out.println("3、 删除名片");
		     System.out.println("4、 查询名片");
		     String r=br.readLine();
		     //验证用户只能输入1-4
		     int result=Integer.parseInt(r);
		     if(result<1 ||result>4){
		    	 return 0;//输入其他的值 返回0
		     }
		     return result;//返回正确的选择
		}catch(Exception ex){
			//ex.printStackTrace();
			return 0;//如果出错 返回0
		}		     
 }	 
   
	//-----------录入名片

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
	//-----------修改名片
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
	//-----------删除名片
	public boolean delete(PersonInfo temp){
		  String sql="DELETE FROM person WHERE sid = '"+temp.getSid()+"';";		
			  
			   return super.executeUpdate(sql) ;
	}
	//-----------查询名片
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
	//-----------查询名片
		public PersonInfo getPersons(String sid){
			//构建sql语句
			String sql="select * from person where sid='"+sid+"';";
			System.out.println(sql);
			PersonInfo temp=null;
			try{
				ResultSet rs=super.executeQuery(sql);//执行查询得到结果
				//如果查到了，说明登录成功，取出用户的全部信息存储在一个UserInfo对象中 返回
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
		persons m=new persons();//定义了一个对象 	
	    	 while(true){
		    	  int result=m.usermenu()  ;
		    	  if(result==0){
		    		  System.out.println("您的输入有误，请重新选择！");
		    	   }
		    	  else if(result==4){
		    		  BufferedReader br=new BufferedReader(
			     	    		 new InputStreamReader(System.in));  
					 		try{
					 		     System.out.println("请输入你要查询的id");			 	
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
					 		     System.out.println("请输入你要删除的id");			 	
					 		     String sid=br.readLine();  
					 		     PersonInfo temp = new PersonInfo(); 
					 		     temp.setSid(sid); 
					 		     m.delete(temp);
					 		}catch(Exception ex){
					 			ex.printStackTrace();				 		
					 		}	
		    		  
		    	  }else if(result==1){ 		
		    		  //实现注册==接受用户输入的要注册的信息，化零为整，把零散的信息转入一个用户对象，调用register方法实现注册
		    		  	BufferedReader br=new BufferedReader(
		     	    		 new InputStreamReader(System.in));  
				 		try{
				 		     System.out.println("请输入你的id");				 	
				 		     String sid=br.readLine();
				 		     System.out.println("请输入你的姓名");				 	
				 		     String sname=br.readLine();
				 		     System.out.println("请输入你的性别");				 	
				 		     String sex=br.readLine();
				 		     PersonInfo temp=new PersonInfo();
				 		     temp.setSid(sid);
				 		     temp.setSname(sname);
				 		     temp.setSex(sex);
				 		     if(m.add(temp)){
				 		    	System.out.println("注册成功");
				 		     }else{
				 		    	System.out.println("注册失败");
				 		     }
				 		}catch(Exception ex){
				 			ex.printStackTrace();				 		
				 		}					 		
		    	  }else if(result==2){
		    		//实现登录 ==接受用户输入的账号和密码，调用login方法实现登录
		    		  	BufferedReader br=new BufferedReader(
		     	    		 new InputStreamReader(System.in));  
				 		try{
				 		     System.out.println("请输入你要修改的id");			 	
				 		     String sid=br.readLine();  
				 		     System.out.println("名字修改为：");				 	
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
