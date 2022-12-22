package pl.psi.exception;

public class HasNotEnoughMoney extends IllegalStateException {
    public HasNotEnoughMoney(){
        super("Hero has not enough money");
    }
}
