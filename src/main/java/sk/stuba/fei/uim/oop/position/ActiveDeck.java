package sk.stuba.fei.uim.oop.position;

import sk.stuba.fei.uim.oop.cards.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveDeck {
    private final ArrayList<Cards> actionCards = new ArrayList<>();

    public ActiveDeck(ArrayList<Cards> newActionCards){
        actionCards.addAll(newActionCards);

        Collections.shuffle(actionCards);
    }


    public void addCard(Cards card){
        actionCards.add(card);
    }

    public ArrayList<Cards> getHand(){
        ArrayList<Cards> temp = new ArrayList<>();

        for(byte i = 0; i < 3; i++) {
            temp.add(actionCards.get(i));
            actionCards.remove(i);
        }
        return temp;
    }

    public Cards getCard(){
        Cards temp = actionCards.get(0);
        actionCards.remove(0);
        return temp;
    }


}
