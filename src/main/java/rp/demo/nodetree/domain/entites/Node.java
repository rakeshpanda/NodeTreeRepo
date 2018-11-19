package rp.demo.nodetree.domain.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "Node.getChildNodes",
        query = "SELECT n FROM Node n where height > :id "
)
@NoArgsConstructor
public @Data class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;

    private long height;

    private long root_id;

    @ManyToOne
    @JoinColumn(name="parent_id")

    private Node parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private List<Node> childs;

    public Node(String name,  long height, long rootId) {
        this.name = name;
        this.height = height;
        this.root_id = rootId;
    }

    public Node(String name) {
        this.name = name;

    }

}
