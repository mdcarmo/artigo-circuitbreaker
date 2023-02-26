package br.com.marcdias.filme.domain;
import java.util.List;
import java.util.Optional;

public interface FilmeRepository {
    void save(Filme filme);
    Optional<Filme> getOne(Long id);
    List<Filme> getAll();
}