class AVLNode extends Node {
    int height;

    public AVLNode(int item) {
        super(item);
        height = 1;
    }
}

class AVLTree extends BinarySearchTree {
    // Get height of node
    private int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Get balance factor
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height((AVLNode)node.left) - height((AVLNode)node.right);
    }

    // Update height
    private void updateHeight(AVLNode node) {
        if (node == null) return;
        node.height = Math.max(height((AVLNode)node.left), height((AVLNode)node.right)) + 1;
    }

    // Rotate right
    private AVLNode rotateRight(AVLNode y) {
        // Implement right rotation
        // current becomes right child of left child
        // any left child's right children becomes left children of parent-now-child
        Node newRoot = y.left;
        Node existingRights = newRoot.right;
        y.left = null;
        newRoot.right = y;
        y.left = existingRights;
        updateHeight(y);
        updateHeight((AVLNode) existingRights);
        updateHeight((AVLNode) newRoot);

        return (AVLNode)newRoot;
    }

    // Rotate left
    private AVLNode rotateLeft(AVLNode x) {
        // Implement left rotation
        // current becomes left child of right child
        // any right child's left children becomes right children of parent-now-child
        Node newRoot = x.right;
        Node existingLefts = newRoot.left;
        x.right = null;
        newRoot.left = x;
        x.right = existingLefts;
        updateHeight(x);
        updateHeight((AVLNode) existingLefts);
        updateHeight((AVLNode) newRoot);

        return (AVLNode) newRoot;
    }

    // Insert
    @Override
    public void insert(int key) {
        root = insertRec((AVLNode)root, key);
    }

    private AVLNode insertRec(AVLNode node, int key) {
        // TODO: Implement AVL insert with balancing
        if(node == null) { // should only catch empty root node
            return (AVLNode) new Node(key);
        }
        AVLNode currentNode = null;
        if(key < node.key) { // go left
            if(node.left == null) { // insert here
                node.left = new AVLNode(key);
                return node;
            } else {
                currentNode = (AVLNode) insertRec(node.left, key);
                updateHeight(currentNode);
            }
        } else if(key > node.key) { // go right
            if(node.right == null) {
                node.right = new AVLNode(key);
                return node;
            } else {
                currentNode = (AVLNode) insertRec(node.right, key);
                updateHeight(currentNode);
            }
        }
        // need to check for when key == existing key??
        // check balance factors
        if(getBalance(currentNode) > 1) { // right heavy
            // check right child balance factor
            if(getBalance((AVLNode) currentNode.right) >= 0) { // if child is right heavy or balanced, left rotation
                currentNode = rotateLeft(currentNode);
            } else { // left heavy, right left rotation
                currentNode = rotateRight(currentNode);
                currentNode = rotateLeft(currentNode);
            }
        } else if (getBalance(currentNode) < -1) { // left heavy
            if(getBalance((AVLNode) currentNode.left) <= 0) { // if child is left ehavy or balanced, right rotation
                currentNode = rotateRight(currentNode);
            } else { // right heavy, left right rotation
                currentNode = rotateLeft(currentNode);
                currentNode = rotateRight(currentNode);
            }
        }

        return currentNode;
    }

    // Delete
    @Override
    public void delete(int key) {
        root = deleteRec((AVLNode)root, key);
    }

    private AVLNode deleteRec(AVLNode node, int key) {
        // TODO: Implement AVL delete with balancing
        return null;
    }
}