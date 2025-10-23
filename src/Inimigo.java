public class Inimigo extends Personagem{

    public Inimigo(String nome) throws Exception{ // inimigo possui construtor diferente porque cada inimigo vai ter um nome único
        super(nome,1,1,1,1, new Inventario());
    }
}
