import java.util.Objects;

public abstract class Personagem {
    private String nome;
    private int pontosVida;
    private int pontosVidaMaximo;
    private int ataque;
    private int defesa;
    private int nivel;
    private Inventario inventario;
    private int experiencia;
    private int experienciaProximoNivel;


    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel, Inventario inventario) throws Exception{
        this.setNome(nome);
        this.setPontosVida(pontosVida);
        this.pontosVidaMaximo = pontosVida;
        this.setAtaque(ataque);
        this.setDefesa(defesa);
        this.setNivel(nivel);
        this.setInventario(inventario);
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaProximoNivel = 100;
    }

    public Personagem (Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.experiencia = outro.experiencia;
        this.experienciaProximoNivel = 100;
        this.inventario = outro.inventario;

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


    public String getNome() {
        return nome;
    }

    public void setPontosVida(int pontosVida){
        this.pontosVida = pontosVida;
    }

    public int getPontosVida(){
        return pontosVida;
    }
    public int getPontosVidaMaximo() {
        return pontosVidaMaximo;
    }
    public void setAtaque(int ataque){
        this.ataque = ataque;
    }

    public int getAtaque(){
        return ataque;
    }

    public void setDefesa(int defesa){
        this.defesa = defesa;
    }

    public int getDefesa(){
        return defesa;
    }

    public void setNivel(int nivel){
        this.nivel = nivel;
    }

    public int getNivel(){
        return nivel;
    }

    public void setInventario(Inventario inventario){
        this.inventario = inventario;
    }

    public Inventario getInventario(){
        return inventario;
    }

    public int getExperiencia() {return experiencia;}

    public int getExperienciaProximoNivel() {return experienciaProximoNivel-experiencia;}

    public boolean ganharExperiencia(int xp) {
        this.experiencia += xp;

        if (this.experiencia >= this.experienciaProximoNivel) {
            subirNivel();
            return true;
        }
        return false;
    }

    private void subirNivel() {
        this.nivel++;
        this.experiencia -= this.experienciaProximoNivel;
        this.pontosVidaMaximo += nivel * 5;
        this.pontosVida = this.pontosVidaMaximo;
        this.ataque += nivel * 2;
        this.defesa += nivel;
        this.experienciaProximoNivel = 100 * nivel;
        System.out.println("Subiu de nivel! Novo nivel: " + this.nivel);
    }

    public void curar(int quantidade) {
        this.pontosVida += quantidade;
        if (this.pontosVida > pontosVidaMaximo) {
            this.pontosVida = pontosVidaMaximo;
        }
    }

    @Override
    public String toString() {
         {
            String status = "--- STATUS: " + this.getNome() + " ---\n" +
                    " Classe: " + this.getClass().getSimpleName() + "\n" +
                    " Nível:  " + this.getNivel() + "\n" +
                    " HP:     " + this.getPontosVida() + "\n" +
                    " Ataque: " + this.getAtaque() + "\n" +
                    " Defesa: " + this.getDefesa();
            return status;
        }
    }

    @Override
    public int hashCode() {
        return this.getNome().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personagem outro = (Personagem) obj;
        return this.getNome().equals(outro.getNome());
    }

    @Override
    public Personagem clone(){
        try {
            return (Personagem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar personagem", e);
        }
    }
}