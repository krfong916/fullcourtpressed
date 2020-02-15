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
    ListNode dummy = new ListNode(Integer.MIN_VALUE);
    ListNode current = dummy;
    for (ListNode node : lists) {
      if (node != null) {
        current = weave(node, current);
      }
    }
    return current.next;
  }

  private ListNode weave(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode current = dummy;
    while (l1 != null && l2 != null) {
      if (l1.val > l2.val) {
        current.next = l2;
        l2 = l2.next;
      } else {
        current.next = l1;
        l1 = l1.next;
      }
      current = current.next;
    }

    if (l1 != null) {
      current.next = l1;
    }

    if (l2 != null) {
      current.next = l2;
    }
    return dummy.next;
  }
}