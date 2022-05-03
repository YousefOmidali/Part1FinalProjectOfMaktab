package service;

import entity.Comment;
import repository.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CommentService {
    CommentRepository commentRepository = new CommentRepository();

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public void update(Comment comment) {
        commentRepository.update(comment);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

}
