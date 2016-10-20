package com.lavr.third.entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 123 on 12.10.2016.
 */
public class Lot {
    private long lotId;
    private AtomicInteger lotPrice;

    public Lot(int lotId, int lotPrice) {
        this.lotId = lotId;
        this.lotPrice=new AtomicInteger(lotPrice);
    }

    public long getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getLotPrice() {
        return lotPrice.get();
    }

    public void setLotPrice(int lotPrice) {
        this.lotPrice.set(lotPrice);
    }
}
