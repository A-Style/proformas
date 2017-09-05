package codigo;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {

    private String link;
    private String user;
    private String password;
    private String host;
    private String database;
    Connection c;

    public conexion() {
        this.host = "localhost:3306";
        this.database = "dbproformas";
        this.user = "root";
        this.password = "";
        this.link = "jdbc:mysql://" + this.host + "/" + this.database
                + "?verifyServerCertificate=false"
                + "&useSSL=false"
                + "&requireSSL=false";
    }

    public Connection conectar() {
        c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(this.link, this.user, this.password);
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.println("Error en Conexion: " + e);
        }
//        finally {
//            c.close();            
//        }
        return c;
    }

    public void desconectar() {
        c = null;
        try {
            c.close();
        } catch (Exception e) {
        }
    }

}
