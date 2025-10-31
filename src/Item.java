public class Item implements Comparable<Item> {
    private String nome;
    private String descricao;
    private String efeito; // CURA, ATAQUE, DEFESA, CURA_COMPLETA
    private int valor; // Valor do efeito (quanto cura, quanto aumenta, etc)

    public Item(String nome, String descricao, String efeito, int valor) {
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setEfeito(efeito);
        this.setValor(valor);
    }


    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        } else {
            this.nome = "Item sem nome";
        }
    }

    public String getNome() {
        return this.nome;
    }

    public void setDescricao(String descricao) {
        if (descricao != null && !descricao.isBlank()) {
            this.descricao = descricao;
        } else {
            this.descricao = "Sem descrição";
        }
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setEfeito(String efeito) {
        if (efeito != null) {
            this.efeito = efeito.toUpperCase(); // Padroniza em maiúsculas
        } else {
            this.efeito = "NENHUM";
        }
    }

    public String getEfeito() {
        return this.efeito;
    }

    public void setValor(int valor) {
        if (valor >= 0) {
            this.valor = valor;
        } else {
            this.valor = 0;
        }
    }

    public int getValor() {
        return this.valor;
    }

    public boolean usar(Personagem personagem) {
        if (efeito == null || personagem == null) {
            return false;
        }

        return switch (efeito) {
            case "CURA" -> usarCura(personagem);
            case "CURA_COMPLETA" -> usarCuraCompleta(personagem);
            case "ATAQUE" -> usarAumentoAtaque(personagem);
            case "DEFESA" -> usarAumentoDefesa(personagem);

            case "Armazenamento" -> {
                System.out.println("Este item é para a sua missão. Não pode ser usado diretamente.");
                yield false;
            }
            default -> {
                System.out.println("Efeito desconhecido: " + efeito);
                yield false;
            }
        };
    }

    private boolean usarCura(Personagem personagem) {
        int vidaAntes = personagem.getPontosVida();
        int vidaMaxima = personagem.getPontosVidaMaximo();

        if (vidaAntes >= vidaMaxima) {
            System.out.println("Sua vida já está no máximo!");
            return false;
        }

        int novaVida = Math.min(vidaAntes + valor, vidaMaxima);
        personagem.setPontosVida(novaVida);
        int vidaCurada = novaVida - vidaAntes;

        System.out.println(personagem.getNome() + " recuperou " + vidaCurada + " HP!");
        return true;
    }


    private boolean usarCuraCompleta(Personagem personagem) {
        int vidaAntes = personagem.getPontosVida();
        int vidaMaxima = personagem.getPontosVidaMaximo();

        if (vidaAntes >= vidaMaxima) {
            System.out.println("Sua vida já está no máximo!");
            return false;
        }

        personagem.setPontosVida(vidaMaxima);
        int vidaCurada = vidaMaxima - vidaAntes;

        System.out.println(personagem.getNome() + " teve sua vida completamente restaurada! (+" + vidaCurada + " HP)");
        return true;
    }


    private boolean usarAumentoAtaque(Personagem personagem) {
        int ataqueAntes = personagem.getAtaque();
        personagem.setAtaque(ataqueAntes + valor);

        System.out.println(personagem.getNome() + " teve seu ataque aumentado em " + valor + "!");
        System.out.println("   Ataque: " + ataqueAntes + " → " + personagem.getAtaque());
        return true;
    }


    private boolean usarAumentoDefesa(Personagem personagem) {
        int defesaAntes = personagem.getDefesa();
        personagem.setDefesa(defesaAntes + valor);

        System.out.println( personagem.getNome() + " teve sua defesa aumentada em " + valor + "!");
        System.out.println("   Defesa: " + defesaAntes + " → " + personagem.getDefesa());
        return true;
    }


    public String getEfeitoDescricao() {
        return switch (efeito) {
            case "CURA" -> "Recupera " + valor + " HP";
            case "CURA_COMPLETA" -> "Restaura toda a vida";
            case "ATAQUE" -> "Aumenta ataque em " + valor;
            case "DEFESA" -> "Aumenta defesa em " + valor;
            default -> "Efeito desconhecido";
        };
    }


    @Override
    public int compareTo(Item outro) {
        return this.getNome().compareToIgnoreCase(outro.getNome());
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Item item = (Item) obj;
        return this.nome.equals(item.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.hashCode() : 0;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao + " (" + getEfeitoDescricao() + ")";
    }
}