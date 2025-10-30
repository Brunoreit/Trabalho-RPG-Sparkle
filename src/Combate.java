import java.util.Random;

public class Combate {
    private Random dado = new Random();

    /**
     * Inicia uma batalha entre o personagem e um inimigo
     * @param personagem O personagem do jogador
     * @param inimigo O inimigo a ser enfrentado
     */
    public void batalhar(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n=== INÍCIO DA BATALHA ===");
        System.out.println(personagem.getNome() + " VS " + inimigo.getNome());
        System.out.println("========================\n");

        int turno = 1;

        // Loop de combate continua até alguém morrer
        while (personagem.getPontosVida() > 0 && inimigo.getPontosVida() > 0) {
            System.out.println("--- TURNO " + turno + " ---");

            // Mostra status antes do turno
            mostrarStatus(personagem, inimigo);

            // Turno do personagem (com menu de opções)
            boolean turnoExecutado = executarTurnoPersonagem(personagem, inimigo);

            // Se o jogador usou um item e perdeu o turno, pula para o turno do inimigo
            if (!turnoExecutado) {
                System.out.println("\n" + personagem.getNome() + " perdeu o turno!");
            }

            // Verifica se o inimigo morreu
            if (inimigo.getPontosVida() <= 0) {
                break;
            }

            // Turno do inimigo
            executarAtaqueInimigo(inimigo, personagem);

            // Verifica se o personagem morreu
            if (personagem.getPontosVida() <= 0) {
                break;
            }

            turno++;
            pausar();
        }

        // Resultado final
        mostrarResultado(personagem, inimigo);
    }

    /**
     * Executa o turno do personagem com menu de opções
     * @return true se executou ataque, false se usou item
     */
    private boolean executarTurnoPersonagem(Personagem personagem, Inimigo inimigo) {
        boolean acaoRealizada = false;

        while (!acaoRealizada) {
            System.out.println("\n=== SUA VEZ, " + personagem.getNome() + "! ===");
            System.out.println("[ 1 ] Atacar");
            System.out.println("[ 2 ] Usar Item");
            System.out.print("Escolha sua ação: ");

            String escolha = Teclado.getUmString();

            switch (escolha) {
                case "1":
                    // Executa ataque normal
                    executarAtaque(personagem, inimigo);
                    acaoRealizada = true;
                    return true;

                case "2":
                    // Abre inventário e usa item
                    usarItem(personagem);
                    // Se voltou do inventário sem usar item, pergunta novamente
                    // Se usou item, sai do loop
                    // Verifica se realmente usou item checando se o método retornou
                    acaoRealizada = true;
                    return false;

                default:
                    System.out.println("\n❌ Opção inválida! Tente novamente.\n");
                    break;
            }
        }

        return true;
    }

    /**
     * Abre o inventário e permite usar um item
     */
    private void usarItem(Personagem personagem) {
        Inventario inv = personagem.getInventario();

        // Verifica se o inventário está vazio - continua tentando até ter item ou voltar ao menu
        while (inv.estaVazio()) {
            System.out.println("❌ Seu inventário está vazio!");
            System.out.println("Digite 0 para voltar e escolher atacar: ");
            String opcao = Teclado.getUmString();
            if (opcao.equals("0")) {
                // Volta para o menu principal do turno
                return;
            }
        }

        boolean itemUsado = false;

        // Loop até usar um item válido ou voltar
        while (!itemUsado) {
            System.out.println("\n=== INVENTÁRIO ===");

            // Mostra os itens disponíveis
            inv.mostrarItens();

            System.out.print("\nDigite o número do item que deseja usar (0 para voltar): ");
            String escolhaItem = Teclado.getUmString();

            if (escolhaItem.equals("0")) {
                System.out.println("Voltando ao menu...\n");
                return;
            }

            try {
                int indice = Integer.parseInt(escolhaItem) - 1;
                Item item = inv.getItem(indice);

                if (item != null) {
                    // Usa o item
                    usarItemEmCombate(personagem, item);

                    // Remove o item do inventário após uso
                    inv.removerItem(indice);

                    System.out.println("✅ Item usado com sucesso!\n");
                    itemUsado = true;
                } else {
                    System.out.println("❌ Item inválido! Tente outro.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida! Tente outro.\n");
            } catch (Exception e) {
                System.out.println("❌ Erro ao usar item: " + e.getMessage() + " Tente outro.\n");
            }
        }
    }

    /**
     * Aplica o efeito do item no personagem
     */
    private void usarItemEmCombate(Personagem personagem, Item item) {
        System.out.println("\n" + personagem.getNome() + " usa " + item.getNome() + "!");
        System.out.println("📜 " + item.getDescricao());

        // Usa o método do próprio item para aplicar o efeito
        item.usar(personagem);
    }

    /**
     * Executa um ataque do personagem
     */
    private void executarAtaque(Personagem atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " ataca!");

        // Rola o dado (1 a 20)
        int valorDado = rolarDado();

        // Calcula o valor total do ataque
        int valorAtaque = atacante.getAtaque() + valorDado;

        System.out.println("🎲 Dado rolado: " + valorDado);
        System.out.println("⚔️ Ataque total: " + valorAtaque + " (Ataque " + atacante.getAtaque() + " + Dado " + valorDado + ")");

        // Verifica se passou da defesa
        if (valorAtaque > defensor.getDefesa()) {
            int dano = valorAtaque - defensor.getDefesa();

            // Calcula a nova vida
            int novaVida = defensor.getPontosVida() - dano;
            if (novaVida < 0) {
                novaVida = 0;
            }

            defensor.setPontosVida(novaVida);
            System.out.println("💥 ACERTOU! " + defensor.getNome() + " recebeu " + dano + " de dano!");
        } else {
            System.out.println("🛡️ DEFENDIDO! A defesa de " + defensor.getNome() + " (" + defensor.getDefesa() + ") bloqueou o ataque!");
        }

        System.out.println();
    }

