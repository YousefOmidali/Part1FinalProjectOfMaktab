package service;

import entity.Experts;
import repository.ExpertRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ExpertService {
    ExpertRepository expertRepository = new ExpertRepository();

    public void save(Experts experts) {
        expertRepository.save(experts);
    }

    public void deleteById(Long id) {
        expertRepository.deleteById(id);
    }

    public void update(Experts experts) {
        expertRepository.update(experts);
    }

    public Experts findById(Long id) {
        return expertRepository.findById(id);
    }

    public List<Experts> findAll() {
        return expertRepository.findAll();
    }

    public List<Experts> expertSuggestions() {
        return expertRepository.suggestions();
    }

    public Experts login(String username, String password) {
        return expertRepository.login(username, password);
    }

    public List<Experts> gridSearch(String firstName,
                                    String lastName,
                                    String email,
                                    String username) {
        return expertRepository.gridSearch(firstName, lastName, email, username);
    }
}
