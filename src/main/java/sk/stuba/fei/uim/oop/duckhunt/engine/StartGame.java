package sk.stuba.fei.uim.oop.duckhunt.engine;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.position.ActiveDeck;
import sk.stuba.fei.uim.oop.position.Pond;

public class StartGame {

    public StartGame(Player[] players, int numOfPlayers, ActiveDeck activeDeck, Pond pond){
        System.out.println("The Duck Hunt is starting with " + getNumberOfPlayers(players) + " players!");
        int currentPlayer = 0;
        int roundCounter = 0;
        CardUsage cardUsage = new CardUsage(pond, players, activeDeck);

        while(getNumberOfPlayers(players) > 1){

            if(currentPlayer >= numOfPlayers){
                currentPlayer = 0;
            }


            if(players[currentPlayer].name() != null){
                this.roundManager(roundCounter,pond);
                this.showPlayerCards(currentPlayer, players);

                cardUsage.useCards(currentPlayer);
            }
            this.playerVibeCheck(players, activeDeck);
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
    private int getNumberOfPlayers(Player[] players){
        int count = 0;
        for(var player : players){
            if(player.name() != null){
                count++;
            }
        }
        return count;
    }

    private void roundManager(int roundCounter, Pond pond){
        System.out.println("┍--------------ROUND " + (roundCounter+1) + "---------------┑");

        for (byte i = 0; i < 6; i++) {
            System.out.println("|  " + i + ". " + pond.changePond().get(i) + "\t\t\t" + (pond.changeAim().get(i) ? "Aimed at\t\t |" : "Not aimed at\t |"));
        }
        System.out.println("┖------------------------------------┙");
    }

    private void showPlayerCards(int currentPlayer, Player[] players){
        System.out.print("\n" + players[currentPlayer].name() + "'s cards(You have " + players[currentPlayer].getPlayerColor() + " Ducks):\n");
        for(int i = 0; i < 3; i++) {
            System.out.println(players[currentPlayer].getHand().get(i));
        }
        System.out.println();
        System.out.println("Your have " + players[currentPlayer].getLife() + " ducks left in the pond");
    }

    private void playerVibeCheck(Player[] players, ActiveDeck activeDeck){
        for(Player player : players){
            if(player.name() == null || (!player.wasRemoved() && player.isActive()) ) continue;
            System.out.println("Player " + player.name() + " has lost the game of Duck Hunt, poor little ducks...");

            for(byte i = 0; i < 3; i++) {
                activeDeck.addCard(player.getHand().get(i));
            }

            player.removePlayer();
        }
    }
}
