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
import cardgame.Witchcraft;
import cardgame.CardGame;
/**
 *
 * @author Sara
 */
public class BoilingHearts implements Card {

    private class BoilingHeartsEffect extends AbstractWitchcraftCardEffect {
        public BoilingHeartsEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){
             for (Creature c : owner.getCreatures()) {
                /*infliggi il danno a ogni creatura del giocatore*/
                c.inflictDamage(1);
            }
            for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                /*infliggi il danno a ogni creatura dell'avversario*/
                c.inflictDamage(1);
            }
        }
        @Override
        protected Witchcraft createWitchcraft() { return new BoilingHeartsWitchcraft(owner); }
    }
    
    @Override
    public Effect getEffect(Player p) { return new BoilingHeartsEffect(p,this); }
    
    private class BoilingHeartsWitchcraft extends AbstractWitchcraft {
        public BoilingHeartsWitchcraft(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Boiling Hearts"; }
    }
    @Override
    public String name() { return "Boiling Hearts";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Boiling Hearts deals 1 damage to each creature";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
