import java.awt.*;
import java.awt.event.*;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

public class Kemps {
    Jogador jogadorUm, jogadorDois, jogadorTres, jogadorQuatro;
    
    ArrayList<Carta> baralho;
    Random random = new Random();
    ArrayList<Carta> mesa;

    Kemps() {
        iniciarJogo();
    }

    public void iniciarJogo() {
        criarBaralho();
        embaralhar();

        //CPU
        jogadorDois = new Jogador("CPU1");
        jogadorTres = new Jogador("CPU2");
        jogadorQuatro = new Jogador("CPU3");

        //Jogador
        jogadorUm = new Jogador("Jogador");

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        jogadores.add(jogadorUm);
        jogadores.add(jogadorDois);
        jogadores.add(jogadorTres);
        jogadores.add(jogadorQuatro);

        //Mesa
        mesa = new ArrayList<Carta>();

        //Comprar 4 cartas iniciais
        for (int i = 0; i < 4; i++) {
            jogadorDois.comprarCarta(baralho);
            jogadorTres.comprarCarta(baralho);
            jogadorQuatro.comprarCarta(baralho);

            //Jogador compra por ultimo
            jogadorUm.comprarCarta(baralho);

            //Checagem para mão inicial inválida, ou seja, ter 3 cartas iguais, utilizando o fezGames()
            if (i >= 3) {
                for (int j = 0; j < jogadores.size(); j++) {
                    boolean todasCartasIguais = jogadores.get(i).fezGames();
                    if (todasCartasIguais) { //Reinicia o jogo, talvez tenha que repaint()
                        System.out.println("Jogo Reiniciado devido a um jogador ter 3 cartas iguais!");
                        iniciarJogo();
                    } 
                }
            }
        }
        //Mesa compra carta
        Carta c;
        for (int i = 0; i < 4; i++) {
            c = baralho.remove(baralho.size()-1);
            mesa.add(c);
        }

        System.out.println(jogadorDois + ": " + jogadorDois.getCartas());
        System.out.println(jogadorTres + ": " + jogadorTres.getCartas());
        System.out.println(jogadorQuatro + ": " + jogadorQuatro.getCartas());

        System.out.println(jogadorUm + ": " + jogadorUm.getCartas());

        System.out.println("Mesa: " + mesa);
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
