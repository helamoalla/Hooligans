/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.user;
import java.util.List;

/**
 *
 * @author 21694
 */
public interface IService<T> {
    void insert(T t);
    void delete(T t);
    void update(T t);
    List<T> readAll();
    public String readByMail(String id_user);
    T readById(int id);    
}
