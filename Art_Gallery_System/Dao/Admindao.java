package com.dao;

import com.entity.Admin;

/**
 * Admindao interface provides method declarations
 * for performing operations related to Admin entity.
 */
public interface Admindao {

    //Saves the provided Admin object to the database.
    void saveAdmin(Admin admin);

    // Retrieves an Admin object from the database based on the username.
     
    
    Admin getAdminByUsername(String username);
}
