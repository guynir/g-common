package gcommon.collections;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Represents a single node in the tree. Each such node contains associated data (optional) and children (optional).
 *
 * @param <K> Generic type of node's key.
 * @param <D> Generic type of node's data.
 */
public class TreeNode<K, D> {

    /**
     * Data associated the current node.
     */
    private D data;

    /**
     * Children of this node.
     */
    private final TreeMap<K, TreeNode<K, D>> children = new TreeMap<>();

    /**
     * Class constructor.
     */
    public TreeNode() {
    }

    /**
     * Class constructor.
     *
     * @param data Optional data to associate with this node.
     */
    public TreeNode(D data) {
        this.data = data;
    }

    /**
     * @return Return associated data. May be {@code null}.
     */
    public D getData() {
        return data;
    }

    /**
     * Associate new data with this node.
     *
     * @param data New data to associate. May be {@code null}.
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * Add a new child to this node.
     *
     * @param key  Key that identifies the child. If this key already exists, it is overridden.
     * @param data Data to set for the new node.
     * @return Newly created node.
     */
    public TreeNode<K, D> add(K key, D data) {
        TreeNode<K, D> newNode = new TreeNode<>(data);
        children.put(key, newNode);
        return newNode;
    }

    /**
     * Fetch node based on given <i>key</i>.
     *
     * @param key Key of node.
     * @return Node matching given <i>key</i> or {@code null} if no such key exist.
     */
    public TreeNode<K, D> getNode(K key) {
        return this.children.get(key);
    }

    /**
     * Fetch node given a path.
     *
     * @param path Path (set of keys) to target node.
     * @return Node matching given <i>path</i> or {@code null} if no such node exists.
     */
    public TreeNode<K, D> getNode(K[] path) {
        TreeNode<K, D> current = null;
        if (path != null && path.length > 0) {
            int index = 1;
            current = getNode(path[0]);
            while (current != null && index < path.length) {
                current = current.getNode(path[index]);
                index++;
            }
        }

        return current;
    }

    /**
     * @return List of children's node data. Will exclude {@code null} values.
     */
    public List<D> getChildrenData() {
        return this.children.values().stream().map(TreeNode::getData).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
