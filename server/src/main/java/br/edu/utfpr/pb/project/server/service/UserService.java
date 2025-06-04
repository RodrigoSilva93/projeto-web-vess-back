package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ICrudService<User, Long> {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User save(User user) { return userRepository.save(user); }

    @Override
    public User saveAndFlush(User entity) {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public Iterable<User> save(Iterable<User> iterable) {
        return userRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        userRepository.flush();
    }

    @Override
    public User findOne(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }

    @Override
    public boolean exists(Long aLong) {
        return userRepository.findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void delete(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public void delete(Iterable<? extends User> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

}