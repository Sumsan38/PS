package com.leetcode;

/*
 * https://leetcode.com/problems/add-two-numbers/
 * Add Two Numbers (Medium)
 * */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode returnNode = null;
        ListNode nextNode = null;

        ListNode l1N = l1;
        ListNode l2N = l2;
        int sum = 0;
        while (true) {
            int l1NV = 0;
            int l2NV = 0;

            if(l1N == null && l2N == null) {
                // break 하기 전에 sum이 10일 경우 하나 더 추가
                if(sum >= 10) {
                    nextNode.next = new ListNode(1);
                }
                break;
            }
            if(l1N != null) l1NV = l1N.val;
            if(l2N != null) l2NV = l2N.val;
            sum = sum != 0? (l1NV + l2NV + (sum / 10)) : (l1NV + l2NV);

            ListNode newNode = new ListNode(sum % 10);
            if(returnNode == null) {
                returnNode = newNode;
            } else {
                nextNode.next = newNode;
            }
            nextNode = newNode;

            if(l1N != null) l1N = l1N.next;
            if(l2N != null) l2N = l2N.next;
        }
        return returnNode;
    }
}
