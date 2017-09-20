package codigo.chart;

import codigo.conexion.conexion;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class codigoChart {

    DefaultCategoryDataset Datos;
    DefaultPieDataset DatosPie = new DefaultPieDataset();
    ChartPanel myChart;
    private JFreeChart Grafica;

    conexion con = new conexion();

    public void chartBar(JPanel p1) {
        Datos=new DefaultCategoryDataset();
        Datos.addValue(1, "Negocio 1", "Lunes");
        Datos.addValue(2, "Negocio 1", "Martes");
        Datos.addValue(3, "Negocio 1", "Miércoles");
        Datos.addValue(4, "Negocio 1", "Jueves");
        Datos.addValue(5, "Negocio 1", "Viernes");
        Datos.addValue(6, "Negocio 1", "Sábado");
        Datos.addValue(7, "Negocio 1", "Domingo");

        Grafica = ChartFactory.createBarChart("Visitas diarias",
                "Días", "Visitas", Datos,
                PlotOrientation.VERTICAL, true, true, false);

        try {

            myChart = new ChartPanel(Grafica);
            myChart.setMouseWheelEnabled(true);
            p1.setLayout(new java.awt.BorderLayout());
            p1.add(myChart, BorderLayout.CENTER);
            p1.setSize(400, 200);
            p1.validate();

        } catch (Exception e) {
            System.out.println("codigo.chart.codigoChart() " + e);
        }
    }

    public void chartPie(JPanel p2) {
        DatosPie.setValue("Negocio 1", 3);
        DatosPie.setValue("Negocio 2", 5);
        DatosPie.setValue("Negocio 3", 6);
        Grafica = ChartFactory.createPieChart("Titulo", DatosPie);

        try {

            myChart = new ChartPanel(Grafica);
            myChart.setMouseWheelEnabled(true);
            p2.setLayout(new java.awt.BorderLayout());
            p2.add(myChart, BorderLayout.CENTER);
            p2.setSize(400, 200);
            p2.validate();

        } catch (Exception e) {
        }
    }

    public void chartBigBar(JPanel p3) {
        Datos=new DefaultCategoryDataset();
        Datos.addValue(1, "Negocio 1", "Lunes");
        Datos.addValue(2, "Negocio 1", "Martes");
        Datos.addValue(3, "Negocio 1", "Miércoles");
        Datos.addValue(4, "Negocio 1", "Jueves");
        Datos.addValue(5, "Negocio 1", "Viernes");
        Datos.addValue(6, "Negocio 1", "Sábado");
        Datos.addValue(7, "Negocio 1", "Domingo");

        Datos.addValue(4, "Negocio 2", "Lunes");
        Datos.addValue(5, "Negocio 2", "Martes");
        Datos.addValue(7, "Negocio 2", "Miércoles");
        Datos.addValue(2, "Negocio 2", "Jueves");
        Datos.addValue(4, "Negocio 2", "Viernes");
        Datos.addValue(10, "Negocio 2", "Sábado");
        Datos.addValue(3, "Negocio 2", "Domingo");

        Grafica = ChartFactory.createBarChart("Visitas diarias",
                "Días", "Visitas", Datos,
                PlotOrientation.VERTICAL, true, true, false);

        try {

            ChartPanel myChart3 = new ChartPanel(Grafica);
            myChart3.setMouseWheelEnabled(true);
            p3.setLayout(new java.awt.BorderLayout());
            p3.add(myChart3, BorderLayout.CENTER);
            p3.setSize(810, 400);
            p3.validate();

        } catch (Exception e) {
            System.out.println("codigo.chart.codigoChart() " + e);
        }
    }

}
