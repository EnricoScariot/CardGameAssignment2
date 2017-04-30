/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Triggers;
import java.util.LinkedList;

/**
 *
 * @author Sara
 */
public class Abduction implements Card{
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Abduction(); }
    }
    
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Abduction",new Factory());

    private class AbductionEffect extends AbstractEnchantmentCardEffect {
        public AbductionEffect(Player p,Card c) { super(p,c); }
        
        public void resolve(){
            LinkedList <Creature> creature = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures());
            for(Creature c: creature){
                c.getDecorator().addFirst(new CreatureDecorator(c));//aggiungo il decoratore di default in testa alla lista di decoratori
                c.getDecorator().addLast(new AbductionDecorator(c));
                c = c.getDecorator().peekLast();
                c.untap();/*stappo tutte le creature*/
            }           
        }           
        @Override
        protected Enchantment createEnchantment() { return new AbductionEnchantment(owner); }   
    }
    @Override
    public Effect getEffect(Player p) { return new AbductionEffect(p,this); }
    
    private class AbductionEnchantment extends AbstractEnchantment {
        public AbductionEnchantment(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Abduction"; } 
    @Override
    
     public void remove() {          
            LinkedList <Creature> creature = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures());
            for(Creature c: creature){
                AbductionDecorator a = new AbductionDecorator(c);
                c.getDecorator().remove(a);/*tolgo il decoratore specifico dalla lista*/
                c = c.getDecorator().peekLast();/*tolgo i decoratori dalla carta*/
                }
            owner.getEnchantments().remove(this);           
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_ENCHANTMENT_FILTER,this);
        }
    }
    
    private class AbductionDecorator extends CreatureDecorator{
        public AbductionDecorator(Creature decorate){
            super(decorate);
        }
        @Override
        public Player getOwner(){
            return CardGame.instance.getCurrentPlayer();
        }
    }   
    @Override
    public String name() { return "Abduction";}
    @Override
    public String type() {return "Enchantment";}
    @Override
    public String ruleText() {return "When abduction come into play untap creatures. You control creature";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}   
    
}
