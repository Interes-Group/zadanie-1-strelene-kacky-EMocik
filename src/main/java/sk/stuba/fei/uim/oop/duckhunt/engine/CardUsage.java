package sk.stuba.fei.uim.oop.duckhunt.engine;


import sk.stuba.fei.uim.oop.cards.Cards;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.position.ActiveDeck;
import sk.stuba.fei.uim.oop.position.Pond;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Objects;

public class CardUsage {

    Pond pond;
    Player[] players;
    ActiveDeck activeDeck;


    public CardUsage(Pond pond, Player[] players, ActiveDeck activeDeck){

        this.pond = pond;
        this.players = players;
        this.activeDeck = activeDeck;
    }



    public boolean useCards(int currentPlayer){

        ArrayList<Boolean> tf =  checkPond(pond, players, currentPlayer); // tf - true false

                                                    // k - allAimFalse  v - allAimTrue                                  // k - allCardsShoot  v - allCardsAim
        if(!checkIfPlayable(new AbstractMap.SimpleEntry<>(tf.get(0), tf.get(1)), new AbstractMap.SimpleEntry<>(tf.get(2),tf.get(3)), currentPlayer))
                    return true;


        int inputPosition = ZKlavesnice.readInt("Choose an action card <0;2>");
        while (inputPosition > 2 || inputPosition < 0){
            inputPosition = ZKlavesnice.readInt("You can only choose between 0 and 2, try again");
        }

        Cards usedCard = players[currentPlayer].getHand().get(inputPosition);

        if((tf.get(0) && Objects.equals("Shoot",usedCard.toString())) ||
                (tf.get(1) && Objects.equals("Aim", usedCard.toString()))){

            System.out.println("You cannot use " + usedCard + " at the moment, please choose another card");

            if(useCards(currentPlayer))
                return true;
        }

        usage(usedCard, currentPlayer, inputPosition);

        return true;
    }




    private ArrayList<Boolean> checkPond(Pond pond, Player[] players, int currentPlayer){
        boolean allAimFalse = true;
        boolean allAimTrue = true;
        boolean allCardsShoot = true;
        boolean allCardsAim = true;

        ArrayList<Boolean> returnList = new ArrayList<>();


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

        returnList.add(allAimFalse);
        returnList.add(allAimTrue);
        returnList.add(allCardsShoot);
        returnList.add(allCardsAim);

        return returnList;
    }



    private boolean checkIfPlayable(AbstractMap.SimpleEntry<Boolean,Boolean> aimCheck, AbstractMap.SimpleEntry<Boolean,Boolean> cardCheck, int currentPlayer){
        if((aimCheck.getKey() && cardCheck.getKey()) || (aimCheck.getValue() && cardCheck.getValue())){
            System.out.println("You cannot play any card, new card has been chosen from the deck automatically. You wait one turn");

            players[currentPlayer].getHand().set(0,activeDeck.getCard());
            activeDeck.addCard(players[currentPlayer].getHand().get(1));

            return false;
        }
        
        return true;
    }


    private void usage(Cards usedCard, int currentPlayer, int inputPosition){
        String killedDuck = usedCard.use();


        if(!Objects.equals(killedDuck,"")){
            for (Player player : players){
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
    }

}
