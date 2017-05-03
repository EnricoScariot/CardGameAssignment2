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
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.DecoratedCreature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Triggers;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class Abduction implements Card{
    
    DecoratedCreature target; 
    AbductionDecorator af= new AbductionDecorator();
    
    
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Abduction(); }
    }
    
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Abduction",new Factory());

    private class AbductionEffect extends AbstractEnchantmentCardEffect {
        public AbductionEffect(Player p,Card c) { super(p,c); }
        
        public void resolve(){
           pickTarget();
           target.untap(); // quando l'incanta creatura entra in gioco, stappa la creatura bersaglio
           target.addDecorator(af);
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
           target.removeDecorator(af);
        }
    }
    
    private class AbductionDecorator extends AbstractCreatureDecorator{
       
        @Override
        public Player getOwner(){
            
            return CardGame.instance.getCurrentPlayer();
        }
        
        
    }   
    
      public void pickTarget(){
            Scanner reader =  new Scanner(System.in);
            int idx,i=1;
            System.out.println("vuoi scegliere come target una tua creatura o una avversaria?0/1");
            idx = reader.nextInt();
            if(idx == 0){
                System.out.println("Scegli la creatura target:");
                System.out.println("Ceature del giocatore:"+ CardGame.instance.getCurrentPlayer().name());
                for (Creature c : CardGame.instance.getCurrentPlayer().getCreatures()) {
                    System.out.println(i +")"+c.name());
                    i++;
                }
            idx = reader.nextInt()-1;
            target = CardGame.instance.getCurrentPlayer().getCreatures().get(idx);
            }else if (idx == 1){
                System.out.println("Scegli la creatura target:");
                System.out.println("Ceature del giocatore:"+ CardGame.instance.getCurrentAdversary().name());
                for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                    System.out.println(i +")"+c.name());
                    i++;
             }
            idx = reader.nextInt()-1;  
            target = CardGame.instance.getCurrentAdversary().getCreatures().get(idx);
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