    /**
     * Executa o ataque do inimigo
     */
    private void executarAtaqueInimigo(Inimigo inimigo, Personagem personagem) {
        System.out.println("\n--- Turno do " + inimigo.getNome() + " ---");

        int tipoAtaque = dado.nextInt(3) + 1;
        int valorDado = rolarDado();
        int valorAtaque = 0;

        switch (tipoAtaque) {
            case 1:
                valorAtaque = inimigo.getAtaque() + valorDado;
                System.out.println(inimigo.getNome() + " usa SOCO!");
                break;
            case 2:
                valorAtaque = (int)(inimigo.getAtaque() * 1.5) + valorDado;
                System.out.println(inimigo.getNome() + " usa CHUTE!");
                break;
            case 3:
                valorAtaque = (inimigo.getAtaque() * 2) + valorDado;
                System.out.println(inimigo.getNome() + " usa ATAQUE PODEROSO! 💥");
                break;
        }

        System.out.println("🎲 Dado rolado: " + valorDado);
        System.out.println("⚔️ Ataque total: " + valorAtaque);

        if (valorAtaque > personagem.getDefesa()) {
            int dano = valorAtaque - personagem.getDefesa();
            int novaVida = personagem.getPontosVida() - dano;
            if (novaVida < 0) {
                novaVida = 0;
            }
            personagem.setPontosVida(novaVida);
            System.out.println("💥 ACERTOU! " + personagem.getNome() + " recebeu " + dano + " de dano!");
        } else {
            System.out.println("🛡️ DEFENDIDO! A defesa de " + personagem.getNome() + " bloqueou o ataque!");
        }

        System.out.println();
    }

    /**
     * Rola um dado de 20 faces
     * @return valor entre 1 e 20
     */
    private int rolarDado() {
        return dado.nextInt(20) + 1;
    }

    /**
     * Mostra o status atual dos combatentes
     */
    private void mostrarStatus(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n📊 STATUS:");
        System.out.println(personagem.getNome() + " - HP: " + personagem.getPontosVida() + "/" + personagem.getPontosVidaMaximo() +
                " | ATK: " + personagem.getAtaque() + " | DEF: " + personagem.getDefesa());
        System.out.println(inimigo.getNome() + " - HP: " + inimigo.getPontosVida() +
                " | ATK: " + inimigo.getAtaque() + " | DEF: " + inimigo.getDefesa());
        System.out.println();
    }

    /**
     * Mostra o resultado final da batalha
     */
    private void mostrarResultado(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n=== FIM DA BATALHA ===");

        if (personagem.getPontosVida() > 0) {
            System.out.println("🎉 VITÓRIA! " + personagem.getNome() + " derrotou " + inimigo.getNome() + "!");
            System.out.println("HP restante: " + personagem.getPontosVida());
        } else {
            System.out.println("💀 DERROTA! " + personagem.getNome() + " foi derrotado por " + inimigo.getNome() + "...");
        }

        System.out.println("======================\n");
    }

    /**
     * Pausa breve entre ações
     */
    private void pausar() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            // Ignora
        }
    }
}