package br.com.marcdias.filme.domain;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FilmeRepositoryImpl implements FilmeRepository{
    private static final List<Filme> FILMES = new ArrayList<>();
    private static long id = 1;

    //Inicializador dos statics
    static {
        FILMES.add(new Filme(nextId(), "Nada de Novo no Front"));
        FILMES.add(new Filme(nextId(), "Top Gun Maverick"));
        FILMES.add(new Filme(nextId(), "Coringa"));
    }

    @Override
    public void save(Filme filme) {
        filme.setId(nextId());
        FILMES.add(filme);
    }

    @Override
    public Optional<Filme> getOne(Long id) {
        return FILMES.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Filme> getAll() {
        return new ArrayList<>(FILMES);
    }

    private static long nextId() {
        return id++;
    }
}
