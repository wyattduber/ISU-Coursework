import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Main Toy Class complete with main method and commands
 *
 * @author Wyatt Duberstein
 */
public class Toy {

    public static Table table;
    public static File file;
    public static FileWriter fw;
    public static String filename;
    public static String tableLine;

    /**
     * Main Method, handles arguments and initial file operations
     * @param args Input arguments
     */
    public static void main(String[] args) {

        if (args.length == 2) {
            Scanner sc;
            try {
                filename = args[1];
                file = new File(filename);
                file.createNewFile();
                sc = new Scanner(file);
            } catch (IOException e) {
                System.out.println("File not found!");
                return;
            }

            switch(args[0]) {
                case "create":
                    try {
                        fw = new FileWriter(file);
                        table = createTable();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "header":
                    System.out.println(sc.nextLine());
                    break;
                case "insert":
                    try {
                        tableLine = sc.nextLine();
                        loadTable();
                        insertRecord();
                        try {
                            fw.close();
                        } catch (NullPointerException ignored) {}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else if (args.length == 3) {

            try {
                filename = args[2];
                file = new File(filename);
                Scanner sc = new Scanner(file);
                tableLine = sc.nextLine();
                loadTable();
            } catch (IOException e) {
                System.out.println("File not found!");
                return;
            }
            switch (args[0]) {
                case "display":
                    display(Integer.parseInt(args[1]));
                    break;
                case "search":
                    search(args[1]);
                    break;
                case "delete":
                    delete(Integer.parseInt(args[1]));
                    break;
            }

            try {
                fw.close();
            } catch (IOException | NullPointerException ignored) {}

        } else {
            System.out.println("Invalid Arguments!");
        }
    }

    /**
     * Creates the table and asks for attribute names/types
     * @return The table that's created
     */
    public static Table createTable() {
        // Generate empty lists
        boolean doRepeat = true;
        Scanner sc = new Scanner(System.in);
        ArrayList<Attribute> attributes = new ArrayList<>();

        // Ask for attribute names and types
        while (doRepeat) {
            System.out.println("Attribute Name:");
            String attributeName = sc.next();
            if (attributeName.contains("[\\w\\Q!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\\E]+")) {
                System.out.println("Invalid Attribute Name!");
                break;
            }
            System.out.println("Select a Type: 1. Integer; 2. Double; 3. Boolean; 4. String");
            int attributeType = sc.nextInt();
            attributes.add(new Attribute(attributeName, attributeType));
            System.out.println("More Attributes? (y/n)");
            if (sc.next().equalsIgnoreCase("n")) doRepeat = false;
        }

        Table table = new Table(attributes); // Declare new table
        try {
            fw.write(getHeader(table)); // Write header to file using attributes of newly created table
        } catch (IOException e) {
            System.out.println("File not found!");
            return null;
        }

        return table;
    }

    /**
     * Inserts a record into the file and queries user for each field
     */
    public static void insertRecord() {
        // Parse header line
        String line = "";
        try {
            Scanner fileScanner = new Scanner(file);
            line = fileScanner.nextLine();
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        // Insert the actual record
        ArrayList<String> attributes = parse(line);
        String[] values = new String[table.getAttributes().size()];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < attributes.size(); i++) {
            System.out.println(attributes.get(i) + ":");
            String value = sc.nextLine(); // Ask user for record input
            if (check(value, table.getAttributes().get(i).type)) { // Check that input is valid
                System.out.println("Invalid field in table!");
                return;
            }
            if (table.getAttributes().get(i).type == 3) { // Change all forms of True to T and False to F
                if (value.equalsIgnoreCase("true")) {
                    value = "T";
                } else if (value.equalsIgnoreCase("false")) {
                    value = "F";
                }
            }
            values[i] = value;
        }

        table.records.add(new Record(values));
        reloadFile();
    }

    /**
     * Parses and displays the indexed record in formatted order
     * @param index Index of the record to parse and display
     */
    public static void display(int index) {
        // Get attributes and records
        ArrayList<Attribute> a = table.getAttributes();
        Record record = table.getRecords().get(index - 1);

        // Display formatted record
        for (int i = 0; i < record.size; i++) {
            System.out.println(a.get(i).name + ": " + record.values[i]);
        }
    }

    /**
     * Searches for a record using a specific condition
     * @param condition The condition from which to search the record
     */
    public static void search(String condition) {
        // Parse query into attribute and attribute type
        Scanner sc = new Scanner(condition);
        sc.useDelimiter("[ = |=]+");
        ArrayList<Attribute> a = table.getAttributes();
        String attribute = sc.next();
        String value = sc.next();
        int index = 0;

        // Find index of the attribute that's being searched
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).name.equalsIgnoreCase(attribute)) {
                index = i;
                break;
            }
        }

        // Find record with matching value from query and display entire record
        ArrayList<Record> r = table.getRecords();
        for (Record record : r) {
            String[] values = record.values;
            if (record.values[index].equalsIgnoreCase(value)) {
                for (int k = 0; k < values.length; k++) {
                    System.out.println(a.get(k).name + ": " + values[k]);
                }
                System.out.println("\n");
            }
        }
    }

    /**
     * Deletes a record at the specified index
     * @param index Index at which to delete a record
     */
    public static void delete(int index) {
        table.records.remove(index - 1);
        reloadFile();
    }

    /**
     * Calls the getString method for a new table
     * @param table New Table for which to return the header
     * @return Returns the new header
     */
    public static String getHeader(Table table) {
        return getString(table);
    }

    /**
     * Calls the getString method to update the header for a table
     * @return Returns the updated header
     */
    public static String getHeader() {
        return getString(table);
    }

    /**
     * Updates the header using the table provided to it
     * @param table The table in which to generate a new header
     * @return Returns the newly updated header
     */
    private static String getString(Table table) {
        int size = table.getAttributes().size();
        ArrayList<Attribute> attributes = table.getAttributes();
        StringBuilder header = new StringBuilder("[" + size + "]");
        for (int i = 0; i < size; i++) {
            Attribute a = attributes.get(i);
            header.append("[").append(a.name).append(":").append(a.type).append("]"); // Append each record value for values array
        }
        try {
            int count = table.records.size();
            header.append("[").append(count).append("]"); // If attribute count is greater than 0
        } catch (NullPointerException e) {
            header.append("[").append(0).append("]"); // If attribute count is less than 0
        }

        return header.toString();
    }

    /**
     * Parse the attributes of the table's header
     * @param line Header line in which to parse
     * @return Returns an ArrayList<String> of the attributes according to the Header line
     */
    private static ArrayList<String> parse(String line) {
        ArrayList<String> items = new ArrayList<>();
        Scanner sc = new Scanner(line);
        sc.useDelimiter("[\\[\\]:]+");
        sc.next();
        int total = 0;

        while (sc.hasNext()) { // Count how many attributes are in title
            sc.next();
            total++;
        }

        sc = new Scanner(line);
        sc.useDelimiter("[\\[\\]:]+");
        sc.next();

        for (int i = 0; i < total - 1; i++) { // Check that all attribute names are valid
            String item = sc.next();
            try {
                Integer.parseInt(item);
                System.out.println("Invalid Attribute Name!");
                return null;
            } catch (NumberFormatException e) {
                items.add(item);
            }
        }

        return items;
    }

    /**
     * Deletes and reloads the file after an addition or deletion of a record
     */
    private static void reloadFile() {
        // Delete and regenerate file
        try {
            file.delete();
            file = new File(filename);
            fw = new FileWriter(file);
            fw.write(getHeader());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Refill empty file with existing records in memory
        for (int i = 0; i < table.records.size(); i++) {
            try {
                fw.write("\n" + buildRecord(table.records.get(i).values));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Loads the table into memory if there is an existing file
     */
    private static void loadTable() {
        // Load Attributes from file
        Scanner sc = new Scanner(tableLine);
        sc.useDelimiter("[\\[\\]]+");
        ArrayList<Attribute> attributes = new ArrayList<>();
        sc.next();
        while (sc.hasNext()) {
            String next = sc.next();
            if (!next.contains(":")) break;
            Scanner sc2 = new Scanner(next);
            sc2.useDelimiter(":");
            String name = sc2.next();
            int type = Integer.parseInt(sc2.next());
            attributes.add(new Attribute(name, type));
        }

        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Record> records = new ArrayList<>();

        // Load Records from file
        sc.nextLine();
        while (sc.hasNextLine()) {
            Scanner scanner = new Scanner(sc.nextLine());
            scanner.useDelimiter("[{}|]+");
            ArrayList<String> recordFields = new ArrayList<>();
            while (scanner.hasNext()) {
                String value = scanner.next();
                recordFields.add(value);
            }
            String[] values = new String[recordFields.size()];
            for (int j = 0; j < recordFields.size(); j++) {
                String value = recordFields.get(j);
                if (check(value, attributes.get(j).type)) {
                    System.out.println("Invalid field in table!");
                    return;
                }
                if (attributes.get(j).type == 3) {
                    if (value.equalsIgnoreCase("true")) {
                        value = "T";
                    } else if (value.equalsIgnoreCase("false")) {
                        value = "F";
                    }
                }
                values[j] = value;
            }
            records.add(new Record(values));
        }

        table = new Table(attributes, records);

    }

    /**
     * Builds the record string when given values to put in file
     * @param values Values in which to format
     * @return Returns the formatted record to be inserted in file
     */
    private static String buildRecord(Object[] values) {
        StringBuilder line = new StringBuilder("{");
        for (int i = 0; i < values.length - 1; i++) {
            line.append(values[i]).append("|");
        }
        line.append(values[values.length - 1]).append("}");

        return line.toString();
    }

    /**
     * Checks inputted values to verify if they are proper integers, doubles, strings, etc
     * @param input Input to verify
     * @param type Attribute type to be verifying
     * @return Returns true if input is *not* valid, returns false if it is
     */
    private static boolean check(String input, int type) {
        switch (type) {
            case 1:
                try {
                    Integer.parseInt(input);
                    return false;
                } catch (NumberFormatException e) {
                    return true;
                }
            case 2:
                try {
                    Double.parseDouble(input);
                    return false;
                } catch (NumberFormatException e) {
                    return true;
                }
            case 4:
                Pattern p = Pattern.compile("[^a-zA-Z0-9 ]");
                return p.matcher(input).find();
        }
        return false;
    }


}