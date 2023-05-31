import java.util.ArrayList;

/**
 * Class that contains Attributes and Records, similar to a database table
 */
public class Table {

    private final ArrayList<Attribute> attributes;
    public ArrayList<Record> records;

    /**
     * Constructor for a new table that has no records
     * @param attributes Attributes of said table
     */
    public Table(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Constructor for a table that has existing records (usually from file input)
     * @param attributes Attributes of said table
     * @param records Records of said table
     */
    public Table(ArrayList<Attribute> attributes, ArrayList<Record> records) {
        this.attributes = attributes;
        this.records = records;
    }

    /**
     * Method that returns the attributes of the table
     * @return Table's attributes
     */
    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Method that returns the records of the table
     * @return Table's records
     */
    public ArrayList<Record> getRecords() {
        return records;
    }

}