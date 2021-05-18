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
}
