import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

public class Kemps {
    Jogador jogadorUm, jogadorDois, jogadorTres, jogadorQuatro;
    
    ArrayList<Carta> baralho;
    Random random = new Random();

    Kemps() {
        iniciarJogo();
    }

    public void iniciarJogo() {
        criarBaralho();
        embaralhar();
    }

    public void criarBaralho() {
        String[] valores = Carta.Valores;
        String[] naipes = Carta.Naipes;

        baralho = new ArrayList<Carta>();

        for (int i = 0; i < naipes.length; i++) {
            for (int j = 0; j < valores.length; j++) {
                Carta carta = new Carta(valores[j],naipes[i]);
                baralho.add(carta);
            }
        }
        System.out.println("Baralho Criado:");
        System.out.println(baralho);
    }

    public void embaralhar() {
        for (int i = 0; i < baralho.size(); i++) {
            int j = random.nextInt(baralho.size());
            Carta cartaAtual = baralho.get(i);
            Carta cartaRandom = baralho.get(j);
            baralho.set(i, cartaRandom);
            baralho.set(j, cartaAtual);
        }
        System.out.println("Baralho embaralhado:");
        System.out.println(baralho);
    }
}
