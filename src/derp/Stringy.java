package derp;

public class Stringy extends Expression{

    private StringBuilder string;

    public Stringy(String in) {
        string = new StringBuilder(in.replace("\"", ""));
    }

    public String get(int index) {
        return string.charAt(index)+"";
    }

    public void set(int index, String newVal) {
        string.setCharAt(index, newVal.charAt(0));
    }

    public void add(String newVal) {
        string.append(newVal);
    }

    public void add(int index, String newVal) {
        string.insert(index, newVal);
    }

    public void remove(int index) {
        string.deleteCharAt(index);
    }

    public void remove(String value) {
        string = new StringBuilder(string.toString().replace(value, ""));
    }

    public int size() {
        return string.length();
    }

    @Override
    public String eval() {
        //return "\""+string.toString().replace("_", " ") +"\"";
        return "\""+string.toString() +"\"";
    }
}
