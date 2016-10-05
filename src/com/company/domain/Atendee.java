package com.company.domain;


import com.company.participation.Participation;
import com.company.participation.RiskyParticipation;
import com.company.participation.SafeParticipation;

/**
 * Created by ignas on 9/25/16.
 */
public class Atendee {

    private String name;

    public Atendee(String name) {
        this.name = name;
    }

    public void participate(Auction auction, boolean safe) {
        Participation participation;

        if (safe) {
            participation = new SafeParticipation(auction, this);
        } else {
            participation = new RiskyParticipation(auction, this);
        }

        new Thread(participation).start();
    }

    public String getName() {
        return name;
    }
}
