package br.edu.utfpr.pb.project.server.repository;

import br.edu.utfpr.pb.project.server.model.Amostra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmostraRepository extends JpaRepository<Amostra, Long> {
}
