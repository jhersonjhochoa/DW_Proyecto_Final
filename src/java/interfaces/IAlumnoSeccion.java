/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models_relation.AlumnoSeccion;

/**
 *
 * @author Jhonatan
 */
public interface IAlumnoSeccion extends ICRUD<AlumnoSeccion>{
    ArrayList<AlumnoSeccion> selectBySeccion(int id_sec);
}
