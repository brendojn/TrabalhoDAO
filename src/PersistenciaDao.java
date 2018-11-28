import java.util.List;

public interface PersistenciaDao<T, K> {
    public T get(K chave);
    public void add(T g);
    public void delete(T g);
    public void update(T g);
    public List<T> getAll();
}
