package derp;

public class Variable extends Expression {

    private String name;
    private Expression value;

    public Variable(String name, Expression value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String eval() {
        return value.eval();
    }

    @Override
    public String toString() {
        return value.eval();
    }

    public String getName() {
        return name;
    }

    public boolean isList(String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            return true;
        }
        return false;
    }

    public List getList() {
        if (isList(value.eval())) {
            return (List)value;
        }
        return null;
    }
}