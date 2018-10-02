
package com.capgemini.serviciosya.dao;


import java.util.List;


public interface IJdbcDao <type, data> {

    void create (type object);
    
    type read (data p_key);
    
    void update (type object);
    
    void delete (data p_key);
    
    List<type> readAll ();
}
