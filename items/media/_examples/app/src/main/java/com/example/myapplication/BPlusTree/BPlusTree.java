package com.example.myapplication.BPlusTree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @param <K> The key type which must be Comparable (e.g., Integer, String).
 * @param <E> The element type stored in the tree nodes.
 * @author Linxiang Hu u7633783
 * A BPlusTree class that implements a generic B+ tree data structure.
 * The tree allows for efficient insertion, deletion, and range queries.
 */
public class BPlusTree<K extends Comparable<K>, E> {

    // Maximum number of keys a node can hold before it must split.
    private final int OVERFLOW_BOUND;

    // Minimum number of keys a node must hold unless it is the root.
    private final int UNDERFLOW_BOUND;

    // Root of the B+ tree.
    private BPlusTreeNode root;

    /**
     * Constructor specifying the order of the tree.
     *
     * @param order The maximum number of children a node can have.
     */
    public BPlusTree(int order) {
        if (order < 3) {
            throw new IllegalArgumentException("The order of BPlus Tree must be greater than or equal to 3");
        }
        this.OVERFLOW_BOUND = order - 1;
        this.UNDERFLOW_BOUND = OVERFLOW_BOUND / 2;
    }

    /**
     * Default constructor initializing the tree with default overflow and underflow bounds.
     */
    public BPlusTree() {
        this.OVERFLOW_BOUND = 8;
        this.UNDERFLOW_BOUND = OVERFLOW_BOUND / 2;
    }

    /**
     * Inserts a key-value pair into the B+ tree.
     *
     * @param entry The key to insert.
     * @param value The value associated with the key.
     */
    public void insert(K entry, E value) {

        if (root == null) {
            root = new BPlusTreeLeafNode(asList(entry), asList(asSet(value)));
            return;
        }


        BPlusTreeNode newChildNode = root.insert(entry, value);
        if (newChildNode != null) {
            K newRootEntry = newChildNode.getClass().equals(BPlusTreeLeafNode.class)
                    ? newChildNode.entries.get(0)
                    : ((BPlusTreeNonLeafNode) newChildNode).findLeafEntry(newChildNode);
            root = new BPlusTreeNonLeafNode(asList(newRootEntry), asList(root, newChildNode));
        }

    }

    /**
     * Queries the tree for all values associated with a specific key.
     *
     * @param entry The key to query.
     * @return A list of values associated with the key. Returns an empty list if key is not found.
     */
    public List<E> query(K entry) {
        if (root == null) {
            return Collections.emptyList();
        }
        return root.query(entry);
    }


    /**
     * Retrieves all values stored in the B+ tree.
     *
     * @return A list of all values in the tree.
     */
    public List<E> queryAllData() {
        List<E> allData = new ArrayList<>();
        BPlusTreeLeafNode current = findFirstLeaf();
        while (current != null) {
            for (Set<E> dataSet : current.data) {
                allData.addAll(dataSet);
            }
            current = current.next;
        }
        return allData;
    }

    /**
     * Finds the first leaf node in the B+ tree, used for full tree traversals.
     *
     * @return The first leaf node of the tree, or null if the tree is empty.
     */
    private BPlusTreeLeafNode findFirstLeaf() {
        BPlusTreeNode node = root;
        if (node == null) return null;
        while (!(node instanceof BPlusTreeLeafNode)) {
            node = ((BPlusTreeNonLeafNode) node).children.get(0);
        }
        return (BPlusTreeLeafNode) node;
    }

    /**
     * Performs a range query, returning all values associated with keys in the specified range.
     *
     * @param startInclude The start key of the range (inclusive).
     * @param endExclude   The end key of the range (exclusive).
     * @return A list of values whose keys fall within the specified range.
     */
    public List<E> rangeQuery(K startInclude, K endExclude) {
        if (startInclude.compareTo(endExclude) >= 0) {
            throw new IllegalArgumentException("invalid range");
        }

        if (root == null) {
            return Collections.emptyList();
        }

        return root.rangeQuery(startInclude, endExclude);
    }

