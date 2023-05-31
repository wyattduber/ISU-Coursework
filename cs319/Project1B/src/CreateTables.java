import java.sql.*;
import java.util.Properties;

/**
 * @author Wyatt Duberstein
 */
public class CreateTables {

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

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS students (" + "snum INT NOT NULL UNIQUE, " +
                        "ssn INT, " + "name VARCHAR(20), " + "gender VARCHAR(1), " +
                        "dob DATETIME, " + "c_addr VARCHAR(20), " + "c_phone VARCHAR(10), " +
                        "p_addr VARCHAR(20), " + "p_phone VARCHAR(10), " + "PRIMARY KEY (ssn)" +
                        ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS departments (" + "code INT, " + "name VARCHAR(50) NOT NULL UNIQUE," +
                "phone VARCHAR(10), " + "college VARCHAR(20), " + "PRIMARY KEY (code)" + ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS degrees (" + "name VARCHAR(50), " + "level VARCHAR(5), " +
                "department_code INT, " + "PRIMARY KEY (name, level), " +
                "FOREIGN KEY (department_code) REFERENCES departments(code)" + ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS courses (" + "number INT, " + "name VARCHAR(50) NOT NULL UNIQUE, " +
                "description VARCHAR(50), " + "credithours INT, " + "level VARCHAR(20), " +
                "department_code INT, " + "PRIMARY KEY (number), " +
                "FOREIGN KEY (department_code) REFERENCES departments(code)" + ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS register (" + "snum INT, " + "course_number INT, " + "regtime VARCHAR(20), " +
                "grade INT, " + "PRIMARY KEY (snum, course_number), " + "FOREIGN KEY (snum) REFERENCES students(snum), " +
                "FOREIGN KEY (course_number) REFERENCES courses(number)" + ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS major (" + "snum INT, " + "name VARCHAR(50), " + "level VARCHAR(5), " +
                "PRIMARY KEY (snum, name, level), " + "FOREIGN KEY (snum) REFERENCES students(snum), " +
                "FOREIGN KEY (name, level) REFERENCES degrees(name, level)" + ");"
        ).execute();

        dbcon.prepareStatement(
                "CREATE TABLE IF NOT EXISTS minor (" + "snum INT, " + "name VARCHAR(50), " + "level VARCHAR(5), " +
                        "PRIMARY KEY (snum, name, level), " + "FOREIGN KEY (snum) REFERENCES students(snum), " +
                        "FOREIGN KEY (name, level) REFERENCES degrees(name, level)" + ");"
        ).execute();

    }

}
