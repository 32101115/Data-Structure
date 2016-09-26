import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
/**
 * Your implementation of a binary search tree.
 *
 * @author Jitae Kim
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        for (T temp : data) {
            add(temp);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null"
                    + " so can't be added");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            addHelper(data, root);
        }
    }
    /**
     * Add helper method, it add data recursively
     * @param root the root node
     * @param data the data to be added
     */
    private void addHelper(T data, BSTNode<T> root) {
        if (data.compareTo(root.getData()) > 0) {
            if (root.getRight() == null) {
                root.setRight(new BSTNode<T>(data));
                size++;
            } else {
                addHelper(data, root.getRight());
            }
        } else if (data.compareTo(root.getData()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new BSTNode<T>(data));
                size++;
            } else {
                addHelper(data, root.getLeft());
            }
        } else {
            return;
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null so"
                    + " can't be removed");
        } else if (size == 0) {
            throw new NoSuchElementException("no tree existed");
        } else if (size == 1) {
            T rv = root.getData();
            root.setData(null);
            root = null;
            size--;
            return rv;
        }
        return removeHelper(data, root, root);
    }
    /**
     * remove helper method, it remove data recursively
     * @param current the root node
     * @param data the data to be removed
     * @param prev storing previous node
     * @return returns data will be removed
     */
    private T removeHelper(T data, BSTNode<T> current, BSTNode<T> prev) {
        T rv;
        BSTNode<T> temp;
        BSTNode<T> tempRoot;
        if (data.compareTo(current.getData()) == 0) {
            if (root.getLeft() == null) {
                rv = current.getData();
                root = root.getRight();
                prev.setRight(null);
                size--;
            } else if (root.getRight() == null) {
                rv = current.getData();
                root = root.getLeft();
                prev.setLeft(null);
                size--;
            } else if (current.getLeft() != null
                    && current.getRight() != null) {
                rv = current.getData();
                tempRoot = current;
                removeReplace(tempRoot, current.getLeft(), current);
            } else if (current.getRight() == null
                    && current.getLeft() == null) {
                rv = current.getData();
                current.setData(null);
                size--;
                if (data.compareTo(prev.getData()) > 0) {
                    prev.setRight(null);
                } else {
                    prev.setLeft(null);
                }
            } else {
                if (current.getRight() == null) {
                    rv = current.getData();
                    if (prev.getData().compareTo(current.getData()) > 0) {
                        prev.setLeft(current.getLeft());
                        current.setLeft(null);
                    } else {
                        prev.setRight(current.getLeft());
                        current.setLeft(null);
                    }
                } else {
                    rv = current.getData();
                    if (prev.getData().compareTo(current.getData()) > 0) {
                        prev.setLeft(current.getRight());
                        current.setRight(null);
                    } else {
                        prev.setRight(current.getRight());
                        current.setRight(null);
                    }
                }
                size--;
            }
            return rv;
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() != null) {
                temp = current;
                return removeHelper(data, current.getRight(), temp);
            } else {
                throw new NoSuchElementException("the data is not found");
            }
        } else {
            if (current.getLeft() != null) {
                temp = current;
                return removeHelper(data, current.getLeft(), temp);
            } else {
                throw new NoSuchElementException("the data is not found");
            }
        }
    }
    /**
     * remove replace method, it returns the largest
     * element that is smaller than
     * the element being removed
     * @param tempRoot tempRoot node having same data as we look for
     * @param current the root node
     * @param prev the previous node
     */
    private void removeReplace(BSTNode<T> tempRoot,
                               BSTNode<T> current, BSTNode<T> prev) {
        if (current.getRight() != null) {
            removeReplace(tempRoot, current.getRight(), current);
        } else if (prev.getData().compareTo(current.getData()) > 0) {
            tempRoot.setData(current.getData());
            current.setData(null);
            size--;
            if (current.getLeft() == null) {
                prev.setLeft(null);
            } else if (current.getRight() == null) {
                prev.setLeft(current.getLeft());
            }
        } else if (prev.getData().compareTo(current.getData()) < 0) {
            tempRoot.setData(current.getData());
            current.setData(null);
            size--;
            if (current.getLeft() == null) {
                prev.setRight(null);
            } else if (current.getRight() == null) {
                prev.setRight(current.getLeft());
            }
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        } else if (size == 0) {
            throw new NoSuchElementException("the data is not founded");
        } else if (size == 1) {
            if (data.equals(root.getData())) {
                return root.getData();
            } else {
                throw new NoSuchElementException("the data is not founded");
            }
        } else {
            return getHelper(data, root);
        }
    }
    /**
     * get helper method
     * @param data the data to be searched
     * @param curr the root node
     * @return returns data
     */
    private T getHelper(T data, BSTNode<T> curr) {
        if (data.compareTo(curr.getData()) == 0) {
            return curr.getData();
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() != null) {
                return getHelper(data, curr.getRight());
            } else {
                throw new NoSuchElementException("the data is not founded");
            }
        } else {
            if (curr.getLeft() != null) {
                return getHelper(data, curr.getLeft());
            } else {
                throw new NoSuchElementException("the data is not founded");
            }
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        } else if (size == 0) {
            return false;
        } else {
            return containsHelper(data, root);
        }
    }
    /**
     * helper method for containsHelper
     * @param data data looking for
     * @param current the root node
     * @return true or false
     */
    private boolean containsHelper(T data, BSTNode<T> current) {
        boolean rv;
        if (data.compareTo(current.getData()) == 0) {
            rv = true;
        } else if (data.compareTo(current.getData()) > 0) {
            if (current.getRight() != null) {
                return containsHelper(data, current.getRight());
            } else {
                rv = false;
            }
        } else {
            if (current.getLeft() != null) {
                return containsHelper(data, current.getLeft());
            } else {
                rv = false;
            }
        }
        return rv;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        return preorderHelper(list, root);
    }
    /**
     * helper method for preorder
     * @param list list returned
     * @param current the root node
     * @return returns list
     */
    private List<T> preorderHelper(List<T> list, BSTNode<T> current) {
        if (current != null) {
            list.add(current.getData());
            preorderHelper(list, current.getLeft());
            preorderHelper(list, current.getRight());
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        return postorderHelper(list, root);
    }

    /**
     * helper method for postorder
     * @param list list returned
     * @param current the root node
     * @return returns list
     */
    private List<T> postorderHelper(List<T> list, BSTNode<T> current) {
        if (current != null) {
            postorderHelper(list, current.getLeft());
            postorderHelper(list, current.getRight());
            list.add(current.getData());
        }
        return list;
    }
    @Override
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        return inorderHelper(list, root);
    }
    /**
     * helper method for inorder
     * @param list list returned
     * @param current the root node
     * @return returns list
     */
    private List<T> inorderHelper(List<T> list, BSTNode<T> current) {
        if (current != null) {
            inorderHelper(list, current.getLeft());
            list.add(current.getData());
            inorderHelper(list, current.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>();
        return levelorderHelper(list, root);
    }
    /**
     * helper method for levelorder
     * @param list list returned
     * @param current the root node
     * @return returns list
     */
    private List<T> levelorderHelper(List<T> list, BSTNode<T> current) {
        Queue<BSTNode<T>> q = new LinkedList<>();
        if (current != null) {
            q.add(current);
        }
        while (!q.isEmpty()) {
            BSTNode<T> temp = q.remove();
            list.add(temp.getData());
            if (temp.getLeft() != null) {
                q.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                q.add(temp.getRight());
            }
        }
        return list;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        if (size == 1) {
            return 0;
        } else {
            return heightHelper(root);
        }
    }
    /**
     * inspect whole tree
     * @param node the data to be searched
     * @return height
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            int rv = Math.max(
                    heightHelper(node.getLeft()),
                    heightHelper(node.getRight()));
            return rv + 1;
        }
    }


    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}

