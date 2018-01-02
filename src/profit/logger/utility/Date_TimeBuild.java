package profit.logger.utility;

import java.util.*;

public class Date_TimeBuild{
    
    Calendar cal;
    
    int day, dayTag, month, year, s, m, h;

    public Date_TimeBuild() {
        cal = new GregorianCalendar();
        
        //date
        day = cal.get(Calendar.DAY_OF_MONTH);
        dayTag = cal.get(Calendar.DAY_OF_WEEK);
        month = cal.get(Calendar.MONTH)+ 1;
        year = cal.get(Calendar.YEAR);
        
        //time
        s = cal.get(Calendar.SECOND);
        m = cal.get(Calendar.MINUTE);
        h = cal.get(Calendar.HOUR);
    }

    public void converter(){
 
        //converter
        String monthName = null;
        String dayName = null;
        
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
/**
       You can then use String variables 'dayName' and 'monthName' instead of corresponding numbers 
  
        System.out.printf("Current Date: %s; %d %s, %d\n", dayName, day, monthName, year);
        System.out.printf("Current Time: %02d:%02d:%02d%s\n", h, m, s,(cal.get(Calendar.AM_PM))==0 ? "AM" : "PM");
//          cal.get(Calendar.AM_PM) returns '0' if AM and '1' if PM
*/
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getS() {
        return s;
    }

    public int getM() {
        return m;
    }

    public int getH() {
        return h;
    }
    
    
    @Override
    public String toString(){
        return String.format("%02d-%02d-%02d %02d:%02d:%02d", year, month, day, h, m, s);
    }
}
