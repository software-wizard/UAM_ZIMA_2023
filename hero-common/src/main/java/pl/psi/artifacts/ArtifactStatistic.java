package pl.psi.artifacts;

import lombok.Getter;

@Getter
public enum ArtifactStatistic implements ArtifactStatisticIf{
    CENTAURS_AXE("Centaur's Axe", 2, 0, 0, 0, "right hand", "treasure"),
    BLACKSHARD_OF_THE_DEAD_KNIGHT("Blackshard of the Dead Knight", 3, 0, 0, 0, "right hand", "minor"),
    GREATER_GNOLLS_FLAIL("Greater Gnoll's Flail", 4, 0, 0, 0, "right hand", "minor"),
    OGRES_CLUB_OF_HAVOC("Ogre's Club of Havoc", 5, 0, 0, 0, "right hand", "major"),
    SWORD_OF_HELLFIRE("Sword of Hellfire", 6, 0, 0, 0, "right hand", "major"),
    TITANS_GLADUIS("Titan's Gladius", 12, -3, 0, 0, "right hand", "relic"),
    //
    SHIELD_OF_THE_DWARVEN_LORDS("Shield of the Dwarven Lords", 0, 2, 0, 0, "left hand", "treasure"),
    SHIELD_OF_THE_YAWNING_DEAD("Shield of the Yawning Dead", 0, 3, 0, 0, "left hand", "minor"),
    BUCKLER_OF_THE_GNOLL_KING("Buckler of the Gnoll King", 0, 4, 0, 0, "left hand", "minor"),
    TARG_OF_THE_RAMPAGING_OGRE("Targ of the Rampaging Ogre", 0, 5, 0, 0, "left hand", "major"),
    SHIELD_OF_THE_DAMNED("Shield of the Damned", 0, 6, 0, 0, "left hand", "major"),
    SENTINELS_SHIELD("Sentinel's Shield", -3, 12, 0, 0, "left hand", "relic"),
    //
    BREASTPLATE_OF_PETRIFIED_WOOD("Breastplate of Petrified Wood", 0, 0, 1, 0, "torso", "treasure"),
    RIB_CAGE("Rib Cage", 0, 0, 2, 0, "torso", "minor"),
    SCALES_OF_THE_GREATER_BASILISK("Scales of the Greater Basilisk", 0, 0, 3, 0, "torso", "minor"),
    TUNIC_OF_THE_CYCLOPS_KING("Tunic of the Cyclops King", 0, 0, 4, 0, "torso", "major"),
    BREASTPLATE_OF_BRIMSTONE("Breastplate of Brimstone", 0, 0, 5, 0, "torso", "major"),
    TITANS_CUIRASS("Titan's Cuirass", 0, 0, 10, -2, "torso", "relic"),
    //
    HELM_OF_THE_ALABASTER_UNICORN("Helm of the Alabaster Unicorn", 0, 0, 0, 1, "head", "treasure"),
    SKULL_HELMET("Skull Helmet", 0, 0, 0, 2, "head", "treasure"),
    HELM_OF_CHAOS("Helm of Chaos", 0, 0, 0, 3, "head", "minor"),
    CROWN_OF_THE_SUPREME_MAGI("Crown of the Supreme Magi", 0, 0, 0, 4, "head", "minor"),
    HELLSTORM_HELMET("Hellstorm Helmet", 0, 0, 0, 5, "head", "major"),
    THUNDER_HELMET("Thunder Helmet", 0, 0, -2, 10, "head", "relic");

    private final String name;
    private final int attack;
    private final int armor;
    private final int spellPower;
    private final int knowledge;
    private final String bodyPart;
    private final String type;

    ArtifactStatistic(final String aName, final int aAttack,
                      final int aArmor, final int aSpellPower,
                      final int aKnowledge, final String aBodyPart,
                      final String aType){
        name = aName;
        attack = aAttack;
        armor = aArmor;
        spellPower = aSpellPower;
        knowledge = aKnowledge;
        bodyPart = aBodyPart;
        type = aType;
    }
}
