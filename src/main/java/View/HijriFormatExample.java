package View;

import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public  class HijriFormatExample {

    public static String getHijrahDate(){
        HijrahDate hijri = HijrahDate.now();

        DateTimeFormatter formatter =
                DateTimeFormatter
                .ofPattern("dd  MMMM  yyyyG ", Locale.forLanguageTag("ar"));

        String formatted = formatter.format(hijri);

        return formatted;
    }

    public static void main(String[] args) {

        System.out.println(getHijrahDate());
    }
}



