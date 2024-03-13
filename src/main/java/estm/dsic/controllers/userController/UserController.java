package estm.dsic.controllers.userController;

import estm.dsic.beans.User;
import estm.dsic.controllers.shared.DefaultController;
import jakarta.transaction.Transactional;

import java.util.List;


public interface UserController extends DefaultController<User, Long>{
    User find(String email, String password);
    User find(String email);

    User verify(Long id);
    User unVerify(Long id);

    User suspend(Long id);
    User unSuspen(Long id);
    User delete(Long id);
    List<User> getAll();
}
