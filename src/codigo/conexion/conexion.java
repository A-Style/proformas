package codigo.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conexion {

    private String link;
    private String user;
    private String password;
    private String host;
    private String database;
    Connection c;

    public conexion() {
        this.host = "localhost:3306";
        //this.host = "192.168.1.206";
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
            //c = DriverManager.getConnection(leerDatos()[0], leerDatos()[1], leerDatos()[2]);
            System.out.println("Conexion Exitosa");
        } catch (Exception e) {
            System.out.println("Error en Conexion: " + e);
        }

        return c;
    }

    public void desconectar() {
        c = null;
        try {
            c.close();
        } catch (Exception e) {
        }
    }

    private String[] leerDatos() {
        String contenido;
        String[] datos = new String[4];

        String link;
        String user;
        String password;
        String host;
        String database;

        try {

            String texto = "";
            String[] con = new String[3];

            //RUTA PARA DESARROLLO
            //File f = new File(getClass().getResource("../config/configuracion.txt").getFile());
            //RUTA PARA COMPILACION
            File f = new File("C:\\configProforma.txt");

            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            while (bfr.ready()) {
                //texto = bfr.readLine();
                datos[0] = "jdbc:mysql://" + bfr.readLine() + "/" + bfr.readLine() + "?verifyServerCertificate=false"
                        + "&useSSL=false"
                        + "&requireSSL=false";
                datos[1] = bfr.readLine();
                datos[2] = bfr.readLine();

                //System.out.println(con[0]+con[1]+con[2]+con[3]);
            }

        } catch (Exception e) {
            //System.out.println("Error al leer datos del txt: " + e);
            JOptionPane.showMessageDialog(null, "Error al leer datos del txt: " + e);
        }

        return datos;
    }

}
