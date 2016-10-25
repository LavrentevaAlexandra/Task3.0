package com.lavr.third.entity;

import com.lavr.third.report.Statistics;
import com.lavr.third.runner.AuctionRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 123 on 11.10.2016.
 */

public class Bidding extends Thread {
    private static final Logger LOG = LogManager.getLogger();
    private static int PARTICIPANTS_NUMBER=5;
    private static int participantMoney[]=new int[5];

    private Lot lot;
    private ArrayList<Participant> participants;
    private  CountDownLatch countDownLatch;


    @Override
    public void run() {
        try {
            Statistics.biddingStart(lot.getLotId());
            Participant.bidding=this;
            Statistics.biddingStartPrice(lot.getLotPrice());
            for (int i = 0; i < PARTICIPANTS_NUMBER; i++) {
                boolean wish=new Random().nextBoolean();
                int purse=participantMoney[i];
                Participant thread = new Participant(i,purse,wish,countDownLatch);
                add(thread);
                thread.start();
            }
            countDownLatch.await();
            if(lot.getLotId()==0){
                Statistics.addInitialMoneyReport(participantMoney,PARTICIPANTS_NUMBER);
            }
            Participant winner = Bidding.this.defineWinner();
                if(winner.getParticipantPrice()!=0){
                    Long id=winner.getId();
                    participantMoney[id.intValue()]-=winner.getParticipantPrice();
                    Statistics.participantWon(winner.getId(),winner.getParticipantPrice());
                }else {
                    Statistics.unclaimedLot(lot.getLotId());
                }
            if(lot.getLotId()==AuctionRunner.LOT_NUMBER-1){
                Statistics.addUltimateMoneyReport(participantMoney,PARTICIPANTS_NUMBER);
            }
        } catch (InterruptedException e) {
            LOG.error("Interrupted thread in bidding "+lot.getLotId(),e);
        }
    }

    public Bidding(int j) {
        int price= new Random().nextInt(100);
        lot=new Lot(j,price);
        this.participants = new ArrayList<>();
        this.countDownLatch=new CountDownLatch(5);
        if(j==0){
            for(int i=0;i<PARTICIPANTS_NUMBER;i++){
                participantMoney[i]=new Random().nextInt(300);
            }
        }
    }

    public Lot getLot() {
        return lot;
    }

    private Participant defineWinner() {
        return Collections.max(participants, new Comparator<Participant>() {
            @Override
            public int compare(Participant ob1, Participant ob2) {
                return ob1.getParticipantPrice() - ob2.getParticipantPrice();
            }
        });
    }

    private boolean add(Participant p) {
        return participants.add(p);
    }

}
