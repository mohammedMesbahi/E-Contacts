package estm.dsic.dao.user;

import estm.dsic.beans.User;
import estm.dsic.dao.CrudAble;

public interface UserDoa extends CrudAble<User, Long>{
    User verify(Long id);

    User unVerify(Long id);

    User suspend(Long id);
    User unSuspend(Long id);
    User delete(Long id);
}
