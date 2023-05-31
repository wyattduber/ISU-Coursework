import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Wyatt Duberstein
 */
public class DropTables {

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

        dbcon.prepareStatement("DROP TABLE IF EXISTS minor;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS major;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS register;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS courses;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS degrees;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS departments;").execute();
        dbcon.prepareStatement("DROP TABLE IF EXISTS students;").execute();

    }

}
