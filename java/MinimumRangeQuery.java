import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinimumRangeQuery {
    private class Node {
        int val;
        int start;
        int end;
        Node left;
        Node right;
        private Node(int val, int start, int end){
            this.val = val;
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
        }
        private Node(int val, int start, int end, Node left, Node right){
            this.val = val;
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }
    }
    private class SegmentTree {
        private Node root;
        public SegmentTree(int[] nums) {
            this.root = buildSegmentTree(nums, 0, nums.length - 1);
        }
        
        public void update(int index, int val) {
            update(index, val, this.root);
        }
        
        public int minRange(int start, int end) {
            return minRange(start, end, this.root);
        }

        private Node buildSegmentTree(int[] nums, int start, int end){
            if(start == end) return new Node(nums[start], start, end);
            int mid = start + (end - start)/2;
            Node leftChild = buildSegmentTree(nums, start, mid);
            Node rightChild = buildSegmentTree(nums, mid + 1, end);
            int total = Math.min(leftChild.val, rightChild.val);
            return new Node(total, start, end, leftChild, rightChild);
        }

        private void update(int index, int val, Node root){
            int start = root.start, end = root.end;
            if(index > end || index < start) return;
            if(start == end){
                if(start == index) root.val = val;
                return;
            }
            int mid = start + (end - start)/2;
            if(index <= mid) update(index, val, root.left);
            else update(index, val, root.right);
            root.val = Math.min(root.left.val, root.right.val);
        }

        private int minRange(int start, int end, Node root){
            // Full overlap
            if(start <= root.start && end >= root.end) return root.val;
            // no overlap - default
            if(start > root.end || end < root.start) return Integer.MAX_VALUE;
            return Math.min(minRange(start, end, root.left), minRange(start, end, root.right));
        }
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() { return Integer.parseInt(next()); }
    }
    public static void main(String[] args){
        // System.out.println("Minimum Range Query Problem!");
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out); // For fast output
        int N = fr.nextInt();
        int Q = fr.nextInt();
        int[] A = new int[N]; 
        for (int i = 0; i < N; i++) {
            A[i] = fr.nextInt();
        }

        MinimumRangeQuery m = new MinimumRangeQuery();
        SegmentTree segmentTree = m.new SegmentTree(A);

        for (int i = 0; i < Q; i++) {
            String type = fr.next(); 
            int l = fr.nextInt();
            int r = fr.nextInt();

            if (type.equals("q")) {
                int result = segmentTree.minRange(l - 1, r - 1); // Query the minimum in range [l-1, r-1]
                out.println(result);
            } else {
                segmentTree.update(l - 1, r); // Update the value at index l-1 to r
                A[l-1] = r;
            }
        }
        out.flush();
    }
}
