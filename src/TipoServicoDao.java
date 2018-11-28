import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TipoServicoDao implements PersistenciaDao<TipoServico, String>, Filtro{
    
    @Override
    public TipoServico get(String chave) {
        TipoServico servico = null;
        TipoServico s = null;

        try (BufferedReader br = new BufferedReader(new FileReader("servico.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                s = new TipoServico();
                s.setNome(nome);

                if (chave.equals(s.getNome())) {
                    servico = s;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o prestador: '" + s.getNome());
            e.printStackTrace();
        }
        return servico;
    }

    @Override
    public void add(TipoServico g) {
        TipoServico servico = g;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("servico.txt", true))) {
            String tab = System.getProperty("line.separator");
            bw.write(servico.getNome() + tab);
            bw.flush();

        } catch (Exception e) {
            System.out.println("ERRO ao inserir serviços '" + servico.getNome());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TipoServico g) {
        List<TipoServico> servicos = new ArrayList<>();
        int index = servicos.indexOf(g);
        if (index != -1) {
            servicos.remove(index);
        }
        saveToFile(servicos);
    }

    @Override
    public void update(TipoServico g) {
        List<TipoServico> servicos = getAll();
        int index = servicos.indexOf(g);
        if (index != -1) {
            servicos.set(index, g);
        }
        saveToFile(servicos);
    }

    @Override
    public List<TipoServico> getAll() {
        List<TipoServico> servicos = new ArrayList<>();
        TipoServico s = null;
        try (BufferedReader br = new BufferedReader(new FileReader("servico.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                s = new TipoServico();
                s.setNome(nome);
                servicos.add(s);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler os Servicos!");
            e.printStackTrace();
        }
        return servicos;
    }

    private void saveToFile(List<TipoServico> servicos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("servico.txt", false))) {

            String separadorDeLinha = System.getProperty("line.separator");
            for (TipoServico s : servicos) {
                bw.write(s.getNome() + separadorDeLinha);
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar os Servicos!");
            e.printStackTrace();
        }
    }

    @Override
    public Object filterByName(String nome) {
        TipoServico s = null;
        List<TipoServico> servicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("servico.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                s = new TipoServico();
                s.setNome(nome);
                servicos.add(s);
            }
            List<TipoServico> result = servicos.stream().filter(x -> x.getNome() == nome).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            System.out.println("Erro ao ler o Servico!");
            e.printStackTrace();
        }
        return 0;
    }
}
