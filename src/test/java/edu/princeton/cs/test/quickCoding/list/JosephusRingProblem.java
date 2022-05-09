package edu.princeton.cs.test.quickCoding.list;

import static ps.philo.playground.CommonStructure.ListNode;
import edu.princeton.cs.test.ListPCallMethod;
import edu.princeton.cs.test.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JosephusRingProblem  implements ListPCallMethod {
    @Override
    public void callListPCallAlgoMethod() {

    }

    @Test
    public void JosephusRingProblemTest() throws Exception {
        ListNode<Integer> head1 = TestUtil.generateCircleListFromSeries(1,2,3,4,5);
        ListNode<Integer> head2 = TestUtil.generateCircleListFromSeries(1,2,3,4);
        ListNode<Integer> head3 = TestUtil.generateCircleListFromSeries(1);
        Assert.assertEquals(kill(head1,1).value, new Integer(4));
        Assert.assertEquals(kill(head2,1).value, new Integer(1));
        Assert.assertEquals(kill(head3,1).value, new Integer(1));
    }

    private <T> ListNode<T> kill(ListNode<T> head, int m) throws Exception {
        if(head == null || head.next == null || m < 0 || m > 2){
            throw new Exception("Wrong Input!");
        }
        T tagV = head.value;
        while (true){
            if(head == null || head.next == null){throw new Exception("not a circle list!");}
            else if(head.next.value.equals(tagV)){
                head = head.next;
                break;
            }
            else {head = head.next;}
        }
        while (head.next != head){
            if(m == 2){
                head.next = head.next.next;
                m = 0;
            }
            else {
                head = head.next;
                m++;
            }
        }
        return head;
    }
}
