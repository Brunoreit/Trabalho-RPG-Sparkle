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
                        personagemJogador = new Guerreiro(nomeJogador);
                        break;
                    case "2":
                        System.out.println("\nVocê escolheu ser um Mago!\n");
                        personagemJogador = new Mago(nomeJogador);
                        break;
                    case "3":
                        System.out.println("\nVocê escolheu ser um Arqueiro\n");
                        personagemJogador = new Arqueiro(nomeJogador);
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

        boolean momentoEscolha = true;
        boolean foiNaTaverna = false;

        while(momentoEscolha){
            System.out.println("\nO que você deseja fazer?");
            System.out.println("[ 1 ] Ir pela rua da esquerda (Taverna)");
            System.out.println("[ 2 ] Ir pela rua da direita (Residencial)");

            System.out.print("\nDigite sua escolha: ");
            String escolha2 = Teclado.getUmString();

            switch (escolha2){
                case "1":
                    if(foiNaTaverna){
                        leitor.mostrarDevagar("\"hmmm acabei de voltar desse caminho. Melhor focar em achar Nissin por agora.\"",30);
                    } else {
                        leitor.lerArquivo("historia/missao1_taverna.txt");

                        Item paoDeMel = new Item("Pão de mel","Um pão doce que restaura 10 HP.", "cura", 1);
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

                default:
                    leitor.mostrarDevagar("Opção inválida.",30);
                    break;
            }
        }
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