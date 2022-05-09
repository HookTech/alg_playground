package edu.princeton.cs.test.quickCoding.list;

import static ps.philo.playground.CommonStructure.ListNode;
import edu.princeton.cs.test.ListPCallMethod;
import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

/**
 * 给定两个有序链表的头指针head1和head2，打印两个链表的公共部分
 * */
public class PrintCommonListPartClass implements ListPCallMethod {

    ListNode<Integer> head1,head2;

    @Override
    public void callListPCallAlgoMethod() {

    }

    @Test
    public void printCommonListPartTest(){
        head1 = new ListNode<>(1);
        head2 = new ListNode<>(2);
        ListNode<Integer> bodyNext1 = head1;
        ListNode<Integer> bodyNext2 = head2;
        for(int i = 2; i < 8; i++,bodyNext1 = bodyNext1.next){
            bodyNext1.next = new ListNode<>(i);
        }
        for(int i = 3; i < 10; i++,bodyNext2 = bodyNext2.next){
            bodyNext2.next = new ListNode<>(i);
        }
        TestUtil.printString("List1");
        TestUtil.printList(head1);
        TestUtil.printString("List2");
        TestUtil.printList(head2);
        TestUtil.printString("Common part of List1 & List2:");
        printCommonPart(head1,head2);
    }

    public void printCommonPart(ListNode<Integer> head1, ListNode<Integer> head2){
        StringBuilder builder = new StringBuilder();
        while (head1 != null && head2 != null){
            if(head1.value.equals(head2.value)) {
                builder.append(head1.value).append(",");
                head1 = head1.next;
                head2 = head2.next;
            }
            else if(head1.value > head2.value){
                head2 = head2.next;
            }
            else {
                head1 = head1.next;
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        TestUtil.printString(builder.toString());
    }

}
