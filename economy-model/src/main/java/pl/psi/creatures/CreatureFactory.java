package pl.psi.creatures;

import pl.psi.hero.EconomyHero;

public abstract class CreatureFactory {

     public static CreatureFactory fractionEconomyFactory(EconomyHero.Fraction fraction) {

          switch (fraction){
               case NECROPOLIS:
                    return new EconomyNecropolisFactory();
               default:
                    return null;
          }
     }


     public  abstract EconomyCreature create(final boolean aIsUpgraded, final int aTier, final int aAmount);

}
