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
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class AetherFlash implements Card{
    
     private class Factory implements ICardFactory {
        @Override
        public Card create() { return new AetherFlash(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Aether Flash ",new Factory());

    private class AetherFlashEffect extends AbstractEnchantmentCardEffect {
        public AetherFlashEffect(Player p,Card c) { super(p,c); }
        
        public void resolve(){}            
        @Override
        protected Enchantment createEnchantment() { return new AetherFlashEnchantment(owner); }
        }
 
    @Override
    public Effect getEffect(Player p) { return new AetherFlashEffect(p,this); }
    
    private class AetherFlashEnchantment extends AbstractEnchantment {
        public AetherFlashEnchantment(Player owner) {
            super(owner);
        }
        
        private final TriggerAction damageaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    Creature target;
                    /*prendi l'utima creatura inserita*/
                    target.inflictDamage(3);
                }
            };      
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, damageaction);
        }        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(damageaction);
        }
    @Override
    public String name() { return "Aether Flash"; }
    }
    @Override
    public String name() { return "Aether Flash ";}
    @Override
    public String type() {return "Enchantment";}
    @Override
    public String ruleText() {return "Whenever a creature comes into play,Aether Flash deals 2 damage to it";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
    

