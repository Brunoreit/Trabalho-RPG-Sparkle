public class Jogo {
    LeitorArquivo leitor = new LeitorArquivo();
    private Personagem personagemJogador; // Armazena o personagem escolhido

    public void iniciarJogo() throws Exception {
        leitor.lerArquivo("SparkleInicio.txt");

        // Pede o nome do jogador
        System.out.print("Digite o nome do seu personagem: ");
        String nomeJogador = Teclado.getUmString();

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

    public static void main(String[] args) {
        try {
            Jogo jogo = new Jogo();
            jogo.iniciarJogo();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}