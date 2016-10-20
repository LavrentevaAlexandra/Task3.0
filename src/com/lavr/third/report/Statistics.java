package com.lavr.third.report;

import java.util.ArrayList;

/**
 * Created by 123 on 18.10.2016.
 */
public class Statistics {
    private static ArrayList<String> report=new ArrayList<>();


    public static void addInitialMoneyReport(int money[], int numberOfParticipants) {
        for(int i=0;i<numberOfParticipants;i++){
            report.add("Participant's #"+i+ " initial capital: "+money[i]);
        }
    }

    public static void addUltimateMoneyReport(int money[], int numberOfParticipants) {
        for(int i=0;i<numberOfParticipants;i++){
            report.add("Participant's #"+i+ " ultimate capital: "+money[i]);
        }
    }

    public void showStatistics(){
        showLine();
        System.out.println("Auction statistics:");
        showLine();
        report.forEach(System.out::println);
    }
    
    private void showLine(){
        for(int i=0;i<60;i++){
            System.out.print("-");
        }
        System.out.println();
    }
}
