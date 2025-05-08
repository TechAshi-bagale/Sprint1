package com.serviceimpl;
import com.dao.Admindao;
import com.daoimpl.AdminDaoImpl;
import com.entity.Admin;
import com.service.AdminService;



public class AdminServiceImpl implements AdminService {

    private Admindao adminDAO = new AdminDaoImpl();

    @Override
    public boolean validateAdminLogin(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null) {
            return admin.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public void registerDefaultAdmin() {
        Admin existing = adminDAO.getAdminByUsername("admin");
        if (existing == null) {
            Admin defaultAdmin = new Admin("admin", "admin@123");
            adminDAO.saveAdmin(defaultAdmin);
            System.out.println("Default Admin Created: Username = admin, Password = admin@123");
        }
    }
}
