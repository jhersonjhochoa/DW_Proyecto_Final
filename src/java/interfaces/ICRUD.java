/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Jhonatan
 * @param <T>
 */
public interface ICRUD<T> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(int id);
    T selectById (int id);
    ArrayList<T> selectAll();
}

