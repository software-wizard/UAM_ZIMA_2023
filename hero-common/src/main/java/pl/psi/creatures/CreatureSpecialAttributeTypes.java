package pl.psi.creatures;

public enum CreatureSpecialAttributeTypes {
    UNDEAD("undead"),
    UNLIVING("unliving"),
    GOOD("good"),
    NEUTRAL("neutral"),
    EVIL("evil"),
    ALL("all"),
    FLYING("flying"),
    RANGE("range");
    private final String attribute;

    CreatureSpecialAttributeTypes(String attribute){
        this.attribute = attribute;
    }
}
