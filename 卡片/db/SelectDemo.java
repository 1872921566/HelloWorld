package db;
import java.sql.*;//导入数据库连接类库
public class SelectDemo extends DB{
	public void getBookById(String id){
		  
		   String sql="select * from books where  图书编号 ='"+id+"';";
		   System.out.println(sql);
		   //利用类库里的方法访问数据库
		   try{
			   //this
			   ResultSet rs=super.executeQuery(sql);//执行select语句，得到一个结果集
			   if(rs.next()){
				   //逐行取出字段的值
				   String bookname=rs.getString("书名");
				   String pubdate=rs.getString("出版时间");
				   float price=rs.getFloat("单价");
				   int num=rs.getInt("库存");
				   System.out.println(bookname+","+pubdate+","+price+","+num);
			   }		  
		   }catch(Exception ex){
			   ex.printStackTrace();
		   }	   
	   }
   public void getBooks(String tiaojian){
	  
	   String sql="select * from books where  书名 like '%"+tiaojian+"%'  ;";
	   //利用类库里的方法访问数据库
	   try{
		   //this
		   ResultSet rs=super.executeQuery(sql);//执行select语句，得到一个结果集
		   while(rs.next()){
			   //逐行取出字段的值
			   String bookname=rs.getString("书名");
			   String pubdate=rs.getString("出版时间");
			   float price=rs.getFloat("单价");
			   int num=rs.getInt("库存");
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
