package br.com.marcdias.filme.api;

import br.com.marcdias.filme.avaliacaoClient.AvaliacaoModel;
import br.com.marcdias.filme.domain.Filme;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

public class FilmeModel {

    public Long id;
    public String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<AvaliacaoModel> avaliacoes;

    public FilmeModel() {

    }

    public FilmeModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public FilmeModel(Long id, String nome, List<AvaliacaoModel> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.avaliacoes = avaliacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<AvaliacaoModel> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoModel> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public static FilmeModel of(Filme filme) {
        return new FilmeModel(
                filme.getId(),
                filme.getNome()
        );
    }

    public static FilmeModel of(Filme filme, List<AvaliacaoModel> avaliacoes) {
        return new FilmeModel(
                filme.getId(),
                filme.getNome(),
                avaliacoes
        );
    }
}
