package yapp.buddycon.app.user.adapter.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import yapp.buddycon.app.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "deleted = false")
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long clientId;

    @NotNull
    private String nickname;

    @NotNull
    private String email;

    private String gender;

    private String age;

    private boolean deleted;

    public UserEntity(Long id, Long clientId, String nickname, String email, String gender,
        String age, boolean deleted) {
        this.id = id;
        this.clientId = clientId;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.deleted = deleted;
    }
}
