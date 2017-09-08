package codigo;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class regProforma {

    private String numProforma, Producto, fecha, tipo, categoria;
    private double precioUnitario, importe;
    private int cantidad;

    private PreparedStatement pst;
    private Statement st;
    private CallableStatement cst;
    private ResultSet rs;
    private String query = "";
    conexion c = new conexion();

    public void registroItem() {
        try {
            query = "INSERT INTO tdetaproforma(cantidad,importe,tipo,idProforma,idProducto) "
                    + "VALUES(?,?,?,(SELECT idProforma FROM tproforma WHERE nroProforma=?),(SELECT idProducto FROM tProductos WHERE producto=?));";
            pst = c.conectar().prepareStatement(query);
            pst.setInt(1, getCantidad());
            pst.setDouble(2, getImporte());
            pst.setString(3, getTipo());
            pst.setString(4, getNumProforma());
            pst.setString(5, getProducto());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Item Registrado");
        } catch (Exception e) {
            System.out.println("codigo.regProforma.registroItem() " + e);
        } finally {
            c.desconectar();
        }
    }

    public void registrarProforma() {
        try {
            query = "INSERT INTO tProforma(nroProforma,fecha) VALUES(?,?);";
            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getNumProforma());
            pst.setString(2, getFecha());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Proforma Registrado");
        } catch (Exception e) {
            System.out.println("codigo.regProforma.registrarProforma() " + e);
        } finally {
            c.desconectar();
        }
    }

    public void llenarTablaItems(JTable tab) {
        try {

            query = "SELECT "
                    + "prof.nroProforma,"
                    + "prof.fecha,"
                    + "CASE detpro.tipo WHEN 'U' THEN 'Unidad' WHEN 'P' THEN 'Paquete' END 'tipo',"
                    + "detpro.cantidad,"
                    + "cat.nomCategoria,"
                    + "prod.producto,"
                    + "prod.precio,"
                    + "detpro.importe"
                    + " FROM tproforma prof \n"
                    + "INNER JOIN tdetaproforma detpro ON prof.idProforma=detpro.idProforma \n"
                    + "INNER JOIN tproductos prod ON prod.idProducto=detpro.idProducto \n"
                    + "INNER JOIN tcategoria cat ON cat.idCategoria=prod.idCategoria \n"
                    + "WHERE prof.nroProforma LIKE '%" + getNumProforma() + "%' and prod.producto LIKE '%" + getProducto() + "%' and cat.nomCategoria LIKE '%" + getCategoria() + "%'";

            st = c.conectar().createStatement();
            rs = st.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Proforma");//String
            model.addColumn("fecha");//String
            model.addColumn("U.Medida");//String
            model.addColumn("Cantidad");//Integer
            model.addColumn("Categoria");//String
            model.addColumn("Descripcion");//String
            model.addColumn("P. Unitario");//Double
            model.addColumn("Importe");//Double

            Object[] fila = new Object[8];
            while (rs.next()) {
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getInt(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getDouble(7);
                fila[7] = rs.getDouble(8);

                model.addRow(fila);
            }

            tab.setModel(model);

            //JOptionPane.showMessageDialog(null, "Proforma Registrado");
        } catch (Exception e) {
            System.out.println("codigo.regProforma.llenarTablaItems() " + e);
        } finally {
            c.desconectar();
        }
    }

    public double sumarFilas(JTable tab) {
        double total = 0;
        try {

            for (int fila = 0; fila < tab.getRowCount(); fila++) {

                total = total + Double.parseDouble(tab.getValueAt(fila, 8).toString());

            }
        } catch (Exception e) {
            System.out.println("codigo.regProforma.sumarFilas(): " + e);
        } finally {
            c.desconectar();
        }
        return total;
    }

    /*SETTER Y GETTER*/
    public String getNumProforma() {
        return numProforma;
    }

    /**
     * @param numProforma the numProforma to set
     */
    public void setNumProforma(String numProforma) {
        this.numProforma = numProforma;
    }

    /**
     * @return the Producto
     */
    public String getProducto() {
        return Producto;
    }

    /**
     * @param Producto the Producto to set
     */
    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precioUnitario
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
