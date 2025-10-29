import java.io.*;

public class LeitorArquivo {
    public void lerArquivo(String caminhoArquivo) {
        try (
                BufferedReader arquivo = new BufferedReader(new FileReader(caminhoArquivo));
        ) {
            while (arquivo.ready()) {
                String linha = arquivo.readLine();

                if(linha.equals("[PAUSE]")){
                    System.out.print("\n(Pressione ENTER para continuar...)");
                    Teclado.getUmString();
                    System.out.println();
                } else if (linha.equals("[CLEAR]")){
                    limparTela();
                } else {
                    mostrarDevagar(linha,30);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void mostrarDevagar(String texto, long delay) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public void limparTela(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
