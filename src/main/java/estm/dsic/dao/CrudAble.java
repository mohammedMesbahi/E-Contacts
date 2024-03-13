package estm.dsic.dao;

public interface CrudAble<T, L> {
    T get(L t);

    T create(T t) throws Exception;

    T update(T t);

    T delete(T t);
}