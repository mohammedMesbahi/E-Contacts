package estm.dsic.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
@NamedQuery(name="Contact.contactsOfUser", query="SELECT c FROM Contact c WHERE c.user.id = :user_id")
public class Contact {
    @Id
    @Column(unique = true, nullable = false)
    private String name;
    private String phone;
    private String email;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public void setUser(User u) {
        this.user = u;
    }
    public User getUser() {
        return this.user;
    }

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String tel) {
        this.phone = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
