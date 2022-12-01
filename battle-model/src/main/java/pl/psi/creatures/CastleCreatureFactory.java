package pl.psi.creatures;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class CastleCreatureFactory
{
    public Creature create( final int aTier, final boolean aIsUpgraded, final int aAmount )
    {
        if( aIsUpgraded )
        {
            if (aTier == 1) {
                return new Creature.Builder().statistic(CreatureStatistic.BLACK_KNIGHT)
                        .amount(aAmount)
                        .build();
            }
        }
        else
        {
            if (aTier == 1) {
                return new Creature.Builder().statistic(CreatureStatistic.BLACK_KNIGHT)
                        .amount(aAmount)
                        .build();
            }
        }
        throw new IllegalArgumentException( "Cannot recognize creature by tier and upgrade or not." );
    }
}
