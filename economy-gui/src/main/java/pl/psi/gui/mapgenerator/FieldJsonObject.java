package pl.psi.gui.mapgenerator;

import lombok.Value;

public class FieldJsonObject {
    private final int x;
    private final int y;
    private final String type;

    public FieldJsonObject(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getType() {
        return type;
    }
}
