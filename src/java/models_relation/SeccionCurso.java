/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models_relation;

import models.Curso;
import models.Seccion;
import models.Usuario;

/**
 *
 * @author Jhonatan
 */
public class SeccionCurso {
    private int id;
    private Seccion seccion;
    private Curso curso;
    private Usuario docente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Usuario getDocente() {
        return docente;
    }

    public void setDocente(Usuario docente) {
        this.docente = docente;
    }
    
}
