package estm.dsic.dao.contact;

import estm.dsic.beans.Contact;
import estm.dsic.dao.CrudAble;

import java.util.List;

public interface ContactDao extends CrudAble<Contact,String> {
    List<Contact> get(Long user_id);


    Contact delete(String name);
}
