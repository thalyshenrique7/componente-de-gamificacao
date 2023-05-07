package test;

import models.Armazenamento;
import models.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TesteArmazenamento {
    private PrintWriter printWriter;
    private Armazenamento armazenamento;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader bufferedReader;


    @Before
    public void inicializacao() throws IOException {
        armazenamento = new Armazenamento();
        Usuario usuario = new Usuario("guerra");
        Usuario usuario2 = new Usuario("clovis");
        armazenamento.registraUsuario(usuario);
        armazenamento.registraUsuario(usuario2);
    }

    @Test
    public void testeAdicionarPontoMoeda() throws IOException{
        armazenamento.registraPonto("guerra", 10, "moeda");
        assertEquals(armazenamento.quantidadePorTipo("guerra", "moeda"), 10);
    }

    @Test
    public void testeAdicionarPontosMoeda2() throws IOException{
        armazenamento.registraPonto("guerra", 10, "moeda");
        armazenamento.registraPonto("guerra", 30, "moeda");
        assertEquals(armazenamento.quantidadePorTipo("guerra", "moeda"), 40);
    }

    @Test
    public void testeAdicionarPontoEstrela() throws IOException{
        armazenamento.registraPonto("guerra", 15, "estrela");
        armazenamento.registraPonto("guerra", 10, "moeda");
        armazenamento.registraPonto("guerra", 40, "estrela");
        assertEquals(armazenamento.quantidadePorTipo("guerra", "estrela"), 55);
        assertEquals(armazenamento.quantidadePorTipo("guerra", "moeda"), 10);
    }

    @Test
    public void testeAdicionarPontosDoisUsuarios() throws IOException{
        armazenamento.registraPonto("guerra", 10, "moeda");
        armazenamento.registraPonto("clovis", 30, "estrela");
        assertEquals(armazenamento.quantidadePorTipo("guerra", "moeda"), 10);
        assertEquals(armazenamento.quantidadePorTipo("clovis", "estrela"), 30);
        armazenamento.registraPonto("guerra", 15, "moeda");
        armazenamento.registraPonto("clovis", 50, "moeda");
        assertEquals(armazenamento.quantidadePorTipo("guerra", "moeda"), 25);
        assertEquals(armazenamento.quantidadePorTipo("clovis", "moeda"), 50);
    }

    @Test
    public void testeUsuariosQueJaReceberamPontos(){
        armazenamento.registraPonto("guerra", 10, "moeda");
        assertEquals(armazenamento.usuariosQueReceberamPontos(), "guerra;");
        armazenamento.registraPonto("clovis", 15, "estrela");
        assertEquals(armazenamento.usuariosQueReceberamPontos(), "guerra;clovis;");
    }

    @Test
    public void testePontosRegistrados(){
        armazenamento.registraPonto("guerra", 10, "moeda");
        assertEquals(armazenamento.pontosRegistrados("guerra"), "moeda;");
        armazenamento.registraPonto("guerra", 20, "moeda");
        assertEquals(armazenamento.pontosRegistrados("guerra"), "moeda;");
        armazenamento.registraPonto("guerra", 20, "estrela");
        assertEquals(armazenamento.pontosRegistrados("guerra"), "moeda;estrela;");

    }

    @Test
    public void testeGerarArquivo() throws IOException{
        armazenamento.registraPonto("guerra", 10, "moeda");
        armazenamento.registraPonto("guerra", 20, "moeda");
        armazenamento.registraPonto("guerra", 20, "estrela");
        armazenamento.registraPonto("clovis", 50, "estrela");

        armazenamento.geraArquivo("arquivo.txt");
    }
}
