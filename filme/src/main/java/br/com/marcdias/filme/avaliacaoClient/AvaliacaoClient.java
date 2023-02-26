package br.com.marcdias.filme.avaliacaoClient;
import java.util.List;

public interface AvaliacaoClient {
    List<AvaliacaoModel> getByFilmeId(Long filmeId);
}
