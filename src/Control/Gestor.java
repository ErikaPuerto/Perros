package Control;

import java.util.ArrayList;
import Modelo.PerroDAO;
import Modelo.PerroVO;

public class Gestor {

    private PerroDAO miPerroDAO;

    public Gestor() {
        //registrarPerro();
        obtenerRegistros();
        buscarPerro();
        //eliminarPerro();
        modificarPerro();
    }

    private void obtenerRegistros() {
        miPerroDAO = new PerroDAO();
        PerroVO miPerro;
        ArrayList<PerroVO> listaPerros = miPerroDAO.listaDePerros();
        if (listaPerros.size() > 0) {
            int numeroPerro = 0;
            for (int i = 0; i < listaPerros.size(); i++) {
                numeroPerro++;
                miPerro = listaPerros.get(i);
                System.out.println("****************Estudiante No. " + numeroPerro + "**********");
              
                System.out.println("Raza: " + miPerro.getNombre());
                System.out.println("Pais de Origen : " + miPerro.getPaisOrigen());
                System.out.println("Clasificacion : " + miPerro.getClasificacion());
                System.out.println("Apariencia: " + miPerro.getApariencia());
                System.out.println("Pelo: " + miPerro.getPelo());
                System.out.println("Color: " + miPerro.getColor());
                System.out.println("Espalda: " + miPerro.getEspalda());
                System.out.println("Lomo: " + miPerro.getLomo());
                System.out.println("Cola: " + miPerro.getCola());
                System.out.println("Pecho: " + miPerro.getPecho());
                
                System.out.println("*************************************************\n");
            }
        } else {
            System.out.println("Actualmente no existen registros de estudiantes");
        }
    }

    private void buscarPerro() {
        miPerroDAO = new PerroDAO();
        String nombre = "Golden";
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(nombre);
        if (perroEncontrado != null) {
            System.out.println("Raza: " + perroEncontrado.getNombre());
            System.out.println("Pais de Origen : " + perroEncontrado.getPaisOrigen());
            System.out.println("Clasificacion : " + perroEncontrado.getClasificacion());
            System.out.println("Apariencia: " + perroEncontrado.getApariencia());
            System.out.println("Pelo: " + perroEncontrado.getPelo());
            System.out.println("Color: " + perroEncontrado.getColor());
            System.out.println("Espalda: " + perroEncontrado.getEspalda());
            System.out.println("Lomo: " + perroEncontrado.getLomo());
            System.out.println("Cola: " + perroEncontrado.getCola());
            System.out.println("Pecho: " + perroEncontrado.getPecho());
            System.out.println("*************************************************\n");

        } else {
            System.out.println("No existen un registro de perro con ese raza");
        }
    }

    private void registrarPerro() {
        miPerroDAO = new PerroDAO();
        PerroVO miPerro1 = new PerroVO();
        miPerro1.setNombre("Karla Rojas");
        miPerro1.setPaisOrigen("");
        miPerro1.setApariencia("Karla Rojas");
        miPerro1.setPelo("Karla Rojas");
        miPerro1.setColor("Karla Rojas");
        miPerro1.setEspalda("Karla Rojas");
        miPerro1.setLomo("Karla Rojas");
        miPerro1.setCola("Karla Rojas");
        miPerro1.setPecho("Karla Rojas");
        miPerroDAO.insertarDatos(miPerro1);
        
        PerroVO miPerro2 = new PerroVO();
        miPerro2.setNombre("Karla Rojas");
        miPerro2.setPaisOrigen("");
        miPerro2.setApariencia("Karla Rojas");
        miPerro2.setPelo("Karla Rojas");
        miPerro2.setColor("Karla Rojas");
        miPerro2.setEspalda("Karla Rojas");
        miPerro2.setLomo("Karla Rojas");
        miPerro2.setCola("Karla Rojas");
        miPerro2.setPecho("Karla Rojas");
        miPerroDAO.insertarDatos(miPerro2);
        
    }
 private void eliminarPerro() {
        String nombre = "Golden";
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(nombre);
        if (perroEncontrado != null) {
            System.out.println("************ Estudiante a Eliminar****************");
            System.out.println("Raza: " + perroEncontrado.getNombre());
            System.out.println("Pais de Origen : " + perroEncontrado.getPaisOrigen());
            System.out.println("Clasificacion : " + perroEncontrado.getClasificacion());
            System.out.println("Apariencia: " + perroEncontrado.getApariencia());
            System.out.println("Pelo: " + perroEncontrado.getPelo());
            System.out.println("Color: " + perroEncontrado.getColor());
            System.out.println("Espalda: " + perroEncontrado.getEspalda());
            System.out.println("Lomo: " + perroEncontrado.getLomo());
            System.out.println("Cola: " + perroEncontrado.getCola());
            System.out.println("Pecho: " + perroEncontrado.getPecho());
            System.out.println("*************************************************\n");

            if (miPerroDAO.eliminarPerro(nombre)) {
                System.out.println("Perro Eliminado");
            } else {
                System.out.println("No se pudo eliminar el registro del perro");
            }
        } else {
            System.out.println("No existen un perro con ese codigo");
        }
    }
private void modificarPerro() {
        String nombre = "1015";
        PerroVO perroEncontrado = miPerroDAO.consultarPerro(nombre);
        if (perroEncontrado != null) {
            System.out.println("****************Estudiante a Modificar****************");
            
            System.out.println("Raza: " + perroEncontrado.getNombre());
            System.out.println("Pais de Origen : " + perroEncontrado.getPaisOrigen());
            System.out.println("Clasificacion : " + perroEncontrado.getClasificacion());
            System.out.println("Apariencia: " + perroEncontrado.getApariencia());
            System.out.println("Pelo: " + perroEncontrado.getPelo());
            System.out.println("Color: " + perroEncontrado.getColor());
            System.out.println("Espalda: " + perroEncontrado.getEspalda());
            System.out.println("Lomo: " + perroEncontrado.getLomo());
            System.out.println("Cola: " + perroEncontrado.getCola());
            System.out.println("Pecho: " + perroEncontrado.getPecho());

            System.out.println("*************************************************\n");
           
            if (miPerroDAO.modificarPerro(nombre)) {
                System.out.println("Estudiante Modificado");
                perroEncontrado = miPerroDAO.consultarPerro(nombre);
                System.out.println("****************Estudiante Modificado****************");
                
                System.out.println("Raza: " + perroEncontrado.getNombre());
                System.out.println("Pais de Origen : " + perroEncontrado.getPaisOrigen());
                System.out.println("Clasificacion : " + perroEncontrado.getClasificacion());
                System.out.println("Apariencia: " + perroEncontrado.getApariencia());
                System.out.println("Pelo: " + perroEncontrado.getPelo());
                System.out.println("Color: " + perroEncontrado.getColor());
                System.out.println("Espalda: " + perroEncontrado.getEspalda());
                System.out.println("Lomo: " + perroEncontrado.getLomo());
                System.out.println("Cola: " + perroEncontrado.getCola());
                System.out.println("Pecho: " + perroEncontrado.getPecho());
                
                System.out.println("*************************************************\n");
            } else {
                System.out.println("No se pudo modificar el registro del perro");
            }
        } else {
            System.out.println("No existen un registro de perro con esa raza");
        }
    }
}
