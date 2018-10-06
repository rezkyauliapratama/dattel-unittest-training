package android.rezkyauliapratama.com.unittesttraining.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtil  {
    public TimeUtil() {
    }

    public String getUserFriendlyDate(Date date){
        return new SimpleDateFormat("dd MMMM yyyy",Locale.getDefault()).format(date);
    }

    public Date convertStringToDate(String str){

        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
