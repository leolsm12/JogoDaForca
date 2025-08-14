package com.forca.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Palavra {

    private final String texto;
    private final Set<Character> letrasUnicas;

    private Palavra(String texto) {
        this.texto = texto.toUpperCase();
        this.letrasUnicas = extrairLetrasUnicas(this.texto);
    }

    public static Palavra of(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("A palavra não pode ser nula ou vazia.");
        }
        return new Palavra(texto);
    }

    public boolean contemLetra(char letra) {
        return letrasUnicas.contains(Character.toUpperCase(letra));
    }

    public String revelar(Set<Character> letrasChutadas) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c) && letrasChutadas.contains(Character.toLowerCase(c))) {
                sb.append(c);
            } else if (Character.isLetter(c)) {
                sb.append("_");
            } else {
                sb.append(c); // mantém espaços, hífens, etc.
            }
        }
        return sb.toString();
    }

    private Set<Character> extrairLetrasUnicas(String texto) {
        Set<Character> letras = new HashSet<>();
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c)) {
                letras.add(Character.toUpperCase(c));
            }
        }
        return letras;
    }
}
