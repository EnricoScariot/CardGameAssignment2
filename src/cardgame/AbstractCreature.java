/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author atorsell
 */
public abstract class AbstractCreature implements Creature {
   
    protected Player owner;
    protected boolean isTapped=false;
    protected int damageLeft = getToughness();
    
    ArrayList<DecoratedCreature> difensori=new ArrayList<>(); // array delle creature che difendono l'attacco di this
   
        
    public AbstractCreature(Player owner) { 
            this.owner=owner;
        }
     
    @Override
        public boolean tap() { 
            if (isTapped) {
                System.out.println("creature " + name() + " already tapped");
                return false;
            }
            
            System.out.println("tapping creature " + name());
            isTapped=true; 
            return true; 
        }
        
    @Override
        public boolean untap() { 
            if (!isTapped) {
                System.out.println("creature " + name() + " not tapped");
                return false;
            }
            
            System.out.println("untapping creature " + name());
            isTapped=false; 
            return true; 
        }
    @Override   
    public int getDamage() { return this.getToughness()-this.damageLeft; }
        
    @Override
        public boolean isTapped() { return isTapped; }
        
   
      @Override
        public void attack() {
            isTapped=true;
        } 
    @Override
        public void defend(DecoratedCreature c) { // questa creatura e' difesa dalla creatura c
            difensori.add(c);             
        } 
        @Override
        public void inflictDamage(int dmg) {
            damageLeft -= dmg; 
            if (damageLeft<=0)
                owner.destroy(this);      
        }
        
        
    @Override
        public void resetDamage() { damageLeft = getToughness(); }
    @Override
        public void insert() {
            CardGame.instance.getTriggers().trigger(Triggers.ENTER_CREATURE_FILTER,this);
        }  
    @Override
        public void remove() {/*le creature non dovrebbero mai chiamarlo*/}  
    @Override
        public String toString() {
            return name() + " (Creature)";
        }
    @Override
        public Player getOwner(){
            return owner;
        }   
    @Override
       public ArrayList<DecoratedCreature> defenders(){
           return difensori;
       }  
      
     
        
}
