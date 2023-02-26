package br.com.marcdias.filme.avaliacaoClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AvaliacaoClientImpl implements  AvaliacaoClient{

    private final Logger logger = LoggerFactory.getLogger(AvaliacaoClientImpl.class);
    private final RestTemplate restTemplate;

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8081/avaliacoes")
            .queryParam("filmeId", "{filmeId}")
            .encode()
            .toUriString();

    public AvaliacaoClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AvaliacaoModel> getByFilmeId(Long filmeId) {
        final List<AvaliacaoModel> avaliacoes = executarRequisicao(filmeId);
        return avaliacoes;
    }

    private List<AvaliacaoModel> executarRequisicao(Long filmeId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("filmeId", filmeId);

        logger.info("Buscando avaliações");
        final AvaliacaoModel[] avaliacoes;

        try {
            avaliacoes = restTemplate.getForObject(API_URL, AvaliacaoModel[].class, parametros);
        } catch (Exception e) {
            logger.error("Erro ao buscar avaliações");
            throw e;
        }
        return Arrays.asList(avaliacoes);
    }
}
