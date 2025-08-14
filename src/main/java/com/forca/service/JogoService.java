package com.forca.service;


import com.forca.model.EstadoJogo;
import com.forca.model.Palavra;
import com.forca.model.Partida;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JogoService {

    private static final int LIMITE_ERROS = 6;
    private static final List<String> LISTA_PALAVRAS = Arrays.asList(
            "java", "spring", "programacao", "tecnologia", "computador",
            "desenvolvimento", "framework", "inteligencia", "variavel", "compilador"
    );

    private final Random random;
    private Partida partidaAtual;

    public JogoService() {
        this.random = new Random();
    }

    public Partida iniciarNovaPartida() {
        String palavraSorteada = LISTA_PALAVRAS.get(random.nextInt(LISTA_PALAVRAS.size()));
        this.partidaAtual = new Partida(Palavra.of(palavraSorteada), LIMITE_ERROS);
        return partidaAtual;
    }

    public boolean processarChute(char letra) {
        validarPartida();
        return partidaAtual.chutarLetra(letra);
    }

    public EstadoJogo verificarEstado() {
        validarPartida();
        return partidaAtual.getEstado();
    }

    public String palavraRevelada() {
        validarPartida();
        return partidaAtual.getMascaraAtual();
    }

    private void validarPartida() {
        if (partidaAtual == null) {
            throw new IllegalStateException("Nenhuma partida em andamento. Inicie uma nova.");
        }
    }
}
