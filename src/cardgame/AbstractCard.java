/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cards.*;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.NotPermanent;
import cardgame.Player;
import cardgame.Triggers;

/**
 *
 * @author Sara
 */
public abstract class AbstractCard implements NotPermanent{
    
     protected Player owner;
     
     protected AbstractCard(Player owner) { this.owner=owner; }
 
        public void insert() {
            CardGame.instance.getTriggers().trigger(Triggers.ENTER_INSTANT_FILTER,this);
        }
    
    
        public void remove() {
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_INSTANT_FILTER,this);
        }

}
