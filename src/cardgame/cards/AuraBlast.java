/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardFactory;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.ICardFactory;
import cardgame.Player;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Sara
 */
public class AuraBlast implements Card{
    private class Factory implements ICardFactory {
        @Override
        public Card create() { return new AuraBlast(); }
    }
        
    private CardFactory.StaticInitializer initializer = new CardFactory.StaticInitializer("Aura Blast",new Factory());

    private class AuraBlastEffect extends AbstractEnchantmentCardEffect {
        public AuraBlastEffect(Player p,Card c) { super(p,c); }
        Enchantment target;
        @Override
        public void getTarget(){
            int i = 0,idx;
            Scanner reader = new Scanner(System.in);
            LinkedList <Enchantment> enchantment = new LinkedList();
            LinkedList <Enchantment> enchantment2 = new LinkedList();
            enchantment.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
            enchantment2.addAll(CardGame.instance.getCurrentAdversary().getEnchantments()); 
            
            System.out.println("vuoi distruggere un incantesimo tuo o del tuo avversario?1/2");
            
            idx = reader.nextInt();
            if(idx == 1){            
                System.out.println("incantesimi del giocatore"+CardGame.instance.getCurrentPlayer().name());
                for (Enchantment c : enchantment){   
                    System.out.println(i+")"+c.name());
                    i++;
                }
                target = enchantment.get(idx);
            }else if(idx == 2){
                System.out.println("incantesimi del giocatore"+CardGame.instance.getCurrentAdversary().name());
                for (Enchantment c : enchantment2) {              
                    System.out.println(i+")"+c.name());
                    i++;
                }
                target = enchantment2.get(idx);
            }
        }
        public void resolve(){
            System.out.println(" removing " + target.name() + " from field");
            target.remove();
            System.out.println(" drawing a card");
            CardGame.instance.getCurrentAdversary().draw();           
        }
        public boolean play() {
            getTarget();
            return super.play();
        }            
        @Override
        protected Enchantment createEnchantment() { return new AuraBlastEnchantment(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new AuraBlastEffect(p,this); }
    
    private class AuraBlastEnchantment extends AbstractEnchantment {
        public AuraBlastEnchantment(Player owner) {
            super(owner);
        }
    @Override
    public String name() { return "Aura Blast"; }
    }
    @Override
    public String name() { return "Aura Blast";}
    @Override
    public String type() {return "Enchantment";}
    @Override
    public String ruleText() {return "Destroy target Enchantment. Draw a card";}
    @Override
    public boolean isInstant() {return false;}
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
}
    
