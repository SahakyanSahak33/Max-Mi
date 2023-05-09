package sahak.sahakyan.maxmi.entity;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_authority")
public class UserAuthority {

    @EmbeddedId
    private UserAuthorityId id = new UserAuthorityId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("authorityId")
    private Authority authority;

}
