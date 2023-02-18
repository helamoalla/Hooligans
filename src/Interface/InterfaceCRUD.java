/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author azizh
 */
public interface InterfaceCRUD<T> {
    //Add
    void insert(T t);
    // Delete
    void delete(int id);
    // Update
    void update(T t);
    // Fetch ALL
    ArrayList<T> readAll();
    // Fetch by ID
    T readById(int id); 
    // Sort by nom column
    ArrayList<T> sortBy(String nom_column,String Asc_Dsc);
    
}

