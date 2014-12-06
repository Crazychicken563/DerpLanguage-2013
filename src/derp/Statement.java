package derp;

public class Statement {

    private Expression contents;

    public Statement(String in) {
        if (isNumber(in)) {
            contents = new Num(Double.parseDouble(in));
            //System.out.println("Num Passed In");
        } else if (isList(in)) {
            contents = new List(in);
            //System.out.println("List Passed In");
        } else if (isString(in)) {
            contents = new Stringy(in);
            //System.out.println("String Passed In");
        } else {
            contents = new Command(in);
            //System.out.println("Command Passed In");
        }
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isList(String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            return true;
        }
        return false;
    }

    private boolean isString(String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return true;
        }
        return false;
    }

    public String eval() {
        return contents.eval();
    }

    public Expression get() {
        return contents;
    }

    @Override
    public String toString() {
        return eval();
    }
}
