import java.io.*;

public class LeitorArquivo {
    public void lerArquivo(String caminhoArquivo) {
        try (
                BufferedReader arquivo = new BufferedReader(new FileReader(caminhoArquivo));
        ) {
            while (arquivo.ready()) {
                String linha = arquivo.readLine();
                mostrarDevagar(linha);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void mostrarDevagar(String texto) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(25); } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }
}
