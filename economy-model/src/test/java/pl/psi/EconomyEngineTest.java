package pl.psi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.psi.creatures.CreatureFactory;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.exception.HasNotEnoughMoney;
import pl.psi.hero.EconomyHero;

import static org.junit.jupiter.api.Assertions.*;

class EconomyEngineTest
{

    private EconomyEngine economyEngine;
    private EconomyHero h1;
    private EconomyHero h2;
    private EconomyNecropolisFactory creatureFactory;

    @BeforeEach
    void init()
    {
        h1 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        h2 = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        economyEngine = new EconomyEngine( h1, h2 );
        creatureFactory = new EconomyNecropolisFactory();
    }

    @Test
    void shouldChangeActiveHeroAfterPass()
    {
        assertEquals( h1, economyEngine.getActiveHero() );
        economyEngine.pass();
        assertEquals( h2, economyEngine.getActiveHero() );
    }

    @Test
    void shouldCountRoundCorrectly()
    {
        assertEquals( 1, economyEngine.getRoundNumber() );
        economyEngine.pass();
        assertEquals( 1, economyEngine.getRoundNumber() );
        economyEngine.pass();
        assertEquals( 2, economyEngine.getRoundNumber() );
    }

    @Test
    void shouldBuyCreatureCreatureInCorrectHero()
    {
        economyEngine.buy( creatureFactory.create( false, 1, 1 ) );
        assertEquals( 940, h1.getGold() );
        assertEquals( 1000, h2.getGold() );
        economyEngine.pass();
        economyEngine.buy( creatureFactory.create( false, 2, 1 ) );
        assertEquals( 900, h2.getGold() );
        assertEquals( 940, h1.getGold() );
    }

    @Test
    void ShouldHaveGoodAmountOfGold(){
      h1= new EconomyHero(EconomyHero.Fraction.NECROPOLIS,200);
      h1.changeGoldAmount(200);
      assertEquals(h1.getGold(),400);

    }

    @Test
    void ShouldHaveThrewError(){
        h1= new EconomyHero(EconomyHero.Fraction.NECROPOLIS,200);
        HasNotEnoughMoney hero_has_not_enough_money = assertThrows(HasNotEnoughMoney.class, () -> h1.changeGoldAmount(-300));
        assertTrue(hero_has_not_enough_money.getMessage().contentEquals("Hero has not enough money"));

    }

    @Test
    void ShouldCreateProperFractionCreatureFactory(){
        EconomyHero economyHero= new EconomyHero(EconomyHero.Fraction.NECROPOLIS,200);
        CreatureFactory factory = CreatureFactory.fractionEconomyFactory(economyHero.getFraction());
        assertEquals("EconomyNecropolisFactory",factory.getClass().getSimpleName());

}

    @Test
    void ShouldHaveDifferentInstance(){
        h1 = new EconomyHero(EconomyHero.Fraction.NECROPOLIS,200);
        EconomyHero player1=economyEngine.getPlayer1();

        assertNotEquals(h1,player1);
    }
}