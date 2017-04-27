/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Sara
 */

public class CreatureDecorator implements Creature{
    public Creature decorate;
    
    public CreatureDecorator(Creature decorate) {
        this.decorate = decorate; 
    }
   
    @Override
    public boolean tap() {return decorate.tap();}
    @Override
    public boolean untap() {return decorate.untap();}
    @Override
    public boolean isTapped() {return decorate.isTapped();}
    @Override
    public void attack() {decorate.attack();}
    @Override
    public void defend(Creature c) {decorate.defend(c);}
    @Override
    public void inflictDamage(int dmg) {decorate.inflictDamage(dmg);}
    @Override
    public void resetDamage() {decorate.resetDamage();}
    @Override
    public int getPower() {return decorate.getPower();}
    @Override
    public int getToughness() {return decorate.getToughness();}
    @Override
    public int getDamage() {return decorate.getDamage();}
    @Override
    public List<Effect> effects() {return decorate.effects();}
    @Override
    public List<Effect> avaliableEffects() {return decorate.avaliableEffects();}
    @Override
    public String name() {return decorate.name();}
    @Override
    public void insert() {decorate.insert();}
    @Override
    public void remove(){ decorate.remove();}
    //@Override
    //public LinkedList<CreatureDecorator> getDecorator() {return decorate.getDecorator();}
    @Override
    public Player getOwner() {return decorate.getOwner();}
    @Override
        public String toString() {
            return name() + " (Creature)";
        }
    @Override
    public LinkedList<CreatureDecorator> getDecorator() {return decorate.getDecorator();}
    @Override
    public Player getOwner() {return decorate.getOwner();}
    @Override
        public String toString() {
            return name() + " (Creature)";
        }
  
}
