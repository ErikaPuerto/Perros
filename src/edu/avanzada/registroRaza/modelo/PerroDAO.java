package edu.avanzada.registroRaza.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PerroDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public PerroDAO() {
        con = null;
        st = null;
        rs = null;
    }
    public PerroVO consultarPerro(String nombre) {
        PerroVO perro = null;
        String consulta = "SELECT * FROM Mascotas where nombre='" + nombre+"'";
        try {
            con = (Connection) Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                perro = new PerroVO();
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("pais"));
                perro.setApariencia(rs.getString("apariencia"));
                perro.setPelo(rs.getString("pelo"));
                perro.setColor(rs.getString("color"));
                perro.setEspalda(rs.getString("espalda"));
                perro.setLomo(rs.getString("lomo"));
                perro.setCola(rs.getString("cola"));
                perro.setPecho(rs.getString("pecho"));
                
                
                
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return perro;
    }
    public ArrayList<PerroVO> listaDePerros() {
        ArrayList<PerroVO> misPerros = new ArrayList<PerroVO>();
        String consulta = "SELECT * FROM Estudiantes";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                PerroVO perro = new PerroVO();
                perro.setNombre(rs.getString("nombre"));
                perro.setPaisOrigen(rs.getString("paisOrigen"));
                perro.setApariencia(rs.getString("apariencia"));
                perro.setPelo(rs.getString("pelo"));
                perro.setColor(rs.getString("color"));
                perro.setEspalda(rs.getString("espalda"));
                perro.setLomo(rs.getString("lomo"));
                perro.setCola(rs.getString("cola"));
                perro.setPecho(rs.getString("pecho"));
                misPerros.add(perro);
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return misPerros;
    }
    public void insertarDatos(PerroVO perro) {
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            String insercion = "INSERT INTO Perros VALUES('" + perro.getNombre() + 
                    "','" + perro.getPaisOrigen() + "','" + perro.getApariencia() + "','" + 
                    perro.getPelo() + "'," + perro.getColor() + "','" + perro.getEspalda() +
                    "','" + perro.getLomo() + "','" + perro.getCola() + "','" + 
                    perro.getPecho()+")";
            
            st.executeUpdate(insercion);
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.print("No se pudo realizar la insercion");
        }
    }
 public boolean eliminarPerro(String nombre) {
        String consulta = "DELETE FROM Perros where nombre='" + nombre + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la eliminacion");
        }
        return false;
    }
 public boolean modificarPerro(String nombre) {
        String consulta = "update Perros set paisOrigen=" + "Canadá" + " where nombre='" + nombre + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la modifcacion");
        }
        return false;
    }
}
