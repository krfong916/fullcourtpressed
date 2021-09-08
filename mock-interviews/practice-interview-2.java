// // Convert Sorted Array to Binary Search Tree
// // Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

// // For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of 
// // the two subtrees of every node never differ by more than 1.

// // Example:

// // Given the sorted array: [-10,-3,0,5,9],

// // One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

// //       0
// //      / \
// //    -3   9
// //    /   /
// //  -10  5

// //       0
// //      / \
// //    -3   5
// //    /     \
// //  -10      9

// // Details:
// // sorted array, ascending order
// // tree - binary search tree height balanced binary tree
// // -- balanced tree

// // binary tree:
// // two nodes, left and right given a parent node
// // left subtree - all values are less than the parent node's value
// // right subtree - all values are greater than or equal to the parent's value

// // define a node class
// // value
// // pointer to right and left child

// // Pseudocode: divide and conquer problem
// // given an array
// // perform binary search over n-items
// // - binary search
// // merging the nodes back together

// // when to insert
// // when to finish the algorithm - when to end

// // [-10,-3,0,5,9]
// // as we see them: given the result of binarySearch (median finding) add that node as the child

// public TreeNode convertTree(int[] arr) {
//     if (arr.length == 0) return null;
//     if (arr.length == 1) return new TreeNode(arr[0]);

//     TreeNode root = new TreeNode(arr[(arr.length-1 + 0) / 2]);
    
//     root.left = iterative(arr, start, arr.length-1 + 0) / 2)
//     root.right = iterative(arr, arr.length-1 + 0) / 2, end)

//     return convertTreeHelper(arr, root, 0, arr.length-1);
// }
// // [-10,-3,0,5,9]
// private TreeNode iterative(arr, start, end) {
//     var newNode: Node = Node(0)
//     var start = start
//     var end = end
//     while (start < end) {
//         // calc median
//         if arr[start] > arr[end] {
//             newNode.left = Node(arr[end])
            
//         }

//     }

//     return node
// }

// // height balanced binary search tree
// // binary search tree
// // binary tree

// private void merge() {

//     while (start <= end) {

//     }

// }




// private TreeNode convertTreeHelper(int[] arr, TreeNode parent, int start, int end) {
//     if (start > end) {
//         return null;
//     }   
//     int median = (start + end) / 2;
//     TreeNode root = new TreeNode(arr[median]);
//     root.left = convertTreeHelper(arr, root, start, median-1);
//     root.right = convertTreeHelper(arr, root, median+1, end);
//     return root; 
// }



class Solution {
    func maxSubArray(_ nums: [Int]) -> Int {
        
    }

    public static int maxSubArray(int[] A) {
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);	
        }
        return maxSoFar;
    }

    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
}

Given an integer array nums,
find the contiguous subarray within an array 
(containing at least one number) which has the largest product.

[3, 100, -2, 9, -8, 10, 11]

// currentMin = -5400
// currentMax = -8 * -5400
// keeping reference to the continuity

maxV = 3

currmax = max(max(currmin*a[i], currmax*a[i]), a[i])
currmin = min(min(currmin*a[i], currmax*a[i]), a[i])

maxV = max(currMax, maxV)

min = [3, 3, ]
max = [3, 300, ]


[2,-3,2,4,-5,8,9]
-6 * 2 * 4 a[i] if > currentMax * a[i]
[2,3,-2,4]
2*3

currentMax = Math.max((i, i+1), (currentMax))


class GFG { 
    static int maxCrossingSum(int arr[], int l, int m, int h) { 
        int sum = 0; 
        int left_sum = Integer.MIN_VALUE; 
        for (int i = m; i >= l; i--) { 
            sum = sum + arr[i]; 
            if (sum > left_sum) 
            left_sum = sum; 
        } 
  
        sum = 0; 
        int right_sum = Integer.MIN_VALUE; 
        for (int i = m + 1; i <= h; i++) { 
            sum = sum + arr[i]; 
            if (sum > right_sum) 
            right_sum = sum; 
        } 
        return left_sum + right_sum; 
    } 
    static int maxSubArraySum(int arr[], int l, int h) { 
        if (l == h) return arr[l];
        int m = (l + h)/2; 
  
    /* Return maximum of following three  
    possible cases: 
    a) Maximum subarray sum in left half 
    b) Maximum subarray sum in right half 
    c) Maximum subarray sum such that the  
    subarray crosses the midpoint */
    return Math.max(Math.max(maxSubArraySum(arr, l, m), 
                    maxSubArraySum(arr, m+1, h)), 
                    maxCrossingSum(arr, l, m, h)); 
    } 
  
    /* Driver program to test maxSubArraySum */
    public static void main(String[] args) { 
        int arr[] = {2, 3, 4, 5, 7}; 
        int n = arr.length; 
        int max_sum = maxSubArraySum(arr, 0, n-1); 
        
        System.out.println("Maximum contiguous sum is "+ 
                                            max_sum); 
    } 
} 