/******************************************************************************
Write a function that does the following:

1) Construct a binary search tree, in order, from the integers given in the
   input argument vector
2) Return the distance between nodes with two given values. If either node
   was not found in the binary tree, the function should return -1

For example, given the following vector of input values:

   8 7 13 6 2 5 1 9 11 3 4 10

and the following two node arguments:

   4 2

the function should return 3.

Given the same input vector, but the following input arguments:

   4 0

the function should return -1

Note:
     - Feel free to use a text editor or IDE of your choice to do this instead.
     - If you do, simply just copy-paste your code here when you're done.
     - Do not change function signature.
     - You are free to add any supporting helper functions, data types, or
       structures you wish.
     - main function has been provided to make code run inside Rextester Web
       IDE. It is not required to be part of the submission and will not be
       graded.
******************************************************************************/

import java.util.*;

class Node {
int value;
Node left;
Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }
}


public class BST {

    Node root;

    public void add(int value) {
        root = Recur(root, value);
    }

    private Node Recur(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = Recur(current.left, value);
        } else if (value > current.value) {
            current.right = Recur(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public static Node Least(Node root, int n1, int n2)
   {
       if (root == null)
           return root;
       if (root.value == n1 || root.value == n2)
           return root;

       Node left = Least(root.left, n1, n2);
       Node right = Least(root.right, n1, n2);

       if (left != null && right != null)
           return root;
       if (left != null)
           return Least(root.left, n1, n2);
       else
           return Least(root.right, n1, n2);
   }

   // Returns level of key k if it is present in
   // tree, otherwise returns -1
   public static int findHeight(Node root, int a, int level)
   {
       if (root == null)
           return -1;
       if (root.value == a)
           return level;
       int left = findHeight(root.left, a, level + 1);
       if (left == -1)
           return findHeight(root.right, a, level + 1);
       return left;
   }

   public static int findDistance(Node root, int a, int b)
   {
       Node Least = Least(root, a, b);

       int d1 = findHeight(Least, a, 0);
       int d2 = findHeight(Least, b, 0);

       return d1 + d2;
   }


}

class Q3
{

    public static int BSTdistance(ArrayList<Integer> values, int nodeA, int nodeB)
    {
        BST tree = new BST();

        for (Integer i: values){
            tree.add(i);
        }

        return findDistance(tree.root, nodeA, nodeB);

    }
}

class Rextester
{
    public static void main(String[] args)
    {
       ArrayList<Integer> input =
           new ArrayList<Integer>(Arrays.asList(8, 7, 13, 6, 2, 5, 1, 9, 11, 3, 4, 10 ));

       Q3.BSTdistance(input, 4, 2);
    }
}
