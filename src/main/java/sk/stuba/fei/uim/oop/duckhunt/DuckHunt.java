package sk.stuba.fei.uim.oop.duckhunt;
import sk.stuba.fei.uim.oop.duckhunt.engine.*;
import sk.stuba.fei.uim.oop.duckhunt.engine.initialization.InitBoard;
import sk.stuba.fei.uim.oop.duckhunt.engine.initialization.InitCards;
import sk.stuba.fei.uim.oop.duckhunt.engine.initialization.InitGamePlayers;
import sk.stuba.fei.uim.oop.duckhunt.engine.initialization.InitPlayer;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.position.*;
import java.util.ArrayList;

public class DuckHunt {

    public DuckHunt(){
        Player[] players;
        ArrayList<String> names;
        Pond pond;
        ActiveDeck activeDeck;
        int numOfPlayers;


        InitGamePlayers initGamePlayers = new InitGamePlayers();
        players = initGamePlayers.getPlayers();
        names = initGamePlayers.getNames();

        InitBoard initBoard = new InitBoard(players.length);
        pond = initBoard.getPond();

        InitCards initCards = new InitCards(pond);
        activeDeck = initCards.getActiveDeck();

        InitPlayer initPlayer = new InitPlayer(players, names, activeDeck, new Color());
        numOfPlayers = initPlayer.getNumOfPlayers();

        StartGame startGame = new StartGame(players, numOfPlayers, activeDeck, pond);

    }

}
