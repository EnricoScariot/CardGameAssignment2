/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.CardFactory;
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
public class AncestralMask implements Card{
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new AncestralMask(); }
    }
    
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Ancestral Mask",new Factory());

    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect {
        public AncestralMaskEffect(Player p,Card c) { super(p,c); }
        @Override
        public void resolve(){
            LinkedList <Creature> creature = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures());
            for(Creature c: creature){
                c.getDecorator().addFirst(new CreatureDecorator(c));//aggiungo il decoratore di default in testa alla lista di decoratori
                c.getDecorator().addLast(new AncestralMaskDecorator(c));
                c = c.getDecorator().peekLast();
            }
        }           
        @Override
        protected Enchantment createEnchantment() { return new AncestralMaskEnchantment(owner); }   
    }
    @Override
    public Effect getEffect(Player p) { return new AncestralMaskEffect(p,this); }
    
    private class AncestralMaskEnchantment extends AbstractEnchantment {
        public AncestralMaskEnchantment(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Ancestral Mask"; } 
    @Override
     public void remove() {          
            LinkedList <Creature> creature = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures());
            for(Creature c: creature){
                AncestralMaskDecorator a = new AncestralMaskDecorator(c);
                c.getDecorator().remove(a);/*tolgo il decoratore specifico dalla lista*/
                c = c.getDecorator().peekLast();/*tolgo i decoratori dalla carta*/
                }
            owner.getEnchantments().remove(this);           
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_ENCHANTMENT_FILTER,this);
        }
    }
    
    private class AncestralMaskDecorator extends CreatureDecorator{
        public AncestralMaskDecorator(Creature decorate){
            super(decorate);
        }
        public int numeroIncantesimi(){
            LinkedList <Enchantment> enchantment = new LinkedList();
            enchantment.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
            enchantment.addAll(CardGame.instance.getCurrentAdversary().getEnchantments());
            int size = enchantment.size();
            return size-1;
        }
        @Override
        public int getPower() {return decorate.getPower()+(2*numeroIncantesimi());}
        @Override
        public int getToughness() {return decorate.getToughness()+(2*numeroIncantesimi());}
    }
    
    @Override
    public String name() { return "Ancestral Mask";}
    @Override
    public String type() {return "Enchantment";}
    @Override
    public String ruleText() {return "Creature gets +2/+2 for each other enchantment on the battlefield";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}    
}