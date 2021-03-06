/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.cards.Afflict;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author atorsell
 */
public interface Creature extends Permanent{
    
    boolean tap();
    boolean untap();
    boolean isTapped();
    void attack();
    void defend(DecoratedCreature c);
    void inflictDamage(int dmg);
    void resetDamage();
    int getPower();
    int getToughness();
    int getDamage();
    Player getOwner();
   
    
    // returns all the effects associated to this creature
    List<Effect> effects();
    
    // returns only the effects that can be played currently
    // depending on state, e.g., tapped/untapped
    List<Effect> avaliableEffects();
    
     ArrayList<DecoratedCreature> defenders(); // restituisce l'array delle creature che vogliono parare l'attacco della mia creatura

   
}
