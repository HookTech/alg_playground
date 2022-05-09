package edu.princeton.cs.test.quickCoding.list;

import edu.princeton.cs.test.ListPCallMethod;
import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

import static ps.philo.playground.CommonStructure.ListNode;

public class ListPartitionClass implements ListPCallMethod {
    @Override
    public void callListPCallAlgoMethod() {

    }

    @Test
    public void listPartitionTest1() throws Exception {
        ListNode<Integer> head = TestUtil.generateListFromSeries(9,0,4,5,1);
        partition(head,new Integer(3));
        TestUtil.assertList(head, 0,1,4,5,9);
    }

    @Test
    public void listPartitionTest2() throws Exception{
        ListNode<Integer> head = TestUtil.generateListFromSeries(1,6,7,2,3,3,0,0,8,1);
        partition(head,new Integer(3));
        TestUtil.assertList(head,1,2,0,0,1,3,3,6,8,7);
    }

    @Test
    public void listPartitionTest3() throws Exception{
        ListNode<Integer> head = TestUtil.generateListFromSeries(7,9,1,8,5,2,5);
        partition(head,new Integer(5));
        TestUtil.assertList(head,1,2,5,5,9,7,8);
    }

    private ListNode<Integer> partition(ListNode<Integer> head, Integer pivot){
        if(head == null || head.next == null) {return head;}
        ListNode<Integer> keep = head;
        ListNode<Integer> sPartition = head;
        ListNode<Integer> firstPivot = null;
        while (sPartition.value < pivot){
            sPartition = sPartition.next;
            head = sPartition;
        }
        while(head.next != null){
            head = head.next;
            if(head.value <= pivot){
                swap(sPartition,head);
                if(sPartition.value <= pivot){
                    if(firstPivot == null && sPartition.value.equals(pivot)){ firstPivot = sPartition;}
                    else if(firstPivot != null && !sPartition.value.equals(pivot)){
                        swap(firstPivot,sPartition);
                        firstPivot = firstPivot.next;
                    }
                }
                sPartition = sPartition.next;
            }
        }
        return keep;
    }

    private <T> void swap(ListNode<T> upStream, ListNode<T> downStream){
        T tmp = downStream.value;
        downStream.value = upStream.value;
        upStream.value = tmp;
    }
}
