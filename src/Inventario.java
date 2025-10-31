import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }


    public void adicionarItem(Item itemAdd) {
        if (itemAdd != null) {
            itens.add(itemAdd);
            System.out.println("✅ " + itemAdd.getNome() + " adicionado ao inventário!");
        } else {
            System.out.println("❌ Não é possível adicionar um item nulo!");
        }
    }


    public void removerItem(Item itemR) {
        if (itemR != null && itens.contains(itemR)) {
            itens.remove(itemR);
            System.out.println(itemR.getNome() + " removido do inventário!");
        } else {
            System.out.println("Item não encontrado no inventário!");
        }
    }

    /**
     * Remove um item do inventário por índice
     * @param indice Índice do item a ser removido
     */
    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            Item itemRemovido = itens.remove(indice);
            System.out.println("🗑️ " + itemRemovido.getNome() + " removido do inventário!");
        } else {
            System.out.println("❌ Índice inválido!");
        }
    }

    /**
     * Lista todos os itens do inventário (nome simples)
     */
    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        System.out.println("\nITENS NO INVENTÁRIO:");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println( " [ " + (i+1) + " ] " + item.getNome());
        }
        System.out.println();
    }

    /**
     * Mostra os itens do inventário com numeração (usado no combate)
     */
    public void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println("[ " + (i + 1) + " ] " + item.getNome() +
                    " - " + item.getDescricao() +
                    " (Efeito: " + item.getEfeito() + " +" + item.getValor() + ")");
        }
    }

    /**
     * Mostra detalhes completos de todos os itens
     */
    public void mostrarItensDetalhados() {
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }

        System.out.println("\n===== INVENTÁRIO DETALHADO =====");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println("\n[ " + (i + 1) + " ] " + item.getNome());
            System.out.println("    Descrição: " + item.getDescricao());
            System.out.println("    Efeito: " + item.getEfeito() + " (Valor: " + item.getValor() + ")");
        }
        System.out.println("===================================\n");
    }


    public boolean estaVazio() {
        return itens.isEmpty();
    }


    public int quantidadeItens() {
        return itens.size();
    }


    public Item getItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    public Item buscarItem(String nome) {
        if (nome == null || nome.isBlank()) {
            return null;
        }

        for (Item item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public boolean contemItem(String nome) {
        return buscarItem(nome) != null;
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