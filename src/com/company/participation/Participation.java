package com.company.participation;

import com.company.domain.Atendee;
import com.company.domain.Auction;

import java.util.Random;

public abstract class Participation implements Runnable {

    protected static Random amountGenerator = new Random(System.currentTimeMillis());

    protected Auction auction;
    protected Atendee atendee;

    public Participation(Auction auction, Atendee atendee) {
        this.auction = auction;
        this.atendee = atendee;
    }


    /**
     * Kritinė sekcija.
     * Esant dideliam statymų kiekiui galima situacija kai statymą laimi mažesnis statymas.
     */
    protected void makeBet() {
        Integer winningBet = auction.getWinningBet();
        Integer betAmount = winningBet + amountGenerator.nextInt(100);

        System.out.println("Laimintis statymas: " + winningBet + "  " + "Stato: " + atendee.getName() + " " + betAmount);

        auction.bet(betAmount);
    }


    @Override
    public void run() {
        while (!auction.hasStarted()) {
            // Wait until beginning
        }

        while (!auction.hasEnded()) {
            handleBets();
        }

        return;
    }

    public abstract void handleBets();
}
