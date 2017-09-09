/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo.proforma;

import javax.swing.JTable;

/**
 *
 * @author Mipc
 */
public class tableSise {
    codProf_Busquedas codBusqueda=new codProf_Busquedas();
    public void llenarTABProducto(JTable tablaProductos) {
        codBusqueda.mostrarTablaProductos(tablaProductos);
        tablaProductos.getColumn("Categoria").setMinWidth(100);
        tablaProductos.getColumn("Producto").setMinWidth(200);
        tablaProductos.getColumn("Formato").setMinWidth(30);
        tablaProductos.getColumn("Codigo").setMinWidth(50);
        tablaProductos.getColumn("Tipo").setMinWidth(30);
       tablaProductos.getColumn("Precio").setMinWidth(30);


    }
}
