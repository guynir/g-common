package gcommon.collections;

/**
 * Implementation of tree data structure (parent with multiple children), where each node has an associated data, and
 * each child can be accessed via a unique key within its context.
 *
 * @author Guy Raz Nir
 * @since 26/06/2017
 */
public class Tree<K, D> {

    /**
     * Root node of the tree.
     */
    private final TreeNode<K, D> rootNode = new TreeNode<>();

    /**
     * Class constructor.
     */
    public Tree() {
    }

    /**
     * Find a node based on path to it.
     *
     * @param path Path to node. Each element of the array represents a key.
     * @return Matching node or {@code null} if path does not exists.
     */
    public TreeNode<K, D> getNode(K[] path) {
        return rootNode.getNode(path);
    }

    public TreeNode<K, D> getRoot() {
        return rootNode;
    }

}
