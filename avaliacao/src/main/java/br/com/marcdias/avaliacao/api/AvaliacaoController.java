package br.com.marcdias.avaliacao.api;

import br.com.marcdias.avaliacao.domain.AvaliacaoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoRepository avaliacoes;

    public AvaliacaoController(AvaliacaoRepository avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @GetMapping
    public List<AvaliacaoModel> buscarPorFilme(@RequestParam Long filmeId) {
        List<AvaliacaoModel> collect = avaliacoes.getAll()
                .stream()
                .filter(avaliacao -> avaliacao.getProdutoId().equals(filmeId))
                .map(AvaliacaoModel::of)
                .collect(Collectors.toList());
        return collect;
    }
}