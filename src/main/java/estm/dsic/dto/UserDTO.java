package estm.dsic.dto;

import estm.dsic.beans.User;

public class UserDTO {
    public Long id;
    public String login;

    public String password;
    public Boolean admin;
    public Boolean verified;
    public Boolean suspended;

    public UserDTO(User verifiedUser) {
        this.id = verifiedUser.getId();
        this.login = verifiedUser.getLogin();
        this.admin = verifiedUser.istAdmin();
        this.verified = verifiedUser.isVerified();
        this.suspended = verifiedUser.isSuspended();
    }
}
