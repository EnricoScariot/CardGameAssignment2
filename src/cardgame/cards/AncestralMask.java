/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreatureDecorator;
import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.CardFactory;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.DecoratedCreature;
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
public class AncestralMask implements Card{
    
    
    DecoratedCreature target; 
        AncestralMaskDecorator af = new AncestralMaskDecorator(); // decoratore globale così posso usare bene la remove
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new AncestralMask(); }
    }
    
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Ancestral Mask",new Factory());

    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect {
        public AncestralMaskEffect(Player p,Card c) { super(p,c); }
        @Override
        public void resolve(){
            target.addDecorator(af);
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
     public void remove() {          
           target.removeDecorator(af);
           CardGame.instance.getTriggers().deregister(maxaction);
        }
     private final TriggerAction maxaction = new TriggerAction() {
                @Override
                public void execute(Object args) {  
                   target.addDecorator(af);
                }
            };      
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER | Triggers.EXIT_CREATURE_FILTER, maxaction);
        }        
        @Override
        public String name() { return "Ancestral Mask"; } 
    }
    
    private class AncestralMaskDecorator extends AbstractCreatureDecorator{
        
        public int numeroIncantesimi(){
            LinkedList <Enchantment> enchantment = new LinkedList();
            enchantment.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
            enchantment.addAll(CardGame.instance.getCurrentAdversary().getEnchantments());
            int size = enchantment.size();
            return size-1;
        }
        @Override
        public int getPower() {return c.getPower()+(2*numeroIncantesimi());}
        @Override
        public int getToughness() {return c.getToughness()+(2*numeroIncantesimi());}
    }
    
     public void pickTarget(){
            Scanner reader =  new Scanner(System.in);
            LinkedList <DecoratedCreature> creature = new LinkedList();
            LinkedList <DecoratedCreature> creature2 = new LinkedList();
            creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            creature2.addAll(CardGame.instance.getCurrentAdversary().getCreatures()); 
            int idx,i=1;
            System.out.println("vuoi scegliere come target una tua creatura o una avversaria?0/1");
            idx = reader.nextInt();
            if(idx == 0){
                System.out.println("Scegli la creatura target:");
                System.out.println("Ceature del giocatore:"+ CardGame.instance.getCurrentPlayer().name());
                for (Creature c : creature) {
                    System.out.println(i +")"+c.name());
                    i++;
                }
            idx = reader.nextInt()-1;
            target = creature.get(idx);
            }else if (idx == 1){
                System.out.println("Scegli la creatura target:");
                System.out.println("Ceature del giocatore:"+ CardGame.instance.getCurrentAdversary().name());
                for (Creature c : creature2) {
                    System.out.println(i +")"+c.name());
                    i++;
             }
            idx = reader.nextInt()-1;  
            target = creature2.get(idx);
            }                 
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