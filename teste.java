package DesafioCadastroPet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class teste {
    public static void main(String[] args) {

        List<String> nomes = new ArrayList<String>();
        nomes.add("Jonathan");
        nomes.add("Nataly");
        nomes.add("Cecilia");
        for(String nome : nomes){
            StringBuilder sb = new StringBuilder();
            int indice = nomes.indexOf(nome);
            int indice2 = indice + 1;
            sb.append((indice2+" - ")+nome);
            System.out.println(sb);
        }
    }
}
