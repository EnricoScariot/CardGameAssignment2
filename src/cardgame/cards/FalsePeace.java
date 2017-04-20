/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;
import cardgame.Witchcraft;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class FalsePeace implements Card{
          
    private class FalsePeaceEffect extends AbstractWitchcraftCardEffect {
        public FalsePeaceEffect(Player p,Card c) { super(p,c); }
        Player target;
        
         public void getTarget(){
            Scanner reader =  new Scanner(System.in);
            int idx;          
            do{
            System.out.println("Scegli quale giocatore salterà la combat phase(1/2). Tu sei il giocatore" + CardGame.instance.getCurrentPlayer());
            idx = reader.nextInt()-1;
            }while(idx < 0 || idx > 1);
            target = CardGame.instance.getPlayer(idx);    
        }
        public boolean play() {
            getTarget();
            return super.play();
        }
        
        @Override
        public void resolve(){ 
            target.setPhase(Phases.COMBAT, new SkipPhase(Phases.COMBAT));
            System.out.println("il giocatore"+target.name()+"salterà la combat phase");
        }
        

        @Override
        protected Witchcraft createWitchcraft() { return new FalsePeaceWitchcraft(owner); }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new FalsePeaceEffect(owner,this);}
    
       private class FalsePeaceWitchcraft extends AbstractWitchcraft {
        public FalsePeaceWitchcraft(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "FalsePeace";}
    }
       
    @Override
    public String name() {return "FalsePeace";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Target player skips her or him next Combat phase";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
}


