import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens;

    public Inventario() {
        this.itens = new ArrayList<>();
    }

    /**
     * Adiciona um item ao inventário
     * @param itemAdd Item a ser adicionado
     */
    public void adicionarItem(Item itemAdd) {
        if (itemAdd != null) {
            itens.add(itemAdd);
            System.out.println("✅ " + itemAdd.getNome() + " adicionado ao inventário!");
        } else {
            System.out.println("❌ Não é possível adicionar um item nulo!");
        }
    }

    /**
     * Remove um item do inventário por referência
     * @param itemR Item a ser removido
     */
    public void removerItem(Item itemR) {
        if (itemR != null && itens.contains(itemR)) {
            itens.remove(itemR);
            System.out.println("🗑️ " + itemR.getNome() + " removido do inventário!");
        } else {
            System.out.println("❌ Item não encontrado no inventário!");
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
            System.out.println("📦 Inventário vazio.");
            return;
        }

        System.out.println("\n📦 ITENS NO INVENTÁRIO:");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println("  • " + item.getNome());
        }
        System.out.println();
    }

    /**
     * Mostra os itens do inventário com numeração (usado no combate)
     */
    public void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("📦 Inventário vazio.");
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
            System.out.println("📦 Inventário vazio.");
            return;
        }

        System.out.println("\n📦 ===== INVENTÁRIO DETALHADO =====");
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            System.out.println("\n[ " + (i + 1) + " ] " + item.getNome());
            System.out.println("    Descrição: " + item.getDescricao());
            System.out.println("    Efeito: " + item.getEfeito() + " (Valor: " + item.getValor() + ")");
        }
        System.out.println("===================================\n");
    }

    /**
     * Verifica se o inventário está vazio
     * @return true se vazio, false caso contrário
     */
    public boolean estaVazio() {
        return itens.isEmpty();
    }

    /**
     * Retorna a quantidade de itens no inventário
     * @return número de itens
     */
    public int quantidadeItens() {
        return itens.size();
    }

    /**
     * Retorna um item pelo índice
     * @param indice Índice do item
     * @return Item na posição ou null se inválido
     */
    public Item getItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            return itens.get(indice);
        }
        return null;
    }

    /**
     * Busca um item pelo nome
     * @param nome Nome do item a buscar
     * @return Item encontrado ou null
     */
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

    /**
     * Verifica se o inventário contém um item específico
     * @param item Item a verificar
     * @return true se contém, false caso contrário
     */
    public boolean contemItem(Item item) {
        return itens.contains(item);
    }

    /**
     * Verifica se o inventário contém um item pelo nome
     * @param nome Nome do item
     * @return true se contém, false caso contrário
     */
    public boolean contemItem(String nome) {
        return buscarItem(nome) != null;
    }

    /**
     * Limpa todos os itens do inventário
     */
    public void limparInventario() {
        itens.clear();
        System.out.println("🗑️ Inventário limpo!");
    }

    /**
     * Retorna a lista de itens (para operações avançadas)
     * @return ArrayList de itens
     */
    public ArrayList<Item> getItens() {
        return new ArrayList<>(itens); // Retorna uma cópia para segurança
    }

    @Override
    public String toString() {
        if (itens.isEmpty()) {
            return "Inventário vazio";
        }

        StringBuilder sb = new StringBuilder("Inventário (" + itens.size() + " itens):\n");
        for (Item item : itens) {
            sb.append("  • ").append(item.getNome()).append("\n");
        }
        return sb.toString();
    }
}