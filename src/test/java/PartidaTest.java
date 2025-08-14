import com.forca.model.EstadoJogo;
import com.forca.model.Palavra;
import com.forca.model.Partida;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PartidaTest {
    @Test
    @DisplayName("Deve ganhar ao acertar todas as letras")
    void deveGanhar() {
        Palavra palavra = Palavra.of("Java");
        Partida partida = new Partida(palavra, 6);

        partida.chutarLetra('j');
        partida.chutarLetra('a');
        partida.chutarLetra('v');

        assertEquals(EstadoJogo.GANHOU, partida.getEstado());
    }

    @Test
    @DisplayName("Deve perder ao ultrapassar limite de erros")
    void devePerder() {
        Palavra palavra = Palavra.of("Java");
        Partida partida = new Partida(palavra, 2);

        partida.chutarLetra('x');
        partida.chutarLetra('y');

        assertEquals(EstadoJogo.PERDEU, partida.getEstado());
    }

    @Test
    @DisplayName("Deve exibir máscara parcial")
    void mascaraParcial() {
        Palavra palavra = Palavra.of("Java");
        Partida partida = new Partida(palavra, 6);

        partida.chutarLetra('j');

        String mascara = partida.getMascaraAtual();
        assertTrue(mascara.startsWith("J"));
        assertTrue(mascara.contains("_"));
    }
}
