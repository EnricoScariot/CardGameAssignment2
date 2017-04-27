/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */

public class Afflict implements Card{ 
   
    Creature target; 
     //aggiungo il decoratore di default in testa alla lista di decoratori
    
  private class Factory implements ICardFactory {
        @Override
        public Card create() { return new Afflict(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("CalmingVerse",new Factory());
      
    private class AfflictEffect extends AbstractCardEffect {
        public AfflictEffect(Player p,Card c) { super(p,c); }
 
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
            target.getDecorator().addFirst(new CreatureDecorator(target));//aggiungo il decoratore di default in testa alla lista di decoratori
            target.getDecorator().addLast(new AfflictDecorator(target));//aggiungo il decoratore di Afflict in fondo alla lista di decoratori
            target = target.getDecorator().peekLast();//aggiungo l'ultimo decoratore inserito al target
           /* if (target.getToughness() <= 0){
                target.remove();
                System.out.println("creature:"+target.name()+" removed");
            }
            else*/
              /*  System.out.println("creatura:"+target.name()+":"+target.getPower()+"/"+target.getToughness());*/
        }
        
    }
    
    @Override
    public Effect getEffect(Player owner) { return new AfflictEffect(owner,this);}
    
    private class AfflictInstant extends AbstractCard {
        public AfflictInstant(Player owner) {
            super(owner);
        }

        @Override
        public String name() {return "Afflict";}       
        
         private final TriggerAction removeaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    target.getDecorator().removeLast();//rimuovo l'ultimo decoratore dalla lista
                    target = target.getDecorator().getLast();//rimetto il decoratore precedente a quello che ho tolto
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
    
    private class AfflictDecorator extends CreatureDecorator{
        public AfflictDecorator(Creature decorate){
            super(decorate);
        }
        @Override
        public int getPower() {return decorate.getPower()-1;}
        @Override
        public int getToughness() {return decorate.getToughness()-1;}
    }
       
    @Override
    public String name() {return "Afflict";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "target creatures get -1/-1 until end of turn ";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
