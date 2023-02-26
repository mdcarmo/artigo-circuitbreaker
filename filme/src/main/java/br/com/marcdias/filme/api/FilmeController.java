package br.com.marcdias.filme.api;

import br.com.marcdias.filme.avaliacaoClient.AvaliacaoClient;
import br.com.marcdias.filme.avaliacaoClient.AvaliacaoModel;
import br.com.marcdias.filme.domain.Filme;
import br.com.marcdias.filme.domain.FilmeRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final FilmeRepository filmes;
    private final AvaliacaoClient avaliacoes;

    public FilmeController(FilmeRepository filmes, AvaliacaoClient avaliacoes) {
        this.filmes = filmes;
        this.avaliacoes = avaliacoes;
    }

    @GetMapping
    public List<FilmeModel> buscarTodos() {
        return filmes.getAll()
                .stream()
                .map(this::converterFilmeParaFilmeModelo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{filmeId}")
    public FilmeModel buscarPorId(@PathVariable Long filmeId) {
        return filmes.getOne(filmeId)
                .map(this::converterFilmeParaFilmeModeloComAvaliacao)
                .orElseThrow(NaoEncontradoException::new);
    }
    private FilmeModel converterFilmeParaFilmeModelo(Filme filme) {
        return FilmeModel.of(filme);
    }
    private FilmeModel converterFilmeParaFilmeModeloComAvaliacao(Filme filme) {
        return FilmeModel.of(filme, buscarAvaliacaoDoFilme(filme.getId()));
    }
    private List<AvaliacaoModel> buscarAvaliacaoDoFilme(Long filmeId) {
        return avaliacoes.getByFilmeId(filmeId);
    }
}