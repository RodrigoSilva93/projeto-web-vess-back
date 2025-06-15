package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.Usuario;
import br.edu.utfpr.pb.project.server.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements ICrudService<Usuario, Long> {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findAll(Sort sort) {
        return usuarioRepository.findAll(sort);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario save(Usuario usuario) { return usuarioRepository.save(usuario); }

    @Override
    public Usuario saveAndFlush(Usuario entity) {
        return usuarioRepository.saveAndFlush(entity);
    }

    @Override
    public Iterable<Usuario> save(Iterable<Usuario> iterable) {
        return usuarioRepository.saveAll(iterable);
    }

    @Override
    public void flush() {
        usuarioRepository.flush();
    }

    @Override
    public Usuario findOne(Long aLong) {
        return usuarioRepository.findById(aLong).orElse(null);
    }

    @Override
    public boolean exists(Long aLong) {
        return usuarioRepository.findById(aLong).isPresent();
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

    @Override
    public void delete(Long aLong) {
        usuarioRepository.deleteById(aLong);
    }

    @Override
    public void delete(Iterable<? extends Usuario> iterable) {
        usuarioRepository.deleteAll(iterable);
    }

    @Override
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

}