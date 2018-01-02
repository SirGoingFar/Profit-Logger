
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import profit.logger.stockrecord.Stock;

public class Tester{
    
    static Connection connection;
    
   
    public static void main(String args[]){
    
        try{
            connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        int id = 0;
        String name;
        String sizeFinal;
        int sizeInitial;
        double currentSellingPrice;
        int qtyAvailable;
        String lastUpdated;
   
        try{
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM profitlogger.AAA");
            
            /**
            while(result.next()){
            
                //DB values
                id = id + 1;
                
                name = result.getString("name");
                
                sizeInitial = result.getInt("size");
                    switch(sizeInitial){
                        case 1: 
                            sizeFinal = "Small Size";
                        case 2: 
                            sizeFinal = "Medium Size";
                        case 3: 
                            sizeFinal = "Small Size";
                        case 4:
                            sizeFinal = "Big Size";
                        case 5:
                            sizeFinal = "Extra Big Size";
                        default: 
                            sizeFinal = "No Variation";
                    }
                    
                currentSellingPrice = result.getDouble("currentSellingPrice");
                
                qtyAvailable = result.getInt("qtyAvailable");
                
                lastUpdated = result.getString("lastUpdated");
               
                System.out.printf("%d %s %s %d %d %s", id, name, sizeFinal, currentSellingPrice, qtyAvailable, lastUpdated);
            }
            */
            
            while(result.next()){
//                System.out.printf("%d %s", result.getInt("id"), result.getString("name"));
                System.out.printf("%d %s %d %.2f %d %s\n", result.getInt("id"), result.getString("name"),result.getInt("size"),result.getDouble("currentSellingPrice"),result.getInt("qtyAvailable"),result.getString("lastUpdated"));
            }
        }catch(SQLException e){}
        
            
    }

}