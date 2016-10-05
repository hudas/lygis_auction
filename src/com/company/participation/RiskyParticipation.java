package com.company.participation;

import com.company.domain.Atendee;
import com.company.domain.Auction;

public class RiskyParticipation extends Participation {

    public RiskyParticipation(Auction auction, Atendee atendee) {
        super(auction, atendee);
    }


    @Override
    public void handleBets() {
        makeBet();
    }
}
