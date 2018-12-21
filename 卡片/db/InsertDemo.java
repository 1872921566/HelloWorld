package db;
import java.sql.*;//导入数据库连接类库
public class InsertDemo extends DB {   
	 public boolean register(){
	   //定义要执行的sql命令
	   String sql="insert into vips   values ('004','lily','女','123456','2017-1-1');";
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
