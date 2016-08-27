package DataBaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class dbConnection {
	 static String bd = "registros";
	 static String user = "root";
	 static String password = "";
	 static String url = "jdbc:mysql://localhost/"+bd;
	 
	   Connection connection = null;
	 
public dbConnection() {
    try{
       
       Class.forName("com.mysql.jdbc.Driver");
       
       connection = DriverManager.getConnection(url,user,password);

       if (connection!=null){
          System.out.println("Conexión a base de datos "+bd+" OK\n");
       }
    }
    catch(SQLException e){
       System.out.println(e);
    }catch(ClassNotFoundException e){
       System.out.println(e);
    }catch(Exception e){
       System.out.println(e);
    }
 }
 
 public Connection getConnection(){
    return connection;
 }

 public void disconnect(){
    connection = null;
 }
}

