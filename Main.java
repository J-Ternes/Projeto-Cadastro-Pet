package DesafioCadastroPet;

public class Main {
    public static void main(String[] args) {
        Formulario formulario = new Formulario();
        Menu menu = new Menu();
        menu.MostrarMenu();
        Integer valor = menu.ValidarEscolha();
        menu.LerEscolha(valor);
    }
    }

