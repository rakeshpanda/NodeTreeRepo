package rp.demo.nodetree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rp.demo.nodetree.domain.entites.Node;
import rp.demo.nodetree.domain.repos.NodeRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

@SpringBootApplication
public class NodeTreeApplication implements CommandLineRunner {

    @Autowired
    private NodeRepo repo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {
        SpringApplication.run(NodeTreeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Node root = new Node("root");
        repo.save(root);
        root.setRoot_id(root.getId());

        Node a = new Node("a",  1, root.getRoot_id());
        Node b = new Node("b",  1, root.getRoot_id());
        a.setParent(root);
        b.setParent(root);

        repo.save(root);
        repo.save(a);
        repo.save(b);
        createNewChildTree(a);
        createNewChildTree(a);
        List<Node> nodeList = repo.findAll();
        logger.info("Total nodes " + nodeList.size());

    }

    private  void createNewChildTree(Node parent){
        if(parent.getHeight() == 9 ) return;
        List<Node> list = new ArrayList<Node>();

        IntStream.range(toIntExact(parent.getHeight()), 9).forEach(
                nbr -> {
                   list.add( new Node(parent.getName()+nbr, (parent.getHeight() +1), parent.getRoot_id()));
                }
        );
        list.forEach(e-> {
            e.setParent(parent);
            repo.save(e);
        });

        list.forEach(e-> {
           createNewChildTree(e);
        });

    }
}
