package sk.stuba.fei.uim.oop.cards;

/*Duck dance - 1 card
Put the cards from the pond back into the duck deck, shuffle the deck and
place 6 new cards into the pond.
 */

import sk.stuba.fei.uim.oop.position.Pond;

import java.util.Collections;

public class DuckDanceCard extends Cards{
    Pond copyOfPond;


    public DuckDanceCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {
        copyOfPond.changeRemainingPond().addAll(copyOfPond.changePond());
        copyOfPond.changePond().clear();     //removeAll(copyOfPond.changePond()); //.clear()
        Collections.shuffle(copyOfPond.changeRemainingPond());

        for(byte i = 0; i < 6; i++){
            copyOfPond.changePond().add(copyOfPond.changeRemainingPond().get(0));
            copyOfPond.changeRemainingPond().remove(0);
        }
        return "";
    }

    @Override
    public String toString() {
        return "Duck Dance";
    }
}
