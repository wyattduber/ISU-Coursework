import java.sql.*;
import java.util.Properties;

/**
 * @author Wyatt Duberstein
 */
public class Query {

    public static Connection dbcon;
    public static Properties props;

    /**
     * Main method, executes all SQL Statements
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        props = new Properties();
        props.put("user", "COMS363");
        props.put("password", "SPRING2021");
        dbcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/<db name>?useSSL=false", props);

        ResultSet rs;
        PreparedStatement stmt;

        stmt = dbcon.prepareStatement("SELECT snum,ssn FROM students WHERE name='Becky';");
        rs = stmt.executeQuery();
        rs.next();
        System.out.println("Becky's Student Number: " + rs.getInt(1));
        System.out.println("Becky's Social Security Number: " + rs.getInt(2));

        stmt = dbcon.prepareStatement("SELECT m.name FROM major m JOIN students s on s.snum = m.snum WHERE s.ssn='123097834';");
        rs = stmt.executeQuery();
        rs.next();
        System.out.println("123097834's Major Name: " + rs.getString(1));
        stmt = dbcon.prepareStatement("SELECT m.level FROM major m JOIN students s on s.snum = m.snum WHERE s.ssn='123097834';");
        rs = stmt.executeQuery();
        rs.next();
        System.out.println("123097834's Major Level: " + rs.getString(1));

        stmt = dbcon.prepareStatement("SELECT c.name FROM courses c JOIN departments d on d.code = c.department_code WHERE d.name='Computer Science';");
        rs = stmt.executeQuery();
        System.out.println("Courses offered by the Dept. of Com. Sci:");
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name"));
        }


        stmt = dbcon.prepareStatement("SELECT de.name,de.level FROM degrees de JOIN departments d on d.code = de.department_code WHERE d.name='Computer Science';");
        rs = stmt.executeQuery();
        System.out.println("Degree Names and Levels offered by the Dept. of Com. Sci:");
        rs.next();
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + ", " + rs.getString("level"));
        }

        stmt = dbcon.prepareStatement("SELECT s.name FROM students s JOIN minor m on m.snum = s.snum;");
        rs = stmt.executeQuery();
        System.out.println("Names of Students with a Minor:");
        rs.next();
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name"));
        }

    }

}
