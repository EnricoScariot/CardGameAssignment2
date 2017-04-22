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
import cardgame.CardFactory.StaticInitializer;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.Witchcraft;
import cardgame.CardGame;
import cardgame.ICardFactory;
import java.util.LinkedList;
/**
 *
 * @author Sara
 */

public class BoilingHeart implements Card {
    
   private class Factory implements ICardFactory {
        @Override
        public Card create() { return new BoilingHeart(); }
    }
        
    private StaticInitializer initializer = new StaticInitializer("Boiling Heart",new Factory());

    private class BoilingHeartEffect extends AbstractWitchcraftCardEffect {
        public BoilingHeartEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){
            LinkedList <Creature> creature = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures()); 
            
            for (Creature c : creature) {
                c.inflictDamage(1);
                System.out.println("danno pari a 1 inflitto a:"+c.name());
            }

        }
        @Override
        protected Witchcraft createWitchcraft() { return new BoilingHeartWitchcraft(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new BoilingHeartEffect(p,this); }
    
    private class BoilingHeartWitchcraft extends AbstractWitchcraft {
        public BoilingHeartWitchcraft(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Boiling Heart"; }
    }
    @Override
    public String name() { return "Boiling Heart";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Boiling Hearts deals 1 damage to each creature";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
