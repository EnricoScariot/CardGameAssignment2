/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Sara
 */
public class CardFactory {
    
    private static HashMap<String,ICardFactory> factoryMap = new HashMap<>();
    private static void register(String s, ICardFactory f) {
    factoryMap.put(s,f);
    }
    
    private CardFactory(){
        InputStream deck = getClass().getClassLoader().getResourceAsStream("cardgame/cards");
    }

public static class StaticInitializer {
    public StaticInitializer(String s,ICardFactory f) {
        CardFactory.register(s,f);
    }
  }
 /*per debug*/
 public static Set<String> getMapCards() { return factoryMap.keySet(); }

}
