package me.pedro2091.banplugin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormat {

    public static String format(String[] banTime) throws NumberFormatException{

        String hour = null;
        int hourValue = 0;
        String minute = null;
        int minuteValue = 0;
        String second = null;
        int secondValue = 0;

        LocalDateTime data;


        for(int i=1; i<banTime.length; i++) {
            //if hour specified
            if (banTime[i].endsWith("h")) {
                //replace the character "h" for a space then remove the spaces
                hour = banTime[i].replace('h',' ').trim();
                //transform the string in a Integer
                hourValue = Integer.parseInt(hour);

            //if minute specified
            }else if (banTime[i].endsWith("m")) {
                //replace the character "m" for a space then remove the spaces
                minute = banTime[i].replace('m',' ').trim();
                //transform the string in a Integer
                minuteValue = Integer.parseInt(minute);

            //if second specified
            }else if (banTime[i].endsWith("s")) {
                //replace the character "s" for a space then remove the spaces
                second = banTime[i].replace('s',' ').trim();
                //transform the string in a Integer
                secondValue = Integer.parseInt(second);

            //if time format not specified
            }else{
                //force a NumberFormatException
                throw new NumberFormatException();
            }

        }

        //LocalTime pattern (hhh:mmm:sss)
        data = LocalDateTime.now().plusHours(hourValue).plusMinutes(minuteValue).plusSeconds(secondValue);

        //return String format convert
        return data.format(DateTimeFormatter.ISO_DATE_TIME);

    }

}
