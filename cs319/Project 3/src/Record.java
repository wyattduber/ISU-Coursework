public class Record {

    public String[] values;
    public int size;

    /**
     * Constructor for records, which contains a string array of all values
     * @param values Values for the record, organized according to attribute order
     */
    public Record(String[] values) {
        this.values = values;
        size = values.length;
    }

}
