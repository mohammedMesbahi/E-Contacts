package estm.dsic.controllers.userController;

import estm.dsic.beans.User;
import estm.dsic.dao.user.ImpUserDoa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Named
@RequestScoped
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
    public User delete(Long id) {
        return userDoa.delete(id);
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = userDoa.get();
        List<User> modifiedList = new Vector<>();
        for (User user : usersList) {
            if (!user.istAdmin()) {
                user.setAdmin(null);
                user.setPassword(null);
                modifiedList.add(user);
            }
        }
        return usersList;
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
    public User unVerify(Long id) {
        return userDoa.unVerify(id);
    }

    @Override
    public User suspend(Long id) {
        return userDoa.suspend(id);
    }

    @Override
    public User unSuspen(Long id) {
        return userDoa.unSuspend(id);
    }

    @Override
    public User verify(Long id) {
        return userDoa.verify(id);
    }

}
