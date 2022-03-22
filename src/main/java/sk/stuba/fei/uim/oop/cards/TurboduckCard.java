package sk.stuba.fei.uim.oop.cards;

/*Turboduck - 1 card
Move any duck to the first space in the pond.
The ducks that were overtaken by it move one space backwards, to fill the gap.
 */

import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.Collections;
import java.util.Objects;

public class TurboduckCard extends Cards {
//    public static int amount = 1;
//    public static String name = "Turboduck";
    Pond copyOfPond;


    public TurboduckCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {
        int position = KeyboardInput.readInt("Select a duck to be moved to first position");
        while(Objects.equals(copyOfPond.changePond().get(position), "Water")){
            position = KeyboardInput.readInt("You can only select a Duck to be moved to first position, try again");
        }

        Collections.swap(copyOfPond.changePond(), position, 0);

        return "";
    }

    @Override
    public String toString() {
        return "Turboduck Card";
    }
}