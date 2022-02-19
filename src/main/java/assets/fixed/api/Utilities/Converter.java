package assets.fixed.api.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
    
    public static String dateToMillis (String date){
        try{
            Date formattedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return String.valueOf(formattedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date millisToDate(String date){
        try{
            Date utilDate= new Date(Long.parseLong(date));
            return utilDate;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
