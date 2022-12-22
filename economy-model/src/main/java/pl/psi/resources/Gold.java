package pl.psi.resources;

import pl.psi.exception.HasNotEnoughMoney;

public class Gold {
    private final int gold;

    public Gold(int gold) {
        this.gold = gold;
    }

    public Gold(Gold gold){
        this.gold = gold.getGold();
    }

    public Gold changeAmountOfGold(Gold gold){
        if(gold.getGold()<0)
        if( this.gold+gold.getGold() < 0 ){
            throw  new HasNotEnoughMoney();
        }
        return new Gold(this.gold+gold.getGold());
    }

    public int getGold() {
        return gold;
    }


}
