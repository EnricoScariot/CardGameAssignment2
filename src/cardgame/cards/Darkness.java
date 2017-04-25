/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCard;
import cardgame.AbstractCardEffect;
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
public class Darkness implements Card{
        LinkedList<Creature> creature = new LinkedList();

    
     private class Factory implements ICardFactory {
        @Override
        public Card create() { return new Darkness(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Darkness",new Factory());
      
    private class AggressiveUrgeEffect extends AbstractCardEffect {
        public AggressiveUrgeEffect(Player p,Card c) { super(p,c); }
       
        public boolean play() {
            return super.play();
        }      
        @Override
        public void resolve(){ 
            
            creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures());
            
            for(Creature c: creature){
                c.getDecorator().addFirst(new CreatureDecorator(c));//aggiungo il decoratore di default in testa alla lista di decoratori
                c.getDecorator().addLast(new DarknessDecorator(c));
                c = c.getDecorator().peekLast();//aggiungo l'ultimo decoratore inserito alle creature
            }
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new AggressiveUrgeEffect(owner,this);}
    
    private class DarknessInstant extends AbstractCard {
        public DarknessInstant(Player owner) {
            super(owner);
        }
    

        @Override
        public String name() {return "Darkness";}       
        
         private final TriggerAction removeaction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    for(Creature c: creature){
                        c.getDecorator().removeLast();//rimuovo l'ultimo decoratore dalla lista                   
                        c = c.getDecorator().getLast();//rimetto il decoratore precedente a quello che ho tolto
                    }
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
    
    private class DarknessDecorator extends CreatureDecorator{
        public DarknessDecorator(Creature decorate){
            super(decorate);
       }
        
       /*rendo inefficace il metodo inflictDamage oppure il metodo che creerò?*/
    }
       
    @Override
    public String name() {return "Darkness";}
    @Override
    public String type() {return "Instant";}
    @Override
    public String ruleText() {return "Prevent all combat damage that would be dealt this turn";}
    @Override
    public boolean isInstant() {return true;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
} 

