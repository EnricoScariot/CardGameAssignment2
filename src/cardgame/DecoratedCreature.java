/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author skari
 */
public class DecoratedCreature implements Creature {

    private Creature c;
    private Player owner;

    public DecoratedCreature(Player p,Creature c) {
        this.c = c;
        owner = p;
    }
     public void addDecorator(CreatureDecorator d){
        c=d.decorate(c);
        
    }
    public void removeDecorator(CreatureDecorator d){
       if (c instanceof CreatureDecorator) {
           CreatureDecorator cd = (CreatureDecorator) c;
           c = cd.remove(d);
       }    
    }
    
    @Override
    public boolean tap() {
        return c.tap();
    }

    @Override
    public boolean untap() {
        return c.untap();
    }

    @Override
    public boolean isTapped() {
        return c.isTapped();
    }

    @Override
    public void attack() {
        c.attack();
    }

    @Override
    public void defend(DecoratedCreature d) {
       c.defend(d);
    }

    @Override
    public void inflictDamage(int dmg) {
       c.inflictDamage(dmg);
    }

    @Override
    public void resetDamage() {
       c.resetDamage();
    }

    @Override
    public int getPower() {
        return c.getPower();
    }

    @Override
    public int getToughness() {
       return c.getToughness();
    }

    @Override
    public int getDamage() {
        return c.getDamage();
    }

    @Override
    public Player getOwner() {
        return c.getOwner();
    }

    @Override
    public List<Effect> effects() {
       return c.effects();
    }

    @Override
    public List<Effect> avaliableEffects() {
       return c.avaliableEffects();
    }

    @Override
    public ArrayList<DecoratedCreature> defenders() {
       return c.defenders();
    }

    @Override
    public String name() {
       return c.name();
    }

    @Override
    public void insert() {
       c.insert();
    }

    @Override
    public void remove() {
        owner.getCreatures().remove(this); 
        System.out.println("removing " + name() + " from field");
        CardGame.instance.getTriggers().trigger(Triggers.EXIT_CREATURE_FILTER);      
    }

    @Override
   public String toString() {
            return name() + " (Creature)";
        }
    
}
