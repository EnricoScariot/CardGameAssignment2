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
import cardgame.PhaseManager;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;
import cardgame.Witchcraft;

/**
 *
 * @author Sara
 */

 /*da completare(non ho fatto ancora nulla)*/  
public class WorldAtWar implements Card{
    
    private class WorldAtWarEffect extends AbstractWitchcraftCardEffect {
        public WorldAtWarEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){ 
        }
        @Override
        protected Witchcraft createWitchcraft() { return new WorldAtWarWitchcraft(owner); }
    }
    
    @Override
    public Effect getEffect(Player owner) { return new WorldAtWarEffect(owner,this);}
    
       private class WorldAtWarWitchcraft extends AbstractWitchcraft {
        public WorldAtWarWitchcraft(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "WorldAtWar";}
    }
  
    private class DoubleCombatPhaseManager implements PhaseManager{  

        @Override
        public Phases currentPhase() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Phases nextPhase() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }   
       
       
       
    @Override
    public String name() {return "WorldAtWar";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "After the first postcombat main phase this turn ,there's an additional "
            + "combat phase followed by an additional main phase. At the beginning of that combat, untap all"
            + "creatures that attacked this turn";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
}
