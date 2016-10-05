package com.company;


import com.company.domain.Atendee;
import com.company.domain.Auction;

/**
 * Verso problema: Aukcionas
 *
 * Aukcione parduodami įvairios prekės.
 * Aukciono vedėjas pateikia pradinę prakės vertę.
 * Aukciono dalyvis siūlo kainą.
 * Prekę nusiperka pasiūlęs didžiausią kainą dalyvis.
 *
 * Kritinė sekcija - kainos siūlymas.
 * Esant interkatyviam internetiniam aukcionui didelė tikimybė, kad kainą vienu metu pasiūlys keli asmenys, tokiu atveju gauname neapibrėžtą situaciją - neaišku kuris iš jų būtų laikomas laimėtoju.
 *
 * Problema trumpai:
 * if (statymas > maksimalus_statymas) {
 *   maksimalus_statymas = statymas;
 * }
 * Esant situacijai: Praėjes statymas: 500e, 2 lygiagreciom uzklausoms: 600e ir 700e statymai.
 * 1. Užklausa 700e, atitinka patikrinimą, todėl pereina prie atnaujinmo vykdymo, tuo metu įvyksta patikrinimas 600 > 500 (nes pirmasis statytojas nespėjo atnaujiti).
 * 2. Pirmasis statytojas atnaujina maksimalų statymą iki 700, antrasis atnaujina iki 600.
 *
 */
public class EntryPoint {

    private enum WorkMode {
        RISKY, THREADSAFE;
    }

    private static final long NUMBER_OF_ATENDEES = 100;

    public static void main(String[] args) {
        organiseAuction(getMode(args)).start(
                System.currentTimeMillis() + 500
        );
    }


    /**
     * Inicializuoja pradini aukciono objekta.
     * Sukuriamas aukcionas, užpildomas dalyviais, nustatoma pradinio statymo reikšmė.
     *
     * @return  paruoštas aukcionas
     */
    private static Auction organiseAuction(WorkMode mode) {
        Auction auction = new Auction(100, mode == WorkMode.THREADSAFE);

        for (int index = 0; index < NUMBER_OF_ATENDEES; index++) {
            auction.addAtendee(new Atendee("Jonas-" + index)); // Sugeneruojam unikalų vardą dalyviui.
        }

        return auction;
    }

    /**
     * Iš programos parametrų nustatomas programos veikimo rėžimas
     *
     *      Numatytasis rėžimas - RISKY
     *
     *  1. Jei parametrai apskirtai nenurodyti, veikia numatytuoju rėžimu.
     *  2. Jei parametras nurodytas, rėžimas parenkamas pagal parametro reikšmę - [RISKY, THREADSAFE]
     *
     * @param params
     * @return darbo rėžimas
     */
    private static WorkMode getMode(String[] params) {
        if (params.length == 0) {
            return WorkMode.RISKY;
        }

        return WorkMode.valueOf(params[0].toUpperCase());
    }
}
