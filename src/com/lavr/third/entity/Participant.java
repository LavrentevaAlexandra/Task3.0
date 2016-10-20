package com.lavr.third.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 123 on 11.10.2016.
 */
class Participant extends Thread {
    private Lock lock = new ReentrantLock();
    private static final Logger LOG = LogManager.getLogger();
    private long participantId;
    private int money;
    private boolean wish;
    private int participantPrice;
    static Bidding bidding;
    private CountDownLatch countDownLatch;

    Participant(long participantId, int money, boolean wish, CountDownLatch countDownLatch) {
        this.participantId = participantId;
        this.money = money;
        this.wish = wish;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public long getId() {
        return participantId;
    }

    public int getParticipantPrice() {
        return participantPrice;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));  //for thinking
            while (wish) {
                lock.lock();
                try {
                    int workingPrise = bidding.getLot().getLotPrice();
                    if (workingPrise != participantPrice) {
                        if (money > workingPrise) {
                            int delta = new Random().nextInt(money - workingPrise);
                            participantPrice = workingPrise + delta;
                            bidding.getLot().setLotPrice(participantPrice);
                            System.out.println("Participant " + getId() + " : " + participantPrice);
                        } else {
                            System.out.println("Participant " + getId() + " skips, not enough money :" + getMoney());
                            wish = false;
                            break;
                        }
                    }
                } finally {
                    lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(500);    //for observing
                wish = new Random().nextBoolean();
            }
            this.countDownLatch.countDown();
        } catch (InterruptedException e) {
            LOG.error("Interrupted thread in participant" + getId(), e);
        }
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + participantId +
                ", participantPrice=" + participantPrice +
                '}';
    }
}
