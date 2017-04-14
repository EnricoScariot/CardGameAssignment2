/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

 /*
 * @author Sara
 */
public class BoilingHeartsDecorator extends CreatureDecorator{
    Creature creaturaDecorata;
    
    public BoilingHeartsDecorator(Creature creatura){
        super(creatura);
        }
    @Override
    public void inflictDamage(int dmg) {
        creaturaDecorata.inflictDamage(1);
    }
  

}
