import java.awt.*;
import java.awt.event.*;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;

public class Kemps {
    Jogador jogadorUm, jogadorDois, jogadorTres, jogadorQuatro;
    
    //Baralho
    ArrayList<Carta> baralho;
    Random random = new Random();
    //Mesa
    ArrayList<Carta> mesa;

    //Janela do jogo
    int janelaLargura = 1600;
    int janelaAltura = 900;
    int cartaLargura = 110; //Razão é 1/1.4
    int cartaAltura = 154;

    JFrame janelaJogo = new JFrame("Kemps");
    JPanel painelJogo = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Mudar para botao JodagorUm e Mesa
            try {
                //Jogador
                //JogadorUm
                for (int i = 0; i < jogadorUm.getCartas().size(); i++) {
                    Carta carta = jogadorUm.getCartas().get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource(carta.getCaminhoImagem())).getImage();
                    g.drawImage(cartaImagem, 20 + (cartaLargura + 5)*i, janelaAltura - cartaAltura - 100, cartaLargura, cartaAltura, null);
                }
                //CPU
                //JogadorDois
                for (int i = 0; i < jogadorDois.getCartas().size(); i++) {
                    Carta carta = jogadorDois.getCartas().get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                    //Se Games for declarado fazer if
                    g.drawImage(cartaImagem, 20 + (cartaLargura + 5)*i, 20, cartaLargura, cartaAltura, null);
                }
                //JogadorTres
                for (int i = 0; i < jogadorUm.getCartas().size(); i++) {
                    Carta carta = jogadorUm.getCartas().get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                    //Se Games for declarado fazer if
                    g.drawImage(cartaImagem, janelaLargura - cartaLargura - 40 - (cartaLargura + 5)*i, 20, cartaLargura, cartaAltura, null);
                }
                //JogadorQuatro
                for (int i = 0; i < jogadorUm.getCartas().size(); i++) {
                    Carta carta = jogadorUm.getCartas().get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                    //Se Games for declarado fazer if
                    g.drawImage(cartaImagem, janelaLargura - cartaLargura - 40 - (cartaLargura + 5)*i, janelaAltura - cartaAltura - 100, cartaLargura, cartaAltura, null);
                }


                //Mesa
                for (int i = 0; i < mesa.size(); i++) {
                    Carta carta = mesa.get(i);
                    Image cartaImagem = new ImageIcon(getClass().getResource(carta.getCaminhoImagem())).getImage();
                    g.drawImage(cartaImagem, janelaLargura/4 + (cartaLargura + 5)*i, janelaAltura/2 - cartaAltura, cartaLargura, cartaAltura, null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    JPanel painelBotoes = new JPanel();
    JButton botaoRecomecar = new JButton("Recomeçar");

    Kemps() {
        iniciarJogo();

        //Iniciar Tela do Jogo
        janelaJogo.setVisible(true);
        janelaJogo.setSize(janelaLargura,janelaAltura);
        janelaJogo.setLocationRelativeTo(null);
        janelaJogo.setResizable(false);
        janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painelJogo.setLayout(new BorderLayout());
        painelJogo.setBackground(new Color(53, 101, 77));
        janelaJogo.add(painelJogo);

        botaoRecomecar.setFocusable(false);
        painelBotoes.add(botaoRecomecar);
        janelaJogo.add(painelBotoes, BorderLayout.SOUTH);

        //Event Listeners
        botaoRecomecar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("Recomeçando!");
                iniciarJogo();

                painelJogo.repaint();
            }
        });

        painelJogo.repaint();
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
            jogadorDois.comprarCarta(baralho.remove(baralho.size()-1));
            jogadorTres.comprarCarta(baralho.remove(baralho.size()-1));
            jogadorQuatro.comprarCarta(baralho.remove(baralho.size()-1));

            //Jogador compra por ultimo
            jogadorUm.comprarCarta(baralho.remove(baralho.size()-1));

            //Checagem para mão inicial inválida, ou seja, ter 3 cartas iguais, utilizando o fezGames()
            if (i >= 2) {
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
