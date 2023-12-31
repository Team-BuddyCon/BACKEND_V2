package yapp.buddycon.app.user.adapter.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yapp.buddycon.app.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    private String nickname;

    @NotNull
    private String email;

    private String gender;

    private String age;
}
