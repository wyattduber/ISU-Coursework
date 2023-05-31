public class Attribute {

    public String name;
    public int type;

    /**
     * Constructor for attribute, which contains a name and a type
     * @param name Name of the attribute
     * @param type Type of the attribute, of which there are 4: 1. Integer 2. Double 3. Boolean 4. String
     */
    public Attribute(String name, int type) {
        this.name = name;
        this.type = type;
    }

}