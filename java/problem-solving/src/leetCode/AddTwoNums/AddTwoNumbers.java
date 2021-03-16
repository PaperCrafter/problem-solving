package leetCode.AddTwoNums;

import java.util.Optional;

public class AddTwoNumbers {
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListClass linkedList = new ListClass();
        int carry = 0;
        while (Optional.of(l1.next).isPresent() || (Optional.of(l2.next).isPresent())) {
            int currentValue = carry;
            carry = 0;
            if (Optional.of(l1.next).isPresent()) {
                currentValue += l1.next.val;
                l1 = l1.next;
            }
            if (Optional.of(l2.next).isPresent()) {
                currentValue += l2.next.val;
                l2 = l2.next;
            }
            if (currentValue >= 10) {
                carry = 0;
                carry = currentValue / 10;
                currentValue = currentValue % 10;
            }
            linkedList.addNode(new ListNode(currentValue));
        }
        return linkedList.getHead();
    }
}

class ListClass {
    ListNode head;
    ListNode tail;

    public ListClass() {
        head = null;
        tail = null;
    }

    public void addNode(ListNode nodeToAdd) {
        if (!Optional.of(head).isPresent()) {
            head = nodeToAdd;
            tail = head;
            return;
        }
        tail.next = nodeToAdd;
        tail = tail.next;
    }

    public ListNode getHead() {
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}