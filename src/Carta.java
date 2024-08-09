public class Carta {
    public static final String[] Valores = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};  //Valores imutaveis da classe
    public static final String[] Naipes = {"C","D","H","S"};                                        //Naipes imutaveis da classe

    private String valor;
    private String naipe;

    Carta(String valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

    public String toString() {
        return this.valor + "-" + this.naipe;
    }

    public boolean equals(Object o) {
        if (o instanceof Carta) {
            Carta c = (Carta) o;
            return this.valor.equals(c.valor);
        }
        return false;
    }

    public String getCaminhoImagem() {
        return "./cards/" + toString() + ".png";
    }
}
