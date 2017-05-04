/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.LinkedList;

/**
 *
 * @author atorsell
 */
public class DefaultEndPhase implements Phase {
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        
        System.out.println(currentPlayer.name() + ": end phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.END_FILTER);
        
        LinkedList <DecoratedCreature> creature = new LinkedList();
        LinkedList <DecoratedCreature> creature1 = new LinkedList();
        creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
        creature1.addAll(CardGame.instance.getCurrentAdversary().getCreatures()); 
        
        for(DecoratedCreature c:creature) {
            if(c.getToughness() <= 0){
                c.remove();
                System.out.println("uccido c"+c.name());
            }
            else{
                System.out.println("...reset damage to " + c.name()+c.getPower()+"/"+c.getToughness());
                c.resetDamage();
            }
        }
        
        for(DecoratedCreature c:creature1) {
            if(c.getToughness() <= 0){
               c.remove();
               System.out.println("uccido c"+c.name());
        }
            else{
                System.out.println("...reset damage to adversary creature " + c.name()+c.getPower()+"/"+c.getToughness());
                c.resetDamage();
            }
        }
        /*elimino le stregonerie alla fine del turno*/
        for(Witchcraft w:CardGame.instance.getCurrentAdversary().getWitchcraft()){
            w.remove();
        }
    }
}
