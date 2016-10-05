package com.company.participation;

import com.company.domain.Auction;

public class SafeParticipation extends Participation {

    public SafeParticipation(Auction auction, String name) {
        super(auction, name);
    }


    @Override
    public void handleBets() {
        synchronized (auction) {
            makeBet();
        }
    }
}
