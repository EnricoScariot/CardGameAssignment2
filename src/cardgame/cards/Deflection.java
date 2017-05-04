/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.AbstractEnchantment;
import cardgame.Card;
import cardgame.CardDecorator;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;

/**
 *
 * @author Sara
 */
public class Deflection implements Card{
    Effect target; 
     
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Cancel(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("Deflection",new Factory());
      
    private class DeflectionEffect extends AbstractCardEffect {
        public DeflectionEffect(Player p,Card c) { super(p,c); }
       
        @Override
        public void resolve(){
        /*cambia il target a una magia*/
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new DeflectionEffect(owner,this);}
    
    private class DeflectionInstant extends AbstractEnchantment {
        public DeflectionInstant(Player owner) {
            super(owner);
        }

        @Override
        public String name() {return "Deflection";}
    }
    private class DeflectionDecorator extends CardDecorator{
        public DeflectionDecorator(Card card){ super(card);}
        
        /*non so come fare*/
       
       } 
      
    @Override
    public String name() {return "Deflection";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "Change the target of target spell with a single target";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}

