/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paingan.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.paingan.chart.domain.ReportData;
import org.paingan.chart.dao.ReportDataDao;

/**
 *
 * @author paulusyansen
 */
public class ChartReportFrame extends javax.swing.JFrame {
    
    private String reportType;

    /**
     * Creates new form ChartMainFrame
     */
    public ChartReportFrame() {
        DefaultCategoryDataset alexaSeries = new DefaultCategoryDataset();
         
        ReportDataDao dataCrud = new ReportDataDao();
        dataCrud.openConnection();
        List<ReportData> alexaList = dataCrud.getAlexaList();
        dataCrud.closeConnection();
         
        for (ReportData alexa : alexaList) {
            alexaSeries.addValue(alexa.getScore(),alexa.getSite(),alexa.getDate());
        }
         
        JFreeChart chart = ChartFactory.createLineChart(
            "Site Speed (Alexa)",  "", "Average Load Time",
            alexaSeries, PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.ORANGE);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke( 3 ));
        plot.getRenderer().setSeriesPaint(1, Color.BLUE);
        plot.getRenderer().setSeriesStroke(1, new BasicStroke( 3 ));
        plot.getRenderer().setSeriesPaint(2, Color.GREEN);
        plot.getRenderer().setSeriesStroke(2, new BasicStroke( 3 ));
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setInverted(true);
        rangeAxis.setAutoRange(false);
        rangeAxis.setRangeWithMargins(1.4,3.4);
        rangeAxis.setTickUnit(new NumberTickUnit(0.1));
        
        NumberFormat formatter = new DecimalFormat("#0.00");     
        rangeAxis.setNumberFormatOverride(formatter);
      
        Color mycolor = new Color(240,240,240); 
        plot.setBackgroundPaint(mycolor);
        
        /* generate chart image */
        int width = 1280;    /* Width of the image */
        int height = 720;   /* Height of the image */ 
        File lineChart = new File( "AlexaChart.jpeg" ); 
        
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,chart, width ,height);
        } catch (IOException ex) {
            Logger.getLogger(ChartReportFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ChartPanel cp = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1280, 720);
            }
        };
    
        add(cp);

        initComponents();
        this.setLocationRelativeTo(null);
    }

    public ChartReportFrame(String reportType) throws HeadlessException {
        this.reportType = reportType;
        
        DefaultCategoryDataset alexaSeries = new DefaultCategoryDataset();
         
        ReportDataDao dataCrud = new ReportDataDao();
        dataCrud.openConnection();
        
        
        List<ReportData> dataList = new ArrayList();
        
        if("4G".equals(reportType)) {
            dataList = dataCrud.get4GList();
        } else {
            dataList = dataCrud.getAlexaList();
        }
        
        dataCrud.closeConnection();
         
        for (ReportData reportData : dataList) {
            alexaSeries.addValue(reportData.getScore(),reportData.getSite(),reportData.getDate());
        }
         
        JFreeChart chart = ChartFactory.createLineChart(
            "Site Speed ("+reportType+")",  "", "Average Load Time",
            alexaSeries, PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, Color.ORANGE);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke( 3 ));
        plot.getRenderer().setSeriesPaint(1, Color.BLUE);
        plot.getRenderer().setSeriesStroke(1, new BasicStroke( 3 ));
        plot.getRenderer().setSeriesPaint(2, Color.GREEN);
        plot.getRenderer().setSeriesStroke(2, new BasicStroke( 3 ));
        
        plot.getRenderer().setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setInverted(true);
        rangeAxis.setAutoRange(false);
        
        if("4G".equals(reportType)) {
            rangeAxis.setRangeWithMargins(0.9,5.7);
            rangeAxis.setTickUnit(new NumberTickUnit(0.5));
        } else {
            rangeAxis.setRangeWithMargins(1.4,3.4);
            rangeAxis.setTickUnit(new NumberTickUnit(0.1));
        }
        
        
        NumberFormat formatter = new DecimalFormat("#0.00");     
        rangeAxis.setNumberFormatOverride(formatter);
      
        Color mycolor = new Color(240,240,240); 
        plot.setBackgroundPaint(mycolor);
        
        /* generate chart image */
        int width = 1280;    /* Width of the image */
        int height = 720;   /* Height of the image */ 
        File lineChart = new File( reportType+"Chart.jpeg" ); 
        
        try {
            ChartUtilities.saveChartAsJPEG(lineChart ,chart, width ,height);
        } catch (IOException ex) {
            Logger.getLogger(ChartReportFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        ChartPanel cp = new ChartPanel(chart);
    
        add(cp);
        
        cp.setMouseWheelEnabled(false);

        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Chart Sample using JFreeChart");
        setMinimumSize(new java.awt.Dimension(1240, 720));
        setName("frmMain"); // NOI18N
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChartReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChartReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChartReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChartReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChartReportFrame().setVisible(true);
            }
        });
    }
    
   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "schools" , "1970" );
      dataset.addValue( 30 , "schools" , "1980" );
      dataset.addValue( 60 , "schools" ,  "1990" );
      dataset.addValue( 120 , "schools" , "2000" );
      dataset.addValue( 240 , "schools" , "2010" );
      dataset.addValue( 300 , "schools" , "2014" );
      
      dataset.addValue( 153 , "schoolss" , "1970" );
      dataset.addValue( 36 , "schoolss" , "1980" );
      dataset.addValue( 69 , "schoolss" ,  "1990" );
      dataset.addValue( 121 , "schoolss" , "2000" );
      dataset.addValue( 245 , "schoolss" , "2010" );
      dataset.addValue( 400 , "schoolss" , "2014" );
      return dataset;
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
