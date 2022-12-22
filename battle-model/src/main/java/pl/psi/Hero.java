package pl.psi;

import java.util.List;

import pl.psi.artifacts.ArtifactStatisticIf;
import pl.psi.creatures.Creature;

import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Hero
{
    @Getter
    private final List< Creature > creatures;

    @Getter
    private final HeroStatisticIf stats;

    public Hero( final List< Creature > aCreatures, final HeroStatisticIf aStats)
    {
        creatures = aCreatures;
        stats = aStats;
    }
}
