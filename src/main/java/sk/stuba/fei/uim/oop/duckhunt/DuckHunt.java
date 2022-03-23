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
    private int numOfPlayers;
    private int currentPlayer = 0;
    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> cards = new ArrayList<>();
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

        int numberPlayers = KeyboardInput.readInt("Enter the number of players");
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

        numOfPlayers = players.length;

        for(int i = 0; i < players.length; i++){
            players[i] = new Player(names.get(0),activeDeck.getHand(),colors.getColor(i));
            names.remove(0);
        }
    }


    private void startGame(){

        System.out.println("The Duck Hunt is starting with " + getNumberOfPlayers() + " players!");


        while(getNumberOfPlayers() > 1){
            if(currentPlayer >= numOfPlayers){
                currentPlayer = 0;
            }

            if(players[currentPlayer].name() != null){
                this.roundManager(roundCounter);
                this.showPlayerCards(currentPlayer);

                this.cardUsage(false);
            }
            this.playerVibeCheck();
            roundCounter++;
            currentPlayer++;

        }

        for (Player player : players) {
            if(player.name() != null){
                System.out.println("Winner winner chicken(duck) dinner! Congrats " + player.name() +
                        " you have won the game of Duck Hunt!");
            }
        }
    }

    private int getNumberOfPlayers(){
        int count = 0;
        for(var player : players){
            if(player.name() != null){
                count++;
            }
        }
        return count;
    }

    private void playerVibeCheck(){
        for(Player player : players){
            if(player.name() == null || (!player.wasRemoved() && player.isActive()) ) continue;
            System.out.println("Player " + player.name() + " has lost the game of Duck Hunt, poor little ducks...");

            for(byte i = 0; i < 3; i++) {
                activeDeck.addCard(player.getHand().get(i));
            }

            player.removePlayer();
        }
    }

    /**
     * Uses an action card and checks all necessary parameters for legal usage
     * @param hasBeenUsed used for recursion ALWAYS SET TO FALSE
     * @return used for recursion, unnecessary for usage beyond that
     */
    private boolean cardUsage(boolean hasBeenUsed){


        boolean allAimFalse = true;
        boolean allAimTrue = true;
        boolean allCardsShoot = true;
        boolean allCardsAim = true;


        for(boolean aim : pond.changeAim()){
            if(!aim) allAimTrue = false;
            if(aim) allAimFalse = false;
        }


        for (Cards card : players[currentPlayer].getHand()){

            if(!Objects.equals("Shoot",card.toString())){
                allCardsShoot = false;
            }

            if(!Objects.equals("Aim",card.toString())){
                allCardsAim = false;
            }
        }


        if((allAimFalse && allCardsShoot) || (allAimTrue && allCardsAim)){
            System.out.println("You cannot play any card, new card has been chosen from the deck automatically. You wait one turn");

            players[currentPlayer].getHand().set(0,activeDeck.getCard());
            activeDeck.addCard(players[currentPlayer].getHand().get(1));

            return true;
        }


        int inputPosition = ZKlavesnice.readInt("Choose an action card <0;2>");
        while (inputPosition > 2 || inputPosition < 0){
            inputPosition = ZKlavesnice.readInt("You can only choose between 0 and 2, try again");
        }

        Cards usedCard = players[currentPlayer].getHand().get(inputPosition);

        if((allAimFalse && Objects.equals("Shoot",usedCard.toString())) ||
                (allAimTrue && Objects.equals("Aim", usedCard.toString()))){

            System.out.println("You cannot use " + usedCard + " at the moment, please choose another card");

            if(this.cardUsage(true))
                return true;
        }

        String killedDuck = usedCard.use();


        if(!Objects.equals(killedDuck,"")){
            for (Player  player : players){
                if(player.name() != null){
                    if(Objects.equals(player.getPlayerColor(), killedDuck)) {
                        player.takeDamage();
                        System.out.println(player.name() + "'s duck has been shot");
                        break;
                    }
                }
            }
        }


        players[currentPlayer].getHand().set(inputPosition,activeDeck.getCard());
        activeDeck.addCard(usedCard);

        hasBeenUsed = true;

        return hasBeenUsed;
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



        for (byte i = 0; i < 6; i++) {
            System.out.println("|  " + i + ". " + pond.changePond().get(i) + "\t\t\t" + (pond.changeAim().get(i) ? "Aimed at\t\t |" : "Not aimed at\t |"));
        }
        System.out.println("┖------------------------------------┙");
    }



}
