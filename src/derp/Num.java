package derp;

public class Num extends Expression {

    private Double value;
    private boolean isInt;

    public Num(int in) {
        value = new Double(in);
        isInt = true;
    }

    public Num(double in) {
        value = new Double(in);
        if (Math.floor(value.doubleValue()) != value.doubleValue()) {
            isInt = false;
        } else {
            isInt = true;
        }
    }

    @Override
    public String eval() {
        if (isInt && !value.isInfinite()) {
            return value.intValue() + "";
        }
        return value.toString();
    }
}