package sahak.sahakyan.maxmi.entity;

import lombok.*;
import sahak.sahakyan.maxmi.validator.CheckEmail;
import sahak.sahakyan.maxmi.validator.Password;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @NotBlank(message = "User's username cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "username")
    String username;
    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "first_name")
    String firstName;
    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "second_name")
    String lastName;
    @NotBlank
    @Pattern(regexp = "\\+\\d{3}-\\d{2}-\\d{3}-\\d{3}", message = "Please use pattern +XXX-XXX-XXX")
    @Column(name = "phone_number")
    String phoneNumber;
    @NotBlank
    @CheckEmail
    @Column(name = "email", unique = true)
    String email;
    @NotBlank
    @Password
    @Column(name = "password")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    String password;
    @Column(name = "gender")
    String gender;
    @Column(name = "date")
    @EqualsAndHashCode.Exclude
    String date;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    private Set<Authority> authorities = new HashSet<>();
}
