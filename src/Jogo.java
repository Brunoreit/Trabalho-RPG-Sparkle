import java.util.Random;

public class Jogo {
    LeitorArquivo leitor = new LeitorArquivo();
    private Personagem personagemJogador; // Armazena o personagem escolhido

    public void iniciarJogo() throws Exception {
        leitor.lerArquivo("historia/SparkleInicio.txt");

        personagemJogador = null;
        while(personagemJogador == null){

            try {
                System.out.print("Digite o nome do seu personagem: ");
                String nomeJogador = Teclado.getUmString();

                System.out.print("Qual a sua vocação?");

                System.out.println("""
                
                
                [ 1 ] Guerreiro
                
                [ 2 ] Mago
                
                [ 3 ] Arqueiro
                """);
                System.out.print("\nDigite sua escolha: ");
                String escolha = Teclado.getUmString();

                switch (escolha) {
                    case "1":
                        System.out.println("\nVocê escolheu ser um Guerreiro!\n");
                        personagemJogador = new Guerreiro(nomeJogador,120,7,12,1);
                        break;
                    case "2":
                        System.out.println("\nVocê escolheu ser um Mago!\n");
                        personagemJogador = new Mago(nomeJogador,80,10,8,1);
                        break;
                    case "3":
                        System.out.println("\nVocê escolheu ser um Arqueiro\n");
                        personagemJogador = new Arqueiro(nomeJogador,100,8,10,1);
                        break;
                    default:
                        System.out.print("\nOpção inválida! (Pressione ENTER para tentar novamente...)");
                        Teclado.getUmString();
                        leitor.limparTela();
                }
            } catch (Exception e){
                leitor.mostrarDevagar("\nErro: " + e.getMessage(), 30);
                System.out.print("\n(Pressione ENTER para tentar novamente...)");
                Teclado.getUmString();
                leitor.limparTela();
            }
        }
        leitor.mostrarDevagar("Personagem criado com sucesso!", 30);
        Thread.sleep(1500);
        missao1();

    }

    public Personagem getPersonagemJogador() {
        return personagemJogador;
    }

