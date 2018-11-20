package rp.demo.nodetree.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rp.demo.nodetree.domain.entites.Node;
import rp.demo.nodetree.domain.repos.NodeRepo;

import javax.transaction.Transactional;
import java.util.*;

@RestController
public class NodeController {

    @Autowired
    private NodeRepo repo;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public void hello() {
        System.out.println("Hi ");
    }

    @RequestMapping("/childs/{id}")
    public List<Node> getChildNodes(@PathVariable long id) {
        Date startTime = new Date();
        List<Node> result = repo.getAllNodes();
        Map<Node, List<Node>> tree = new TreeMap<Node, List<Node>>();
        List<Node> opList = new ArrayList<Node>();
        for (Node n : result) {
            if (n.getParent() == null) {
                continue;
            }
            if (!tree.containsKey(n.getParent()))
                tree.put(n.getParent(), new ArrayList<Node>());
            tree.get(n.getParent()).add(n);

        }

        for (Map.Entry<Node, List<Node>> elem : tree.entrySet()) {
            if (elem.getKey().getId() == id || opList.contains(elem.getKey())) {
                opList.addAll(elem.getValue());
            }

        }


        logger.info("size of child list " + opList.size());
        Date endTime = new Date();
        logger.info(" Execution time " + (startTime.getTime() - endTime.getTime()) / 1000);
        return opList;
    }


    @Transactional
    @RequestMapping("/update/{nodeId}/parent/{parentId}")
    public Node replaceParent(@PathVariable("nodeId") long nodeId, @PathVariable("parentId") long parentId) {
        Node currNode = repo.findById(nodeId).get();
        Node parNode = repo.findById(parentId).get();
        currNode.setParent(parNode);
        repo.save(currNode);
        return currNode;
    }
}