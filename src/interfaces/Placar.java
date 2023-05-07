package interfaces;

import java.util.List;

public interface Placar {
    void registraPonto(String user, int ponto, String tipo);
    String[] usuarioPontoTipo();
    List<String> pontosRanking(String tipo);
}
