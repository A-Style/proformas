package codigo.proforma;

import codigo.conexion.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class codProf_Actualizar {

    
    conexion c = new conexion();

    private String codigo, producto, categoria,tipo,nroProforma;
    private int cantidad;
    private double importe;

    private String query;

    PreparedStatement pst;
    ResultSet rs;

    public void actualizarItem(JTable tab) {
        try {
            query = "UPDATE tdetaproforma deta INNER JOIN tproforma pro ON deta.idProforma=pro.idProforma\n"
                    + "INNER JOIN tproductos prod ON prod.idProducto=deta.idProducto\n"
                    + "SET deta.cantidad=?,deta.importe=?,deta.tipo=? \n"
                    + "WHERE pro.nroProforma=? AND prod.codigo=?";
            pst = c.conectar().prepareStatement(query);
            pst.setInt(1, getCantidad());
            pst.setDouble(2, getImporte());
            pst.setString(3, getTipo());
            pst.setString(4, getNroProforma());
            pst.setString(5, getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro Actualizado");
        } catch (Exception e) {
            System.out.println("codigo.proforma.codProf_Actualizar.actualizarItem() " + e.getMessage());

        }
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
