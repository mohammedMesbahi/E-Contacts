package estm.dsic.business.contactService;

import estm.dsic.beans.Contact;
import jakarta.ws.rs.core.Response;

public interface ContactService {

    Response get(Long t);
    Response create(Contact contact);
    Response update(Contact contact);
    Response delete(String name);
    Response get();
}
