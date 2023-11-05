package Objects;

public class Complex {
    public Complex() {
        this.intField = 0;
        this.doubleField = 0.0;
        this.stringField = "";
        this.simpleField = new Simple();
    }
    public Complex(int i, double d, String s, Simple obj) {
        this.intField = i;
        this.doubleField = d;
        this.stringField = s;
        this.simpleField = obj;
        
    }

    private final int intField;
    private final double doubleField;
    private final String stringField;
    private final Simple simpleField;

    @Override
    public String toString() {
        return "Complex Object: " + this.intField + ", " + this.doubleField + ", " + this.stringField + ", " + this.simpleField.toString();
    }
}
