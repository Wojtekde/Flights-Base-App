package project.sda.domain.user;

import javax.persistence.*;

@Entity
@Table (name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name= "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private int role;

    @Column(name = "blocked")
    private int blocked;

}
