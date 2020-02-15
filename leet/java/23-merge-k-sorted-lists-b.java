/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((a,b) -> a.val - b.val);
    for (ListNode node : lists) {
      if (node != null) {
        weave(node, minHeap);
      }
    }
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    while (minHeap.size() != 0) {
      ListNode temp = new ListNode(minHeap.remove().val);
      current.next = temp;
      current = current.next;
    }
    return dummy.next;
  }

  // base case: initial list, l1 is defined, l2 is not
  // we assume l2 will always be defined
  private void weave(ListNode list, PriorityQueue<ListNode> minHeap) {
    while (list != null) {
      minHeap.add(list);
      list = list.next;
    }
  }
}