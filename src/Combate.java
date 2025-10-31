import java.util.Random;

public class Combate {
    private Random dado = new Random();

    public int batalhar(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n=== INÍCIO DA BATALHA ===");
        System.out.println(personagem.getNome() + " VS " + inimigo.getNome());
        System.out.println("========================\n");

        int turno = 1;

        while (personagem.getPontosVida() > 0 && inimigo.getPontosVida() > 0) {
            System.out.println("--- TURNO " + turno + " ---");

            mostrarStatus(personagem, inimigo);


            boolean turnoExecutado = executarTurnoPersonagem(personagem, inimigo);


            if (!turnoExecutado) {
                System.out.println("\n" + personagem.getNome() + " perdeu o turno!");
            }

            if (inimigo.getPontosVida() <= 0) {
                System.out.println("\nVocê derrotou " + inimigo.getNome() + " e coletou seus itens!");
                personagem.getInventario().transferirInventario(inimigo.getInventario());
                personagem.ganharExperiencia(inimigo.getXpRecompensa());
                break;
            }

            executarAtaqueInimigo(inimigo, personagem);

            if (personagem.getPontosVida() <= 0) {
                break;
            }

            turno++;
            pausar();
        }

         return mostrarResultado(personagem, inimigo);
    }

    private boolean executarTurnoPersonagem(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n=== SUA VEZ, " + personagem.getNome() + "! ===");
        System.out.println("[ 1 ] Atacar");
        System.out.println("[ 2 ] Usar Item");
        System.out.println("[ 3 ] Fugir");
        System.out.print("Escolha sua ação: ");

        String escolha = Teclado.getUmString();

        switch (escolha) {
            case "1":
                executarAtaque(personagem, inimigo);
                break;
            case "2":
                usarItem(personagem);
                break;
            case "3":
                fugirCombate(personagem, inimigo);
                break;
            default:
                System.out.println("\nOpção inválida! Tente novamente.\n");

        }
        return true;
    }




    private void usarItem(Personagem personagem) {
        Inventario inv = personagem.getInventario();

        while (inv.estaVazio()) {
            System.out.println("Seu inventário está vazio!");
            System.out.println("Digite 0 para voltar e escolher atacar: ");
            String opcao = Teclado.getUmString();
            if (opcao.equals("0")) {

                return;
            }
        }

        boolean itemUsado = false;
        while (!itemUsado) {
            System.out.println("\n=== INVENTÁRIO ===");

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
                    usarItemEmCombate(personagem, item);

                    inv.removerItem(indice);

                    System.out.println("Item usado com sucesso!\n");
                    itemUsado = true;
                } else {
                    System.out.println("Item inválido! Tente outro.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Tente outro.\n");
            } catch (Exception e) {
                System.out.println("Erro ao usar item: " + e.getMessage() + " Tente outro.\n");
            }
        }
    }


    private void usarItemEmCombate(Personagem personagem, Item item) {
        System.out.println("\n" + personagem.getNome() + " usa " + item.getNome() + "!");
        System.out.println(item.getDescricao());
        item.usar(personagem);
    }


    private void executarAtaque(Personagem atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " ataca!");

        int valorDado = rolarDado();

        int valorAtaque = atacante.getAtaque() + valorDado;

        System.out.println("Dado rolado: " + valorDado);
        System.out.println("Ataque total: " + valorAtaque + " (Ataque " + atacante.getAtaque() + " + Dado " + valorDado + ")");

        if (valorAtaque > defensor.getDefesa()) {
            int dano = valorAtaque - defensor.getDefesa();

            int novaVida = defensor.getPontosVida() - dano;
            if (novaVida < 0) {
                novaVida = 0;
            }

            defensor.setPontosVida(novaVida);
            System.out.println( defensor.getNome() + " recebeu " + dano + " de dano!");
        } else {
            System.out.println("A defesa de " + defensor.getNome() + " (" + defensor.getDefesa() + ") bloqueou o ataque!");
        }
        System.out.println();
    }

    private void fugirCombate(Personagem atacante, Personagem defensor) {
        int valorDado = rolarDado();
        System.out.println(valorDado);
     if (valorDado > 10) {
         System.out.println(atacante.getNome() + "Fugiu Com Sucesso de " +defensor.getNome() + "!");
     }
     else {
         System.out.println(atacante.getNome() + "Não Conseguiu Fugir dos Ataques de'" + defensor.getNome() + "'!");
         System.out.println("Vez Do Inimigo!");
     }
    }
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
                valorAtaque = (int)(inimigo.getAtaque() * 1.2) + valorDado;
                System.out.println(inimigo.getNome() + " usa CHUTE!");
                break;
            case 3:
                valorAtaque = (int)(inimigo.getAtaque() * 1.5) + valorDado;
                System.out.println(inimigo.getNome() + " usa ATAQUE PODEROSO!");
                break;
        }

        System.out.println("Dado rolado: " + valorDado);
        System.out.println("Ataque total: " + valorAtaque);

        if (valorAtaque > personagem.getDefesa()) {
            int dano = valorAtaque - personagem.getDefesa();
            int novaVida = personagem.getPontosVida() - dano;
            if (novaVida < 0) {
                novaVida = 0;
            }
            personagem.setPontosVida(novaVida);
            System.out.println(personagem.getNome() + " recebeu " + dano + " de dano!");
        } else {
            System.out.println("A defesa de " + personagem.getNome() + " bloqueou o ataque!");
        }

        System.out.println();
    }


    private int rolarDado() {
        return dado.nextInt(20) + 1;
    }


    private void mostrarStatus(Personagem personagem, Inimigo inimigo) {
        System.out.println("\nSTATUS:");
        System.out.println(personagem.getNome() + " - HP: " + personagem.getPontosVida() + "/" + personagem.getPontosVidaMaximo() +
                " | ATK: " + personagem.getAtaque() + " | DEF: " + personagem.getDefesa() + " | NIVEL: "+ personagem.getNivel() + " | EXP:" + personagem.getExperiencia()) ;
        System.out.println(inimigo.getNome() + " - HP: " + inimigo.getPontosVida() +
                " | ATK: " + inimigo.getAtaque() + " | DEF: " + inimigo.getDefesa() + " | NIVEL: "+ inimigo.getNivel()) ;
        System.out.println();
    }


    private int mostrarResultado(Personagem personagem, Inimigo inimigo) {
        System.out.println("\n=== FIM DA BATALHA ===");

        if (personagem.getPontosVida() > 0) {
            System.out.println("VITÓRIA! " + personagem.getNome() + " derrotou " + inimigo.getNome() + "!");
            System.out.println("HP restante: " + personagem.getPontosVida());
            System.out.println("Experiencia recebida " + inimigo.getXpRecompensa());
            System.out.println("Experiencia restante para subir de nivel: " + personagem.getExperienciaProximoNivel());
            return 1;
        } else if (personagem.getPontosVida() < 0) {
            System.out.println("DERROTA! " + personagem.getNome() + " foi derrotado por " + inimigo.getNome() + "...");
            return -1;
        }
        else {
            System.out.print(" FUGIU! ");
            return 0;
        }

    }

    private void pausar() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            // Ignora
        }
    }
}