package com.company.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Aukciono verslo esybę imituojanti klasė.
 *
 * Created by ignas on 9/25/16.
 */
public class Auction {

    private static final long AUCTION_LENGTH_MILLIS = 10;
    private Long startTime;
    private Boolean safe;

    private Integer winningBet;
    private List<Atendee> atendees = new ArrayList<>();


    public Auction(Integer entryPrice, boolean threadSafe) {
        this.winningBet = entryPrice;
        this.safe = threadSafe;
    }

    public void addAtendee(Atendee atendee) {
        atendees.add(atendee);
    }

    public void start(long startTime) {
        this.startTime = startTime;

        for (Atendee atendee : atendees) {
            atendee.participate(this, safe);
        }
    }

    public void bet(Integer amount) {
        if (winningBet.compareTo(amount) < 0) {
            winningBet = amount;
        }
    }

    public Integer getWinningBet() {
        return winningBet;
    }

    public boolean hasEnded() {
        return startTime + AUCTION_LENGTH_MILLIS < System.currentTimeMillis();
    }

    public boolean hasStarted() {
        return startTime != null && startTime < System.currentTimeMillis();
    }
}

