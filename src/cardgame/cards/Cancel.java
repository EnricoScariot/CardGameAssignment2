/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCard;
import cardgame.AbstractCardEffect;
import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.CardStack;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Witchcraft;
import java.util.Scanner;

/**
 *
 * @author Sara
 */

public class Cancel implements Card{
    
    private class Factory implements ICardFactory {
        @Override
        public Card create() { return new Cancel(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Cancel",new Factory());
      
    private class CancelEffect extends AbstractCardEffect {
        public CancelEffect(Player p,Card c) { super(p,c); }
        Effect target; 
        
        public void getTarget(){
            Scanner reader =  new Scanner(System.in);
            int idx,i = 1;    
            CardStack stack = CardGame.instance.getStack();
            
            System.out.println("Scegli il target di questa carta:" + name());
            for (Effect e:stack) {
                System.out.println( i + ") " + e.name());
                ++i;
            }                      
            idx = reader.nextInt()-1;          
            target = CardGame.instance.getStack().get(idx);    
        }
       
        public boolean play() {
            getTarget();
            return super.play();
        }
       
        @Override
        public void resolve(){ 
           System.out.println(" removing " + target.name() + " from stack");
            CardGame.instance.getStack().remove(target);        
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new CancelEffect(owner,this);}
    
    private class CancelInstant extends AbstractCard {
        public CancelInstant(Player owner) {
            super(owner);
        }

        @Override
        public String name() {return "Cancel";}
    }
   
       
    @Override
    public String name() {return "Cancel";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "Counter target spell";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