    public void missao1() throws Exception{
        leitor.limparTela();
        leitor.lerArquivo("historia/missao1_inicio.txt");

        int amostrasColetadas = 0;
        boolean momentoEscolha = true;
        boolean foiNaTaverna = false;

        while(momentoEscolha){
            System.out.println("\nO que você deseja fazer?");
            System.out.println("[ 1 ] Ir pela rua da esquerda (Taverna)");
            System.out.println("[ 2 ] Ir pela rua da direita (Residencial)");
            System.out.println("[ 3 ] Ver Inventário / usar item");
            System.out.println("[ 4 ] Ver Status do Personagem");
            System.out.println("[ 5 ] Sair do Jogo");

            System.out.print("\nDigite sua escolha: ");
            String escolha2 = Teclado.getUmString();

            switch (escolha2){
                case "1":
                    if(foiNaTaverna){
                        leitor.mostrarDevagar("\"hmmm acabei de voltar desse caminho. Melhor focar em achar Nissin por agora.\"",30);
                    } else {
                        leitor.lerArquivo("historia/missao1_taverna.txt");

                        Item paoDeMel = new Item("Pão de mel","Um pão doce que restaura 5 HP.", "cura", 1);
                        personagemJogador.getInventario().adicionarItem(paoDeMel);

                        leitor.mostrarDevagar("Você ganhou 1x Pão de mel!",50);

                        leitor.mostrarDevagar("\n\"Obrigado...\" você responde, meio envergonhado.", 30);

                        System.out.print("\n(Pressione ENTER para perguntar sobre Nissin...)");
                        Teclado.getUmString();

                        leitor.mostrarDevagar("\nVocê pergunta a jovém moça sobre o endereço da carta...", 30);
                        leitor.mostrarDevagar("Ela responde: \"Ah, a maga Nissin! Claro. Fica na Rua Residencial. Volte e pegue o caminho da direita!\"", 30);
                        leitor.mostrarDevagar("\"Lembre-se de me visitar de vez em quando!\"",30);

                        foiNaTaverna = true;
                    }
                    break;

                case "2":
                    leitor.lerArquivo("historia/missao1_casa_nissin.txt");
                    momentoEscolha = false;
                    break;

                case "3":
                    gerenciarInventario();
                    break;

                case "4":
                    leitor.mostrarDevagar(personagemJogador.toString(), 30);
                    break;

                case "5":
                    leitor.mostrarDevagar("Saindo do jogo...",30);
                    System.exit(0);
                    break;

                default:
                    leitor.mostrarDevagar("Opção inválida.",30);
                    break;
            }
        }

        leitor.lerArquivo("historia/missao1_conversa_Nissin.txt");

        Item pocao = new Item("Poção de Cura", "Restaura 20 HP.", "cura_20", 2);
        personagemJogador.getInventario().adicionarItem(pocao);
        leitor.mostrarDevagar("Você recebeu 2x Poção de Cura!", 30);

        Item tubo_ensaio = new Item("Tubo de ensaio","Um tubo feito de vidro que pode guardar líquidos e essências","Armazenamento",1);
        personagemJogador.getInventario().adicionarItem(tubo_ensaio);
        leitor.mostrarDevagar("Você recebeu 1x Tubo de ensaio!", 30);

        System.out.print("\n(Pressione ENTER para continuar...)");
        Teclado.getUmString();

        boolean momentoEscolha2 = true;
        while (momentoEscolha2){
            leitor.limparTela();
            System.out.println("\n=== HUB DA MISSÃO ===");
            System.out.println("[ 1 ] Vasculhar Floresta (PERIGO)");
            System.out.println("[ 2 ] Ver Inventário / usar item");
            System.out.println("[ 3 ] Ver Status do Personagem");
            System.out.println("[ 4 ] Sair do Jogo");
            System.out.print("Sua escolha: ");

            String escolhaMissao = Teclado.getUmString();

            switch (escolhaMissao) {
                case "1":
                    leitor.limparTela();
                    if (new Random().nextInt(4) == 0) {
                        leitor.mostrarDevagar("Você pisa em uma armadilha de espinhos!", 30);
                        int dano = 5;
                        personagemJogador.setPontosVida(personagemJogador.getPontosVida() - dano);
                        leitor.mostrarDevagar("Você perde " + dano + " HP.", 30);
                        Thread.sleep(1500);
                    }

                    Inimigo servoDaIra = new Inimigo("servo da Ira",30,4,9,1,25);
                    leitor.mostrarDevagar("Você avança para a floresta e uma criatura bizzara aparece!", 30);
                    Thread.sleep(1000);
                    Combate gerenciadorBatalha = new Combate();

                    int resultado = gerenciadorBatalha.batalhar(personagemJogador, servoDaIra);

                    if (resultado == 1) { // VITÓRIA
                        leitor.mostrarDevagar("Você venceu a batalha!", 30);
                        System.out.print("\n(Pressione ENTER para coletar essência do monstro...)");
                        Teclado.getUmString();

                        leitor.mostrarDevagar("Você coleta 1 amostra para Nissin!", 30);
                        amostrasColetadas++;
                    } else if (resultado == -1) {
                        leitor.mostrarDevagar("Você foi derrotado...", 30);
                        Thread.sleep(2000);
                        return;
                    } else {
                        leitor.mostrarDevagar("Você fugiu da batalha e não coletou a amostra...", 30);
                    }

                    if(amostrasColetadas >= 2){
                        leitor.limparTela();
                        leitor.mostrarDevagar("Você coletou 2 amostras! Hora de voltar para a Nissin.", 30);
                        leitor.mostrarDevagar("Ela te agradece e diz que vai começar a analisar...", 30);

                        System.out.print("\n(Pressione ENTER para concluir a missão...)");
                        Teclado.getUmString();

                        momentoEscolha2 = false;
                        break;
                    } else {

                        int faltam = 2 - amostrasColetadas;
                        if (faltam > 1) {
                            leitor.mostrarDevagar("Você ainda precisa de " + faltam + " amostras.", 30);
                        } else {
                            leitor.mostrarDevagar("Você ainda precisa de 1 amostra.", 30);
                        }

                        System.out.print("\n(Pressione ENTER para continuar...)");
                        Teclado.getUmString();
                        break;
                    }
                case "2":
                    gerenciarInventario();
                    break;

                case "3":
                    leitor.mostrarDevagar(personagemJogador.toString(), 30);
                    System.out.print("\n(Pressione ENTER para continuar...)");
                    Teclado.getUmString();
                    break;

                case "4":
                    leitor.mostrarDevagar("Saindo do jogo...",30);
                    System.exit(0);
                    break;

                default:
                    leitor.mostrarDevagar("Opção inválida.", 30);
                    break;
            }
        }
    }

