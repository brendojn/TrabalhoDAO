import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Prestador {

    private String nome;
    private int horas;
    private List<TipoServico> tipoServico = new ArrayList<>();

    public Prestador() {
    }

    public Prestador(String nome, Date horario) {
        setNome(nome);
        cadastrarDisponibilidade(horas);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHoras() {
        return horas;
    }

    public void cadastrarDisponibilidade(int horas) {
        this.horas = horas;
    }

    public List<TipoServico> getTipoServico() {
        return tipoServico;
    }

    public void addTipoServico(TipoServico tipoServico) {
        this.tipoServico.add(tipoServico);
    }

    public void removeTipoServico(TipoServico tipoServico) {
        this.tipoServico.remove(tipoServico);
    }

}
