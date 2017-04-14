/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Sara
 */
public class AbstractWitchcraft implements Witchcraft{
    protected Player owner;        
    protected AbstractWitchcraft(Player owner) { this.owner=owner; }
        
    @Override
        public void insert() {
            CardGame.instance.getTriggers().trigger(Triggers.ENTER_WITCHCRAFT_FILTER,this);
        }
    
    @Override
        public void remove() {
            owner.getWitchcraft().remove(this);
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_WITCHCRAFT_FILTER,this);
        }
        
    @Override
        public String toString() {
            return name() + " (Witchcraft)";    /*name va implementato nelle carte stregoneria con una classe astratta*/
        }
}
