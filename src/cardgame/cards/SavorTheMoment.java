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
import cardgame.TurnManager;
import cardgame.Witchcraft;
/**
 *
 * @author Sara
 */
public class SavorTheMoment implements Card{
   
    
    private class SavorTheMomentEffect extends AbstractWitchcraftCardEffect {
        public SavorTheMomentEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){ 
            CardGame.instance.getCurrentPlayer().setPhase(Phases.UNTAP, new SkipPhase(Phases.UNTAP));
            CardGame.instance.setTurnManager(new ExtraTurnManager());
        }
        @Override
        protected Witchcraft createWitchcraft() { return new SavorTheMomentWitchcraft(owner); }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new SavorTheMomentEffect(owner,this);}
    
       private class SavorTheMomentWitchcraft extends AbstractWitchcraft {
        public SavorTheMomentWitchcraft(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "Savor The Moment";}
    }
       
    @Override
    public String name() {return "Savor The Moment";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Take an extra turn after this one. Skip the untap step of that turn.";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
 
    /*turn manager modificato*/
    private class ExtraTurnManager implements TurnManager{
        private Player player;
        private Player adversary;
        int currentPlayerIdx=1;

        public ExtraTurnManager() {
            player = CardGame.instance.getCurrentPlayer();
            adversary = CardGame.instance.getCurrentAdversary();
        }

        @Override
        public Player getCurrentPlayer() { return player; }

        @Override
        public Player getCurrentAdversary() { return adversary; }

        @Override
        public Player nextPlayer() { 
            CardGame.instance.removeTurnManager(this);
            return getCurrentPlayer();
        }      
    }
}
