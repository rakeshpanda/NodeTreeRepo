# NodeTreeRepo

This application creates a node tree using in memory h2 db.
We have a root node (only one) and several children nodes, each one with its own children as well. It's a tree-based structure.
Something like:

          root

        /    \

       a      b

       |

       c
Each node has the following info:

1) node identification

2) who is the parent node

3) who is the root node

4) the height of the node. In the above example, height(root) = 0 and height(a) == 1.

There are two HTTP APIs that will serve the two basic operations:


1) Get all children nodes of a given node (the given node can be anyone in the tree structure).

url:  /childs/:id (id of the given node)

2) Change the parent node of a given node (the given node can be anyone in the tree structure).

url:  /upate/id1/parent/id2   : {id1 ->id of given node}  {id2 ->id of parent node}

Application is taking a default heiight of 6 for creating dummy data. Each parent is created with 9 immediate childs. To increase the height pass command line argument node.height=x



