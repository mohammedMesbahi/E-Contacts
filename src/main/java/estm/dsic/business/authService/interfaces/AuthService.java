package estm.dsic.business.authService.interfaces;

import estm.dsic.beans.User;
import jakarta.ws.rs.core.Response;

public interface AuthService {
    Response login(User user);
    Response signup(User user);
    Response logout();
}
