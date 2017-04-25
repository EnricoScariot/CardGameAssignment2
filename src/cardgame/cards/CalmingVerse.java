/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Witchcraft;
import java.util.LinkedList;

/**
 *
 * @author Sara
 */
public class CalmingVerse implements Card {

   private class Factory implements ICardFactory {
        @Override
        public Card create() { return new CalmingVerse(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("CalmingVerse",new Factory());
      
    private class CalmingVerseEffect extends AbstractWitchcraftCardEffect {
        public CalmingVerseEffect(Player p,Card c) { super(p,c); }
       
        public boolean play() {
            return super.play();
        }
        
        @Override
        public void resolve(){ 
            LinkedList <Enchantment> enchantment = new LinkedList();
            enchantment.addAll(CardGame.instance.getCurrentAdversary().getEnchantments()); 
            
            for (Enchantment c : enchantment) {
                enchantment.remove();
                System.out.println(c.name() + " rimosso");
            }
        }
        @Override
        protected Witchcraft createWitchcraft() { return new CalmingVerseWitchcraft(owner); }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new CalmingVerseEffect(owner,this);}
    
       private class CalmingVerseWitchcraft extends AbstractWitchcraft {
        public CalmingVerseWitchcraft(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "CalmingVerse";}
    }
       
    @Override
    public String name() {return "CalmingVerse";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Destroy all enchantment you don't control";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
