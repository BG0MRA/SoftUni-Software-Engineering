package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "constellations")
public class Constellation extends BaseEntity {

@Column(nullable = false, unique = true)
    private String name;
@Column(length = 255, nullable = false)
    private String description;
    @OneToMany(mappedBy = "constellation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Star> stars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }
}
