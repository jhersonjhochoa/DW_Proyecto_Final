/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models_relation.SeccionCurso;

/**
 *
 * @author Jhonatan
 */
public class Evaluacion {
    private int id;
    private SeccionCurso seccion_curso;
    private String descripcion;
    private int porcentaje;
    private boolean bonus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SeccionCurso getSeccion_curso() {
        return seccion_curso;
    }

    public void setSeccion_curso(SeccionCurso seccion_curso) {
        this.seccion_curso = seccion_curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }
    
}
