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
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Permanent;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class AetherBarrier implements Card{
        
     private class Factory implements ICardFactory {
        @Override
        public Card create() { return new AetherBarrier(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("AetherBarrier",new Factory());

    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect {
        public AetherBarrierEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){
            System.out.println("Whenever a player plays a creature spell, that player sacrifices a permanent");
        }            
        @Override
        protected Enchantment createEnchantment() { return new AetherBarrierEnchantment(owner); }
        }
 
    @Override
    public Effect getEffect(Player p) { return new AetherBarrierEffect(p,this); }
    
    private class AetherBarrierEnchantment extends AbstractEnchantment {
        public AetherBarrierEnchantment(Player owner) {
            super(owner);
        }
        
        private final TriggerAction discardaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    //if(args.instanceOf(Creature)){
                    LinkedList<Permanent> permanent= new LinkedList();
                    Player owner = CardGame.instance.getCurrentPlayer();
                   // permanent.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
                    permanent.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
                    int i = 0,idx;
                    Scanner reader = new Scanner(System.in);
                    if (permanent.isEmpty())
                        System.out.println("Player "+ owner.name()+"has no permanent");
                    else{
                        System.out.println("Select a permanent to sacrifice");
                        for(Permanent e: permanent){
                            System.out.println(i+")"+e.name());
                            i++;
                            }
                        idx = reader.nextInt();
                        permanent.remove(idx);
                    }
                    //}
                }
            };      
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, discardaction);
        }        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(discardaction);
        }
    @Override
    public String name() { return "Aether Barrier"; }
    }
    @Override
    public String name() { return "Aether Barrier";}
    @Override
    public String type() {return "Enchantment";}
    @Override
    public String ruleText() {return "Whenever a player plays a creature spell, that player sacrifices a permanent";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}

