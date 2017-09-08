package codigo;

import codigo.conexion.conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class regProducto {

    private String producto, categoria;
    private double precio;

    private PreparedStatement pst;
    private CallableStatement cst;
    private ResultSet rs;
    private String query = "";
    conexion c = new conexion();

    public void registrarProducto() {
        try {
//            query="Call reg_Producto(?,?,?)";
//            cst=c.conectar().prepareCall(query);
//            cst.setString(1, getProducto());
//            cst.setDouble(2, getPrecio());
//            cst.setString(3,getCategoria());
//            cst.executeUpdate();
              query="INSERT INTO tProductos(producto,precio,idCategoria) VALUES(?,?,(SELECT idCategoria FROM tcategoria WHERE nomCategoria=?));";
              
            pst=c.conectar().prepareStatement(query);
            pst.setString(1, getProducto());
            pst.setDouble(2, getPrecio());
            pst.setString(3, getCategoria());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Producto Registrado");
        } catch (Exception e) {
            System.out.println("codigo.regProducto.registrarProducto(): " + e);
        } finally {
            c.desconectar();
        }
    }
    
     public void llenarComboProducto(JComboBox cbo) {
        try {
            query = "SELECT DISTINCT(producto) FROM tproductos";
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
    
   
    
    

    /*SETTER Y GETTER*/
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
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

}

