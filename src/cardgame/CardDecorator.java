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
public class CardDecorator implements Card{
    Card card;
    
    public CardDecorator(Card card){ this.card = card;}

    @Override
    public Effect getEffect(Player owner) {return card.getEffect(owner);}
    @Override
    public String name() {return card.name();}
    @Override
    public String type() {return card.type();}
    @Override
    public String ruleText() {return card.ruleText();}
    @Override
    public boolean isInstant() {return card.isInstant();}
    
}
