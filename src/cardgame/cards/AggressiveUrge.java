/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCard;
import cardgame.AbstractCardEffect;
import cardgame.AbstractCreatureDecorator;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.DecoratedCreature;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Targetable;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class AggressiveUrge implements Card{
        
        DecoratedCreature target; 
    
  private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new AggressiveUrge(); }
    }
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("AggressiveUrge",new Factory());
      
    private class AggressiveUrgeEffect extends AbstractCardEffect implements Targetable{
        public AggressiveUrgeEffect(Player p,Card c) { super(p,c); }
 
        @Override
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
        public boolean play() {
           pickTarget();
            return super.play();
        }      
        @Override
        public void resolve(){  
            
          target.addDecorator(d);
            System.out.println("creatura:"+target.name()+":"+target.getPower()+"/"+target.getToughness());
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new AggressiveUrgeEffect(owner,this);}
    
    private class AggressiveUrgeInstant extends AbstractCard {
        public AggressiveUrgeInstant(Player owner) {
            super(owner);
        }

        @Override
        public String name() {return "AggressiveUrge";}       
        
         private final TriggerAction removeaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                 //   target.getDecorator().removeLast();//rimuovo l'ultimo decoratore dalla lista
                //    target = target.getDecorator().getLast();//rimetto il decoratore precedente a quello che ho tolto
                }
            };      
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.END_FILTER, removeaction);
        }        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(removeaction);
        }
    }
    
    private class AggressiveUrgeDecorator extends AbstractCreatureDecorator{
        
        @Override
        public int getPower() {return c.getPower()+1;}
        @Override
        public int getToughness() {return c.getToughness()+1;}
    }
       
    @Override
    public String name() {return "AggressiveUrge";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "target creatures get +1/+1 until end of turn ";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}

