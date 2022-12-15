package pl.psi.specialfields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"x", "y", "terrainType", "obstacleType"})
public class JsonConvertedField {
    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;
    @JsonProperty("terrainType")
    private String terrainType;

    @JsonProperty("obstacleType")
    private String obstacleType;

    @JsonCreator
    public JsonConvertedField(
            @JsonProperty("x") int x,
            @JsonProperty("y") int y,
            @JsonProperty("terrainType") String terrainType,
            @JsonProperty("obstacleType") String obstacleType){
        this.x = x;
        this.y = y;
        this.terrainType = terrainType;
        this.obstacleType = obstacleType;
    }

    @JsonGetter
    public int getX() {
        return x;
    }

    @JsonGetter
    public int getY() {
        return y;
    }

    @JsonGetter
    public String getTerrainType() {
        return terrainType;
    }

    @JsonGetter
    public String getObstacleType() {
        return obstacleType;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", terrainType=" + terrainType + ", obstacleType=" + obstacleType;
    }
}
