public class Guerreiro extends Personagem {

    public Guerreiro(String nome,int pontosVida,int ataque,int defesa,int nivel) throws Exception{
        super(nome,pontosVida,ataque,defesa,nivel, new Inventario());
    }
}
