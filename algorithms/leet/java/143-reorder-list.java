/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public void reorderList(ListNode head) {
    if (head == null) return;
    Stack<ListNode> stack = new Stack<>();
    Queue<ListNode> queue = new LinkedList<>();
    
    ListNode current = head;
    while (current != null) {
      stack.push(current);
      queue.add(current);
      current = current.next;
    }
    
    current = head;
    int nodesRemoved = 0;
    int numNodesToRemove = stack.size() / 2;
    while (nodesRemoved <= numNodesToRemove) {
      if (nodesRemoved == numNodesToRemove) {
        current.next = null;
        break;
      }
      ListNode temp = queue.remove();
      ListNode nextnext = temp.next;
      ListNode next = stack.pop();
      current.next = next;
      current = current.next;
      current.next = nextnext;
      current = current.next;
      nodesRemoved++;
    }
  }
}