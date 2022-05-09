package edu.princeton.cs.test.quickCoding.list;

import static ps.philo.playground.CommonStructure.ListNode;
import edu.princeton.cs.test.ListPCallMethod;
import edu.princeton.cs.test.TestUtil;
import org.testng.annotations.Test;

public class RemoveLastKthNodeClass implements ListPCallMethod {

    @Override
    public void callListPCallAlgoMethod() {

    }

    @Test()
    public void removeLastKthNodeTest() throws Exception {
        ListNode<Integer> head = TestUtil.generateListFromSeries(1,2,3,4,5);
        TestUtil.assertList(removeLastKthNode(head, 4), 1,3,4,5);

        head = TestUtil.generateListFromSeries(1,2,3,4,5);
        TestUtil.assertList(removeLastKthNode(head,1), 1,2,3,4);

        head = TestUtil.generateListFromSeries(1,2,3,4,5);
        TestUtil.assertList(removeLastKthNode(head,5), 2,3,4,5);

        head = TestUtil.generateListFromSeries(1,2,3,4,5);
        TestUtil.assertList(removeLastKthNode(head,0), 1,2,3,4,5);

    }

    private ListNode<Integer> removeLastKthNode(ListNode<Integer> head, Integer K) throws Exception {
        ListNode<Integer> del = head, save = head;
        if(K < 0){throw new Exception("K must be positive num");}
        if(K == 0){return save;}
        //iterate to the end
        while (del != null){
            K--;
            del = del.next;
        }
        if(K > 0){throw new Exception("K must be less than list's length");}
        else if(K == 0){save = head.next;}
        else{
            del = head;
            while ( ++K != 0){
                del = del.next;
            }
            ListNode<Integer> pre = del;
            del = del.next;
            pre.next = del.next;
        }
        return save;
    }
}
