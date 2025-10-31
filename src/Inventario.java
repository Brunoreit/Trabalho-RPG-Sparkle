import java.util.ArrayList;
import java.util.Collections;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }


    public void adicionarItem(Item itemAdd) {
        if (itemAdd != null) {
            itens.add(itemAdd);
            System.out.println(itemAdd.getNome() + " adicionado ao inventário!");
        } else {
            System.out.println("Não é possível adicionar um item nulo!");
        }
    }

    public void removerItem(Item item) {
        if(item == null || itens.isEmpty()) {
            System.out.println("Item invalido! ou Inventario vazio! ");
        }
        for (int i = 0; i < itens.size(); i++) {
            if(itens.get(i).getNome().equals(item.getNome())) {
                Item removido = itens.remove(i);
                System.out.println(removido.getNome() + " removido com sucesso!");
                return;
            }
        }
        System.out.println("Item nao encontrado no inventario!");
    }

    public void ordenarNome() {
        Collections.sort(itens);
    }

    public void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        ArrayList<String> mostrados = new ArrayList<>();
        int indice = 1;
        for (Item item : itens) {
            if (mostrados.contains(item.getNome())){
                continue;
            }
            int quantidade = 0;
            for (Item i :itens){
                if (i.getNome().equals(item.getNome())){
                    quantidade++;
                }
            }
            System.out.println("[ " + indice + " ] " + item.getNome() + " x " + quantidade +
                    " - " + item.getDescricao() +
                    " (Efeito: " + item.getEfeito() + " +" + item.getValor() + ")");
            indice++;
        }
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }


    public int getQuantidadeItem(Item item) {
        if (item == null || itens.isEmpty()) {
            return 0;
        }
        int quantidade = 0;
        for (Item i:itens) {
            if (i.getNome().equals(item.getNome())) {
                quantidade++;
            }
        }
        return quantidade;
    }


    public Item getItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    public void limparInventario() {
        itens.clear();
        System.out.println("Inventário limpo!");
    }

    public void transferirInventario(Inventario outro) {
        for (Item item : outro.itens) {
            this.adicionarItem(item);
        }
        outro.limparInventario();
    }

    public ArrayList<Item> getItens() {
        return new ArrayList<>(itens);
    }
    @Override
    public Inventario clone() {
        try {
            return (Inventario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar inventário", e);
        }
    }
    @Override
    public String toString() {
        String texto = "Inventario:\n";
        if (itens.isEmpty()) {
            return "Inventário vazio";
        }
        for (int i =0; i < itens.size(); i++) {
            texto += (i+1) + " - " + itens.get(i).getNome() + "\n";
        }
        return texto;
    }
}