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
public abstract class AbstractWitchcraftCardEffect extends AbstractCardEffect {
    protected AbstractWitchcraftCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Witchcraft createWitchcraft();
    
    @Override
    public void resolve() {
        Witchcraft w=createWitchcraft();
        owner.getWitchcraft().add(w); //inserisce la stregoneria nell'elenco delle stregonerie nel campo
        w.insert();
    }
}
