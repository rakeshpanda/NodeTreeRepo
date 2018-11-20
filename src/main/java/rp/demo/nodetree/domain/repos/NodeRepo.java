package rp.demo.nodetree.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rp.demo.nodetree.domain.entites.Node;

import java.util.List;

@Repository

public interface NodeRepo extends JpaRepository<Node, Long> {
    List<Node> getAllNodes();
}
