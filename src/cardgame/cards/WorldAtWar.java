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
import cardgame.Creature;
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
            CardGame.instance.getCurrentPlayer().setPhaseManager(new DoubleCombatPhaseManager());
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
        int id = 0;
        @Override
        public Phases currentPhase() {
            Phases phase;        
            switch(id){
                case 0:phase = Phases.MAIN;break;
                case 1:phase = Phases.COMBAT;break;
                case 2:phase = Phases.MAIN;break;
                default:CardGame.instance.getCurrentPlayer().removePhaseManager(this);
                phase = CardGame.instance.getCurrentPlayer().currentPhaseId();
            }
            return phase;
        }
       

        @Override
        public Phases nextPhase() {
            id = id + 1;
            if(id > 2){
                CardGame.instance.getCurrentPlayer().removePhaseManager(this);
                System.out.println("DoubleCombatPhaseManager removed");
                return CardGame.instance.getCurrentPlayer().currentPhaseId().next();/*prende la prossima fase nell'ordine normale*/
            }
            else if (id == 1){
                System.out.println("Untapping creatures before new Combat Phase");
                for(Creature c:CardGame.instance.getCurrentPlayer().getCreatures())
                    c.untap();
                    }
                return currentPhase();
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
