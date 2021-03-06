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
 * @author atorsell
 */
public class Reflexologist implements Card {
    
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Reflexologist(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("Reflexologist",new Factory());
    
    
    
    private class ReflexologistEffect extends AbstractCreatureCardEffect {
        public ReflexologistEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new ReflexologistCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new ReflexologistEffect(p,this); }
    
    
    private class ReflexologistCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        ReflexologistCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    @Override
                                    public String name(){return "Reflexologist";}
                                    
                                    @Override
                                    public boolean play() { 
                                        CardGame.instance.getStack().push(this);
                                        return tap(); 
                                    }
                                    @Override
                                    public void resolve() {}
                                    @Override
                                    public String toString() 
                                        { return "tap: Reflexology does nothing"; }
                                }
                ); 
        }
        
        @Override
        public String name() { return "Reflexologist"; }
        
        @Override
        public int getPower() { return 0; }
        @Override
        public int getToughness() { return 1; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "Reflexologist"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature Reflexologist(0/1) with tap: Reflexology does nothing"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }

}
