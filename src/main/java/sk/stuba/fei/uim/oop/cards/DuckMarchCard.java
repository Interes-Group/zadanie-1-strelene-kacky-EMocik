package sk.stuba.fei.uim.oop.cards;

/*Duck march - 6 cards
All the ducks move one space forward. The duck in the first
space is moved to the bottom of the duck deck,
the empty space at the end is filled with the duck from the top of the duck deck.
 */

import sk.stuba.fei.uim.oop.position.Pond;

public class DuckMarchCard extends Cards{
    Pond copyOfPond;

    public DuckMarchCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {
        copyOfPond.changeRemainingPond().add(copyOfPond.changePond().get(0));
        copyOfPond.changePond().remove(0);
        copyOfPond.changePond().add(copyOfPond.changeRemainingPond().get(0));
        copyOfPond.changeRemainingPond().remove(0);

        return "";
    }
    @Override
    public String toString() {
        return "Duck March";
    }
}
