package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.Usuario;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ICrudService<Usuario, Long> {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Usuario> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Usuario save(Usuario usuario) { return userRepository.save(usuario); }

    @Override
    public Usuario saveAndFlush(Usuario entity) {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public Iterable<Usuario> save(Iterable<Usuario> iterable) {
        return userRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        userRepository.flush();
    }

    @Override
    public Usuario findOne(Long aLong) {
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
    public void delete(Iterable<? extends Usuario> iterable) {
        userRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

}