/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.CardGame;
import cardgame.Witchcraft;

/**
 *
 * @author Sara
 */

/*NON FUNZIONA*/
public class DayOfJudgment implements Card{
    
     private class DayOfJudgmentEffect extends AbstractWitchcraftCardEffect {
        public DayOfJudgmentEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){    
            
            for (Creature c : owner.getCreatures()) {
                c.remove();
                System.out.println("carta distrutta"+c.name());
            }
            for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                c.remove();
                System.out.println("creatura distrutta:"+c.name());
            }
        }
        @Override
        protected Witchcraft createWitchcraft() { return new DayOfJudgmentWitchcraft(owner); }
    }

    @Override
    public Effect getEffect(Player owner) { return new DayOfJudgmentEffect(owner,this);}
    
       private class DayOfJudgmentWitchcraft extends AbstractWitchcraft {
        public DayOfJudgmentWitchcraft(Player owner) {
            super(owner);
        }
        @Override
        public String name() {return "Day Of Judgment";}
    }
 
    @Override
    public String name() { return "Day Of Judgment";}
    @Override
    public String type() { return "Witchcraft";}
    @Override
    public String ruleText() { return "Destroy all creatures";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
