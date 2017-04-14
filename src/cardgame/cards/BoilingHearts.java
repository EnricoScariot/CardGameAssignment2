/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;
import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.Witchcraft;
import java.util.List;

/**
 *
 * @author Sara
 */
public class BoilingHearts implements Card {
    
    /*DA FINIRE, MANCA PRATICAMENTE TUTTO*/

    private class BoilingHeartsEffect extends AbstractWitchcraftCardEffect {
        public BoilingHeartsEffect(Player p, Card c) { super(p,c); }
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
    
}