    /**
     * Updates the value associated with a specific key.
     *
     * @param entry    The key whose value is to be updated.
     * @param oldValue The old value to replace.
     * @param newValue The new value to replace the old value with.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(K entry, E oldValue, E newValue) {
        if (root == null) {
            return false;
        }

        return root.update(entry, oldValue, newValue);
    }

    /**
     * Removes a specific value associated with a key in the tree.
     *
     * @param entry The key whose value is to be removed.
     * @param value The value to be removed.
     * @return true if the value was removed, false otherwise.
     */
    public boolean remove(K entry, E value) {
        if (root == null) {
            return false;
        }

        RemoveResult removeResult = root.remove(entry, value);
        if (!removeResult.isRemoved) {
            return false;
        }

        if (root.entries.isEmpty()) {
            this.handleRootUnderflow();
        }

        return true;
    }

    /**
     * Removes all values associated with a key in the tree.
     *
     * @param entry The key to remove from the tree.
     * @return true if the key and its associated values were removed, false otherwise.
     */
    public boolean remove(K entry) {
        if (root == null) {
            return false;
        }

        RemoveResult removeResult = root.remove(entry);
        if (!removeResult.isRemoved) {
            return false;
        }

        if (root.entries.isEmpty()) {
            this.handleRootUnderflow();
        }

        return true;
    }

    /**
     * Handles the case of underflow in the root after deletions.
     */
    private void handleRootUnderflow() {
        root = root.getClass().equals(BPlusTreeLeafNode.class) ? null : ((BPlusTreeNonLeafNode) root).children.get(0);
    }

    /**
     * Helper method to convert an array of items into a List.
     *
     * @param e The items to be included in the list.
     * @return A list containing the items.
     */
    @SafeVarargs
    private final <T> List<T> asList(T... e) {
        List<T> res = new ArrayList<>();
        Collections.addAll(res, e);
        return res;
    }

    /**
     * Helper method to convert an array of items into a Set.
     *
     * @param e The items to be included in the set.
     * @return A set containing the items.
     */
    @SafeVarargs
    private final <T> Set<T> asSet(T... e) {
        Set<T> res = new HashSet<>();
        Collections.addAll(res, e);
        return res;
    }

