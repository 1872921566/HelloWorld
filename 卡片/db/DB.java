package db;
import java.sql.*;
public class DB{
        private Connection conn;
        private Statement stmt;
        private String driver;
        private String url;
        private String username;
        private String password;       
        public DB(){
                driver=	"org.gjt.mm.mysql.Driver";
                url="jdbc:mysql://localhost/bookstore";
               
                username="root";
                password="123456";
                conn=null;
                stmt=null;
        }
        public DB(String driver,String url,String username,String password){
                this.driver=driver;
                this.url=url;
                this.username=username;
                this.password=password;
                conn=null;
                stmt=null;
        }        
        public void setDriver(String driver){this.driver=driver;}
        public void setUrl(String url){this.url=url;}
        public void setUsername(String username){this.username=username;}
        public void setPassword(String password){this.password=password;}
        public String getDriver(){return this.driver;}
        public String getUrl(){return this.url;}
        public String  getUsername(){return this.username;}
        public String getPassword(){return this.password;}
        //打开连接
        public void getConnection(){
                try{
                        Class.forName(driver).newInstance();
                        System.out.println("准备创建连接");
                        conn=DriverManager.getConnection(url,username,password);
                        
                }catch(Exception e){
                	    System.out.println("创建连接出错"+e.getMessage());
                        e.printStackTrace();
                }
        }
        //关闭连接
        public void closeConnection(){
                try{
                        if(conn!=null){
                                conn.close();
                        }
                }catch(Exception e){
                        System.out.println(e.getMessage());
                }
        }
       //执行查询
        public ResultSet executeQuery(String sql){
                ResultSet rs=null;
                try{
                        if (conn==null)
                                getConnection();
                       // System.out.println("DB3.executeQuery:"+sql);
                        stmt=conn.createStatement();
                        
                        rs=stmt.executeQuery(sql);
                        return rs;
                }catch(Exception e){
                        System.out.println("DB3.executeQuery出错了："+e.getMessage());
                        return null;
                }
                
                
        }
        //执行单个更新
        public boolean executeUpdate(String sql){                
                try{
                        getConnection();
                        if(conn!=null){
                                stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                                stmt.executeUpdate(sql);
                                return true;
                        }
                        return false;
                }catch(Exception e){ e.printStackTrace(); return false;}                
        }
        //执行批量处理的更新语句
        public boolean executeUpdate(String[] sql){                
                try{  
                	    if (conn==null)
                                getConnection();
                	    conn.setAutoCommit(false);
                        conn.setSavepoint();
                         stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                         for(int i=0;i<sql.length;i++){
                            stmt.addBatch(sql[i]);
                            System.out.println(sql[i]);
                         }
                         stmt.executeBatch();   
                        conn.commit();
                         return true;
                }catch(Exception e){
	                e.printStackTrace();
	                try{conn.rollback();}catch(Exception ex){ex.printStackTrace();}
	                return false;
                }
        }

}
