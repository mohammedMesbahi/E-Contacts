package estm.dsic.controllers.contactsController;

import estm.dsic.beans.Contact;
import estm.dsic.dao.contact.ImpContactDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class ImpContactController implements ContactController {
    @Inject
    ImpContactDao contactDao;

    @Override
    public List<Contact> get(Long user_id) {
        List<Contact> contactList = contactDao.get(user_id);
        for (Contact contact : contactList) {
            contact.setUser(null);
        }
        return contactList;
    }

    @Override
    public Contact get(String name) {
        return null;
    }

    @Override
    public Contact create(Contact contact) {
        return contactDao.create(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactDao.update(contact);
    }

    @Override
    public Contact delete(Contact contact) {
        return null;
    }

    @Override
    public Contact delete(String name) {
        return contactDao.delete(name);
    }
}
