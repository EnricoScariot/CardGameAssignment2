/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCard;
import cardgame.AbstractCardEffect;

import cardgame.Card;
import cardgame.CardFactory;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;


/**
 *
 * @author Sara
 */
public class Darkness implements Card{
       
     private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Darkness(); }
    }
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Darkness",new Factory());
      
    private class DarknessEffect extends AbstractCardEffect {
        public DarknessEffect(Player p,Card c) { super(p,c); }
           
        @Override
        public void resolve(){ 
         /*modifica la comabt phase*/
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new DarknessEffect(owner,this);}
    
    private class DarknessInstant extends AbstractCard {
        public DarknessInstant(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "Darkness";}
    }       
          
    @Override
    public String name() {return "Darkness";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "Prevent all combat damage that would be dealt this turn";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
} 

