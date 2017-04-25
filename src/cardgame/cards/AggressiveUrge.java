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
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class AggressiveUrge implements Card{
        LinkedList<CreatureDecorator> decorate = new LinkedList();//lista di decoratori
        Creature target; 
    
  private class Factory implements ICardFactory {
        @Override
        public Card create() { return new AggressiveUrge(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("CalmingVerse",new Factory());
      
    private class AggressiveUrgeEffect extends AbstractEnchantmentCardEffect {
        public AggressiveUrgeEffect(Player p,Card c) { super(p,c); }
 
        public void getTarget(){
            Scanner reader =  new Scanner(System.in);
            LinkedList <Creature> creature = new LinkedList();
            LinkedList <Creature> creature2 = new LinkedList();
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
            getTarget();
            return super.play();
        }      
        @Override
        public void resolve(){  
            decorate.addFirst(new CreatureDecorator(target));//aggiungo il decoratore di default in testa alla lista di decoratori
            decorate.addLast(new AggressiveUrgeDecorator(target));//aggiungo il decoratore di Afflict in fondo alla lista di decoratori
            target = decorate.peekLast();//aggiungo l'ultimo decoratore inserito al target
            System.out.println("creatura:"+target.name()+":"+target.getPower()+"/"+target.getToughness());
        }
        @Override
        protected Enchantment createEnchantment() { return new AfflictEnchantment(owner); }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new AggressiveUrgeEffect(owner,this);}
    
    private class AfflictEnchantment extends AbstractEnchantment {
        public AfflictEnchantment(Player owner) {
            super(owner);
        }

        @Override
        public String name() {return "AggressiveUrge";}       
        
         private final TriggerAction removeaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    decorate.removeLast();//rimuovo l'ultimo decoratore dalla lista
                    target = decorate.getLast();//rimetto il decoratore precedente a quello che ho tolto
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
    
    private class AggressiveUrgeDecorator extends CreatureDecorator{
        public AggressiveUrgeDecorator(Creature decorate){
            super(decorate);
        }
        @Override
        public int getPower() {return decorate.getPower()+1;}
        @Override
        public int getToughness() {return decorate.getToughness()+1;}
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

