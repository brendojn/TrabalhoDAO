import java.util.Date;

public class Cliente {

    private String nome;
    private long cpf;

    public Cliente() {
    }

    public Cliente(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public void solicitarServicos(Date date) {

    }
}