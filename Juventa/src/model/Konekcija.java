package model;;

import java.sql.*;

public abstract class Konekcija {
    private String host;
    private String korisnik;
    private String lozinka;
    private String baza;

    protected Connection konekcija;

    protected Konekcija () {
        this("localhost:6033","root","bT4lI0cS5jZ7dA1e","juventa");
    }

    protected Konekcija (String host, String korisnik, String lozinka, String baza) {
        this.host = host;
        this.korisnik = korisnik;
        this.lozinka = lozinka;
        this.baza = baza;
    }

    public abstract ResultSet select (String sql);
    public abstract PreparedStatement exec (String sql);

    public void connect () {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.konekcija = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.baza+"?"
                    + "user="+this.korisnik+"&password="+this.lozinka);
        } catch (ClassNotFoundException e) {
            System.out.println ("Sustav nije uspio pronaći klasu za konekciju na MYSQL...");
        } catch (SQLException e) {
            System.out.println ("Sustav nije se mogao spojiti na bazu podataka...");
        }
    }

    public void close () {
        try {
            this.konekcija.close();
        } catch (SQLException ex) {
            System.out.println("Konekcija se ne moze zatvoriti.");
        }
    }



}
