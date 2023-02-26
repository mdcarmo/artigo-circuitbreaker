package br.com.marcdias.filme.avaliacaoClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class AvaliacaoClientImpl implements  AvaliacaoClient{

    private final Logger logger = LoggerFactory.getLogger(AvaliacaoClientImpl.class);
    private final RestTemplate restTemplate;
    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8081/avaliacoes")
            .queryParam("filmeId", "{filmeId}")
            .encode()
            .toUriString();

    private final Map<Long, List<AvaliacaoModel>> CACHE_AVALIACAO = new HashMap<>();
    public AvaliacaoClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name = "avaliacao-circuitBreaker", fallbackMethod = "getByFilmeIdInCache")
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
        logger.info("Colocando em cache");
        CACHE_AVALIACAO.put(filmeId, Arrays.asList(avaliacoes));

        return Arrays.asList(avaliacoes);
    }
    private List<AvaliacaoModel> getByFilmeIdInCache(Long filmeId, Throwable e){
        logger.info("Buscando no cache a informação das avaliações de filme");
        return CACHE_AVALIACAO.getOrDefault(filmeId, new ArrayList<>());
    }
}
