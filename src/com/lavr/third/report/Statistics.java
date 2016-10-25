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
        report.add("\n");
    }

    public static void addUltimateMoneyReport(int money[], int numberOfParticipants) {
        for(int i=0;i<numberOfParticipants;i++){
            report.add("Participant's #"+i+ " ultimate capital: "+money[i]);
        }
    }

    public static void participantBet(long id, int price){
        System.out.println("Participant " + id + " : " + price);
    }

    public static void participantSkips(long id, int money) {
        System.out.println("Participant " + id + " skips, not enough money :" + money);
    }

    public static void participantWon(long id, int price) {
        System.out.println("Participant #" + id + ", price:" + price + " win ");
    }

    public static void unclaimedLot(long lotId) {
        System.out.println("Lot"+lotId+" is not in demand");
    }

    public static void biddingStart(long lotId) {
        System.out.print("\n Bidding runs. Lot " + lotId);
    }

    public static void biddingStartPrice(long lotPrice) {
        System.out.println(". Starting price : "+ lotPrice);
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
