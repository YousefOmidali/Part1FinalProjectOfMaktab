package service;

import entity.SubService;
import repository.SubServiceRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubServiceService {
    SubServiceRepository subServiceRepository = new SubServiceRepository();

    public void save(SubService subService) {
        subServiceRepository.save(subService);
    }

    public void deleteById(Long id) {
        subServiceRepository.deleteById(id);
    }

    public void update(SubService subService) {
        subServiceRepository.update(subService);
    }

    public SubService findById(Long id) {
        return subServiceRepository.findById(id);
    }

    public List<SubService> findAll() {
        return subServiceRepository.findAll();
    }
}
