/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
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
    
    public ICardFactory getFactory(String s){
        return factoryMap.get(s);
    }
    
    
    public CardFactory(){
        
        InputStream deck = getClass().getClassLoader().getResourceAsStream("cardgame/cards");     
        InputStreamReader isr = new InputStreamReader (deck);
        BufferedReader br = new BufferedReader ( isr );
       /*
        try{
            while(true){
                String s = br.readLine();
                System.out.println(s);
            }
        }
        catch(IOException e){}
        */
        
    }

public static class StaticInitializer {
    public StaticInitializer(String s,ICardFactory f) {
        CardFactory.register(s,f);
    }
  }
 /*per debug*/
 public static Collection<ICardFactory> getMapCards() { return factoryMap.values(); }

}
