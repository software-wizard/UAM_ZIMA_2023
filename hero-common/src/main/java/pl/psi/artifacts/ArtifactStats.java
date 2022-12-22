package pl.psi.artifacts;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArtifactStats implements ArtifactStatisticIf{
    private final String name;
    private final int attack;
    private final int armor;
    private final int spellPower;
    private final int knowledge;
    private final String bodyPart;
    private final String type;

}
