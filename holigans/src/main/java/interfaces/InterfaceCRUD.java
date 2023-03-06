/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface InterfaceCRUD<T> {
    void insert(T t);
    void delete(int id);
    void update(T t);
    ArrayList<T> readAll();
    T readById(int id); 
    ArrayList<T> sortBy(String nom_column,String Asc_Dsc);
}
