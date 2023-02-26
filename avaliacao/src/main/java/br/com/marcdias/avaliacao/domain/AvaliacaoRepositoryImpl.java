package br.com.marcdias.avaliacao.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {
    private static final List<Avaliacao> AVALIACOES = new ArrayList<>();
    private static long id = 1;

    //Inicializador dos statics
    static {
        AVALIACOES.add(new Avaliacao(nextId(), 10,
                "Ótimo filme de guerra, com um diferencial entre outros do gênero de não ter a intenção de florear a história ou encaixar finais felizes, tudo é retratado da forma mais crua e realista possíve.",  "Pedro Junio",1L));
        AVALIACOES.add(new Avaliacao(nextId(), 10,
                "Acabo de assistir o filme, uma maravilha.","Luca Guerra", 1L));
        AVALIACOES.add(new Avaliacao(nextId(), 10,
                "Filme muito bem construido, com cenas realistas que mostram um lado pouco explorado de uma guerra pouco explorada em filmes.", "Maria",1L));
        AVALIACOES.add(new Avaliacao(nextId(), 10,
                "Por um tempo fiquei temoroso em assistir Top Gun: Maverick, após tantas frustrações com franquias sendo revividas depois de muitos anos. E ao terminar de ver o filme, notei meu sorriso e satisfação ao sair do cinema.","Alexandre", 2L));
        AVALIACOES.add(new Avaliacao(nextId(), 5,
                "Não gostei, prefiro a primeira historia.", "Alex", 2L));
        AVALIACOES.add(new Avaliacao(nextId(), 8,
                "Um clássico excepcional, que retorna após 35 anos, só pode trazer uma sequência incrível também, eu me transporto para aquele cenário de aviões no ar, é muito legal a sensação de ação e aventura, que o filme proporciona, já que se trata de aviação de caça", "Alex",2L));
        AVALIACOES.add(new Avaliacao(nextId(), 5,
                "Um excelente drama psicológico que é ao mesmo tempo perturbador e envolvente, quem assiste pode gostar ou não do filme mas é difícil ficar indiferente.","Marcelo", 3L));
        AVALIACOES.add(new Avaliacao(nextId(), 5,
                "Esse foi um dos melhores filmes que eu já vi, com certeza! Ele foi capaz de me prender, de me fazer criar uma empatia e de me indentificar com o personagem de uma forma tremenda, o drama é muito envolvente e a atuação de Joaquin Phoenix é fantástica","Arthur", 3L));
        AVALIACOES.add(new Avaliacao(nextId(), 5,
                "Não à toa, Joaquin Phoenix venceu o Oscar de melhor ator! Que atuação soberba! Que carisma! Que domínio de cena! E de quebra, a presença luxuosa de Robert de Niro","Fernada", 3L));
    }

    @Override
    public void save(Avaliacao avaliacao) {
        avaliacao.setId(nextId());
        AVALIACOES.add(avaliacao);
    }

    @Override
    public Optional<Avaliacao> getOne(Long id) {
        return AVALIACOES.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Avaliacao> getAll() {
        return new ArrayList<>(AVALIACOES);
    }

    private static long nextId() {
        return id++;
    }
}
