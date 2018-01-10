/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.paingan.chart.dao;

import org.paingan.chart.domain.Alexa;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class AlexaDao {
    private final static Logger logger = Logger.getLogger(AlexaDao.class.getName());
    
    private Connection conn;
    private String status;

    public AlexaDao() {
        
    }

    public void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            setConn(DriverManager.getConnection("jdbc:sqlite:alexa-data.db"));
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
    
    public void insertAlexa(Alexa alexa) {
        
    }
    
    public void deleteAlexa(Alexa alexa) {
        
    }
        
    public List<Alexa> getAlexaList() {
        List<Alexa> alexaList = new ArrayList();
        
        try {
            Statement stat = getConn().createStatement();
            ResultSet rs = stat.executeQuery("select * from alexa;");
            
            
            while (rs.next()) {
                Alexa alexa = new Alexa();
                
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
//        AlexaDao datacrud = new AlexaDao();
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
