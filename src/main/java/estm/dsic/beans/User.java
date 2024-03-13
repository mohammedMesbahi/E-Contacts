package estm.dsic.beans;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.annotation.Repeatable;

@Entity
@Table(name = "user")
/* named query findByLoginAndPassword*/
@NamedQuery(name = "User.findByLoginAndPassword", query = "SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
public class User implements Serializable{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @Column(unique = true)
    private String login;
    private String password;
    @Column(name = "isAdmin",columnDefinition = "BOOLEAN default 0")
    private Boolean admin;
    @Column(name = "isVerified",columnDefinition = "BOOLEAN default 0")

    private Boolean verified;
    @Column(name = "isSuspended",columnDefinition = "BOOLEAN default 0")
    private Boolean suspended;


    public User() {
        admin = false;
        verified = false;
        suspended = false;
    }
    public Boolean getAdmin() {
        return admin;
    }

    public Boolean getVerified() {
        return verified;
    }

    public Boolean getSuspended() {
        return suspended;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean istAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }
}