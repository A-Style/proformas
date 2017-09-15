package codigo.proforma;

import codigo.conexion.conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class codProf_Busquedas {

    conexion c = new conexion();

    private PreparedStatement pst;
    private Statement st;
    private CallableStatement cst;
    private ResultSet rs;
    private String query = "";

    private String categoria;
    private String producto;
    private String codigo;
    private String nroProforma;

    public void llenarComboCategoria(JComboBox cbo) {
        try {
            query = "SELECT DISTINCT(nomCategoria) FROM tcategoria ORDER BY nomCategoria";
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

    public void llenarComboProductoPorCategoria(JComboBox cbo) {
        try {
            query = "SELECT DISTINCT(producto)\n"
                    + "FROM tproductos pro\n"
                    + "INNER JOIN tcategoria cat\n"
                    + "ON pro.idcategoria=cat.idcategoria\n"
                    + "WHERE cat.nomCategoria=? AND pro.codigo LIKE ?";
            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getCategoria());
            pst.setString(2, "%" + getCodigo() + "%");
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

    public void mostrarTablaProductos(JTable tab) {
        try {
            query = "SELECT cat.nomCategoria,pro.producto,pro.formato,pro.codigo,pro.tipo,pro.precio\n"
                    + "FROM tproductos pro\n"
                    + "INNER JOIN tcategoria cat\n"
                    + "ON pro.idCategoria=cat.idCategoria\n"
                    + "ORDER BY cat.nomCategoria,pro.producto";
            pst = c.conectar().prepareStatement(query);
            rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Categoria");
            model.addColumn("Producto");
            model.addColumn("Formato");
            model.addColumn("Codigo");
            model.addColumn("Tipo");
            model.addColumn("Precio");
            Object[] fila = new Object[6];
            while (rs.next()) {

                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getDouble(6);

                model.addRow(fila);
            }
            tab.setModel(model);

        } catch (Exception e) {
            System.out.println("Error en regCategoria.mostrarTablaCategoria: " + e);
        } finally {
            c.desconectar();

        }

    }

    public void mostrarTablaItemsProforma(JTable tab) {
        try {
            query = "    SELECT CASE pro.tipo WHEN 'U' THEN CONCAT('0',detpro.cantidad) WHEN 'P' THEN detpro.cantidad END cantidad,\n"
                  //query=  "SELECT detpro.cantidad,\n"
                    + "cat.nomCategoria,pro.codigo,pro.producto,pro.precio,detpro.importe\n"
                    + "                    FROM tcategoria cat\n"
                    + "                    INNER JOIN tproductos pro ON cat.idCategoria=pro.idCategoria\n"
                    + "                    INNER JOIN tdetaproforma detpro ON pro.idProducto=detpro.idProducto\n"
                    + "                    WHERE detpro.idProforma=(SELECT idProforma FROM tproforma WHERE nroProforma LIKE ?)";
            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getNroProforma());
            rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Cantidad");
            model.addColumn("Categoria");
            model.addColumn("Codigo");
            model.addColumn("Producto");            
            model.addColumn("Precio U.");
            model.addColumn("Importe");
            
            Object[] fila = new Object[6];
            while (rs.next()) {

                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getDouble(5);
                fila[5] = rs.getDouble(6);
                
                

                model.addRow(fila);
            }
            tab.setModel(model);

        } catch (Exception e) {
            System.out.println("Error en regCategoria.mostrarTablaCategoria: " + e);
        } finally {
            c.desconectar();

        }

    }

    public void mostrarTablaItems(JTable tab) {
        try {
            query = "SELECT det.cantidad,pro.producto,pro.precio,det.importe FROM tdetaproforma det \n"
                    + "INNER JOIN tproductos pro ON det.idProducto=pro.idProducto"
                    + "WHERE det.idProforma=(SELECT idProforma FROM tproforma WHERE nroProforma LIKE ?)";
            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getNroProforma());
            rs = pst.executeQuery();
            DefaultTableModel t = new DefaultTableModel();
            t.addColumn("Cant");
            t.addColumn("Producto");
            t.addColumn("P.Unitario");
            t.addColumn("Importe");
            Object[] obj = new Object[4];
            while (rs.next()) {
                obj[0] = rs.getInt(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getDouble(3);
                obj[3] = rs.getDouble(4);
                t.addRow(obj);
            }

            tab.setModel(t);
        } catch (Exception e) {
        }
    }

    /**
     * **** SETTER y GETTER***********
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

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nroProforma
     */
    public String getNroProforma() {
        return nroProforma;
    }

    /**
     * @param nroProforma the nroProforma to set
     */
    public void setNroProforma(String nroProforma) {
        this.nroProforma = nroProforma;
    }

}
