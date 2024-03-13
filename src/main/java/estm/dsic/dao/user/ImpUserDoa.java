package estm.dsic.dao.user;

import estm.dsic.beans.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
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
        em.persist(user);
        return user;
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public List<User> get() {
        return em.createQuery("select u from User u where u.admin=false", User.class)
                .getResultList();
    }

    @Override
    public User delete(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        em.remove(user);
        return user;
    }

    public User get(String login, String password) {
        try {
            return em.createNamedQuery("User.findByLoginAndPassword", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User verify(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        user.setVerified(true);
        return em.merge(user);
    }

    @Override
    public User unVerify(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        user.setVerified(false);
        return em.merge(user);
    }

    @Override
    public User suspend(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        user.setSuspended(true);
        return em.merge(user);
    }

    @Override
    public User unSuspend(Long id) {
        User user = em.find(User.class, id);
        if (user == null) {
            return null;
        }
        user.setSuspended(false);
        return em.merge(user);
    }
}
