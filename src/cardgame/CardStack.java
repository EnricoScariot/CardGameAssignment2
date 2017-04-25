/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author atorsell
 */
public class CardStack implements Iterable<Effect> { 
    
    private final ArrayList<Effect> stack = new ArrayList<>();
    
    public Iterator<Effect> iterator() { return stack.iterator(); }
    
    public void push(Effect e) { 
        stack.add(e); 
    }
    
    public Effect pop() {
        return stack.remove(stack.size()-1);
    }
    
    public void remove(Effect e) {  
        stack.remove(e);  
    }
    
    public int size() { return stack.size(); }
    public Effect get(int i) { return stack.get(i); }
    public int indexOf(Effect e) { return stack.indexOf(e); }
    
    public void resolve() {
        while(!stack.isEmpty()) { 
            Effect e = pop();
            
            System.out.println("Stack: resolving " + e);
            e.resolve(); 
        }
    }
}
