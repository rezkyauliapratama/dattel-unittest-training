package android.rezkyauliapratama.com.unittesttraining.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

    public class TimeUtil  {
        private final SimpleDateFormat simpleDateFormat;

        public TimeUtil(SimpleDateFormat simpleDateFormat) {
            this.simpleDateFormat = simpleDateFormat;
        }

        public String getUserFriendlyDate(Date date){
//            return new SimpleDateFormat("dd MMMM yyyy",Locale.getDefault()).format(date);
            return simpleDateFormat.format(date);
        }

        public Date convertStringToDate(String str){

            try {
//                return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
                return simpleDateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
