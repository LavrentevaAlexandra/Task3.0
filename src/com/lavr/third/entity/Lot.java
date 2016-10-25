package com.lavr.third.entity;

/**
 * Created by 123 on 12.10.2016.
 */

public class Lot {
    private long lotId;
    private int lotPrice;

    public Lot(int lotId, int lotPrice) {
        this.lotId = lotId;
        this.lotPrice=lotPrice;
    }

    public long getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getLotPrice() {
        return lotPrice;
    }

    public void setLotPrice(int lotPrice) {
        this.lotPrice=lotPrice;
    }
}
