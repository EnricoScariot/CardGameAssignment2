/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author atorsell
 */
public abstract class AbstractCreatureCardEffect extends AbstractCardEffect {
    protected AbstractCreatureCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Creature createCreature();
    Creature c = createCreature();
  
    
    
    @Override
    public void resolve() { 
        Creature c = createCreature();   // qua devo creare il decoratore iniziale e passarlo alla getCreatures 
        Player p = c.getOwner();//non sono molto sicura di ciò
        DecoratedCreature dc = new DecoratedCreature(p,c);
        
        owner.getCreatures().add(dc);//inserisce la creatura nell'elenco delle creature nel campo
        dc.insert();
    }
}
