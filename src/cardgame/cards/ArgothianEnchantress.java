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
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sara
 */
public class ArgothianEnchantress implements Card{
      
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new ArgothianEnchantress(); }
    }
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Argothian Enchantress",new Factory());
        
        
 private class ArgothianEnchantressEffect extends AbstractCreatureCardEffect {
        public ArgothianEnchantressEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new ArgothianEnchantressCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new ArgothianEnchantressEffect(p,this); }
    
    
    private class ArgothianEnchantressCreature extends AbstractCreature {
        Player owner;
        
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        
        ArgothianEnchantressCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    @Override
                                    public String name(){return "Argothian Enchantress";}                                 
                                    @Override
                                    public boolean play() { 
                                        CardGame.instance.getStack().push(this);
                                        return tap(); 
                                    }
                                    @Override
                                    public void resolve() {
                                    Player owner = CardGame.instance.getCurrentPlayer();
                                    }
                                    @Override
                                    public String toString() 
                                        { return "Whenever you cast an enchantment spell,draw a card"; }                                    
                                }
                ); 
        }
        
        private final TriggerAction DrawAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (CardGame.instance.getCurrentPlayer().equals(owner))
                        CardGame.instance.getCurrentPlayer().draw();
                    /*altrimenti non pesca*/
                }
            };
        
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER, DrawAction);
        }
        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(DrawAction);
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