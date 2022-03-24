package sk.stuba.fei.uim.oop.duckhunt.engine.initialization;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.position.ActiveDeck;
import sk.stuba.fei.uim.oop.position.Color;

import java.util.ArrayList;

public class InitPlayer {

    private int numOfPlayers;
    public InitPlayer(Player[] players, ArrayList<String> names, ActiveDeck activeDeck, Color colors){

        numOfPlayers = players.length;

        for(int i = 0; i < players.length; i++){
            players[i] = new Player(names.get(0),activeDeck.getHand(),colors.getColor(i));
            names.remove(0);
        }
    }

    public int getNumOfPlayers(){
        return numOfPlayers;
    }
}
