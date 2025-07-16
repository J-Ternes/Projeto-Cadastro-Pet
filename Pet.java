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
    List<String> respostaEndereco = new ArrayList<String>();

    public void CadastrarNovoPet() {
        System.out.println("\nPara cadastrar um novo Pet, favor respoder as perguntas : ");
        List<String> perguntasFormulario = formulario.MostrarFormulario();
        try {
            for (String linhas : perguntasFormulario) {

                if(linhas.equals(perguntasFormulario.get(3))){
                    System.out.println(linhas);
                    System.out.print("    i) Qual o número da casa que o cachorro mora: ");
                    String numero = scanner.nextLine();
                    respostaEndereco.add(numero);

                    System.out.print("    ii) Qual a cidade que o cachorro mora: ");
                    String cidade = scanner.nextLine();
                    respostaEndereco.add(cidade);

                    System.out.print("    iii) Qual a rua que o cachorro mora: ");
                    String rua = scanner.nextLine();
                    respostaEndereco.add(rua);

                    respostasUsuario.add(String.valueOf(("Rua " + respostaEndereco.get(2)) + ", " +
                            respostaEndereco.get(0) + ", " + respostaEndereco.get(1)));
                }else{
                    System.out.println(linhas);
                    System.out.print("Responda a pergunta a cima: ");
                    String resposta = scanner.nextLine();
                    respostasUsuario.add(resposta);
                }
            }

            ArmazenarDados(respostasUsuario);
        } catch (NumberFormatException ex) {
            ex.getStackTrace();
        }
    }

    public void ArmazenarDados(List<String> dadoInformado) {
        String nomeEsobrenome = respostasUsuario.get(0);
        adicionarNomeESobrenome(nomeEsobrenome);

        String tipo = respostasUsuario.get(1); //armazeno o tipo do animal informado pelo usuario
        adicionarTipo(tipo); // verificar se satisfaz algum valor do enum.

        String sexo = respostasUsuario.get(2);
        adicionarSexo(sexo);

        Respostas(respostasUsuario);
    }

    public void adicionarTipo(String tipo) {
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
    public void adicionarSexo(String sexo) {
        while (true) {
            try {
                if (sexo.equals("Macho") || sexo.equals("M")) {
                    Sexo sexoCachorro = Sexo.MACHO;
                    String sexoCachorroString = sexoCachorro.name();
                    respostasUsuario.remove(2);
                    respostasUsuario.add(2, sexoCachorroString);
                    break;
                }
                if (sexo.equals("Femea") || sexo.equals("F")) {
                    Sexo sexoCachorro = Sexo.FEMEA;
                    String sexoCachorroString = sexoCachorro.name();
                    respostasUsuario.remove(2);
                    respostasUsuario.add(2, sexoCachorroString);
                    break;
                }
            } catch (NullPointerException e) {
                System.err.println("Favor, preencher com um valor não nulo (Macho/Femea): ");
                List<String> terceiraPergunta = formulario.MostrarFormulario();
                System.out.println(terceiraPergunta.get(2));
                novaResposta = scanner.nextLine();
                respostasUsuario.remove(2);
                respostasUsuario.add(2, novaResposta);
                sexo = novaResposta;
            }
            System.err.println("Favor, digitar o sexo do animal corretamente (Macho/Femea): ");
            List<String> terceiraPergunta = formulario.MostrarFormulario();
            System.out.println(terceiraPergunta.get(2));
            novaResposta = scanner.nextLine();
            respostasUsuario.remove(2);
            respostasUsuario.add(2, novaResposta);
            sexo = novaResposta;
        }
    }
    public void adicionarNomeESobrenome(String nomeESobrenome){
        while (true) {
            String[] verificarNomeESobrenome = nomeESobrenome.split(" ");
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$"); //Irá verificar se existe apenas letras maiusculas ou minusculas
            Matcher matcher = pattern.matcher(nomeESobrenome);
            boolean verificador = matcher.find();
            if ((verificarNomeESobrenome.length > 1) && (verificador == true)) {
                break;
            } else
                System.err.println("Favor, responder nome e sobrenome do Pet com um espaço entre eles e sem caracter especial!");
            List<String> primeiraPergunta = formulario.MostrarFormulario();
            System.out.println(primeiraPergunta.get(0));
            String novaResposta = scanner.nextLine();
            respostasUsuario.remove(0);
            respostasUsuario.add(0, novaResposta);
        }
    }
    public void Respostas(List<String> resposta) {
        for (String respostas : respostasUsuario) {
            System.out.println(respostas);
        }
    }
}


