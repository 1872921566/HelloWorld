package db;
import java.sql.*;//�������ݿ��������
public class InsertDemo extends DB {   
	 public boolean register(){
	   //����Ҫִ�е�sql����
	   String sql="insert into vips   values ('004','lily','Ů','123456','2017-1-1');";
	   return super.executeUpdate(sql)  ;
   }
	public static void main(String args[]){
		if(new InsertDemo().register() ){
			System.out.println("sucess");
		}else{
			System.out.println("fail");
		}
	}
}
