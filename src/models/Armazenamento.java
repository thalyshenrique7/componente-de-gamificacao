package models;

import java.io.*;
import java.util.ArrayList;

public class Armazenamento {
    ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();

    public void geraArquivo(String nomeArquivo) throws IOException {
        FileWriter fileWriter = new FileWriter(nomeArquivo);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("Pontuação:\r\n");
        printWriter.printf("\tUsuario \t   Tipo \t Pontos\r\n");
        for(Usuario u : listaDeUsuarios){
            pontoPorTipoUsuario(printWriter, u);
        }
        fileWriter.close();
    }

    public String lerArquivo(String nomeArquivo) throws IOException {
        FileReader fileReader = new FileReader(nomeArquivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String texto;
        texto = bufferedReader.readLine();
        bufferedReader.close();
        return texto;
    }

    public void registraUsuario(Usuario usuario){
        listaDeUsuarios.add(usuario);
    }

    public void registraPonto(String usuario, int ponto, String tipo) {
        Usuario usuarioExistente = verificaUsuario(usuario);
        usuarioExistente.adicionaTipo(tipo, ponto);
    }

    public int quantidadePorTipo(String usuario, String tipo) {
        Usuario usuarioExistente = verificaUsuario(usuario);
        return usuarioExistente.getPontos(tipo);
    }

    public Usuario verificaUsuario(String nome) {
        for(Usuario usuario : listaDeUsuarios){
            if(usuario.getNome() == nome)
                return usuario;
        }
        return null;
    }

    public String usuariosQueReceberamPontos() {
        String nomes = "";
        for(Usuario user : listaDeUsuarios){
            if(user.isTipos()){
                nomes = nomes + user.getNome() + ";";
            }
        }
        return nomes;
    }

    public void usuarioSplit(PrintWriter pw){
        String s = usuariosQueReceberamPontos();
        String[] nomes = s.split(";");
        for(String n : nomes){
            pw.println("- " + n);
        }
    }

    public String pontosRegistrados(String nome) {
        Usuario user = verificaUsuario(nome);
        return user.getTipos();

    }

    public void pontoPorTipoUsuario(PrintWriter printWriter, Usuario usuario){
        String s = usuario.getTipos();
        String[] nomes = s.split(";");
        for(String n : nomes){
            printWriter.printf("%15s %15s %15d\r\n", usuario.getNome(), n, usuario.getPontos(n));
        }
    }
}
