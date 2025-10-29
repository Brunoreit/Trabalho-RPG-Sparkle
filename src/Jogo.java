public class Jogo {
    LeitorArquivo leitor = new LeitorArquivo();
    private Personagem personagemJogador; // Armazena o personagem escolhido

    public void iniciarJogo() throws Exception {
        leitor.lerArquivo("SparkleInicio.txt");

        // Pede o nome do jogador
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
                personagemJogador = new Guerreiro(nomeJogador); // Passa o nome
                break;
            case "2":
                System.out.println("\nVocê escolheu ser um Mago!\n");
                personagemJogador = new Mago(nomeJogador); // Passa o nome
                break;
            case "3":
                System.out.println("\nVocê escolheu ser um Arqueiro\n");
                personagemJogador = new Arqueiro(nomeJogador); // Passa o nome
                break;
        }
    }

    public Personagem getPersonagemJogador() {
        return personagemJogador;
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
                    System.out.print("\\nOpção inválida! (Pressione ENTER para tentar novamente...)");
                    Teclado.getUmString();
                    break;
            }
        }
    }
}