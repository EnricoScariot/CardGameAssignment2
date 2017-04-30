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
public interface Targetable extends Effect{
    /*le carte che necessitano di un target estendono questa interfaccia*/
    void pickTarget();
}
