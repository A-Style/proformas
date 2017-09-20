package codigo.busqueda;

import codigo.conexion.conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class codBus_Busqueda {

    conexion c = new conexion();

    private String nroProforma, codigo, producto, categoria;

    private PreparedStatement pst;
    private Statement st;
    private CallableStatement cst;
    private ResultSet rs;
    private String query = "";

    public void mostrarTablaBusqueda(JTable tab) {
        try {
            query = "SELECT \n"
                    + "prof.nroProforma,\n"
                    + "prof.fecha,\n"
                    + "prod.formato,\n"
                    + "CASE prod.tipo WHEN 'V' THEN 'Vidrio' WHEN 'P' THEN 'Plastico' WHEN 'T' THEN 'Tetrapack' END 'Tipo',\n"
                    + "CASE detpro.tipo WHEN 'U' THEN 'Unidad' WHEN 'P' THEN 'Paquete' END 'U.Medida',\n"
                    + "prod.codigo,\n"
                    + "detpro.cantidad,\n"
                    + "cat.nomCategoria,\n"
                    + "prod.producto,\n"
                    + "prod.precio,\n"
                    + "detpro.importe\n"
                    + "FROM tproforma prof \n"
                    + "INNER JOIN tdetaproforma detpro ON prof.idProforma=detpro.idProforma\n"
                    + "INNER JOIN tproductos prod ON prod.idProducto=detpro.idProducto\n"
                    + "INNER JOIN tcategoria cat ON cat.idCategoria=prod.idCategoria \n"
                    + "WHERE prof.nroProforma= ? OR prod.codigo= ? OR (prod.producto like ? OR cat.nomCategoria= ?)\n"
                    + "ORDER BY prof.fecha";

            pst = c.conectar().prepareStatement(query);
            pst.setString(1, getNroProforma());
            pst.setString(2, getCodigo());
            pst.setString(3,"%"+getProducto()+"%");
            pst.setString(4,getCategoria());
            rs = pst.executeQuery();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Proforma");
            model.addColumn("Fecha");
            model.addColumn("Formato");
            model.addColumn("Tipo");
            model.addColumn("U.Medida");
            model.addColumn("Codigo");
            model.addColumn("Cantidad");
            model.addColumn("Categoria");
            model.addColumn("Producto");
            model.addColumn("P.Unitario");
            model.addColumn("Importe");
            Object[] fila = new Object[11];
            while (rs.next()) {

                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getInt(3);
                fila[3] = rs.getString(4);
                fila[4] = rs.getString(5);
                fila[5] = rs.getString(6);
                fila[6] = rs.getInt(7);
                fila[7] = rs.getString(8);
                fila[8] = rs.getString(9);
                fila[9] = rs.getDouble(10);
                fila[10] = rs.getDouble(11);

                model.addRow(fila);
            }
            tab.setModel(model);

        } catch (Exception e) {
            System.out.println("Error en codigo.busqueda.codBus_Busqueda.mostrarTablaBusqueda() " + e);
        } finally {
            c.desconectar();

        }

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
