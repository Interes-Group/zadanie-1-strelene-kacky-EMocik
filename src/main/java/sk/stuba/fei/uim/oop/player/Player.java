package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.Cards;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class Player {
    private ArrayList<Cards> hand = new ArrayList<>();
    private String name;
    private String color;
    private boolean removed = false;
    private int life = 5;

    public Player(String name, ArrayList<Cards> hand, String color){
        this.name = name;
        this.hand.addAll(hand);
        this.color = color;
    }

    public boolean isActive() {
       return life >= 1;
    }

    public int getLife(){
        return life;
    }

    public void takeDamage(){
        life--;
    }

    public void removePlayer(){
        removed = true;
        hand.clear();
        hand = null;
        name = null;
        color = null;
    }

    public boolean wasRemoved( ){
        return removed;
    }

    public ArrayList<Cards> getHand(){
        return hand;
    }

    public String name(){
        return name;
    }

    public String getPlayerColor(){
        return color;
    }

}


