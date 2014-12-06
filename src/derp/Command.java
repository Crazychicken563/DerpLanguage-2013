package derp;

public class Command extends Expression{

    private String value;

    public Command(String value) {
        //System.out.println("do i actually use this?");
        this.value = value;
    }

    @Override
    public String eval() {
        //System.out.println(value);
        return value+"";
    }
}