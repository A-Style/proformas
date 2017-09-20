package codigo.database;

import codigo.conexion.conexion;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



public class codBackup {

    JFileChooser selector = new JFileChooser(System.getProperty("user.dir"));
    conexion con = new conexion();
    PreparedStatement pst;
    ResultSet rs;

    String query;
    File file;
    String path;
    Runtime rt = Runtime.getRuntime();

    
    
    
    public void crearBackupData(Component comp) {
        selector.resetChoosableFileFilters();
        selector.setAcceptAllFileFilterUsed(true);

        selector.setDialogTitle("Guardar Backup de Datos");

        int returnVal = selector.showSaveDialog(comp);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = selector.getSelectedFile();

        } else {
            System.out.println("Error al Crear Bckup de datos");

        }

        try {
//                File file;
//                file=new File(txtruta.getText());
            path = file.getAbsolutePath() + ".sql";
            System.out.println("" + path);
//                int c=JOptionPane.showConfirmDialog(comp, "Desea Crear una Copia de Seguridad en esta ruta \n"+path, "Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//                if(c==JOptionPane.YES_OPTION){
            //query = "mysqldump --opt -u" + con.getUser() + " -p" + con.getPassword() + " " + con.getDatabase() + "  -r " + path;
            query = "mysqldump --opt -u root -p" + con.getPassword() + " " + con.getDatabase() + "  -r " + path;
            rt.exec(query);
            JOptionPane.showMessageDialog(comp, "BackUp Creado Correctamente en " + file.getPath());
            //this.dispose();
//                }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

}
