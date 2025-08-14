package com.forca.view;

import com.forca.model.EstadoJogo;
import com.forca.model.Partida;

import java.util.Scanner;
import java.util.Set;

public class ForcaView {

    private final Scanner scanner;

    public ForcaView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Mostra a palavra com letras reveladas e ocultas.
     */
    public void mostrarPalavra(Partida partida) {
        System.out.println("\nPalavra: " + partida.getMascaraAtual());
    }

    /**
     * Mostra letras chutadas até agora.
     */
    public void mostrarLetrasChutadas(Partida partida) {
        Set<Character> letras = partida.getLetrasChutadas();
        System.out.println("Letras chutadas: " + letras);
    }

    /**
     * Mostra quantos erros o jogador cometeu e o limite.
     */
    public void mostrarErros(Partida partida) {
        System.out.println("Erros: " + partida.getErros() + " / " + partida.getLimiteErros());
    }

    /**
     * Solicita ao usuário um chute de letra.
     */
    public char solicitarChute() {
        System.out.print("Digite uma letra: ");
        String input = scanner.nextLine().trim().toLowerCase();
        while (input.isEmpty() || input.length() > 1 || !Character.isLetter(input.charAt(0))) {
            System.out.print("Entrada inválida. Digite apenas uma letra: ");
            input = scanner.nextLine().trim().toLowerCase();
        }
        return input.charAt(0);
    }

    /**
     * Mostra mensagem de vitória ou derrota.
     */
    public void mostrarResultado(Partida partida) {
        if (partida.getEstado() == EstadoJogo.GANHOU) {
            System.out.println("\n🎉 Parabéns! Você ganhou! Palavra: " + partida.getMascaraAtual());
        } else if (partida.getEstado() == EstadoJogo.PERDEU) {
            System.out.println("\n💀 Você perdeu! Palavra era: " + partida.getPalavra().getTexto());
        }
    }

    /**
     * Mensagem de boas-vindas.
     */
    public void mostrarBoasVindas() {
        System.out.println("=== Bem-vindo ao Jogo da Forca ===");
    }

    /**
     * Pergunta ao usuário se deseja jogar novamente.
     */
    public boolean perguntarNovaPartida() {
        System.out.print("Deseja jogar novamente? (S/N): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("s") || input.equals("sim");
    }
}
