package pl.rejniak.claim.domain.application.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rejniak.claim.domain.application.State;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "applications")
public class AppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String content;
    private State state;
    private String reason;

    public AppEntity(String name, String content, State state) {
        this.name = name;
        this.content = content;
        this.state = state;
    }

    public AppEntity(String name, String content, State state, String reason) {
        this.name = name;
        this.content = content;
        this.state = state;
        this.reason = reason;
    }
}
