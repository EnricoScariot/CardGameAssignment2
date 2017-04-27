/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {
    
    Creature attacker;
    ArrayList<Creature> defenders=new ArrayList<>();
    
    
    
    @Override
    public void execute() {
               
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        Player adversary = CardGame.instance.getCurrentAdversary();
        Player activePlayer;
        CardStack stack= CardGame.instance.getStack(); // stack da riempire e svuotare
        
        ArrayList<Creature> could_be_attackers=new ArrayList<>(); //creature che potrebbero attaccare
        ArrayList<Creature> attackers=new ArrayList<>(); // creature che attaccano
        int[] attackers_boolean=new int[could_be_attackers.size()];
        
        ArrayList<Creature> could_be_defenders= new ArrayList<>();
        
        
        
        
        
        System.out.println(currentPlayer.name() + ": combat phase");
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        // TODO combat
        //commento
        //Dichiarazione Creature Attaccanti
        
          System.out.println("Il Giocatore " + currentPlayer.name() + " sceglie gli attaccanti");
            
            int i=1;
            for (Creature c:currentPlayer.getCreatures()) {
                if (!c.isTapped()) {
                    System.out.println(i + ") " + c);
                    could_be_attackers.add(c);
                    ++i;
                }
            }
            
           i=0;
            
            while(i<attackers_boolean.length){
                System.out.println("vuoi che la creatura " + could_be_attackers.get(i).name() + " sia uno degli attaccanti (0/1)");
                int num;
                do{
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 || num!=1);
                
                 attackers_boolean[i]=num; 
                 if(attackers_boolean[i]==1){
                     attackers.add(could_be_attackers.get(i));
                    
                     
                 }
               i++;      
            }
            
            for (Creature c: attackers){
                c.attack();
                
            }
            
            // inizia il botta e risposta tra current player ed adversary con effetti ecc che vengono inseriti nello stack
         
        System.out.println( adversary.name().toString() + " puoi rispondere mettendo un effetto nello stack"); 
        int numberPasses=0;
        int id=0;
        if (!Functions.playAvailableEffect(currentPlayer, true))
            ++numberPasses;
        
        while (numberPasses<2) {
            if(id==0)
                    activePlayer=adversary;
                else
                    activePlayer=currentPlayer;
            if (Functions.playAvailableEffect(activePlayer,false))
                numberPasses=0;
            else ++numberPasses;
            
            id=(id+1)%2;
        }
        
        CardGame.instance.getStack().resolve();
        
              
        //Dichiarazione Creature difendenti
       // array di potenziali difensori-> per ogni attaccante decido se e con cosa contrattaccare prendendo le robe dall'array di potenziali difensori
        
        System.out.println("Il Giocatore " + currentPlayer.name() + " sceglie i difensori");
            
            i=0;
            for (Creature c:adversary.getCreatures()) {
                if (!c.isTapped()) {
                    System.out.println(i + ") " + c);
                    could_be_defenders.add(c);
                    ++i;
                }
            }
            
            while(i<could_be_defenders.size()){
                System.out.println("vuoi che la creatura " + could_be_defenders.get(i).name().toString() + " difenda una delle creature attaccanti? (0/1)");
                int num;
                do{
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                } while(num!=0 || num!=1);
                
                if(num==1){
                    System.out.println("da quale attaccante vuoi difenderti? schegli un numero tra 0 e" + attackers.size() + "-1");
                     do{
                    Scanner in = new Scanner(System.in);
                    num = in.nextInt();
                    
                    } while(num<0 && num>=attackers.size());
                   
                     attackers.get(num).defend(could_be_defenders.get(i));// ho aggiunto al mio array di difensori la creatura
               }
                
              i++;  
             }
            
            
            
       
       
            // botta e risposta tra i 2 giocatori mettendo magie nello stack
            
              
        System.out.println( currentPlayer.name().toString() + " puoi rispondere mettendo un effetto nello stack"); 
        numberPasses=0;
        id=1;
        if (!Functions.playAvailableEffect(currentPlayer, true))
            ++numberPasses;
        
        while (numberPasses<2) {
            if(id==0)
                    activePlayer=adversary;
                else
                    activePlayer=currentPlayer;
            if (Functions.playAvailableEffect(activePlayer,false))
                numberPasses=0;
            else ++numberPasses;
            
            id=(id+1)%2;
        }
        
        CardGame.instance.getStack().resolve();
            
        
        //Distribuzione del Danno
  
    for( Creature c: attackers){
        
        if(c.defenders().size()==0){ // nessun difensore sta bloccando questa creatura quindi il danno va all'avversario
            int playerlife=adversary.getLife()-c.getPower();
            adversary.setLife(playerlife);
        }
        
        else{ // il danno viene distribuito tra le creature che difenono
            int totale_danni_attaccante=c.getPower();
            int totale_danni_difensore=0;
            
            for(Creature d: c.defenders()){
                totale_danni_difensore+=d.getDamage();
            }
         
            
            // devo vedere se muore anche qualche difensore
            
            for( Creature d: c.defenders()){
                if(totale_danni_attaccante>d.getToughness()){
                    totale_danni_attaccante-=d.getDamage();
                    d.remove();
                }
                else
                    totale_danni_attaccante-=d.getDamage();
                    
            }
                           
            if(totale_danni_difensore>=c.getToughness()) // se il danno è maggiore, rimuovi la creatura poichè muore
                c.remove();
            
        }
        
               
        System.out.println( currentPlayer.name() + " ha "+currentPlayer.getLife() +"punti vita"); 
               
        System.out.println( currentPlayer.name()+ " ha "+adversary.getLife() +"punti vita"); 
        
    }
    
    
    
    
    
    }
            
            
            
          
            
      
        
        
        
        
        
        
        
        
       
    
}
