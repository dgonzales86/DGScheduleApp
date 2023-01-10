package util;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.time.*;

public class TimeConversion {

    public static LocalDate myLD = LocalDate.of(2023,02,13);
    public static LocalTime myLT = LocalTime.of(22,0);
    public static LocalDateTime myLDT = LocalDateTime.of(myLD,myLT);
    public static ZoneId myZoneId = ZoneId.systemDefault();
    public static ZonedDateTime myZDT = ZonedDateTime.of(myLDT,myZoneId);

    public static void print(){
        System.out.println(myZDT);
    }



}
