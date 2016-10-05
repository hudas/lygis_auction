package com.company.participation;

import com.company.domain.Auction;

public class RiskyParticipation extends Participation {

    public RiskyParticipation(Auction auction, String name) {
        super(auction, name);
    }


    @Override
    public void handleBets() {
        makeBet();
    }
}
