/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Usuario;


/**
 *
 * @author Jhonatan
 */
public interface IUsuario extends ICRUD<Usuario>{
    boolean login();
}
