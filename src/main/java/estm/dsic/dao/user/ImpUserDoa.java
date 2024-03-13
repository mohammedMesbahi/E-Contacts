package estm.dsic.dao.user;

import estm.dsic.beans.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.SQLIntegrityConstraintViolationException;

@RequestScoped
public class ImpUserDoa implements UserDoa {
    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @Override
    public User get(Long t) {
        return null;
    }

    @Override
    public User create(User user) {
        /* handle SQLIntegrityConstraintViolationException */
        try {
            em.persist(user);
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }
    public User get(String login, String password) {
        return em.createNamedQuery("User.findByLoginAndPassword", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }
}
