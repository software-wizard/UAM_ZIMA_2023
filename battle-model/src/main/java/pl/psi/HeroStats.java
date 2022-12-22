package pl.psi;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HeroStats implements HeroStatisticIf {

    private final int attack;
    private final int armor;
    private final int spellPower;
    private final int knowledge;

}
