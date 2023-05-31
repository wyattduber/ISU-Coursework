import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Wyatt Duberstein
 */
public class ModifyRecords {

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

        dbcon.prepareStatement("UPDATE students SET name='Scott' WHERE ssn='746897816';").execute();
        dbcon.prepareStatement("UPDATE major SET name='Computer Science',level='MS' WHERE snum=746897816;").execute();
        dbcon.prepareStatement("DELETE FROM register WHERE regtime='Spring2021';").execute();

    }
}
