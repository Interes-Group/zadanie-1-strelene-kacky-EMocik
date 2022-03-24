package sk.stuba.fei.uim.oop.duckhunt.engine.initialization;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.ArrayList;

public class InitGamePlayers {
    private Player[] players;
    private final ArrayList<String> names = new ArrayList<>();
    public InitGamePlayers(){
        System.out.println("Welcome to Duck Hunt!");

        int numberPlayers = ZKlavesnice.readInt("Enter the number of players <2;6>: ");
        this.players = new Player[numberPlayers];

        while(numberPlayers < 2 || numberPlayers > 6){
            System.out.println("This game can be only played between 2-6 players, " +
                    "please enter a valid amount of players.");
            numberPlayers = ZKlavesnice.readInt("Enter the number of players: ");
        }


        for (byte i = 0; i<players.length; i++) {
            names.add(ZKlavesnice.readString("Enter PLAYER " + (i + 1) + " name: "));
//            players[i] = new Player(names.get(i), );
        }
    }

    public Player[] getPlayers(){
        return players;
    }

    public ArrayList<String> getNames(){
        return names;
    }
}
