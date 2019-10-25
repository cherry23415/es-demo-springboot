package com.ying;

public class ListNode {
    ListNode next;
    int item;

    public ListNode(int item) {
        this.item = item;
        this.next = null;
    }

    @Override
    public String toString() {
        return this.item + "";
    }
}
