package db;
import java.sql.*;//�������ݿ��������
public class SelectDemo extends DB{
	public void getBookById(String id){
		  
		   String sql="select * from books where  ͼ���� ='"+id+"';";
		   System.out.println(sql);
		   //���������ķ����������ݿ�
		   try{
			   //this
			   ResultSet rs=super.executeQuery(sql);//ִ��select��䣬�õ�һ�������
			   if(rs.next()){
				   //����ȡ���ֶε�ֵ
				   String bookname=rs.getString("����");
				   String pubdate=rs.getString("����ʱ��");
				   float price=rs.getFloat("����");
				   int num=rs.getInt("���");
				   System.out.println(bookname+","+pubdate+","+price+","+num);
			   }		  
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }	   
	   }
   public void getBooks(String tiaojian){
	  
	   String sql="select * from books where  ���� like '%"+tiaojian+"%'  ;";
	   //���������ķ����������ݿ�
	   try{
		   //this
		   ResultSet rs=super.executeQuery(sql);//ִ��select��䣬�õ�һ�������
		   while(rs.next()){
			   //����ȡ���ֶε�ֵ
			   String bookname=rs.getString("����");
			   String pubdate=rs.getString("����ʱ��");
			   float price=rs.getFloat("����");
			   int num=rs.getInt("���");
			   System.out.println(bookname+","+pubdate+","+price+","+num);
		   }		  
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }	   
   }
   public static void main(String args[]){
	   new SelectDemo().getBookById("tp002");
   }
}
