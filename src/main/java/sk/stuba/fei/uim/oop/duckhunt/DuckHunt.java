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

        this.initGamePlayers();
        this.initBoard();
        this.initCards();
        this.initPlayer();
        this.startGame();

    }

}
