package DesafioCadastroPet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    String novaResposta;
    List<String> respostasUsuario = new ArrayList<String>();
    Scanner scanner = new Scanner(System.in);
    Formulario formulario = new Formulario();

    public void CadastrarNovoPet() {
        System.out.println("\nPara cadastrar um novo Pet, favor respoder as perguntas : ");
        List<String> perguntasFormulario = formulario.MostrarFormulario();
        try {
            for (String linhas : perguntasFormulario) {
                System.out.println(linhas);
                System.out.print("Responda a pergunta a cima: ");
                String resposta = scanner.nextLine();
                respostasUsuario.add(resposta);
            }
            ArmazenarDados(respostasUsuario);
        } catch (NumberFormatException ex) {
            ex.getStackTrace();
        }
    }
    public void ArmazenarDados (List<String> dadoInformado){
        while(true){
            String nomeESobrenome = dadoInformado.get(0);
            String[] verificarNomeESobrenome = nomeESobrenome.split(" ");
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$"); //Irá verificar se existe apenas letras maiusculas ou minusculas
            Matcher matcher = pattern.matcher(nomeESobrenome);
            boolean verificador = matcher.find();
            if((verificarNomeESobrenome.length > 1) && (verificador == true)){
                break;
            }else
                System.err.println("Favor, responder nome e sobrenome do Pet com um espaço entre eles e sem caracter especial!");
            List<String> primeiraPergunta = formulario.MostrarFormulario();
            System.out.println(primeiraPergunta.get(0));
            String novaResposta = scanner.nextLine();
            dadoInformado.remove(0);
            dadoInformado.add(0,novaResposta);
        }

        String tipo = respostasUsuario.get(1); //armazeno o tipo do animal informado pelo usuario
        procurarTipo(tipo); // verificar se satisfaz algum valor do enum.
        Respostas(respostasUsuario);
        }
    public void procurarTipo (String tipo) {
        while (true) {
            try {
                if (tipo.equals("Cachorro")) {
                    Tipo tipoCachorro = Tipo.CACHORRO;
                    String tipoCachorroString = tipoCachorro.name();
                    respostasUsuario.remove(1);
                    respostasUsuario.add(1, tipoCachorroString);
                    break;
                }
                if (tipo.equals("Gato")) {
                    Tipo tipoGato = Tipo.GATO;
                    String tipoGatoString = tipoGato.name();
                    respostasUsuario.remove(1);
                    respostasUsuario.add(1, tipoGatoString);
                    break;
                }
            } catch (NullPointerException e) {
                System.err.println("Favor, preencher com um valor não nulo (Gato/Cachorro): ");
                List<String> segundaPergunta = formulario.MostrarFormulario();
                System.out.println(segundaPergunta.get(1));
                novaResposta = scanner.nextLine();
                respostasUsuario.remove(1);
                respostasUsuario.add(1, novaResposta);
                tipo = novaResposta;
            }
            System.err.println("Favor, digitar o tipo do animal corretamente (Cachorro/Gato): ");
            List<String> segundaPergunta = formulario.MostrarFormulario();
            System.out.println(segundaPergunta.get(1));
            novaResposta = scanner.nextLine();
            respostasUsuario.remove(1);
            respostasUsuario.add(1, novaResposta);
            tipo = novaResposta;
        }
    }
    public void Respostas(List<String> resposta){
        for(String respostas : respostasUsuario) {
            System.out.println(respostas);
        }

    }
    }


