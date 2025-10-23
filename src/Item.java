public class Item {
    private String nome;
    private String descricao;
    private String efeito; // cura,aumento de ataque etc
    private int quantidade;


    public Item(String nome, String descricao, String efeito, int quantidade){
        this.setNome(nome);
        this.setDescrição(descricao);
        this.setEfeito(efeito);
        this.setQuantidade(quantidade);
    }

    public void setNome(String nome){}
    public void setDescrição(String descricao){}
    public void setEfeito(String efeito){}
    public void setQuantidade(int quantidade){}

    @Override
    public boolean equals (Object obj){
        if (this == obj) return true;

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Item item = (Item) obj;

        return this.nome.equals(item.nome);

    }
}
