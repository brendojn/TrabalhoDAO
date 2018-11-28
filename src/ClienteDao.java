import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDao implements PersistenciaDao<Cliente, String>, Filtro{



    @Override
    public Cliente get(String chave) {
        Cliente cliente = null;
        Cliente c = null;

        try (BufferedReader br = new BufferedReader(new FileReader("cliente.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                c = new Cliente();
                c.setNome(nome);
                c.setCpf(Long.parseLong(br.readLine()));

                if (chave.equals(c.getNome())) {
                    cliente = c;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO ao ler o cliente: '" + c.getNome());
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void add(Cliente g) {
        Cliente cliente = g;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cliente.txt", true))) {
            String tab = System.getProperty("line.separator");
            bw.write(cliente.getNome() + tab);
            bw.write(cliente.getCpf() + tab);
            bw.flush();

        } catch (Exception e) {
            System.out.println("ERRO ao inserir cliente '" + cliente.getNome());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Cliente g) {
        List<Cliente> clientes = new ArrayList<>();
        int index = clientes.indexOf(g);
        if (index != -1) {
            clientes.remove(index);
        }
        saveToFile(clientes);
    }

    @Override
    public void update(Cliente g) {
        List<Cliente> clientes = getAll();
        int index = clientes.indexOf(g);
        if (index != -1) {
            clientes.set(index, g);
        }
        saveToFile(clientes);
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente c = null;
        try (BufferedReader br = new BufferedReader(new FileReader("cliente.txt"))) {
            String nome;

            while ((nome = br.readLine()) != null) {
                c = new Cliente();
                c.setNome(nome);
                c.setCpf(Long.parseLong(br.readLine()));
                clientes.add(c);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler os Clientes!");
            e.printStackTrace();
        }
        return clientes;
    }

    private void saveToFile(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("cliente.txt", false))) {

            String separadorDeLinha = System.getProperty("line.separator");
            for (Cliente c : clientes) {
                bw.write(c.getNome() + separadorDeLinha);
                bw.write(c.getCpf() + separadorDeLinha);
                bw.flush();
            }
        } catch (Exception e) {
            System.out.println("ERRO ao gravar os Clientes!");
            e.printStackTrace();
        }
    }

    @Override
    public Object filterByName(String nome) {
        Cliente c = null;
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("prestador.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                c = new Cliente();
                c.setNome(nome);
                c.setCpf(Integer.parseInt(br.readLine()));
                clientes.add(c);
            }
            List<Cliente> result = clientes.stream().filter(x -> x.getNome() == nome).collect(Collectors.toList());
            return result;
        } catch (Exception e) {
            System.out.println("Erro ao ler o Cliente!");
            e.printStackTrace();
        }
        return 0;
    }
}
