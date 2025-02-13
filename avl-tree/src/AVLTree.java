class AVLNode {
    int height;
    int key;
    AVLNode left, right;

    public AVLNode(int item) {
        height = 1;
        key = item;
        left = right = null;
    }
}

class AVLTree {
    // Get height of node
    AVLNode root;
    public AVLTree() {
        root = null;
    }

    private int height(AVLNode node) {
        if (node == null) return 0;
        return node.height;
    }

    // Get balance factor
    private int getBalance(AVLNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    // Update height
    private void updateHeight(AVLNode node) {
        if (node == null) return;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    // Rotate right
    private AVLNode rotateRight(AVLNode y) {
        // Implement right rotation
        // current becomes right child of left child
        // any left child's right children becomes left children of parent-now-child
        AVLNode newRoot = y.left;
        AVLNode existingRights = newRoot.right;
        y.left = null;
        newRoot.right = y;
        y.left = existingRights;
        updateHeight(y);
        updateHeight(existingRights);
        updateHeight(newRoot);

        return newRoot;
    }

    // Rotate left
    private AVLNode rotateLeft(AVLNode x) {
        // Implement left rotation
        // current becomes left child of right child
        // any right child's left children becomes right children of parent-now-child
        AVLNode newRoot = x.right; //11
        AVLNode existingLefts = newRoot.left; // 0
        x.right = null;
        newRoot.left = x;
        System.out.println(newRoot.left.key);
        x.right = existingLefts;
        updateHeight(x);
        updateHeight(existingLefts);
        updateHeight(newRoot);

        return newRoot;
    }

    // Insert

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private AVLNode insertRec(AVLNode node, int key) {
        // TODO: Implement AVL insert with balancing
        if(node == null) { // should only catch empty root node
            return new AVLNode(key);
        }

        if(key < node.key) { // go left
            if(node.left == null) { // insert here
                node.left = new AVLNode(key);
                updateHeight(node);
                return node;
            } else {
                node.left = insertRec(node.left, key);
                updateHeight(node);

            }
        } else if(key > node.key) { // go right
            if(node.right == null) {
                node.right = new AVLNode(key);
                updateHeight(node);
                return node;
            } else {
                node.right = insertRec(node.right, key);// 10
                updateHeight(node);
            }
        }


        // check balance factors
        System.out.println(getBalance(node) + " " + node.key);
        if(getBalance(node) < -1) { // right heavy
            // check right child balance factor
            if(getBalance(node.right) <= 0) { // if child is right heavy or balanced, left rotation
                node = rotateLeft(node);
            } else { // left heavy, right left rotation
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
                updateHeight(node);
            }
        } else if (getBalance(node) > 1) { // left heavy
            if(getBalance(node.left) >= 0) { // if child is left heavy or balanced, right rotation
                node = rotateRight(node);
            } else { // right heavy, left right rotation
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
                updateHeight(node);
            }
        }

        return node;
    }

    // Delete
    public void delete(int key) {
        root = deleteRec((AVLNode)root, key);
    }

    private AVLNode deleteRec(AVLNode node, int key) {
        // TODO: Implement AVL delete with balancing
        return null;
    }
}