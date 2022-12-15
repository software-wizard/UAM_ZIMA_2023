package pl.psi.gui.mapgenerator;

public class BattleMapField {
    private int x;
    private int y;
    private String terrainType;
    private String obstacleType;

    public BattleMapField(int x, int y, String terrainType, String obstacleType) {
        this.x = x;
        this.y = y;
        this.terrainType = terrainType;
        this.obstacleType = obstacleType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getTerrainType() {
        return terrainType;
    }

    public String getObstacleType() {
        return obstacleType;
    }

    @Override
    public String toString() {
        return "x= " + x + " y= " + y + " terrainType= " + terrainType + " obstacleType= " + obstacleType;
    }
}
