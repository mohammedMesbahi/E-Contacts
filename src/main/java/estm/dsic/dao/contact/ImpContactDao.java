package estm.dsic.dao.contact;

import estm.dsic.beans.Contact;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Named
@RequestScoped
@Transactional
public class ImpContactDao implements ContactDao {
    @PersistenceContext(name = "jpa-unit")
    EntityManager em;

    @Override
    public List<Contact> get(Long user_id) {
        /* use named querty contacts of a suer*/
        return em.createNamedQuery("Contact.contactsOfUser", Contact.class)
                .setParameter("user_id", user_id).getResultList();
    }

    @Override
    public Contact get(String name) {
        return em.find(Contact.class, name);
    }

    @Override
    public Contact create(Contact contact) {
        em.persist(contact);
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact contact1 = em.find(Contact.class, contact.getName());
        if (contact1 == null) return null;
        if (contact.getPhone() != null)
            contact1.setPhone(contact.getPhone());
        if (contact.getEmail() != null)
            contact1.setEmail(contact.getEmail());
        if (contact.getAddress() != null)
            contact1.setAddress(contact.getAddress());
        em.merge(contact1);
        return contact1;
    }

    @Override
    public Contact delete(Contact contact) {
        return null;
    }

    @Override
    public Contact delete(String name) {
        Contact contact = em.find(Contact.class, name);
        if (contact == null) return null;
        em.remove(contact);
        return contact;
    }

    @Override
    public List<Contact> get() {
        return null;
    }

}
