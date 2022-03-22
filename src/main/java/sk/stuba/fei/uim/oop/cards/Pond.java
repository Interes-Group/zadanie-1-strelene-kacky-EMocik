package sk.stuba.fei.uim.oop.cards;

import java.util.ArrayList;

public class Pond{

    public Pond(ArrayList<String> cards){

        for (byte i = 0; i < 6; i++){
            pond.add(cards.get(i));
            cards.remove(i);
        }

        remainingPond.addAll(cards);


        for (byte i = 0; i < 6; i++) {
            aim.add(false);
        }
    }

    private ArrayList<String> pond = new ArrayList<>();
    private ArrayList<String> remainingPond = new ArrayList<>();
    private ArrayList<Boolean> aim = new ArrayList<>();



    public ArrayList<String> changePond(){
        return pond;
    }
    public ArrayList<String> changeRemainingPond(){
        return remainingPond;
    }
    public ArrayList<Boolean> changeAim(){
        return aim;
    }


}
