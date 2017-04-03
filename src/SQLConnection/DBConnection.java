package SQLConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ynad
 */
public final class DBConnection {
    
    private Connection  con;
    
    public DBConnection() {
        this.con = null;
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        try{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://127.0.01:1433;databaseName=Bacalaureat;user=Mirica;password=123456";
        Connection con = DriverManager.getConnection(connectionURL);
        return con;
        }
        catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Nu se poate conecta\n "+e);
            System.exit(0);
            return null;
        }
    }

    
}
