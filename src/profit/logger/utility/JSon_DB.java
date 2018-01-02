package profit.logger.utility;

import com.google.gson.Gson;
import java.sql.*;

public class JSon_DB{
    
    static Connection connection;

    public static void main(String args[]){
    
        Date_TimeBuild date = new Date_TimeBuild();
        
        Gson gson = new Gson();
        String dateRep = gson.toJson(date);
            System.out.println(dateRep);
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
         }catch(SQLException e){
            e.printStackTrace();
         }
        
        String Query = String.format("INSERT INTO profitlogger.json(name,DOB,DOB_obj) VALUES('Feyikemi','"+date.toString()+"','"+dateRep+"')");

        try{
        Statement statement = connection.createStatement();
//        statement.executeUpdate(Query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}