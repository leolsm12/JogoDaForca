package com.forca;


import com.forca.model.Partida;
import com.forca.service.JogoService;
import com.forca.view.ForcaView;

public class Main {
    public static void main(String[] args) {

        JogoService jogoService = new JogoService();
        ForcaView view = new ForcaView();

        view.mostrarBoasVindas();

        boolean jogarNovamente;
        do {
            Partida partida = jogoService.iniciarNovaPartida();

            while (partida.getEstado().name().equals("JOGANDO")) {
                view.mostrarPalavra(partida);
                view.mostrarLetrasChutadas(partida);
                view.mostrarErros(partida);

                char chute = view.solicitarChute();
                jogoService.processarChute(chute);
            }

            view.mostrarResultado(partida);

            jogarNovamente = view.perguntarNovaPartida();
        } while (jogarNovamente);

        System.out.println("Obrigado por jogar! Até a próxima. 👋");
    }



}