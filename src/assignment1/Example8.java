package assignment1;

// Sorting Algorithm
/*
    8. Imagine you are reading in stream of integers. Periodically, you wish to be able to look up the rank of number x(the number of values
       less than or equal to x). Implement the data structures and algorithms to support these operations. That is, Implement the method
       track(int x), which is called when each number is generated, and the method getRankOfNumber(int x), which return the number of values
       less than or equal to x(not including x itself).
       EXAMPLE:
       Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
       getRankOfNumber(1) = 0
       getRankOfNumber(3) = 1
       getRankOfNumber(4) = 3
*/


class TreeNode {
    int val;
    int leftSize;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.leftSize = 0;
        this.left = null;
        this.right = null;
    }
}


public class Example8 {

    private TreeNode root;

    private TreeNode insert(TreeNode node, int val) {
        if(node == null) {
            return new TreeNode(val);
        }

        if(val <= node.val) {
            node.leftSize++;
            node.left = insert(node.left, val);
        } else {
            node.right = insert(node.right, val);
        }

        return node;
    }

    public void track(int x) {
        root = insert(root, x);
    }

    private int getRank(TreeNode node, int val) {
        if(node == null) {
            return 0;
        }

        if(val < node.val) {
            return getRank(node.left, val);
        } else if (val == node.val) {
            return node.leftSize;
        } else {
            return node.leftSize + 1 + getRank(node.right, val);
        }
    }

    public int getRankOfNumber(int x) {
        return getRank(root, x);
    }

    public static void main(String[] args) {
        Example8 streamRank = new Example8();
        int[] stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};

        for(int num : stream) {
            streamRank.track(num);
        }

        System.out.println("getRankOfNumber(1) : " + streamRank.getRankOfNumber(1));  // Output: 0
        System.out.println("getRankOfNumber(3) : " + streamRank.getRankOfNumber(3)); // Output: 1
        System.out.println("getRankOfNumber(4) : " + streamRank.getRankOfNumber(4)); // Output: 3
    }

}

