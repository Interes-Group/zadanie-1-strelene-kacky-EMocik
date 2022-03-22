package sk.stuba.fei.uim.oop.cards;

/*Scatter - 2 cards
Randomly rearrange the positions of all the cards in the pond.
The targeted spaces do not change (perform a shuffle of the cards in the pond).
 */

import java.util.Collections;

public class ScatterCard extends Cards{
//    public static int amount = 2;
//    public static String name = "Scatter";
    Pond copyOfPond;

    public ScatterCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }
    @Override
    public String use() {
        Collections.shuffle(copyOfPond.changePond());

        return "";
    }


    @Override
    public String toString() {
        return "Scatter Card";
    }
}
