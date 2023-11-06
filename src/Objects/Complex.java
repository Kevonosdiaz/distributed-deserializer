package Objects;

public class Complex {
    public Complex() {
        this.intField = 0;
        this.doubleField = 0.0;
        this.stringField = "";
        this.objectField = new Simple();
    }
    public Complex(int i, double d, String s, Object obj) {
        this.intField = i;
        this.doubleField = d;
        this.stringField = s;
        this.objectField = obj;
        
    }

    public void setObjectField(Object object) {
        this.objectField = object;
    }

    private final int intField;
    private final double doubleField;
    private final String stringField;
    private Object objectField;

    @Override
    public String toString() {
        if (this.objectField != null) {
            return "Complex Object: " + this.intField + ", " + this.doubleField + ", " + this.stringField + ", " + this.objectField.getClass().toString();
        }
        return "Complex Object: " + this.intField + ", " + this.doubleField + ", " + this.stringField + ", " + "null";
    }


}
