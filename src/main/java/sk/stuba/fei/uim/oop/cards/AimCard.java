package sk.stuba.fei.uim.oop.cards;

//Aim - 10 cards

import sk.stuba.fei.uim.oop.utility.KeyboardInput;

public class AimCard extends Cards{
//      public int amount = 10;
//    public static String name = "Aim";
    Pond copyOfPond;


    public AimCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {

        int position = KeyboardInput.readInt("Select a position to aim at");
        while(position > 6 || position <= 0) {
            position = KeyboardInput.readInt("You can only select a valid <0-5> or a not aimed at position");

            if (!copyOfPond.changeAim().get(position)) {
                break;
            }
        }
        copyOfPond.changeAim().set(position, true);

        return "";
    }


    @Override
    public String toString() {
        return "Aim Card";
    }
}
