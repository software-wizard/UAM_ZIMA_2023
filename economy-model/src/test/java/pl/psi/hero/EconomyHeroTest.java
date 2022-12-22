package pl.psi.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.EconomyNecropolisFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EconomyHeroTest
{

    private EconomyHero hero;

    @BeforeEach
    void init()
    {
        hero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 3000 );
    }

    @Test
    void shouldThrowExceptionWhileHeroHas7CreatureAndYoTryToAddNextOne()
    {
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );
        hero.addCreature( factory.create( true, 1, 1 ) );

        assertThrows( IllegalStateException.class, () -> hero.addCreature( factory.create( true, 1, 1 ) ) );
    }

    @Test
    void shouldThrowExceptionWhileYouTrySubstractMoreGoldThanHeroHas()
    {
        assertThrows( IllegalStateException.class, () -> hero.changeGoldAmount( -3001 ) );
    }
}