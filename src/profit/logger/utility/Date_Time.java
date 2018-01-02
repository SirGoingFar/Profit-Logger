package profit.logger.utility;

import java.util.*;

public class Date_Time{
 
        Calendar cal = new GregorianCalendar();

        int day;
        int dayTag;
        int month;
        int year;
        
        int s;
        int m;
        int h;
        
        //converter
        String monthName = null;
        String dayName = null;
        
        
    protected void currentDate(){
        
        //date
        day = cal.get(Calendar.DAY_OF_MONTH);
        dayTag = cal.get(Calendar.DAY_OF_WEEK);
        month = cal.get(Calendar.MONTH)+ 1;
        year = cal.get(Calendar.YEAR);
        
        //converter
        monthName = null;
        dayName = null;
        
        switch(month){
            case 1: 
                monthName = "January";
                break;
            case 2: 
                monthName = "February";
                break;
            case 3: 
                monthName = "March";
                break;
            case 4: 
                monthName = "April";
                break;
            case 5: 
                monthName = "May";
                break;
            case 6: 
                monthName = "June";
                break;
            case 7: 
                monthName = "July";
                break;
            case 8: 
                monthName = "August";
                break;
            case 9: 
                monthName = "September";
                break;
            case 10: 
                monthName = "October";
                break;
            case 11: 
                monthName = "November";
                break;
            case 12: 
                monthName = "December";
                break;
            default: 
                break;
        }
        
        switch(dayTag){
            case 1: 
                dayName = "Sunday";
                break;
            case 2: 
                dayName = "Monday";
                break;
            case 3: 
                dayName = "Tuesday";
                break;
            case 4: 
                dayName = "Wednesday";
                break;
            case 5: 
                dayName = "Thursday";
                break;
            case 6: 
                dayName = "Friday";
                break;
            case 7: 
                dayName = "Saturday";
                break;
            default: 
                break;
        }
    }
    
    protected void currentTime(){
        
        //time
        s = cal.get(Calendar.SECOND);
        m = cal.get(Calendar.MINUTE);
        h = cal.get(Calendar.HOUR);
    }
    
    @Override
    public String toString(){
       return String.format("%02d/%02d/%02d, %02d:%02d:%02d", day, month, year, h, m, s);
    }
}
