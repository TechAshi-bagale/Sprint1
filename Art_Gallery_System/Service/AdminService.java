package com.service;

import com.entity.Admin;

public interface AdminService {
    boolean validateAdminLogin(String username, String password);
    void registerDefaultAdmin(); // Creates default admin if not already present
}