    /**
     * Provides a string representation of the B+ tree.
     *
     * @return A string describing the B+ tree.
     */
    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        return root.toString();
    }

    /**
     * Abstract class representing a node in a B+ tree.
     * This class encapsulates common properties and behaviors of nodes within the B+ tree,
     * such as handling underflow and overflow conditions and finding the median index for splits.
     */
    private abstract class BPlusTreeNode {

        // List of keys stored in this node.
        protected List<K> entries;

        /**
         * Determines if the node is in an underflow state.
         * Underflow is defined as having fewer keys than the specified underflow bound.
         *
         * @return true if the node is underflowing, false otherwise.
         */
        protected boolean isUnderflow() {
            return entries.size() < UNDERFLOW_BOUND;
        }

        /**
         * Determines if the node is in an overflow state.
         * Overflow is defined as having more keys than the specified overflow bound.
         *
         * @return true if the node is overflowing, false otherwise.
         */
        protected boolean isOverflow() {
            return entries.size() > OVERFLOW_BOUND;
        }

        /**
         * Computes the median index of the entries list, used when splitting the node.
         * The median is calculated based on the overflow bound, which defines the maximum number of keys a node can hold.
         *
         * @return The index of the median key in this node's entries list.
         */
        protected int getMedianIndex() {
            return OVERFLOW_BOUND / 2;
        }

        /**
         * Calculates the upper bound index for a given entry in a sorted list.
         * This method performs a binary search to find the index of the first element
         * that is greater than the specified entry. This is useful in maintaining
         * an ordered list where the exact insertion point of new elements needs
         * to be determined, or when duplicates are allowed but a strict order
         * must be kept.
         *
         * @param entry the element to compare against the elements in the list.
         * @return the index of the first element that is greater than the specified entry,
         * which is the same as the number of elements less than or equal to the entry.
         * If all elements in the list are less than or equal to the entry, returns the list size.
         */
        protected int entryIndexUpperBound(K entry) {
            // Initialize left boundary to 0
            int l = 0;
            // Initialize right boundary to the size of the list
            int r = entries.size();

            // Continue to search while left boundary is less than right boundary
            while (l < r) {
                // Calculate middle index to split the search range
                // Use bit shift to avoid overflow and improve efficiency
                int mid = l + ((r - l) >> 1);

                // Compare middle element with the given entry
                if (entries.get(mid).compareTo(entry) <= 0) {
                    // If middle element is less than or equal to the entry, adjust left boundary to right of mid
                    // Ensures that l will stop at the first element greater than entry
                    l = mid + 1;
                } else {
                    // If middle element is greater than the entry, adjust right boundary to mid
                    // Ensures r always points to the minimum element that is greater than entry
                    r = mid;
                }
            }

            // Return the computed upper bound index, where l is the index of the first element greater than entry
            return l;
        }

        /**
         * Performs a range query to find all entries within the specified key range.
         *
         * @param startInclude The lower bound of the range (inclusive).
         * @param endExclude   The upper bound of the range (exclusive).
         * @return A list of entries that fall within the specified range.
         */
        public abstract List<E> rangeQuery(K startInclude, K endExclude);

        /**
         * Queries for all entries associated with a specific key.
         *
         * @param entry The key to query for.
         * @return A list of entries associated with the key.
         */
        public abstract List<E> query(K entry);

        /**
         * Inserts a key-value pair into the appropriate node.
         *
         * @param entry The key to be inserted.
         * @param value The value associated with the key.
         * @return A new node if the insertion causes a split, otherwise null.
         */
        public abstract BPlusTreeNode insert(K entry, E value);

        /**
         * Updates a value associated with a specific key to a new value.
         *
         * @param entry    The key whose value is to be updated.
         * @param oldValue The old value to replace.
         * @param newValue The new value to replace the old value.
         * @return true if the update was successful, false otherwise.
         */
        public abstract boolean update(K entry, E oldValue, E newValue);

        /**
         * Removes an entry based on the key.
         *
         * @param entry The key of the entry to be removed.
         * @return A RemoveResult object containing the outcome of the removal and additional data.
         */
        public abstract RemoveResult remove(K entry);

        /**
         * Removes a specific key-value pair from the node.
         *
         * @param entry The key of the entry to be removed.
         * @param value The specific value to be removed.
         * @return A RemoveResult object indicating whether the removal was successful and whether underflow occurred.
         */
        public abstract RemoveResult remove(K entry, E value);

        /**
         * Combines the contents of this node with a neighboring node.
         * Typically used during node underflow to maintain minimum occupancy.
         *
         * @param neighbor    The neighbor node to combine with.
         * @param parentEntry The entry in the parent node that may need to be updated after the combine.
         */
        public abstract void combine(BPlusTreeNode neighbor, K parentEntry);

        /**
         * Borrows an entry from a neighboring node.
         * This method is called when a node is underflowing and needs to rebalance by borrowing entries.
         *
         * @param neighbor    The neighbor node to borrow from.
         * @param parentEntry The entry in the parent that divides this node and its neighbor.
         * @param isLeft      Indicates whether the neighbor is to the left or right; true if left, false if right.
         */
        public abstract void borrow(BPlusTreeNode neighbor, K parentEntry, boolean isLeft);
    }

    /**
     * Represents a non-leaf node in a B+ tree. Non-leaf nodes store keys and have children that are either
     * other non-leaf nodes or leaf nodes at the lowest level.
     */
    private class BPlusTreeNonLeafNode extends BPlusTreeNode {

        // List of child nodes.
        public List<BPlusTreeNode> children;

        /**
         * Constructs a non-leaf node with specified entries and children.
         *
         * @param entries  The keys within this node.
         * @param children The child nodes corresponding to the keys.
         */
        public BPlusTreeNonLeafNode(List<K> entries, List<BPlusTreeNode> children) {
            this.entries = entries;
            this.children = children;
        }


        @Override
        public List<E> rangeQuery(K startInclude, K endExclude) {
            // Delegates to the appropriate child based on the key range.
            return children.get(entryIndexUpperBound(startInclude)).rangeQuery(startInclude, endExclude);
        }

        @Override
        public List<E> query(K entry) {
            // Directs the query to the child node that could contain the entry.
            return children.get(entryIndexUpperBound(entry)).query(entry);
        }

        @Override
        public boolean update(K entry, E oldValue, E newValue) {
            // Delegates the update operation to the appropriate child node.
            return children.get(entryIndexUpperBound(entry)).update(entry, oldValue, newValue);
        }

        @Override
        public BPlusTreeNode insert(K entry, E value) {
            // Inserts a value into the appropriate child and handles node splitting if necessary.
            BPlusTreeNode newChildNode = children.get(entryIndexUpperBound(entry)).insert(entry, value);

            if (newChildNode != null) {
                K newEntry = findLeafEntry(newChildNode);
                int newEntryIndex = entryIndexUpperBound(newEntry);
                entries.add(newEntryIndex, newEntry);
                children.add(newEntryIndex + 1, newChildNode);
                return isOverflow() ? split() : null;
            }

            return null;
        }

        @Override
        public RemoveResult remove(K entry) {
            // Removes an entry from the appropriate child and handles underflow if necessary.
            int childIndex = entryIndexUpperBound(entry);
            int entryIndex = Math.max(0, childIndex - 1);
            BPlusTreeNode childNode = children.get(childIndex);
            RemoveResult removeResult = childNode.remove(entry);
            if (!removeResult.isRemoved) {
                return removeResult;
            }

            if (removeResult.isUnderflow) {
                this.handleUnderflow(childNode, childIndex, entryIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public RemoveResult remove(K entry, E value) {
            // Similar to remove(K entry) but specifically targets a key-value pair.
            int childIndex = entryIndexUpperBound(entry);
            int entryIndex = Math.max(0, childIndex - 1);

            BPlusTreeNode childNode = children.get(childIndex);
            RemoveResult removeResult = childNode.remove(entry, value);
            if (!removeResult.isRemoved) {
                return removeResult;
            }

            if (removeResult.isUnderflow) {
                this.handleUnderflow(childNode, childIndex, entryIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }


        /**
         * Handles underflow in a child node by either borrowing from or combining with adjacent siblings.
         *
         * @param childNode  The child node experiencing underflow.
         * @param childIndex The index of the child node.
         * @param entryIndex The index of the entry in this node that precedes the child node.
         */
        private void handleUnderflow(BPlusTreeNode childNode, int childIndex, int entryIndex) {
            BPlusTreeNode neighbor;
            // Borrow or combine logic based on sibling's entries count.
            if (childIndex > 0 && (neighbor = this.children.get(childIndex - 1)).entries.size() > UNDERFLOW_BOUND) {

                childNode.borrow(neighbor, this.entries.get(entryIndex), true);
                K boundEntry = childNode.getClass().equals(BPlusTreeNonLeafNode.class) ? findLeafEntry(childNode) : childNode.entries.get(0);
                this.entries.set(entryIndex, boundEntry);

            } else if (childIndex < this.children.size() - 1 && (neighbor = this.children.get(childIndex + 1)).entries.size() > UNDERFLOW_BOUND) {

                int parentEntryIndex = childIndex == 0 ? 0 : Math.min(this.entries.size() - 1, entryIndex + 1);
                childNode.borrow(neighbor, this.entries.get(parentEntryIndex), false);
                this.entries.set(parentEntryIndex, childNode.getClass().equals(BPlusTreeNonLeafNode.class) ? findLeafEntry(neighbor) : neighbor.entries.get(0));

            } else {

                if (childIndex > 0) {
                    // combine current child to left child
                    neighbor = this.children.get(childIndex - 1);
                    neighbor.combine(childNode, this.entries.get(entryIndex));
                    this.entries.remove(entryIndex);
                    this.children.remove(childIndex);

                } else {
                    // combine right child to current child (child index = 0)
                    neighbor = this.children.get(1);
                    childNode.combine(neighbor, this.entries.get(0));
                    this.entries.remove(0);
                    this.children.remove(1);
                }

            }

        }

        /**
         * Splits this node into two due to an overflow condition. Creates a new sibling node to the right.
         *
         * @return A new non-leaf node that holds the upper half of the entries and children of the original node.
         */
        private BPlusTreeNonLeafNode split() {
            int medianIndex = getMedianIndex();
            List<K> allEntries = entries;
            List<BPlusTreeNode> allChildren = children;

            this.entries = new ArrayList<>(allEntries.subList(0, medianIndex));
            this.children = new ArrayList<>(allChildren.subList(0, medianIndex + 1));

            List<K> rightEntries = new ArrayList<>(allEntries.subList(medianIndex + 1, allEntries.size()));
            List<BPlusTreeNode> rightChildren = new ArrayList<>(allChildren.subList(medianIndex + 1, allChildren.size()));
            return new BPlusTreeNonLeafNode(rightEntries, rightChildren);
        }

        @Override
        public void combine(BPlusTreeNode neighbor, K parentEntry) {
            // Combines this non-leaf node with its neighbor node and adjusts entries and children accordingly.
            BPlusTreeNonLeafNode nonLeafNode = (BPlusTreeNonLeafNode) neighbor;
            this.entries.add(parentEntry);
            this.entries.addAll(nonLeafNode.entries);
            this.children.addAll(nonLeafNode.children);
        }

        @Override
        public void borrow(BPlusTreeNode neighbor, K parentEntry, boolean isLeft) {
            // Borrows an entry and child node from the neighbor node to balance underflow.
            BPlusTreeNonLeafNode nonLeafNode = (BPlusTreeNonLeafNode) neighbor;
            if (isLeft) {
                this.entries.add(0, parentEntry);
                this.children.add(0, nonLeafNode.children.get(nonLeafNode.children.size() - 1));
                nonLeafNode.children.remove(nonLeafNode.children.size() - 1);
                nonLeafNode.entries.remove(nonLeafNode.entries.size() - 1);
            } else {
                this.entries.add(parentEntry);
                this.children.add(nonLeafNode.children.get(0));
                nonLeafNode.entries.remove(0);
                nonLeafNode.children.remove(0);
            }
        }

        public K findLeafEntry(BPlusTreeNode cur) {
            // Recursively finds the leftmost entry of a leaf node in the subtree rooted at the given node.
            if (cur.getClass().equals(BPlusTreeLeafNode.class)) {
                return cur.entries.get(0);
            }
            return findLeafEntry(((BPlusTreeNonLeafNode) cur).children.get(0));
        }

        @Override
        public String toString() {
            // Generates a string representation of this non-leaf node and its children for visualization.
            StringBuilder res = new StringBuilder();
            Queue<BPlusTreeNode> queue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; ++i) {
                    BPlusTreeNode cur = queue.poll();
                    assert cur != null;
                    res.append(cur.entries).append("  ");
                    if (cur.getClass().equals(BPlusTreeNonLeafNode.class)) {
                        queue.addAll(((BPlusTreeNonLeafNode) cur).children);
                    }
                }
                res.append('\n');
            }
            return res.toString();
        }
    }

    /**
     * Represents a leaf node in the B+ tree structure.
     */
    private class BPlusTreeLeafNode extends BPlusTreeNode {

        //Stores the data elements associated with the keys.
        public List<Set<E>> data;

        // Points to the next leaf node.
        public BPlusTreeLeafNode next;

        /**
         * Constructs a leaf node with the specified entries and associated data.
         *
         * @param entries The list of keys in the leaf node.
         * @param data    The list of sets containing data elements associated with the keys.
         */
        public BPlusTreeLeafNode(List<K> entries, List<Set<E>> data) {
            this.entries = entries;
            this.data = data;
        }

        @Override
        public List<E> rangeQuery(K startInclude, K endExclude) {
            // Implements a range query operation to retrieve elements within the specified key range.
            List<E> res = new ArrayList<>();
            int startUpperBound = Math.max(1, entryIndexUpperBound(startInclude));

            int end = entryIndexUpperBound(endExclude) - 1;
            if (end >= 0 && entries.get(end) == endExclude) {
                --end;
            }

            for (int i = startUpperBound - 1; i <= end; ++i) {
                res.addAll(data.get(i));
            }

            BPlusTreeLeafNode nextLeafNode = next;
            while (nextLeafNode != null) {
                for (int i = 0; i < nextLeafNode.entries.size(); ++i) {
                    if (nextLeafNode.entries.get(i).compareTo(endExclude) < 0) {
                        res.addAll(nextLeafNode.data.get(i));
                    } else {
                        return res;
                    }
                }
                nextLeafNode = nextLeafNode.next;
            }
            return res;
        }

        @Override
        public List<E> query(K entry) {
            // Implements a query operation to retrieve elements associated with a specific key.
            int entryIndex = getEqualEntryIndex(entry);
            return entryIndex == -1 ? Collections.emptyList() : new ArrayList<>(data.get(entryIndex));
        }

        @Override
        public boolean update(K entry, E oldValue, E newValue) {
            // Updates the data associated with a specific key in the leaf node.
            int entryIndex = getEqualEntryIndex(entry);
            if (entryIndex == -1 || !data.get(entryIndex).contains(oldValue)) {
                return false;
            }

            data.get(entryIndex).remove(oldValue);
            data.get(entryIndex).add(newValue);
            return true;
        }

        @Override
        public RemoveResult remove(K entry) {
            // Removes a key and its associated data from the leaf node.
            int entryIndex = getEqualEntryIndex(entry);
            if (entryIndex == -1) {
                return new RemoveResult(false, false);
            }

            this.entries.remove(entryIndex);
            this.data.remove(entryIndex);

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public RemoveResult remove(K entry, E value) {
            // Removes a specific data element associated with a key from the leaf node.
            int entryIndex = getEqualEntryIndex(entry);
            if (entryIndex == -1 || !data.get(entryIndex).contains(value)) {
                return new RemoveResult(false, false);
            }

            data.get(entryIndex).remove(value);
            if (data.get(entryIndex).isEmpty()) {
                this.entries.remove(entryIndex);
                this.data.remove(entryIndex);
            }

            return new RemoveResult(true, isUnderflow());
        }

        @Override
        public void combine(BPlusTreeNode neighbor, K parentEntry) {
            // Combines this leaf node with its neighbor node and adjusts entries and data accordingly.
            BPlusTreeLeafNode leafNode = (BPlusTreeLeafNode) neighbor;
            this.entries.addAll(leafNode.entries);
            this.data.addAll(leafNode.data);
            this.next = leafNode.next;
        }

        @Override
        public void borrow(BPlusTreeNode neighbor, K parentEntry, boolean isLeft) {
            // Borrows an entry and associated data from the neighbor node to balance underflow.
            BPlusTreeLeafNode leafNode = (BPlusTreeLeafNode) neighbor;
            int borrowIndex;

            if (isLeft) {
                borrowIndex = leafNode.entries.size() - 1;
                this.entries.add(0, leafNode.entries.get(borrowIndex));
                this.data.add(0, leafNode.data.get(borrowIndex));
            } else {
                borrowIndex = 0;
                this.entries.add(leafNode.entries.get(borrowIndex));
                this.data.add(leafNode.data.get(borrowIndex));
            }

            leafNode.entries.remove(borrowIndex);
            leafNode.data.remove(borrowIndex);
        }

        @Override
        public BPlusTreeNode insert(K entry, E value) {
            // Inserts a new key and its associated data into the leaf node.
            int equalEntryIndex = getEqualEntryIndex(entry);
            if (equalEntryIndex != -1) {
                data.get(equalEntryIndex).add(value);
                return null;
            }

            int index = entryIndexUpperBound(entry);
            entries.add(index, entry);
            data.add(index, asSet(value));
            return isOverflow() ? split() : null;
        }

        private BPlusTreeLeafNode split() {
            // Splits the leaf node into two nodes to handle overflow.
            int medianIndex = getMedianIndex();
            List<K> allEntries = entries;
            List<Set<E>> allData = data;

            this.entries = new ArrayList<>(allEntries.subList(0, medianIndex));
            this.data = new ArrayList<>(allData.subList(0, medianIndex));

            List<K> rightEntries = new ArrayList<>(allEntries.subList(medianIndex, allEntries.size()));
            List<Set<E>> rightData = new ArrayList<>(allData.subList(medianIndex, allData.size()));
            BPlusTreeLeafNode newLeafNode = new BPlusTreeLeafNode(rightEntries, rightData);

            newLeafNode.next = this.next;
            this.next = newLeafNode;
            return newLeafNode;
        }

        private int getEqualEntryIndex(K entry) {
            // Binary search to find the index of a specific key in the leaf node.
            int l = 0;
            int r = entries.size() - 1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                int compare = entries.get(mid).compareTo(entry);
                if (compare == 0) {
                    return mid;
                } else if (compare > 0) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            // Generates a string representation of the leaf node for visualization.
            return entries.toString();
        }
    }

    /**
     * Represents the result of a remove operation in the B+ tree.
     */
    private static class RemoveResult {

        //Indicates whether the remove operation was successful.
        public boolean isRemoved;

        //Indicates whether the node is underflowing after the remove operation.
        public boolean isUnderflow;

        /**
         * Constructs a RemoveResult object with the specified parameters.
         *
         * @param isRemoved   Whether the remove operation was successful.
         * @param isUnderflow Whether the node is underflowing after the remove operation.
         */
        public RemoveResult(boolean isRemoved, boolean isUnderflow) {
            this.isRemoved = isRemoved;
            this.isUnderflow = isUnderflow;
        }
    }
}

