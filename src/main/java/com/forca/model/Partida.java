package com.forca.model;


import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class Partida {

    private final Palavra palavra;
    private final Set<Character> letrasChutadas = new HashSet<>();
    @Getter
    private int erros;
    private final int limiteErros;
    @Getter
    private EstadoJogo estado;

    public Partida(Palavra palavra, int limiteErros) {
        this.palavra = palavra;
        this.limiteErros = limiteErros;
        this.estado = EstadoJogo.JOGANDO;
    }

    public Set<Character> getLetrasChutadas() {
        return Set.copyOf(letrasChutadas);
    }

    public String getMascaraAtual() {
        return palavra.revelar(letrasChutadas);
    }

    /**
     * Processa um chute de letra.
     * @param letra a letra chutada pelo jogador
     */
    public void chutarLetra(char letra) {
        letra = Character.toLowerCase(letra);

        // Ignora se já chutou
        if (letrasChutadas.contains(letra) || estado != EstadoJogo.JOGANDO) {
            return;
        }

        letrasChutadas.add(letra);

        if (!palavra.contemLetra(letra)) {
            erros++;
            if (erros >= limiteErros) {
                estado = EstadoJogo.PERDEU;
            }
        } else if (todasLetrasReveladas()) {
            estado = EstadoJogo.GANHOU;
        }
    }

    private boolean todasLetrasReveladas() {
        return letrasChutadas.stream()
                .filter(Character::isLetter)
                .map(Character::toUpperCase)
                .collect(HashSet::new, HashSet::add, HashSet::addAll)
                .containsAll(palavra.getLetrasUnicas());
    }
}
