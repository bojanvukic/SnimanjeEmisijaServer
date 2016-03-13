/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbbr;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Bojan
 */
public class DBBroker {
    private Connection connection;
    private static DBBroker instance;
    
    private DBBroker() {
    }
    
    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }
    
    public void ucitajDriver() throws RuntimeException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver nije pronadjen!");
        }
    }
    
    public void otvoriKonekciju() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:odbc:BazaEmisija", "", "");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new RuntimeException("Neuspesno otvaranje konekcije!");
        }
    }
    
    public void commitTransakcije() throws RuntimeException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Neuspesan commit!");
        }
    }
    
    public void rollbackTransakcije() throws RuntimeException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException("Neuspesan rollback!");
        }
    }
    
    public void zatvoriKonekciju() throws RuntimeException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Neuspesno zatvaranje konekcije!");
        }
    }
    

    public void sacuvaj(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "INSERT INTO " + odo.vratiNazivTabele() + " " + odo.vratiInsert();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            sqlNaredba.executeUpdate(sql);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno cuvanje objekta!");
        }
    }
    
    public List<OpstiDomenskiObjekat> vratiListu(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String sql = "SELECT * FROM " + odo.vratiSpajanje();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(sql);
            return odo.vratiListu(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno ucitavanje objekata!");
        }
    }
    
    public List<OpstiDomenskiObjekat> vratiListuPoKriterijumu(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String sql = "SELECT * FROM " + odo.vratiSpajanje() + " " + odo.vratiKriterijum();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(sql);
            return odo.vratiListu(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno ucitavanje objekata!");
        }
    }

    public String vratiID(OpstiDomenskiObjekat odo) throws RuntimeException {
        try {
            String sql = "SELECT MAX("+odo.vratiNazivKoloneID()+") AS Sifra FROM " + odo.vratiNazivTabele();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            ResultSet rs = sqlNaredba.executeQuery(sql);
            return odo.vratiID(rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno ucitavanje ID!");
        }
    }
    
     public void izmeni(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiUpdate();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            sqlNaredba.executeUpdate(sql);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno cuvanje objekta!"); 
        }
    }
    
    
     public void obrisi(OpstiDomenskiObjekat odo) throws Exception{
        try {
            String sql = "DELETE * FROM " + odo.vratiNazivTabele() + " WHERE " + odo.vratiDelete();
            System.out.println(sql);
            Statement sqlNaredba = connection.createStatement();
            sqlNaredba.executeUpdate(sql);
            sqlNaredba.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Neuspesno cuvanje objekta!"); 
        }
    }

}
