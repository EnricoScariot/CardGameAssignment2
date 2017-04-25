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
import cardgame.Creature;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class ArgothianEnchantress implements Card{
      
    private class Factory implements ICardFactory {
        @Override
        public Card create() { return new ArgothianEnchantress(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Argothian Enchantress",new Factory());
        
        
 private class ArgothianEnchantressEffect extends AbstractCreatureCardEffect {
        public ArgothianEnchantressEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new ArgothianEnchantressCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new ArgothianEnchantressEffect(p,this); }
    
    
    private class ArgothianEnchantressCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        ArgothianEnchantressCreature(Player owner) { 
            super(owner);
        }
        
        @Override
        public String name() { return "Argothian Enchantress"; }
        
        @Override
        public void attack() {}
        @Override
        public void defend(Creature c) {}
        @Override
        public int getPower() { return 2; }
        @Override
        public int getToughness() { return 1; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "Argothian Enchantress"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature ArgothianEnchantress (0/1).Whenever you cast an enchantment spell,draw a card"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }

}