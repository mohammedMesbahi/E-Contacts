package estm.dsic.controllers.contactsController;

import estm.dsic.beans.Contact;
import estm.dsic.controllers.sharedController.DefaultController;

import java.util.List;

public interface ContactController extends DefaultController<Contact, String>{
    public List<Contact> get(Long user_id);
    public Contact get(String name);
    public Contact delete(String name);
}
