package DesafioCadastroPet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Integer escolha;

    public void MostrarMenu() {
        List<String> menu = new ArrayList<>();
        menu.add("1.Cadastrar um novo Pet");
        menu.add("2.Alterar os dados do Pet cadastrado");
        menu.add("3.Deletar um pet cadastrado");
        menu.add("4.Listar todos os pets cadastrados");
        menu.add("5.Listar pets por algum critério");
        menu.add("6.Sair");
        for (String linha : menu) {
            System.out.println(linha);
        }
    }
    public Integer ValidarEscolha() {
        while (true) {
            try{
                System.out.print("\nEscolha uma das opções acima: ");
                 escolha = Integer.parseInt(scanner.nextLine());
            if (!((escolha) > 0) || (escolha > 6)) {
                System.out.println("Opção Inválida. Favor, tente novamente\n");
                MostrarMenu();
            } else {
                break;
            }
        }catch (NumberFormatException e){
                System.err.println("Opção inválida. Favor, digitar um número");
                MostrarMenu();
            }
        }
        return escolha;
    }

    public void LerEscolha(Integer escolhaDoUsuario){
        switch (escolhaDoUsuario){
            case 1:
                Pet cadastroPet = new Pet();
                cadastroPet.CadastrarNovoPet();
                break;

            case 2:
                AlterarPet alterar = new AlterarPet();
                Integer criterioSelecionado = alterar.menuCriterio();
                alterar.realizarBusca(criterioSelecionado);
                break;

        }

    }

}






