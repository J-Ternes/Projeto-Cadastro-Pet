package DesafioCadastroPet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
   public static final String  NAO_INFORMADO = "NÃO INFORMADO";
   String numero;
    StringBuilder conteudo = new StringBuilder();

    public void CadastrarNovoPet() {
        System.out.println("\nPara cadastrar um novo Pet, favor respoder as perguntas : ");
        List<String> perguntasFormulario = formulario.MostrarFormulario();
        try {
            for (String linhas : perguntasFormulario) {
                if(linhas.equals(perguntasFormulario.get(3))){
                    System.out.println(linhas);
                    System.out.print("    i) Qual o número da casa que o cachorro mora: ");
                    numero = scanner.nextLine();
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
        } catch (NumberFormatException | IOException ex) {
            ex.getStackTrace();
        }
    }

    public void ArmazenarDados(List<String> dadoInformado) throws IOException {
        String nomeEsobrenome = respostasUsuario.get(0);
        adicionarNomeESobrenome(nomeEsobrenome);

        String tipo = respostasUsuario.get(1); //armazeno o tipo do animal informado pelo usuario
        adicionarTipo(tipo); // verificar se satisfaz algum valor do enum.

        String sexo = respostasUsuario.get(2);
        adicionarSexo(sexo);

        Integer idade = Integer.parseInt(respostasUsuario.get(4));
        adicionarIdade(idade);

        Integer peso = Integer.parseInt(respostasUsuario.get(5));
        adicionarPeso(peso);

        String raca = respostasUsuario.get(6);
        adicionarRaca(raca);


        cadastrarPet(respostasUsuario);
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
            } else{
                System.err.println("Favor, responder nome e sobrenome do Pet com um espaço entre eles e sem caracter especial!");
                List<String> primeiraPergunta = formulario.MostrarFormulario();
                System.out.println(primeiraPergunta.get(0));
                String novaResposta = scanner.nextLine();
                respostasUsuario.remove(0);
                respostasUsuario.add(0, novaResposta);
                nomeESobrenome = novaResposta;
            }
        }
    }

    public void adicionarPeso(Integer peso){
        try{
            while(true){
                if(peso > 60 || peso < 0.5){
                    System.err.println("Favor, digitar um peso entre 0.5 kg e 60 kg: ");
                    List<String> sextaPergunta = formulario.MostrarFormulario();
                    System.out.println(sextaPergunta.get(5));
                    Integer novaResposta =  Integer.parseInt(scanner.nextLine());
                    respostasUsuario.remove(5);
                    respostasUsuario.add(5, novaResposta.toString());
                }else
                    break;
            }
        }catch(InputMismatchException e){
            System.err.println("Favor, digitar um número referente ao peso do animal: ");
            List<String> sextaPergunta = formulario.MostrarFormulario();
            System.out.println(sextaPergunta.get(5));
            Integer novaResposta =  Integer.parseInt(scanner.nextLine());
            respostasUsuario.remove(5);
            respostasUsuario.add(5, novaResposta.toString());
            peso = novaResposta;
        }
    }

    public void adicionarIdade(Integer idade){
        try{
            while(true){
                if(idade > 20){
                    System.err.println("Favor, digitar uma idade até 20 anos: ");
                    String quintaPergunta = respostasUsuario.get(4);
                    System.out.println(quintaPergunta);
                    Integer novaResposta = Integer.parseInt(scanner.nextLine());
                    respostasUsuario.remove(4);
                    respostasUsuario.add(4,novaResposta.toString());
                    idade = novaResposta;
                }else
                    break;

            }
        }catch(InputMismatchException e){
            System.err.println("Favor, digitar um número inteiro para a idade: ");
            String quintaPergunta = respostasUsuario.get(4);
            System.out.println(quintaPergunta);
            Integer novaResposta = Integer.parseInt(scanner.nextLine());
            respostasUsuario.remove(4);
            respostasUsuario.add(4,novaResposta.toString());
            idade = novaResposta;
        }
    }

    public void adicionarRaca(String raca){
        while(true) {
            Pattern pattern = Pattern.compile("^[a-zA-Z ]+$"); //Irá verificar se existe apenas letras maiusculas ou minusculas
            Matcher matcher = pattern.matcher(raca);
            boolean verificador = matcher.find();
            if (verificador == true) {
                break;
            }else {
                System.err.println("Não é permitido usar números ou caracter especial ao informar a raça do animal");
                System.out.println(respostasUsuario.get(6));
                String novaResposta = scanner.nextLine();
                respostasUsuario.remove(6);
                respostasUsuario.add(6,novaResposta);
                raca = novaResposta;
        }
        }
    }

    public void cadastrarPet(List<String> resposta) throws IOException {
        Path arquivo = Paths.get("C:\\Desafio - cadastroPet");

        LocalDate diaAtual = LocalDate.now();
        DateTimeFormatter diaAtualFormatado = DateTimeFormatter.ofPattern("yyyMMdd");

        LocalTime horaAtual = LocalTime.now();

        Integer hora = horaAtual.getHour();
        Integer minuto = horaAtual.getMinute();

        String nome = respostasUsuario.get(0).replaceAll("\\s+","");
        String nomeMaiusculo = nome.toUpperCase();

        File novoArquivo = new File(arquivo.toFile(), diaAtualFormatado.format(diaAtual)+ "T" + hora + minuto + "-" + nomeMaiusculo + ".txt");
        novoArquivo.createNewFile(); //Criei o arquivo

        for(String linha : respostasUsuario){
            int indice = respostasUsuario.indexOf(linha);
            int indice2 = indice + 1;
            conteudo.append((indice2 + " - ") + (linha + "\n"));
            Files.write(novoArquivo.toPath(),conteudo.toString().getBytes(StandardCharsets.UTF_8),StandardOpenOption.CREATE);
        }


    }
}



