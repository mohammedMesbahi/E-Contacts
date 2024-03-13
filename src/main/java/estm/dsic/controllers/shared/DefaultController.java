package estm.dsic.controllers.shared;

public interface DefaultController<T, L>{
    T get(L t);

    T create(T t);

    T update(T t);

    T delete(T t);
}
