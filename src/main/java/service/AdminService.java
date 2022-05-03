package service;

import entity.Admin;
import repository.AdminRepository;

import javax.transaction.Transactional;
import java.util.List;


public class AdminService {
    AdminRepository adminRepository = new AdminRepository();

    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    public void update(Admin admin) {
        adminRepository.update(admin);
    }

    public Admin findById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin login(String username, String password) {
        return adminRepository.login(username, password);
    }

    public List<Admin> gridSearch(String firstName,
                                  String lastName,
                                  String email,
                                  String username) {
        return adminRepository.gridSearch(firstName, lastName, email, username);
    }
}
