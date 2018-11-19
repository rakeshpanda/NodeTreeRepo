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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class NodeController {

    @Autowired
    private NodeRepo repo;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public void hello() {
        System.out.println("Hi ");
    }

    @RequestMapping("/child/{id}")
    public List<Node> getChildNodes(@PathVariable long id) {
        Date startTime= new Date();
        List<Node> result =    repo.getChildNodes(id);
        Date endTime= new Date();
        logger.info(" Execution time " + (startTime.getTime()-endTime.getTime())/1000);
        return result;
    }



    @Transactional
   @RequestMapping("/{nodeId}/replaceParent/{parentId}")
    public Node replaceParent(@PathVariable("nodeId") long nodeId, @PathVariable("parentId") long parentId) {
        Node currNode = repo.findById(nodeId).get();
       Node parNode = repo.findById(parentId).get();
        currNode.setParent(parNode);
        repo.save(currNode);
        return currNode;
    }
}
