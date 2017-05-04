/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCard;
import cardgame.AbstractCardEffect;

import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.CardStack;
import cardgame.Creature;
import cardgame.DecoratedCreature;
import cardgame.Effect;
import cardgame.Functions;
import cardgame.ICardFactory;
import cardgame.Phase;
import cardgame.Player;
import cardgame.Triggers;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Sara
 */
public class Darkness implements Card{
       
     private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new Darkness(); }
    }
        
    private static CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Darkness",new Factory());
      
    private class DarknessEffect extends AbstractCardEffect {
        public DarknessEffect(Player p,Card c) { super(p,c); }
           
        @Override
        public void resolve(){ 
         /*modifica la comabt phase*/
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new DarknessEffect(owner,this);}
    
    private class DarknessInstant extends AbstractCard {
        public DarknessInstant(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "Darkness";}
    }
    
    private class DummyCombatPhase implements Phase{
           ArrayList<DecoratedCreature> defenders=new ArrayList<>();
    Player currentPlayer;
    Player adversary;
    Player activePlayer;
    CardStack stack; 
    ArrayList<DecoratedCreature> could_be_attackers=new ArrayList<>(); //creature che potrebbero attaccare
    ArrayList<DecoratedCreature> attackers=new ArrayList<>(); // creature che attaccano     
    ArrayList<DecoratedCreature> could_be_defenders= new ArrayList<>();
    
    
    @Override
    public void execute() {
        currentPlayer = CardGame.instance.getCurrentPlayer();
        adversary = CardGame.instance.getCurrentAdversary();
        stack= CardGame.instance.getStack(); // stack da riempire e svuotare  
               
        System.out.println(currentPlayer.name() + ": combat phase");
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);

        dicAttaccanti(); 
        stackResolve(adversary,currentPlayer);
        dicDifensori();
        stackResolve(currentPlayer,adversary);
        distDanno();
    
    
    System.out.println( currentPlayer.name() + " ha "+currentPlayer.getLife() +"punti vita");            
    System.out.println( adversary.name()+ " ha "+adversary.getLife() +"punti vita"); 
    
    }
 
    void dicAttaccanti(){
        //Dichiarazione Creature Attaccanti
         if(currentPlayer.getCreatures().isEmpty()) 
              System.out.println("Il Giocatore " + currentPlayer.name() + " non ha attaccanti"); 
        else{ 
          System.out.println("Il Giocatore " + currentPlayer.name() + " sceglie gli attaccanti");
            
            int i=1;
            for (DecoratedCreature c:currentPlayer.getCreatures()) {
                if (!c.isTapped()) {
                    System.out.println(i + ") " + c.name());
                    could_be_attackers.add(c);
                    ++i;
                }
            }
            
         i=0;
         int[] attackers_boolean=new int[could_be_attackers.size()];
            while(i<attackers_boolean.length){
                System.out.println("vuoi che la creatura " + could_be_attackers.get(i).name() + " sia uno degli attaccanti (0/1)");
                int num;
                do{
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 && num!=1);
                
                 attackers_boolean[i]=num; 
                 if(attackers_boolean[i]==1){
                     attackers.add(could_be_attackers.get(i));                  
                 }
               i++;      
            }          
        }   
    }
    void dicDifensori(){
        
        if(adversary.getCreatures().isEmpty())
              System.out.println("Il Giocatore " + adversary.name() + " non ha difensori");
        else if(could_be_attackers.isEmpty())
            System.out.println("Non ci sono attaccanti da cui difendersi");
        else{
        System.out.println("Il Giocatore " + currentPlayer.name() + " sceglie i difensori");         
            int i=0;
            for (DecoratedCreature c:adversary.getCreatures()) {
                if (!c.isTapped()) {
                    System.out.println(i + ") " + c.name());
                    could_be_defenders.add(c);
                    i++;
                }
            }
            i = 0;       
            while(i < could_be_defenders.size()){
                System.out.println("vuoi che la creatura " + could_be_defenders.get(i).name() + " difenda una delle creature attaccanti? (0/1)");
                int num;
                do{
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 && num!=1);
                
                if(num==1){
                    int j = 0;
                     do{                 
                    System.out.println("da quale attaccante vuoi difenderti? schegli un numero tra 0 e" + attackers.size() + "-1");   
                    for(Creature c:attackers){
                        System.out.println(j+")"+c.name());
                        j++;
                    }
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                    } while(num<0 && num>=attackers.size());                   
                     attackers.get(num).defend(could_be_defenders.get(i));// ho aggiunto al mio array di difensori la creatura
               }               
              i++;  
             }
        }
    }
    void distDanno(){}
    void stackResolve(Player avversario,Player giocatore){
        System.out.println( avversario.name() + " puoi rispondere mettendo un effetto nello stack"); 
        int numberPasses=0;
        if (!Functions.playAvailableEffect(avversario, false))
            ++numberPasses;
        
        while (numberPasses<2) {
            if (Functions.playAvailableEffect(giocatore,false))
                numberPasses=0;
            else ++numberPasses;
        }
        
        CardGame.instance.getStack().resolve();
    
    }
               
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

