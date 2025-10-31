import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;


    public void adicionarItem(Item itemAdd){}
    public void removerItem(Item itemR){}
    public void listarItens(){}

    public Inventario(){
        this.itens = new ArrayList<>();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public Item getItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    public void removerItem(int indice) {
        System.out.println("Removendo item no índice " + indice);
    }

}
