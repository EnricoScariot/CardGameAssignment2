/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;
import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author skari
 */
public class NorwoodRanger implements Card {
    
 private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new NorwoodRanger(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("Norwood Ranger",new Factory());
    
 private class NorwoodRangerEffect extends AbstractCreatureCardEffect {
        public NorwoodRangerEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new NorwoodRangerCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new NorwoodRangerEffect(p,this); }
    
    
    private class NorwoodRangerCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        NorwoodRangerCreature(Player owner) { 
            super(owner);
        }
        
        @Override
        public String name() { return "NorwoodRanger"; }
        
        @Override
        public int getPower() { return 1; }
        @Override
        public int getToughness() { return 2; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "NorwoodRanger"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature NorwoodRanger (1/2)"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }

}
