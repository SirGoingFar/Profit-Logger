package profit.logger.stockrecord;

import java.util.regex.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;

public class Stock{

    String name, size, lastUpdated, qtyAddPieces, qtyAddCartons, currentSellingPrice;
    int qtyAvailable,ID;
    Pattern p;
    Matcher m;
    

    public Stock(){
        this.ID = 0;
        this.name = "";
        this.size = "";
        this.currentSellingPrice = "";
        this.qtyAvailable = 0;
        this.lastUpdated = "";
        this.qtyAddPieces = "";
        this.qtyAddCartons = "";
    }

    public Stock(int id, String name, String size, String currentSellingPrice, int qtyAvailable, String lastUpdated){
        setID(id);
        setName(name);
        setSize(size);
        setCurrentSellingPrice(currentSellingPrice);
        setQtyAvailable(qtyAvailable);
        setLastUpdated(lastUpdated);
    }
    
    public int getID() {
        return ID;
    }

    public Boolean setID(int id) {
        
        String value = String.format("%d",id); //converts the INT value to STRING
            
        p = Pattern.compile("[0-9]+");
        m = p.matcher(value);
        
            if(m.find() && m.group().equals(value)){
                this.ID = id;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("ID Error!"));
               return false;
            }
    }
    
    public String getName() {
        return name;
    }

    public Boolean setName(String name) {
        
//        p = Pattern.compile("[A-Za-z0-9]([A-Za-z0-9 ]-_./)*");
        p = Pattern.compile("[A-Za-z0-9][A-Za-z0-9 ]*[-_/.]*");
        m = p.matcher(name);
        
            if(m.find() && m.group().equals(name)){
                this.name = name;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Name Error!"));
               return false;
            }
    }

    public String getSize() {
        return size;
    }

    public Boolean setSize(String size) {
       
        p = Pattern.compile("[A-Z]([A-Za-z ])*");
        m = p.matcher(size);
        
            if(m.find() && m.group().equals(size)){
                this.size = size;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Size Error!"));
               return false;
            }
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Boolean setLastUpdated(String lastUpdated) {
        
         p = Pattern.compile("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]");
         m = p.matcher(lastUpdated);
        
            if(m.find() && m.group().equals(lastUpdated)){
                this.lastUpdated = lastUpdated;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Date Error!"));
               return false;
            }
    }

    public double getCurrentSellingPrice() {
        return Double.parseDouble(currentSellingPrice);
    }

    public Boolean setCurrentSellingPrice(String currentSellingPrice) {
        
//        String value = String.format("%.2f",currentSellingPrice); //converts the DOUBLE value to STRING
            
        p = Pattern.compile("[0-9]+.[0-9]+");
//        p = Pattern.compile("[0-9](.[0-9]*)?");
        m = p.matcher(currentSellingPrice);
        
            if(m.find() && m.group().equals(currentSellingPrice)){
                this.currentSellingPrice = currentSellingPrice;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Selling Price Error!"));
                return false;
            }
    }

    public int getQtyAvailable() {
        return qtyAvailable;
    }

    public Boolean setQtyAvailable(int qtyAvailable) {     //receives int
       
        String value = String.format("%d",qtyAvailable); //converts the INT value to STRING
            
        p = Pattern.compile("[0-9]+");
        m = p.matcher(value);
        
            if(m.find() && m.group().equals(value)){
                this.qtyAvailable = qtyAvailable;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Qty available Error!"));
               return false;
            }
    }
    
    public Boolean setQtyAvailable(String qtyAvailable) {     //receives String
      
        p = Pattern.compile("[0-9]+");
        m = p.matcher(qtyAvailable);
        
            if(m.find() && m.group().equals(qtyAvailable)){
                this.qtyAvailable = Integer.parseInt(qtyAvailable);
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Qty available Error!"));
               return false;
            }
    }

    
    public int getCalcQtyAddPieces() {
        return Integer.parseInt(qtyAddPieces);
    }
    
//    public String getqtyAddPieces() {
//        return qtyAddPieces;
//    }

    public Boolean setqtyAddPieces(String qtyAddPieces) {
        
        p = Pattern.compile("[0-9]+");
        m = p.matcher(qtyAddPieces);
        
            if(m.find() && m.group().equals(qtyAddPieces)){
                this.qtyAddPieces = qtyAddPieces;
                return true;
            }
            else{
//              send a warning alert using an ALERT DIALOG
//                profit.logger.utility.currentStatus.display("Input Error", String.format("Qty added {Pieces) Error!"));
               return false;
            }
    }
    
    public int getCalcQtyAddCartons() {
        return Integer.parseInt(qtyAddCartons);
    }
    
//    public String getqtyAddCartons() {
//        return qtyAddCartons;
//    }

    public Boolean setqtyAddCartons(String qtyAddCartons) {
        
        p = Pattern.compile("[0-9]+");
        m = p.matcher(qtyAddCartons);
       
            if(m.find() && m.group().equals(qtyAddCartons)){
                this.qtyAddCartons = qtyAddCartons;
                return true;
            }
            else{
                Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Carton Quantity Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Enter a valid carton quantity value");
                    alert.showAndWait();
               return false;
            }
    }
}