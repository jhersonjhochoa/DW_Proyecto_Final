/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models_relation;

import models.Seccion;
import models.Usuario;

/**
 *
 * @author Jhonatan
 */
public class AlumnoSeccion {
    private int id;
    private Usuario alumno;
    private Seccion seccion;
    private double promedio;
    private int orden_merito;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getAlumno() {
        return alumno;
    }

    public void setAlumno(Usuario alumno) {
        this.alumno = alumno;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getOrden_merito() {
        return orden_merito;
    }

    public void setOrden_merito(int orden_merito) {
        this.orden_merito = orden_merito;
    }
    
}
