public abstract class Personagem {
    private String nome;
    private int pontosVida;
    private int ataque;
    private int defensa;
    private int nivel;
    private Inventario inventario;

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel, Inventario inventario) throws Exception{
        this.setNome(nome);
        this.setPontosVida(pontosVida);
        this.setAtaque(ataque);
        this.setDefesa(defesa);
        this.setNivel(nivel);
        this.setInventario(inventario);

    }

    public void setNome(String nome) throws Exception{
        if (nome == null) throw new Exception("Nome não pode ser nulo!");

        if(nome.isBlank()) throw new Exception("Nome não pode ser vazio ou apenas espaço em branco!");

        if(nome.length() > 25) throw new Exception("Nome não pode ter mais de 25 caracteres. Espaços contam");

        char primeiro = nome.charAt(0);
        if(!Character.isLetter(primeiro)) throw new Exception("O primeiro caractere do nome precisa ser uma letra!");

        for(int i = 1; i < nome.length(); i++){
            char c = nome.charAt(i);
            if(Character.isLetter(c)) continue;

            if(c == ' ' || c == '-' || c == '\'' ){

                if(i == nome.length()-1) throw new Exception("Não pode ter espaço, hífen e apóstrofo ao final do nome!");
                char proximo = nome.charAt(i + 1);
                if(!Character.isLetter(proximo)) throw new Exception("Não pode ter separadores um seguido do outro no nome!");
            } else {
                throw new Exception("só pode conter: letras (a-z, A-Z), espaço, hífen ou apóstrofo");
            }
        }
        this.nome = nome;
    }

    public void setPontosVida(int pontosVida){}
    public void setAtaque(int ataque){}
    public void setDefesa(int defensa){}
    public void setNivel(int nivel){};
    public void setInventario(Inventario inventario){}
}
