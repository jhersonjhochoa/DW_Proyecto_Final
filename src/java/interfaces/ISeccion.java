/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.Seccion;

/**
 *
 * @author Jhonatan
 */
public interface ISeccion extends ICRUD<Seccion>{
    ArrayList<Seccion> selectByYears(int from, int to);
    ArrayList<Seccion> selectByGrado(int id_grado);
}
