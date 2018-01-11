/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paingan.chart.dao;

import org.paingan.chart.domain.ReportData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paulusyansen
 */
public class ReportDataDao {
    private final static Logger logger = Logger.getLogger(ReportDataDao.class.getName());
    
    private Connection conn;
    private String status;

    public ReportDataDao() {
        
    }

    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            setConn(DriverManager.getConnection("jdbc:sqlite:data.db"));
        } catch (ClassNotFoundException | SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeConnection() {
        try {       
            getConn().close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAlexa(ReportData reportData) throws SQLException {
        String query = "insert into alexa(date, site, score) values(?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, reportData.getDate());
        ps.setString(2, reportData.getSite());
        ps.setFloat(3, reportData.getScore());
        ps.execute();
    }
    
    public void insert4G(ReportData reportData) throws SQLException {
        String query = "insert into mobiledata(date, site, score) values(?,?,?)";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, reportData.getDate());
        ps.setString(2, reportData.getSite());
        ps.setFloat(3, reportData.getScore());
        ps.execute();
    }
    
    public void deleteAlexa(ReportData alexa) {
        
    }
        
    public List<ReportData> getAlexaList() {
        List<ReportData> alexaList = new ArrayList();
        
        try {
            Statement stat = getConn().createStatement();
            ResultSet rs = stat.executeQuery("select * from alexa;");
            
            
            while (rs.next()) {
                ReportData alexa = new ReportData();
                
                alexa.setId(rs.getInt("id"));
                alexa.setDate(rs.getString("date"));
                alexa.setSite(rs.getString("site"));
                alexa.setScore(rs.getFloat("score"));
                alexa.setShowYN(rs.getString("showYN"));
                
                alexaList.add(alexa);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return alexaList;
    }
    
    public List<ReportData> get4GList() {
        List<ReportData> alexaList = new ArrayList();
        
        try {
            Statement stat = getConn().createStatement();
            ResultSet rs = stat.executeQuery("select * from mobiledata;");
            
            
            while (rs.next()) {
                ReportData alexa = new ReportData();
                
                alexa.setId(rs.getInt("id"));
                alexa.setDate(rs.getString("date"));
                alexa.setSite(rs.getString("site"));
                alexa.setScore(rs.getFloat("score"));
                alexa.setShowYN(rs.getString("showYN"));
                
                alexaList.add(alexa);
            }
            rs.close();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        return alexaList;
    }
        
//    public static void main(String[] args) {
//        System.out.println("testsing");
//        ReportDataDao datacrud = new ReportDataDao();
//        datacrud.openConnection();
//       // datacrud.getAllData();
//        datacrud.closeConnection();
//        
//    }
        
//            private void editData() {
//        if (status.equals("add")) {
//            txtId.setText("");
//            txtNama.setText("");
//            txtAlamat.setText("");
//            txtTelp.setText("");
//            txtEmail.setText("");
//        }
//        txtId.setEditable(true);
//        txtNama.setEditable(true);
//        txtAlamat.setEditable(true);
//        txtEmail.setEditable(true);
//        btSimpan.setEnabled(true);
//        btBatal.setEnabled(true);
//        btTambah.setEnabled(false);
//        btUbah.setEnabled(false);
//        btHapus.setEnabled(false);
//        btKeluar.setEnabled(false);
//        txtNama.requestFocus();
//    }
//
//    private void readData() {
//        txtId.setEditable(false);
//        txtNama.setEditable(false);
//        txtAlamat.setEditable(false);
//        txtEmail.setEditable(false);
//        btSimpan.setEnabled(false);
//        btBatal.setEnabled(false);
//        btTambah.setEnabled(true);
//        btUbah.setEnabled(true);
//        btHapus.setEnabled(true);
//        btKeluar.setEnabled(true);
//    }
//    
//        public BukuAlamat() {
//        initComponents();
//        openConnection();
//        kolomTabel();
//        getAllData();
//        closeConnection();
//    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
