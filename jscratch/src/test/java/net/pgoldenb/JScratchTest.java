package net.pgoldenb;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class JScratchTest {

    @Test
    public void test1() {
//        assertEquals(3, canCompleteCircuit(ints(1, 2, 3, 4, 5), ints(3, 4, 5, 1, 2)));
        assertEquals(3, canCompleteCircuit(ints(4, 5, 2, 6, 5, 3), ints(3, 2, 7, 3, 2, 9)));

    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int st = 0;
        do {
            while (st < gas.length && gas[st] < cost[st]) st++;
            if (st >= gas.length) return -1;

            int tank = 0;
            int trav = st;
            do {
                tank += gas[trav] - cost[trav];
                if (tank < 0)
                    trav = st;
                else
                    trav = (trav + 1) % gas.length;
            } while (trav != st);

            if(tank >= 0) return st;
            st++;
        } while (st < gas.length);

        return -1;
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    static int[] ints(int... vals) { return vals; }

    static boolean intsEqual(int[] a, int[] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    static boolean intintsEqual(int[][] a, int[][] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (!intsEqual(a[i], b[i]))
                return false;
        }
        return true;
    }

    static <T> List<T> l(T... vals) { return new ArrayList(Arrays.asList(vals)); }

    static <T> Set<T> s(T... vals) { return Sets.newHashSet(vals); }

    static TreeNode treeOf(int val, TreeNode left, TreeNode right) {
        TreeNode ret = new TreeNode(val);
        ret.left = left;
        ret.right = right;
        return ret;
    }

    static TreeNode treeOf(int val) { return treeOf(val, null, null); }

    static TreeNode treeOf(int val, TreeNode left) { return treeOf(val, left, null); }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }

        @Override
        public boolean equals(Object other) {

            if (other == null) return false;
            if (!(other instanceof TreeNode)) return false;

            TreeNode otherNode = (TreeNode) other;

            if (val != otherNode.val) return false;
            if (left == null) {
                if (otherNode.left != null) return false;
            } else if (!left.equals(otherNode.left)) {
                return false;
            }

            if (right == null) {
                if (otherNode.right != null) return false;
            } else if (!right.equals(otherNode.right)) {
                return false;
            }

            return true;
        }
    }

    static Node ntreeOf(int val, Node... children) {

        List<Node> c = new ArrayList();
        for (Node n : children)
            c.add(n);
        return new Node(val, c);
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public boolean equals(Object other) {

            if (other == null) return false;
            if (!(other instanceof Node)) return false;

            Node otherNode = (Node) other;

            if (val != otherNode.val) return false;
            for (Node c : children) {
                boolean found = false;
                for (Node oc : otherNode.children) {
                    if (c.equals(oc))
                        found = true;
                }
                if (!found) return false;
            }
            return true;
        }
    }

    static ListNode listOf(int... vals) {
        if (vals == null || vals.length < 1) return null;

        ListNode head = new ListNode(vals[0]);
        ListNode cur = head;
        for (int i = 1; i < vals.length; i++) {
            cur.next = new ListNode(vals[i]);
            cur = cur.next;
        }
        return head;
    }

    static public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) { val = x; }

        @Override
        public boolean equals(Object other) {

            if (other == null) return false;
            if (!(other instanceof ListNode)) return false;

            ListNode thisNode = this;
            ListNode otherNode = (ListNode) other;

            while (thisNode != null) {
                if (otherNode == null || thisNode.val != otherNode.val) return false;
                thisNode = thisNode.next;
                otherNode = otherNode.next;
            }

            return (otherNode == null);
        }
    }

}
