package sk.stuba.fei.uim.oop.duckhunt.engine.initialization;

import sk.stuba.fei.uim.oop.position.Color;
import sk.stuba.fei.uim.oop.position.Pond;

import java.util.ArrayList;
import java.util.Collections;

public class InitBoard {
    private final Pond pond;

    public InitBoard(int playersLength){

        ArrayList<String> cards = new ArrayList<>();
        for (byte i = 0; i < 5; i++) {
            cards.add("Water");
        }

        Color colors = new Color();

        for (int i = 0; i < playersLength; i++) {
            for (int j = 0; j < 5; j++) {

                cards.add(colors.getColor(i));
            }
        }

        Collections.shuffle(cards);

        pond = new Pond(cards);
    }

    public Pond getPond(){
        return pond;
    }
}
