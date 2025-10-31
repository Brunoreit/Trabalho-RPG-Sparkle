public class Inimigo extends Personagem{
    private int xpRecompensa;
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel, int xpRecompensa) throws Exception{
        super(nome, pontosVida, ataque, defesa, nivel, new Inventario());
        this.xpRecompensa = xpRecompensa;
    }
}
