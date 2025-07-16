package DesafioCadastroPet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Formulario {
    public List<String> MostrarFormulario(){
        List<String> perguntasFormulario = new ArrayList<String>();
        try{
            Path arquivo = Paths.get("C:\\Desafio - cadastroPet\\formulario.txt");
            List<String> conteudo = Files.readAllLines(arquivo);
            for(String linha : conteudo){
                perguntasFormulario.add(linha);
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return perguntasFormulario;
    }

}
