package pl.psi.artifacts;

public class EconomyArtifactFactory {

    private static final String EXCEPTION_MESSAGE = "NO SUCH ITEM";
    public EconomyArtifact create(final String aBodyPart, final String aName)
    {
        switch (aBodyPart) {
            case "right hand":
                switch (aName) {
                    case "Centaur's Axe":
                        return new EconomyArtifact(ArtifactStatistic.CENTAURS_AXE, 2000);
                    case "Blackshard of the Dead Knight":
                        return new EconomyArtifact(ArtifactStatistic.BLACKSHARD_OF_THE_DEAD_KNIGHT, 3000);
                    case "Greater Gnoll's Flail":
                        return new EconomyArtifact(ArtifactStatistic.GREATER_GNOLLS_FLAIL, 4000);
                    case "Ogre's Club of Havoc":
                        return new EconomyArtifact(ArtifactStatistic.OGRES_CLUB_OF_HAVOC, 5000);
                    case "Sword of Hellfire":
                        return new EconomyArtifact(ArtifactStatistic.SWORD_OF_HELLFIRE, 6000);
                    case "Titan's Gladius":
                        return new EconomyArtifact(ArtifactStatistic.TITANS_GLADUIS, 10000);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
                //
            case "left hand":
                switch (aName) {
                    case "Shield of the Dwarven Lords":
                        return new EconomyArtifact(ArtifactStatistic.SHIELD_OF_THE_DWARVEN_LORDS, 2000);
                    case "Shield of the Yawning Dead":
                        return new EconomyArtifact(ArtifactStatistic.SHIELD_OF_THE_YAWNING_DEAD, 3000);
                    case "Buckler of the Gnoll King":
                        return new EconomyArtifact(ArtifactStatistic.BUCKLER_OF_THE_GNOLL_KING, 4000);
                    case "Targ of the Rampaging Ogre":
                        return new EconomyArtifact(ArtifactStatistic.TARG_OF_THE_RAMPAGING_OGRE, 5000);
                    case "Shield of the Damned":
                        return new EconomyArtifact(ArtifactStatistic.SHIELD_OF_THE_DAMNED, 6000);
                    case "Sentinel's Shield":
                        return new EconomyArtifact(ArtifactStatistic.SENTINELS_SHIELD, 10000);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
                //
            case "torso":
                switch (aName) {
                    case "Breastplate of Petrified Wood":
                        return new EconomyArtifact(ArtifactStatistic.BREASTPLATE_OF_PETRIFIED_WOOD, 1000);
                    case "Rib Cage":
                        return new EconomyArtifact(ArtifactStatistic.RIB_CAGE, 3000);
                    case "Scales of the Greater Basilisk":
                        return new EconomyArtifact(ArtifactStatistic.SCALES_OF_THE_GREATER_BASILISK, 4000);
                    case "Tunic of the Cyclops King":
                        return new EconomyArtifact(ArtifactStatistic.TUNIC_OF_THE_CYCLOPS_KING, 5000);
                    case "Breastplate of Brimstone":
                        return new EconomyArtifact(ArtifactStatistic.BREASTPLATE_OF_BRIMSTONE, 6000);
                    case "Titan's Cuirass":
                        return new EconomyArtifact(ArtifactStatistic.TITANS_CUIRASS, 10000);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
                //
            case "head":
                switch (aName) {
                    case "Helm of the Alabaster Unicorn":
                        return new EconomyArtifact(ArtifactStatistic.HELM_OF_THE_ALABASTER_UNICORN, 1000);
                    case "Skull Helmet":
                        return new EconomyArtifact(ArtifactStatistic.SKULL_HELMET, 3000);
                    case "Helm of Chaos":
                        return new EconomyArtifact(ArtifactStatistic.HELM_OF_CHAOS, 4000);
                    case "Crown of the Supreme Magi":
                        return new EconomyArtifact(ArtifactStatistic.CROWN_OF_THE_SUPREME_MAGI, 5000);
                    case "Hellstorm Helmet":
                        return new EconomyArtifact(ArtifactStatistic.HELLSTORM_HELMET, 6000);
                    case "Thunder Helmet":
                        return new EconomyArtifact(ArtifactStatistic.THUNDER_HELMET, 10000);
                    default:
                        throw new IllegalArgumentException(EXCEPTION_MESSAGE);
                }
            default:
                throw new IllegalArgumentException("NO SUCH BODY PARTY");
        }
    }
}
