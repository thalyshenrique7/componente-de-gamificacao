package test.mock;

import interfaces.Placar;
import models.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MockPlacar implements Placar {

    ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();

    @Override
    public void registraPonto(String nome, int ponto, String tipo) {
        Usuario usuario = verificaUsuario(nome);
        usuario.adicionaTipo(tipo, ponto);

    }

    @Override
    public String[] usuarioPontoTipo() {
        String tipos;
        String[] tiposSplit;
        String[] usuarioPontoTipo = null;
        int cont = 0;

        for(Usuario usuarios : listaDeUsuarios){
            tipos = usuarios.getTipos();
            tiposSplit = tipos.split(";");

            usuarioPontoTipo = new String[tiposSplit.length];
            for(String s : tiposSplit){
                usuarioPontoTipo[cont] = usuarios.getNome() + ";" + usuarios.getPontos(s) + ";" + s;
            }
            cont++;
        }
        return usuarioPontoTipo;
    }

    @Override
    public List<String> pontosRanking(String tipo) {
        List<String> ranking = new ArrayList<>();

        for(String s : ranking){
            System.out.println(s);
        }
        return ranking;
    }

    public Usuario verificaUsuario(String nome) {
        for(Usuario usuario : listaDeUsuarios){
            if(usuario.getNome() == nome)
                return usuario;
        }
        return null;
    }

    Usuario usuario1;
    Usuario usuario2;

    @Before
    public void inicializa(){
        listaDeUsuarios.add(new Usuario("guerra"));
        listaDeUsuarios.add(new Usuario("clovis"));
        this.registraPonto("guerra", 10, "estrela");
        this.registraPonto("guerra", 15, "estrela");
        this.registraPonto("clovis", 5, "estrela");
        this.registraPonto("clovis", 20, "moeda");
    }

    @Test
    public void testeRegistraPonto(){
        usuario1 = verificaUsuario("guerra");
        usuario2 = verificaUsuario("clovis");

        assertEquals(usuario1.getPontos("estrela"), 25);
        assertEquals(usuario2.getPontos("estrela"), 5);
        assertEquals(usuario2.getPontos("moeda"), 20);
    }

    @Test
    public void testeUsuarioPontoTipo(){
        pontosRanking("estrela");
    }
}
