import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agenda {

    private Date data;
    public static Agenda instancia;

    private Agenda() {
    }


    public Agenda(Date data) {
        this.data = data;
    }

    public static Agenda getInstancia() {
        if (instancia == null)
            instancia = new Agenda();
        return instancia;
    }

    public void cadastrar(Date data, Cliente cliente, Prestador prestador) {

        String path = "agenda.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {

                bw.write("Data marcada:" + data + " " + "Nome do cliente: " + cliente.getNome() + "  " + "Nome do prestador: " + prestador.getNome());
                bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
