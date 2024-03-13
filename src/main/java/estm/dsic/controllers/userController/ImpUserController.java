package estm.dsic.controllers.userController;

import estm.dsic.beans.User;
import estm.dsic.dao.user.ImpUserDoa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@RequestScoped
@Transactional
public class ImpUserController implements UserController {
    @Inject
    private ImpUserDoa userDoa;

    @Override
    public User get(Long t) {
        return null;
    }

    @Override
    public User create(User user) {
        return userDoa.create(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User find(String login, String password) {
        return userDoa.get(login, password);
    }

    @Override
    public User find(String email) {
        return null;
    }

    @Override
    public User verify(User user) {
        return null;
    }
}
