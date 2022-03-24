package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.position.Pond;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Objects;

//Wild Bill - 2 cards

public class KillBillCard extends Cards{
//    public static int amount = 2;
    Pond copyOfPond;


    public KillBillCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {
        
        String killedDuck = "";
        
        int position = ZKlavesnice.readInt("Select a position for Wild Bill to shoot: ");
        while(position > 5 || position < 0) {
            position = ZKlavesnice.readInt("You can only select a valid <0-5> position: ");

        }
        if (!Objects.equals(copyOfPond.changePond().get(position), "Water")){
            killedDuck = copyOfPond.changePond().get(position);
            copyOfPond.changePond().remove(position);
            copyOfPond.changePond().add(copyOfPond.changeRemainingPond().get(0));
            copyOfPond.changeRemainingPond().remove(0);
            copyOfPond.changeAim().set(position, false);
        }
        else{
            copyOfPond.changeAim().set(position, false);
        }
        
        return killedDuck;
    }


    @Override
    public String toString() {
        return "Wild Bill";
    }

}
