package estm.dsic.beans;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Contact {
    @Id
    private String nom;
    private String tel;
    private String email;
    private String adresse;
    @ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
}
