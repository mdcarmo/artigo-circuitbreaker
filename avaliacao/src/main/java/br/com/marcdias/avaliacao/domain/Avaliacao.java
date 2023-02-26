package br.com.marcdias.avaliacao.domain;

public class Avaliacao {
    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private Long filmeId;

    public Avaliacao() {

    }

    public Avaliacao(Long id, Integer nota, String descricao, String nomeAvaliador, Long filmeId) {
        this.id = id;
        this.nota = nota;
        this.descricao = descricao;
        this.nomeAvaliador = nomeAvaliador;
        this.filmeId = filmeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeAvaliador() {
        return nomeAvaliador;
    }

    public void setNomeAvaliador(String nomeAvaliador) {
        this.nomeAvaliador = nomeAvaliador;
    }

    public Long getProdutoId() {
        return filmeId;
    }

    public void setProdutoId(Long produtoId) {
        this.filmeId = produtoId;
    }
}
