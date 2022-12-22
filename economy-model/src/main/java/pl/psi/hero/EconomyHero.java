package pl.psi.hero;

import pl.psi.creatures.EconomyCreature;
import pl.psi.resources.Gold;

import java.util.ArrayList;
import java.util.List;

public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private Gold gold;

    public EconomyHero( final Fraction aFraction, final int aGold )
    {
        fraction = aFraction;
        gold = new Gold(aGold);
        creatureList = new ArrayList<>();
    }
    public EconomyHero(EconomyHero economyHero){
        fraction=economyHero.getFraction();
        gold=new Gold(economyHero.getGold());
        creatureList=economyHero.getCreatures();


    }

    void addCreature( final EconomyCreature aCreature )
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }


    public int getGold(){
        return gold.getGold();
    }
    public void changeGoldAmount(final int aAmount )
    {
        gold = gold.changeAmountOfGold(new Gold(aAmount));
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    public Fraction getFraction() {
        return fraction;
    }

    public enum Fraction
    {
        NECROPOLIS;
    }
}
