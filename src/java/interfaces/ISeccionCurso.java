/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
public interface ISeccionCurso extends ICRUD<SeccionCurso>{
    ArrayList<SeccionCurso> selectByCurso(int id_curso);
    ArrayList<SeccionCurso> selectBySeccion(int id_seccion);
    ArrayList<SeccionCurso> selectByDocente(int id_docente);
    SeccionCurso selectBySeccionCurso(int id_seccion, int id_curso);
}
