package pl.psi.exception;

public class TiersAboveLimit extends IllegalArgumentException {
    public TiersAboveLimit(){
        super("We support tiers from 1 to 7");
    }
}
