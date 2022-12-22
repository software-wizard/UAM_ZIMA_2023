package pl.psi.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.psi.Hero;
import pl.psi.HeroStats;
import pl.psi.artifacts.EconomyArtifact;
import pl.psi.artifacts.EconomyArtifactFactory;
import pl.psi.converter.EcoBattleConverter;
import pl.psi.creatures.Creature;
import pl.psi.creatures.EconomyNecropolisFactory;
import pl.psi.creatures.NecropolisFactory;

class EcoBattleConverterTest
{

    @Test
    void shouldConvertCreaturesCorrectly()
    {
        final EconomyHero ecoHero = new EconomyHero( EconomyHero.Fraction.NECROPOLIS, 1000 );
        final EconomyNecropolisFactory factory = new EconomyNecropolisFactory();
        final List<EconomyArtifact> artifacts = new ArrayList<>();
        final EconomyArtifactFactory artifactFactory = new EconomyArtifactFactory();
        artifacts.add(artifactFactory.create("right hand","Centaur's Axe"));
        artifacts.add(artifactFactory.create("right hand","Blackshard of the Dead Knight"));
        artifacts.add(artifactFactory.create("left hand","Shield of the Dwarven Lords"));
        artifacts.add(artifactFactory.create("torso", "Breastplate of Petrified Wood"));
        artifacts.add(artifactFactory.create("head", "Helm of the Alabaster Unicorn"));
        int allAttack = 0, allArmor = 0, allSpellPower = 0, allKnowledge = 0;
        for(EconomyArtifact i : artifacts) {
            allAttack += i.getAttack();
            allArmor += i.getArmor();
            allSpellPower += i.getSpellPower();
            allKnowledge += i.getSpellPower();
        }
        final HeroStats heroStats = HeroStats.builder()
                .attack(allAttack)
                .armor(allArmor)
                .spellPower(allSpellPower)
                .knowledge(allKnowledge)
                .build();
        ecoHero.addCreature( factory.create( false, 1, 1 ) );
        ecoHero.addCreature( factory.create( false, 2, 2 ) );
        ecoHero.addCreature( factory.create( false, 3, 3 ) );
        ecoHero.addCreature( factory.create( false, 4, 4 ) );
        ecoHero.addCreature( factory.create( false, 5, 5 ) );
        ecoHero.addCreature( factory.create( false, 6, 6 ) );
        ecoHero.addCreature( factory.create( false, 7, 7 ) );

        final List< Creature > convertedCreatures = EcoBattleConverter.convert( ecoHero )
            .getCreatures();
//        convertedCreatures.forEach(economyCreature -> System.out.println(factory.create(economyCreature.isUpgraded(),
//                economyCreature.getTier(),economyCreature.getAmount())));
        final List< Creature > creatures = new ArrayList<>();
        final NecropolisFactory factoryk = new NecropolisFactory();
        ecoHero.getCreatures()
                .forEach( ecoCreature -> creatures.add( factoryk.create( ecoCreature.isUpgraded(),
                        ecoCreature.getTier(), ecoCreature.getAmount() ) ) );
        Hero hero = new Hero(creatures, heroStats);
        assertEquals(5, hero.getStats().getAttack());
        assertEquals(2, hero.getStats().getArmor());
        assertEquals(1, hero.getStats().getSpellPower());
        assertEquals(1, hero.getStats().getKnowledge());

        assertEquals( 7, convertedCreatures.size() );

        assertEquals( "Skeleton", convertedCreatures.get( 0 )
            .getName() );
        assertEquals( 1, convertedCreatures.get( 0 )
            .getAmount() );

        assertEquals( "Walking Dead", convertedCreatures.get( 1 )
            .getName() );
        assertEquals( 2, convertedCreatures.get( 1 )
            .getAmount() );

        assertEquals( "Wight", convertedCreatures.get( 2 )
            .getName() );
        assertEquals( 3, convertedCreatures.get( 2 )
            .getAmount() );

        assertEquals( "Vampire", convertedCreatures.get( 3 )
            .getName() );
        assertEquals( 4, convertedCreatures.get( 3 )
            .getAmount() );

        assertEquals( "Lich", convertedCreatures.get( 4 )
            .getName() );
        assertEquals( 5, convertedCreatures.get( 4 )
            .getAmount() );

        assertEquals( "Black Knight", convertedCreatures.get( 5 )
            .getName() );
        assertEquals( 6, convertedCreatures.get( 5 )
            .getAmount() );

        assertEquals( "Bone Dragon", convertedCreatures.get( 6 )
            .getName() );
        assertEquals( 7, convertedCreatures.get( 6 )
            .getAmount() );
    }

}