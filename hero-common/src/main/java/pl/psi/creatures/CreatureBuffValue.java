package pl.psi.creatures;

public class CreatureBuffValue {
    private final String type;
    private final int modifier;

    private final String alignment;

    public CreatureBuffValue(String type, int modifier, String alignment) {
        this.type = type;
        this.modifier = modifier;
        this.alignment = alignment;
    }

    public String getType() {
        return type;
    }

    public int getModifier() {
        return modifier;
    }

    public String getAlignment() {
        return alignment;
    }
}
