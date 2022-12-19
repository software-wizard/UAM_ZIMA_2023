package pl.psi;

import java.io.*;
import java.util.List;
import java.util.Optional;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


import pl.psi.creatures.Creature;
import pl.psi.mapgenerator.MapGeneratorValues;
import pl.psi.specialfields.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class Board {
    private static final int MAX_WITDH = 14;
    private final BiMap<Point, Creature> map = HashBiMap.create();
    private final BiMap<Point, Field> fieldBiMap = HashBiMap.create();
    private final BiMap<Point, Obstacle> obstacleBiMap = HashBiMap.create();

    public Board(final List<Creature> aCreatures1, final List<Creature> aCreatures2) {
        addCreatures(aCreatures1, 0);
        addCreatures(aCreatures2, MAX_WITDH);
        deserializeMapFromJson();
    }

    private void addCreatures(final List<Creature> aCreatures, final int aXPosition) {
        for (int i = 0; i < aCreatures.size(); i++) {
            map.put(new Point(aXPosition, i * 2 + 1), aCreatures.get(i));
        }
    }

    Optional<Creature> getCreature(final Point aPoint) {
        return Optional.ofNullable(map.get(aPoint));
    }

    void move(final Creature aCreature, final Point aPoint) {
        if (canMove(aCreature, aPoint)) {
            map.inverse()
                    .remove(aCreature);
            map.put(aPoint, aCreature);
            fieldBiMap.get(aPoint).buffCreature(aCreature);
        }
    }

    boolean canMove(final Creature aCreature, final Point aPoint) {
        if (map.containsKey(aPoint)) {
            return false;
        }
        final Point oldPosition = getPosition(aCreature);
        return aPoint.distance(oldPosition.getX(), oldPosition.getY()) < aCreature.getMoveRange();
    }

    Point getPosition(Creature aCreature) {
        return map.inverse()
                .get(aCreature);
    }

    private void deserializeMapFromJson(){
        ObjectMapper mapper = new ObjectMapper();
        String fileName;
        try {
            fileName = getSelectedMapName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File jsonFile = new File(MapGeneratorValues.MAPS_PATH + fileName);
        List<JsonConvertedField> jsonConvertedFieldList;
        try {
            jsonConvertedFieldList = mapper.readValue(jsonFile, new TypeReference<List<JsonConvertedField>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FieldFactory fieldFactory = new FieldFactory();
        ObstacleFactory obstacleFactory = new ObstacleFactory();
        for (JsonConvertedField jsonConvertedField : jsonConvertedFieldList) {
            Point point = new Point(jsonConvertedField.getX(), jsonConvertedField.getY());
            fieldBiMap.put(point, fieldFactory.getType(jsonConvertedField.getTerrainType()));
            if (jsonConvertedField.getObstacleType() != null) {
                obstacleBiMap.put(point, obstacleFactory.getType(jsonConvertedField.getObstacleType()));
            }
        }
    }

    private String getSelectedMapName() throws IOException {
        String path = "hero-common/src/main/java/pl/psi/mapgenerator/selectedMap.txt";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String result = reader.readLine();
        reader.close();
        result += ".json";
        return result;
    }

    public String getTerrainType(Point point) {
        return (fieldBiMap.containsKey(point)) ? fieldBiMap.get(point).getType() : null;
    }

    public String getObstacleType(Point point) {
        return (obstacleBiMap.containsKey(point)) ? obstacleBiMap.get(point).getType() : null;
    }
}
