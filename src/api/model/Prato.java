package api.model;


    // Primeira classe criada, pois é a mais simples e serve de exemplo para as demais, já que não depende de outras classes para ser identificada
public class Prato extends BaseEntity{

    private static int contador = 1;
    
    // Atributos de acordo com as regras de negócio    
    private String nome;
    private String descricao;
    private double preco;

    // CONSTRUTORES

    // Construtor com todos os atributos (exceto o id, que é herdado da classe pai "BaseEntity")
    public Prato(String nome, String descricao, double preco) {
        this.setId(contador++); // Incrementa o contador e define o ID do prato
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Prato() {
        this.setId(contador++); // Incrementa o contador e define o ID do prato
    }

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    // MÉTODOS
    
    @Override //Uso de IA para geração de StringBuilder personalizado
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRATO - ID: ").append(getId())
        .append("\nNome: ").append(nome)
        .append("\nDescrição: ").append(descricao)
        .append("\nPreço: R$ ").append(String.format("%.2f", preco))
        .append("\n");
        return sb.toString();
    }

}
