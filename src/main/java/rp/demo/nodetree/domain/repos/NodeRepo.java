package rp.demo.nodetree.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rp.demo.nodetree.domain.entites.Node;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface NodeRepo extends JpaRepository<Node, Long> {
    List<Node> getChildNodes(@Param("id") Long id);


}
