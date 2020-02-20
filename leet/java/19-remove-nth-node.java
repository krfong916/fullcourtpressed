/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    int length = 1;
    int counter = 0;

    ListNode lengthRunner = head;
    ListNode removeRunner = head;
    ListNode prevRunner = new ListNode(0);
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    prevRunner.next = head;

    // get a count of all nodes in the list
    while (lengthRunner != null) {
      if (lengthRunner.next == null) {
        int indexOfNodeToRemove = length - n;

        while (counter != indexOfNodeToRemove) {
          prevRunner = prevRunner.next;
          removeRunner = removeRunner.next;
          counter++;
        }
        // remove the node
        // case 1: head
        if (removeRunner == head) {
          head = head.next;
          dummy.next = head;
          // case 2: tail
        } else if (removeRunner.next == null) {
          prevRunner.next = null;
          // case 3: middle of the list
        } else {
          prevRunner.next = removeRunner.next;
        }
      }
      length++;
      lengthRunner = lengthRunner.next;
    }
    return dummy.next;
  }
}