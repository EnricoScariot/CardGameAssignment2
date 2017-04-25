/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;
import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author skari
 */
public class BenevolentAncestor implements Card {
    Creature target;
    private class Factory implements ICardFactory {
        @Override
        public Card create() { return new BenevolentAncestor(); }
    }
        
    private StaticInitializer initializer = new StaticInitializer("Benevolent Ancestor",new Factory());
        
        
 private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new BenevolentAncestorCreature(owner); }
        
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
            target.getDecorator().addLast(new BenevolentAncestor.BenevolentAncestorDecorator(target));//aggiungo il decoratore di Afflict in fondo alla lista di decoratori
            target = target.getDecorator().peekLast();//aggiungo l'ultimo decoratore inserito al target
        } 
        
    }
    @Override
    public Effect getEffect(Player p) { return new BenevolentAncestorEffect(p,this); }
    
    
    private class BenevolentAncestorCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        BenevolentAncestorCreature(Player owner) { 
            super(owner);
        }
        
        @Override
        public String name() { return "BenevolentAncestor"; }
        
        @Override
        public void attack() {}
        @Override
        public void defend(Creature c) {}
        @Override
        public int getPower() { return 0; }
        @Override
        public int getToughness() { return 4; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    private class BenevolentAncestorDecorator extends CreatureDecorator{
        public BenevolentAncestorDecorator(Creature decorate){
            super(decorate);
        }
        @Override
       public void inflictDamage(int dmg) { decorate.inflictDamage(dmg-1);} // effetto della carta, previene un danno
      
    }
    
    @Override
    public String name() { return "Benevolent Ancestor"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature Benevolent Ancestor (0/4)"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }

}