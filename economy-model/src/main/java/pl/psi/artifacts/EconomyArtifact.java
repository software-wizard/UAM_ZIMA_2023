package pl.psi.artifacts;

public class EconomyArtifact {
    private final ArtifactStatistic stats;
    private final int goldCost;

    EconomyArtifact( final ArtifactStatistic aStats, final int aGoldCost )
    {
        stats = aStats;
        goldCost = aGoldCost;
    }

    public int getGoldCost()
    {
        return goldCost;
    }

    public String getName() {
        return stats.getName();
    }

    public String getBodyPart()
    {
        return stats.getBodyPart();
    }

    public int getAttack() {
        return stats.getAttack();
    }

    public int getArmor() {
        return stats.getArmor();
    }

    public int getSpellPower() {
        return stats.getSpellPower();
    }

    public int getKnowledge() {
        return stats.getKnowledge();
    }

    public String getType()
    {
        return stats.getType();
    }
}
