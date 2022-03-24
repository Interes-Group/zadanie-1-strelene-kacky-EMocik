package sk.stuba.fei.uim.oop.cards;

//Aim - 10 cards

import sk.stuba.fei.uim.oop.position.Pond;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class AimCard extends Cards{
    Pond copyOfPond;

    public AimCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }


    @Override
    public String use() {

        int position;
        while(true) {
            position = ZKlavesnice.readInt("Select a position to aim at: ");
            if(position >=0 && position <= 5){
                if (!copyOfPond.changeAim().get(position)) {
                    break;
                }
                System.out.println("Selected position is already aimed at, try another one.");
            }
            else{
                System.out.println("You can only select a valid <0-5> or a not aimed at position.");
            }
        }
        copyOfPond.changeAim().set(position, true);

        return "";
    }


    @Override
    public String toString() {
        return "Aim";
    }
}
