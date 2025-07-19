package DesafioCadastroPet;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlterarPet {
    List<String> buscaPet = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    List<String> nomeArquivos = new ArrayList<>();

    public Integer menuCriterio(){
        buscaPet.add("1 - Nome ou sobrenome");
        buscaPet.add("2 - Sexo");
        buscaPet.add("3 - Idade");
        buscaPet.add("4 - Peso");
        buscaPet.add("5 - Raça");
        buscaPet.add("6 - Endereço");
        for (String linha : buscaPet){
            System.out.println(linha);
        }
        System.out.println("Selecione o número do critério que você deseja realizar a sua busca:  ");
        Integer resposta = Integer.parseInt(scanner.nextLine());
        return resposta;
    }

    public void realizarBusca(Integer criterio){
        if(criterio == 1){
            System.out.println("Digite o nome do cachorro que você deseja realizar a busca: ");
            String nomeCachorro = scanner.nextLine();
            File diretorio = new File("C:\\Desafio - cadastroPet\\AnimaisCadastrados");
            for (String file : diretorio.list()) {
                System.out.println(file);
            }

        }
    }
}
