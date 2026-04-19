class SegmentTree {
    private static class Node {
        int value;
        int start, end;
        Node left, right;

        Node(int start, int end, int value, Node left, Node right) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    private Node root;
    private int[] nums;
    public SegmentTree(int[] nums){
        this.nums = nums;
        this.root = buildSegmentTree(0, nums.length - 1);
    }
    public void update(int index, int newValue){
        if(index >= nums.length) return;
        update(index, newValue, this.root);
        this.nums[index] = newValue;
        return;
    }
    public int query(int start, int end){
        return query(start, end, this.root);
    }
    private void update(int index, int newValue,  Node root){
        int nodeStart = root.start;
        int nodeEnd = root.end;
        if(nodeStart > index ||  nodeEnd < index) return;
        else if(nodeStart == index && nodeEnd == index){
            root.value = newValue;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid) {
            update(index, newValue, root.left);
        } else {
            update(index, newValue, root.right);
        }
        root.value = root.left.value + root.right.value;
        return;
    }   
    private int query(int start, int end, Node root){
        if (root == null) return 0;
        int nodeStart = root.start;
        int nodeEnd = root.end;
        // Case 1: Node interval is fully inside the query - return root;
        if(start <= nodeStart && end >= nodeEnd) return root.value;
        // Case 2: Node interval is fully outside the query - return default;
        if(start > nodeEnd || end < nodeStart) return 0;
        // Case 3: 
        return query(start, end, root.left) + query(start, end, root.right);
    }
    private Node buildSegmentTree(int start, int end){
        if(start == end){
            int currentValue = this.nums[start];
            return new Node(start, end, currentValue, null, null);
        }
        int mid = start + (end - start)/2;
        Node leftChild = buildSegmentTree(start, mid);
        Node rightChild = buildSegmentTree(mid+1, end);
        int currentValue = leftChild.value + rightChild.value;
        Node currentChild = new Node(start, end, currentValue, leftChild, rightChild);
        return currentChild;
    }
}
