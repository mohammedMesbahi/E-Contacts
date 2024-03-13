package estm.dsic.controllers.userController;

import estm.dsic.beans.User;
import estm.dsic.controllers.shared.DefaultController;
import jakarta.transaction.Transactional;


@Transactional
public interface UserController extends DefaultController<User, Long>{
    User find(String email, String password);
    User find(String email);
    User verify(User user);
}
