import java.util.ArrayList;

public class Jogador {
    private String nome;
    private ArrayList<Carta> cartas;

    Jogador(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<Carta>();
    }

    public String toString() {
        return "Jogador " + this.nome;
    }

    public boolean equals(Object o) {
        if (o instanceof Jogador) {
            Jogador j = (Jogador) o;
            return this.nome.equals(j.nome);
        }
        return false;
    }

    public boolean fezGames() {
        Carta c = this.cartas.get(0); //Primeira carta
        Carta x;
        int count = 0;
        for (int i = 0; i < this.cartas.size(); i++) { //Compara com a mão toda
            x = this.cartas.get(i);
            if (c.equals(x))
                count++;
        }
        if (count == this.cartas.size())
            return true;
        return false;
    }

    public void comprarCarta(ArrayList<Carta> baralho) {
        Carta c = baralho.remove(baralho.size()-1); //Remove da ultima posição
        this.cartas.add(c);
    }

    public void descartarCarta(Carta c) {
        this.cartas.remove(c);
    }

    public ArrayList<Carta> getCartas() {
        return this.cartas;
    }
}
