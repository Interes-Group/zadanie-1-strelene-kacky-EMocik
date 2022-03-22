package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;
//Shoot - 12 cards

public class ShootCard extends Cards{
//    public static int amount = 12;
//    public static String name = "Shoot";
    Pond copyOfPond;


    public ShootCard(Pond copyOfPond){
        this.copyOfPond = copyOfPond;
    }

    @Override
    public String use() {
        String killedDuck = "";

        int position = KeyboardInput.readInt("Select an aimed position to shoot at");
        while(position > 6 || position <= 0) {
            position = KeyboardInput.readInt("You can only select a valid <0-5> or an aimed at position");
            //if pos isnt water, remove pos
            if (copyOfPond.changeAim().get(position)) {
                break;
            }
            else
                System.out.println("The place you want to shoot at is not aimed at");
        }
        killedDuck = copyOfPond.changePond().get(position);
        copyOfPond.changePond().remove(position);
        copyOfPond.changeAim().set(position, false);
        copyOfPond.changePond().add(copyOfPond.changeRemainingPond().get(0));
        copyOfPond.changeRemainingPond().remove(0);

        return killedDuck;
    }



    @Override
    public String toString() {
        return "Shoot Card";
    }
}
