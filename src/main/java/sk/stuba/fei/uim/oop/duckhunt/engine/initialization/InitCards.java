package sk.stuba.fei.uim.oop.duckhunt.engine.initialization;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.position.ActiveDeck;
import sk.stuba.fei.uim.oop.position.Pond;

import java.util.ArrayList;

public class InitCards {

    private final ActiveDeck activeDeck;

    public InitCards(Pond pond){
        ArrayList<Cards> tempDeck = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            tempDeck.add(new AimCard(pond));
        }
        for(int i = 0; i < 2; i++){
            tempDeck.add(new KillBillCard(pond));
        }
        for(int i = 0; i < 12; i++){
            tempDeck.add(new ShootCard(pond));
        }
        for(int i = 0; i < 6; i++){
            tempDeck.add(new DuckMarchCard(pond));
        }
        for(int i = 0; i < 1; i++){
            tempDeck.add(new TurboduckCard(pond));
        }
        for(int i = 0; i < 2; i++){
            tempDeck.add(new ScatterCard(pond));
        }
        for(int i = 0; i < 1; i++){
            tempDeck.add(new DuckDanceCard(pond));
        }

        activeDeck = new ActiveDeck(tempDeck);
    }

    public ActiveDeck getActiveDeck(){
        return activeDeck;
    }
}
