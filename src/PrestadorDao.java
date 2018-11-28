import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrestadorDao implements PersistenciaDao<Prestador, String>, Filtro{
    @Override
    public Prestador get(String chave) {
        Prestador prestador = null;
        Prestador p = null;

        try (BufferedReader br = new BufferedReader(new FileReader("prestador.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                p = new Prestador();
                p.setNome(nome);
                p.cadastrarDisponibilidade(Integer.parseInt(br.readLine()));

                if (chave.equals(p.getNome())) {
                    prestador = p;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o prestador: '" + p.getNome());
            e.printStackTrace();
        }
        return prestador;
    }

    @Override
    public void add(Prestador g) {
        Prestador prestador = g;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prestador.txt", true))) {
            String tab = System.getProperty("line.separator");
            bw.write(prestador.getNome() + tab);
            bw.write(prestador.getHoras() + tab);
            bw.flush();

        } catch (Exception e) {
            System.out.println("ERRO ao inserir prestador '" + prestador.getNome());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Prestador g) {
        List<Prestador> prestadores = new ArrayList<>();
        int index = prestadores.indexOf(g);
        if (index != -1) {
            prestadores.remove(index);
        }
        saveToFile(prestadores);
    }

    @Override
    public void update(Prestador g) {
        List<Prestador> prestadores = getAll();
        int index = prestadores.indexOf(g);
        if (index != -1) {
            prestadores.set(index, g);
        }
        saveToFile(prestadores);
    }

    @Override
    public List<Prestador> getAll() {
        List<Prestador> prestadores = new ArrayList<>();
        Prestador p = null;
        try (BufferedReader br = new BufferedReader(new FileReader("prestador.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                p = new Prestador();
                p.setNome(nome);
                p.cadastrarDisponibilidade(Integer.parseInt(br.readLine()));
                prestadores.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler os Prestadores!");
            e.printStackTrace();
        }
        return prestadores;
    }

    private void saveToFile(List<Prestador> prestadores) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("prestador.txt", false))) {

            String separadorDeLinha = System.getProperty("line.separator");
            for (Prestador p : prestadores) {
                bw.write(p.getNome() + separadorDeLinha);
                bw.write(p.getHoras() + separadorDeLinha);
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar os Prestadores!");
            e.printStackTrace();
        }
    }

    @Override
    public Object filterByName(String nome) {
        Prestador p = null;
        List<Prestador> prestadores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("prestador.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                p = new Prestador();
                p.setNome(nome);
                p.cadastrarDisponibilidade(Integer.parseInt(br.readLine()));
                prestadores.add(p);
            }
            List<Prestador> result = prestadores.stream().filter(x -> x.getNome() == nome).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            System.out.println("Erro ao ler o Prestador!");
            e.printStackTrace();
        }
        return 0;
    }
}
