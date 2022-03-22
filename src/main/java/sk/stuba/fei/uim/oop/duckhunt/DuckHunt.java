package sk.stuba.fei.uim.oop.duckhunt;

import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.position.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class DuckHunt {
    private Player[] players;
    private int roundCounter;
    private int currentPlayer;
    private int numberPlayers;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> cards = new ArrayList<>();
    private final Color colors = new Color();
    private Pond pond;
    private ActiveDeck activeDeck;


    public DuckHunt(){

        this.initGamePlayers();
        this.initBoard();
        this.initCards();
        this.initPlayer();
        this.startGame();

    }


    private void initGamePlayers(){
        System.out.println("Welcome to Duck Hunt!");

        numberPlayers = KeyboardInput.readInt("Enter the number of players");
        this.players = new Player[numberPlayers];

        while(numberPlayers < 2 || numberPlayers > 6){
            System.out.println("This game can be only played between 2-6 players, " +
                    "please enter a valid amount of players.");
            numberPlayers = KeyboardInput.readInt("Enter the number of players");
        }


        for (byte i = 0; i<players.length; i++) {
            names.add(KeyboardInput.readString("Enter PLAYER " + (i + 1) + " name"));
//            players[i] = new Player(names.get(i), );
        }


    }

    private void initBoard(){

        for (byte i = 0; i < 5; i++) {
            cards.add("Water");
        }

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < 5; j++) {
                cards.add(colors.getColor(i));
            }
        }

        Collections.shuffle(cards);

        pond = new Pond(cards);
    }

    private void initCards(){

        ArrayList<Cards> tempDeck = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            tempDeck.add(new AimCard(pond));
        }
        for(int i = 0; i < 2; i++){
            tempDeck.add(new KillBillCard(pond));
        }
        for(int i = 0; i < 12; i++){
            tempDeck.add(new ShootCard(pond));
        }
        for(int i = 0; i < 6; i++){
            tempDeck.add(new DuckMarchCard(pond));
        }
        for(int i = 0; i < 1; i++){
            tempDeck.add(new TurboduckCard(pond));
        }
        for(int i = 0; i < 2; i++){
            tempDeck.add(new ScatterCard(pond));
        }
        for(int i = 0; i < 1; i++){
            tempDeck.add(new DuckDanceCard(pond));
        }

        activeDeck = new ActiveDeck(tempDeck);
    }

    private void initPlayer() {
        for(int i = 0; i < players.length; i++){
            players[i] = new Player(names.get(0),activeDeck.getHand(),colors.getColor(i));
            names.remove(0);
        }
    }


    private void startGame(){


        System.out.println("The Duck Hunt is starting with " + getNumberOfPlayers() + " players!");

        String killedDuck = "";

        while(getNumberOfPlayers() > 1){
            currentPlayer = roundCounter % players.length; //determine which player turn it is
            this.playerVibeCheck();

            if(players[currentPlayer] != null){
                this.roundManager(roundCounter);
                this.showPlayerCards(currentPlayer);

                int inputPosition = ZKlavesnice.readInt("Choose an action card <0;2>");
                while (inputPosition > 2 || inputPosition < 0){
                    inputPosition = ZKlavesnice.readInt("You can only choose between 0 and 2, try again");
                }
                this.lifeSubtractor(killedDuck, inputPosition);




            }
            roundCounter++;
            break;
        }
    }

    private int getNumberOfPlayers(){
        int count = 0;
        for(var player : players){
            if(player.isActive()){
                count++;
            }
        }
        return count;
    }

    private void playerVibeCheck(){
        for(Player player : players){
            if(player == null || (!player.wasRemoved() && player.isActive()) ) continue;
            System.out.println("Player " + players[currentPlayer].name() + " has lost the game of Duck Hunt, poor little ducks...");

            player.removePlayer();
            players[currentPlayer] = null;
        }
    }

    private void lifeSubtractor(String killedDuck, int inputPosition){
        killedDuck = players[currentPlayer].getHand().get(inputPosition).use();

        if(!Objects.equals(killedDuck,"")){
            for (Player  player : players){
                if(Objects.equals(player.getPlayerColor(), killedDuck)){
                    player.takeDamage();
                    System.out.println(player.name() + "'s duck has been shot");
                    break;
                }
            }
        }
    }

    private void showPlayerCards(int currentPlayer){
        System.out.print("\n" + this.players[currentPlayer].name() + "'s cards(You have " + this.players[currentPlayer].getPlayerColor() + " Ducks): ");
        for(int i = 0; i < 3; i++) {
            System.out.print(players[currentPlayer].getHand().get(i) + ", ");
        }
        System.out.println();
        System.out.println("Your have " + this.players[currentPlayer].getLife() + " ducks left in the pond");
    }

    private void roundManager(int roundCounter){
        System.out.println("┍--------------ROUND " + (roundCounter+1) + "---------------┑");



        for (byte i = 0; i < 5; i++) {
            System.out.println("|  " + i + ". " + cards.get(i) + "\t\t\t" + (pond.changeAim().get(i) ? "Aimed at\t\t |" : "Not aimed at\t |"));
        }
        System.out.println("┖------------------------------------┙");
    }



}
