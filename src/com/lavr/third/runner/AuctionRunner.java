package com.lavr.third.runner;

import com.lavr.third.entity.Bidding;
import com.lavr.third.report.Statistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by 123 on 11.10.2016.
 */
public class AuctionRunner {
    public static final int LOT_NUMBER=5;
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[ ] args) {
        for(int j=0;j<LOT_NUMBER; j++){
            try {
                Bidding bidding = new Bidding(j);
                bidding.start();
                bidding.join();
            }catch (InterruptedException e){
                LOG.error("Interrupted thread in bidding "+j,e);
            }
        }
        Statistics s=new Statistics();
        s.showStatistics();
    }
}
