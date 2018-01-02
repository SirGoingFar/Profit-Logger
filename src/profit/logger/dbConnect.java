package profit.logger;

import java.sql.*;

public class dbConnect{

    private Connection connection;
    
  public dbConnect(){
      try{
            connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
         }catch(Exception e){
            e.printStackTrace();
         }
  }  
}