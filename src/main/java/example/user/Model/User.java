package example.user.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // ✅ Auto-generated primary key

    @Column(unique = true)
    private String username; ;
    private String password;
    private int wins=0;
    private String role;

}
