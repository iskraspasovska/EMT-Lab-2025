package mk.finki.ukim.mk.emtlab.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Condition condition;

    private Boolean available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Copy() {
    }

    public Copy(Long id, Condition condition, Boolean available) {
        this.id = id;
        this.condition = condition;
        this.available = available;
    }

    public Copy(Condition condition, Boolean available) {
        this.condition = condition;
        this.available = available;
    }
}
