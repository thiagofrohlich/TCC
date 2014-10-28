package br.ufpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufpr.domain.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {

}
