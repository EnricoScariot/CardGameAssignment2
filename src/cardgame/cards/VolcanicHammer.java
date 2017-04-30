/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractWitchcraft;
import cardgame.AbstractWitchcraftCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardFactory.StaticInitializer;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.ICardFactory;
import cardgame.Player;
import cardgame.Targetable;
import cardgame.Witchcraft;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class VolcanicHammer implements Card{
    
private static class Factory implements ICardFactory {
        @Override
        public Card create() { return new BoilingHeart(); }
    }
        
    private static StaticInitializer initializer = new StaticInitializer("VolcanicHammer",new Factory());

    private class VolcanicHammerEffect extends AbstractWitchcraftCardEffect implements Targetable {
        public VolcanicHammerEffect(Player p,Card c) { super(p,c); }
        int idx;
        @Override
        public void pickTarget(){          
            Scanner reader =  new Scanner(System.in);
            System.out.println("Vuoi colpire le creature in campo o il tuo avversario?(1/2)");
            idx = reader.nextInt()-1;
        }
        
        @Override
        public void resolve(){ 
            if(idx == 1){
                LinkedList <Creature> creature = new LinkedList();
                creature.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
                creature.addAll(CardGame.instance.getCurrentAdversary().getCreatures()); 

                for (Creature c : creature) {
                    c.inflictDamage(3);
                    System.out.println("danno pari a 3 inflitto a:"+c.name());
                }
            }
            else if (idx == 2){
                CardGame.instance.getCurrentAdversary().inflictDamage(3);
                System.out.println("Danno pari a 3 inflitto a:"+ CardGame.instance.getCurrentAdversary().name());
            }
        }
        @Override
        protected Witchcraft createWitchcraft() { return new VolcanicHammerWitchcraft(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new VolcanicHammerEffect(p,this); }
    
    private class VolcanicHammerWitchcraft extends AbstractWitchcraft {
        public VolcanicHammerWitchcraft(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "VolcanicHammer"; }
    }
    @Override
    public String name() { return "VolcanicHammer";}
    @Override
    public String type() {return "Witchcraft";}
    @Override
    public String ruleText() {return "VolcanicHammer deals 3 damage to any creature or player";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
