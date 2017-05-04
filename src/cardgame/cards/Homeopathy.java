/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;

/**
 *
 * @author atorsell
 */
public class Homeopathy implements Card {
    
    
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Homeopathy(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("Homeopathy",new Factory());
    
    private class HomeopathyEffect extends AbstractCardEffect {
        public HomeopathyEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {}
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new HomeopathyEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Homeopathy"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " does nothing"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
