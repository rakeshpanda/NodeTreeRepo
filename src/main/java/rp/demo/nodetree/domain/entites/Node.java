package rp.demo.nodetree.domain.entites;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(indexes = {@Index(name = "IDX_height", columnList = "id")})
@NoArgsConstructor
@NamedQuery(name = "Node.getAllNodes",
        query = "SELECT distinct n FROM Node n left join fetch n.childs "
)
public @Data
class Node implements Comparable<Node> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;

    private long height;

    private long root_id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Node parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Node> childs;

    public Node(String name, long height, long rootId) {
        this.name = name;
        this.height = height;
        this.root_id = rootId;
    }

    public Node(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "-" + getId();
    }


    @Override
    public int compareTo(Node o) {
        return Objects.equals(getId(), o.getId()) ? 0 : (getId() > o.getId() ? 1 : -1);
    }
}
