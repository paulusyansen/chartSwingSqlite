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
