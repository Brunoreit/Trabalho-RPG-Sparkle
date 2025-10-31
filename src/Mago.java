public class Mago extends Personagem {

    public Mago(String nome, int pontosVida, int ataque, int defesa, int nivel) throws Exception {
        super(nome, pontosVida, ataque, defesa, nivel, new Inventario());
    }
}