    private void gerenciarInventario() throws Exception{
        Inventario inv = personagemJogador.getInventario();

        if (inv.estaVazio()) {
            leitor.mostrarDevagar("Seu inventário está vazio.", 30);
            System.out.print("\n(Pressione ENTER para voltar...)");
            Teclado.getUmString();
            return;
        }

        inv.listarItens();
        System.out.print("\nDeseja usar um item? (Digite o número ou 0 para voltar): ");

        String escolhaItem = Teclado.getUmString();

        if (escolhaItem.equals("0")) {
            leitor.mostrarDevagar("Voltando ao hub...", 30);
            Thread.sleep(800);
            return;
        }

        try {
            // Converter de volta para índice (ex: "1" vira índice 0)
            int indice = Integer.parseInt(escolhaItem) - 1;

            Item item = inv.getItem(indice);

            if (item != null) {
                item.usar(personagemJogador);

                inv.removerItem(indice);

                leitor.mostrarDevagar("Item " + item.getNome() + " usado com sucesso!", 30);

                leitor.mostrarDevagar(personagemJogador.toString(), 30);

            } else {
                leitor.mostrarDevagar("Item inválido!", 30);
            }

        } catch (NumberFormatException e) {
            leitor.mostrarDevagar("Opção inválida! Digite um número.", 30);
        } catch (Exception e) {
            leitor.mostrarDevagar("Erro ao usar o item: " + e.getMessage(), 30);
        }

        System.out.print("\n(Pressione ENTER para voltar...)");
        Teclado.getUmString();

    }





    public static void main(String[] args) throws Exception {
        Jogo jogo = new Jogo();

        while (true) {
            jogo.leitor.limparTela();
            jogo.leitor.mostrarDevagar("=======================", 20);
            jogo.leitor.mostrarDevagar("      S P A R K L E      ",50);
            jogo.leitor.mostrarDevagar("=======================", 20);
            System.out.println();
            System.out.println("  [ 1 ] Iniciar Aventura");
            System.out.println("  [ 2 ] Créditos");
            System.out.println("  [ 3 ] Sair");
            System.out.println();
            System.out.print("  Escolha uma opção: ");

            String opc = Teclado.getUmString();

            switch (opc){
                case "1":
                    jogo.iniciarJogo();
                    break;

                case "2":
                    jogo.leitor.limparTela();
                    jogo.leitor.mostrarDevagar("=======================", 20);
                    jogo.leitor.mostrarDevagar("        CRÉDITOS       ", 50);
                    jogo.leitor.mostrarDevagar("=======================", 20);
                    System.out.println();
                    jogo.leitor.mostrarDevagar("Jogo criado por:", 30);
                    jogo.leitor.mostrarDevagar("  - Bruno Reitano", 50);
                    jogo.leitor.mostrarDevagar("  - Gabriel Flores", 50);
                    System.out.println();
                    jogo.leitor.mostrarDevagar("Disciplina:", 30);
                    jogo.leitor.mostrarDevagar("  Paradigma e Programação Orientada a Objetos", 30);
                    System.out.println();
                    System.out.print("(Pressione ENTER para voltar...)");
                    Teclado.getUmString();
                    break;

                case "3":
                    jogo.leitor.limparTela();
                    jogo.leitor.mostrarDevagar("Obrigado por jogar!", 30);
                    System.exit(0);
                    break;

                default:
                    System.out.print("\nOpção inválida! (Pressione ENTER para tentar novamente...)");
                    Teclado.getUmString();
                    break;
            }
        }
    }
}