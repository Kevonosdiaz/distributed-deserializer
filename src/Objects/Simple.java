package Objects;

public class Simple {
    // Store the following primitive fields
    public Simple(int i, double d, char c) {
        this.intField = i;
        this.doubleField = d;
        this.charField = c;
    }

    private int intField;
    private double doubleField;
    private char charField;

    @Override
    public String toString() {
        return "Simple Object: " + this.intField + ", " + this.doubleField + ", " + + this.charField;
    }

    

}
