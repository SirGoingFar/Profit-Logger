package profit.logger.login;

import java.sql.*;

public class loginValidation{

    private String Username;
    private String Password;
    private Connection connection;
    
    public loginValidation(){
      try{
            connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/profitlogger", "root", "root");
         }catch(Exception e){
            e.printStackTrace();
         }
    }  
    
    protected Boolean validate(String username, String password){
        
        //this become TRUE if USERNAME is present and corresponds to the PASSWORD - FINAL VERDICT: TRUE means allow user to proceed
        Boolean approved = false;
        
        Boolean usernamePresent = false; //becomes TRUE if USERNAME is present
        Boolean correctPassword = false; //becomes TRUE if PASSWORD corresponds to the USERNAME that's available
        
        String usernameHolder; 
        String passwordHolder; 
        
        try{
            
            Statement st = connection.createStatement();
            ResultSet result1 = st.executeQuery("SELECT * FROM profitlogger.login");
            
            //iterate through the USERNAME column on DB
            while(result1.next() && usernamePresent == false){ 

               usernameHolder = result1.getString("username");

                    if(username.equals(usernameHolder)){
                        usernamePresent = true;
                    }
                   
                        //pop up an alert box to notify that: USERNAME NOT FOUND
                        if(result1.isLast() && usernamePresent==false)
                        profit.logger.utility.currentStatus.display("Username Error", String.format("Username (%s) not found!", username));
            }
            
            
            ResultSet result2 = st.executeQuery("SELECT * FROM profitlogger.login WHERE username='"+ username +"'");
            
            if(usernamePresent==true){
                
                while(result2.next() && correctPassword==false){
                    
                    passwordHolder = result2.getString("password");
                        
                    if(password.equals(passwordHolder))
                            correctPassword = true;

                        //pop up an alert box to notify that: USERNAME AND PASSWORD MISMATCH
                        if(result2.isLast() && correctPassword==false)
                        profit.logger.utility.currentStatus.display("Login Error", "Username and Password mismatch");
                }
                
                //set variable Boolean approve's value
                if(usernamePresent==true && correctPassword==true)
                    approved = true;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }

        return approved;
    }
    
    protected Boolean passwordResetting(String username, String oldPassword, String newPassword, String confirmNewPassword){
 
        
        //validate USERNAME if it exists in the DB and if the PASSWORD corresponds
        Boolean status = false; //becomes TRUE if the DB was updated successfully
        Boolean usernamePresent = false; //becomes TRUE if USERNAME is present
        Boolean correctPassword = false; //becomes TRUE if PASSWORD corresponds to the USERNAME that's available
        Boolean new_Confirm_Match = false; //becomes TRUE if NEW PASSWORD corresponds to the CONFIRM NEW PASSWORD 
        
        String usernameHolder; 
        String passwordHolder; 
        
        try{
            
            Statement st = connection.createStatement();
            ResultSet result1 = st.executeQuery("SELECT * FROM profitlogger.login");
            
            //iterate through the USERNAME column on DB
            while(result1.next() && usernamePresent == false){ 

               usernameHolder = result1.getString("username");

                    if(username.equals(usernameHolder)){
                        usernamePresent = true;
                    }
                    
                        //pop up an alert box to notify that: USERNAME NOT FOUND
                        if(result1.isLast() && usernamePresent==false)
                        profit.logger.utility.currentStatus.display("Username Error", String.format("Username (%s) does not exist!", username));
            }
            
            
            ResultSet result2 = st.executeQuery("SELECT * FROM profitlogger.login WHERE username='"+ username +"'");
            
            if(usernamePresent==true){
                
                while(result2.next() && correctPassword==false){
                    
                    passwordHolder = result2.getString("password");
                        
                    if(oldPassword.equals(passwordHolder))
                            correctPassword = true;

                        //pop up an alert box to notify that: USERNAME AND PASSWORD MISMATCH
                        if(result2.isLast() && correctPassword==false)
                        profit.logger.utility.currentStatus.display("Mismatch", "Username and Old Password do not mismatch");
                }
                
        
            //find out if the NEW PASSWORD corresponds with the CONFIRM NEW PASSWORD
                if(newPassword.equals(confirmNewPassword))
                    new_Confirm_Match = true;
                
                else
                        if(correctPassword == true)
                    profit.logger.utility.currentStatus.display("Mismatch", "New Password and Confirm New Password fields do not match.");
                
                //THEN update the database using the retrieved USERNAME and PASSWORD
                if(usernamePresent==true && correctPassword==true && new_Confirm_Match){
                    //OLD PASSWORD must not be the same as the NEW PASSWORD
                    if(oldPassword.equals(newPassword)){
                        profit.logger.utility.currentStatus.display("Not allowed", "Old Password and New Password must not be the same.");
                    }
                    
                    else{
                        try{
                            //update DB
                            st.executeUpdate("UPDATE login SET password = '"+ newPassword +"' WHERE username = '"+ username +"'");
                            //set status as TRUE, i.e. UPDATE done succesfully
                            status = true;

                        }catch(SQLException e){}
                    }
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return status;
    }
        
        
}
