import com.forca.model.EstadoJogo;
import com.forca.model.Partida;
import com.forca.service.JogoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoServiceTest {

    @Test
    @DisplayName("Deve iniciar nova partida com palavra aleatória")
    void iniciarNovaPartida() {
        JogoService service = new JogoService();
        Partida partida = service.iniciarNovaPartida();
        assertNotNull(partida);
        assertEquals(EstadoJogo.JOGANDO, partida.getEstado());
    }

    @Test
    @DisplayName("Deve processar chute e atualizar palavra revelada")
    void processarChute() {
        JogoService service = new JogoService();
        service.iniciarNovaPartida();
        String antes = service.palavraRevelada();
        service.processarChute('a');
        String depois = service.palavraRevelada();
        assertNotEquals(antes, depois);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar chutar sem iniciar partida")
    void chuteSemPartida() {
        JogoService service = new JogoService();
        assertThrows(IllegalStateException.class, () -> service.processarChute('a'));
    }
}
