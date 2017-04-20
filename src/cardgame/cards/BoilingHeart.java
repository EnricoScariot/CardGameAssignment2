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
/*NON FUNZIONA*/
public class BoilingHeart implements Card {

    private class BoilingHeartEffect extends AbstractWitchcraftCardEffect {
        public BoilingHeartEffect(Player p,Card c) { super(p,c); }
        
        @Override
        public void resolve(){
            
            for (Creature c : owner.getCreatures()) {
                /*infliggi il danno a ogni creatura del giocatore*/
                c.inflictDamage(1);
                System.out.println("danni al giocatore fatti");
            }
            for (Creature c : CardGame.instance.getCurrentAdversary().getCreatures()) {
                /*infliggi il danno a ogni creatura dell'avversario*/
                c.inflictDamage(1);
                System.out.println("danni all'avversario fatti");
            }
        }
        @Override
        protected Witchcraft createWitchcraft() { return new BoilingHeartWitchcraft(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new BoilingHeartEffect(p,this); }
    
    private class BoilingHeartWitchcraft extends AbstractWitchcraft {
        public BoilingHeartWitchcraft(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Boiling Heart"; }
    }
    @Override
    public String name() { return "Boiling Heart";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "Boiling Hearts deals 1 damage to each creature";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
