package sahak.sahakyan.maxmi.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import sahak.sahakyan.maxmi.validator.CheckEmail;
import sahak.sahakyan.maxmi.validator.Password;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 20)
    @Column(name = "username")
    private String username;
    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @Pattern(regexp = "\\+\\d{3}-\\d{2}-\\d{3}-\\d{3}", message = "Please use pattern +XXX-XX-XXX-XXX")
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotBlank
    @Column(name = "email", unique = true)
    @CheckEmail
    private String email;
    @NotBlank
    @Password
    @Column(name = "password")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private String password;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date")
    @EqualsAndHashCode.Exclude
    private String date;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    private List<Authority> authorities;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }
}