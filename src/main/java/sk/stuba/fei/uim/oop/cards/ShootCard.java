package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.position.Pond;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.Objects;
//Shoot - 12 cards

public class ShootCard extends Cards{
    Pond copyOfPond;


    public ShootCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {

        int position;

        while(true) {
            position = KeyboardInput.readInt("Select an aimed position to shoot at");  //
            if(position <=5 && position >= 0) {
                if (copyOfPond.changeAim().get(position)) {
                    break;
                }
                System.out.println("The place you want to shoot at is not aimed at");

            }
            else{
                System.out.println("You can only select a valid <0-5> or an aimed at position");
            }

        }


        copyOfPond.changeAim().set(position, false);
        String killedDuck = copyOfPond.changePond().get(position);

        if(Objects.equals(killedDuck, "Water")){
            return "";
        }

        copyOfPond.changePond().remove(position);
        copyOfPond.changePond().add(copyOfPond.changeRemainingPond().get(0));
        copyOfPond.changeRemainingPond().remove(0);

        return killedDuck;
    }



    @Override
    public String toString() {
        return "Shoot";
    }
}
