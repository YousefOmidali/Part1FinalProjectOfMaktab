package service;

import entity.Service;
import repository.ServiceRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ServiceService {
    ServiceRepository serviceRepository = new ServiceRepository();

    public void save(Service service) {
        serviceRepository.save(service);
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }

    public void update(Service service) {
        serviceRepository.update(service);
    }

    public Service findById(Long id) {
        return serviceRepository.findById(id);
    }

    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

}
