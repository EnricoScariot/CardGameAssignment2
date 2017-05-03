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
import cardgame.AbstractCreatureDecorator;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.CreatureDecorator;
import cardgame.DecoratedCreature;
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
    DecoratedCreature target;
    Player targetPlayer;
    BenevolentAncestorDecorator af = new BenevolentAncestorDecorator(); // decoratore globale così posso usare bene la remove
    int selectedCreature=1; // valore booleano che diventa false nel caso in cui si decida di scegliere come target un giocatore
    
   
    private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new BenevolentAncestor(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("Benevolent Ancestor",new Factory());
        
        
 private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new BenevolentAncestorCreature(owner); }
        
       
        public boolean play() {
             int num;
                do{
                    System.out.println("vuoi selezionare come target una creatura (0) oppure un giocatore? (1)");
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 && num!=1);
            if(num==1){
                pickPlayer(); // seleziono il giocatore bersaglio
                selectedCreature=0; // metto a 0 il flag
                
            }
            else{
                pickTarget(); // seleziono la creatura bersaglio
            }
                     
            return super.play();
        }      
        @Override
        public void resolve(){  
            if(selectedCreature==1)
                 target.addDecorator(af);
            
            else
                targetPlayer.heal(1); // previeni un danno aumentando di 1 la sua vita
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
    
    private class BenevolentAncestorDecorator extends AbstractCreatureDecorator{
       
        @Override
       public void inflictDamage(int dmg) {
           c.inflictDamage(dmg-1);
       
       } // effetto della carta, previene un danno
      
    }
    
    public void pickPlayer(){
          int num;
                do{
                    System.out.println("vuoi selezionare come target te stesso o il giocatore avversario?");
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 && num!=1);
                
              if(num==1){
                targetPlayer=CardGame.instance.getCurrentPlayer();
                
            }
            else{
                targetPlayer=CardGame.instance.getCurrentAdversary();
            }   
         
         
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