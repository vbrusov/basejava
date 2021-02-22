package com.basejava.webapp;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class MainDate {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(System.currentTimeMillis() - date.getTime());

        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("\n" + ld);
        System.out.println(lt);
        System.out.println(ldt);

        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
        System.out.println("\n" + sdf.format(date));
    }
}
