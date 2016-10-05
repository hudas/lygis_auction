package com.company.participation;

import com.company.domain.Atendee;
import com.company.domain.Auction;

public class SafeParticipation extends Participation {

    public SafeParticipation(Auction auction, Atendee atendee) {
        super(auction, atendee);
    }


    @Override
    public void handleBets() {
        synchronized (auction) {
            makeBet();
        }
    }
}
