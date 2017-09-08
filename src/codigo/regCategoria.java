/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import codigo.conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mipc
 */
public class regCategoria {

    private String categoria;

    private PreparedStatement pst;
    private ResultSet rs;
    private String query = "";
    conexion c = new conexion();

    public void nuevoCategoria() {
        try {
            query = "INSERT INTO tcategoria(nomCategoria) VALUES(?)";
            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getCategoria());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Se registro la Categoria");
        } catch (Exception e) {
            System.out.println("Error en regCategoria.nuevoCategoria: " + e);
        } finally {
            c.desconectar();
        }
    }

    public void mostrarTablaCategoria(JTable tab) {
        try {
            query = "SELECT nomCategoria FROM tcategoria";
            pst = c.conectar().prepareStatement(query);
            rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Categoria");
            Object[] fila = new Object[1];
            while (rs.next()) {

                fila[0] = rs.getString(1);

                model.addRow(fila);
            }
            tab.setModel(model);

        } catch (Exception e) {
            System.out.println("Error en regCategoria.mostrarTablaCateogira: " + e);
        } finally {
            c.desconectar();

        }

    }

    public void llenarComboCategoria(JComboBox cbo) {
        try {
            query = "SELECT DISTINCT(nomCategoria) FROM tcategoria";
            pst = c.conectar().prepareStatement(query);
            rs = pst.executeQuery();
            cbo.removeAllItems();           
          
            while (rs.next()) {              
                cbo.addItem(rs.getString(1).toString());
            }

        } catch (Exception e) {
            System.out.println("Error en regCategoria.llenarCombo: " + e);
        } finally {
            c.desconectar();
        }
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
