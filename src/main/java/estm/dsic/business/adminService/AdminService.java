package estm.dsic.business.adminService;

import estm.dsic.beans.User;
import jakarta.ws.rs.core.Response;

public interface AdminService {
    Response verify(Long id);
    Response unVerify(Long id);
    Response suspend(Long id);
    Response unSuspend(Long id);

    Response delete(Long id);
    Response get();

}
