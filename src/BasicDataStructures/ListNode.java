/**
 * Class to Implement ListNode
 *
 * @author Sharayu Sharad Mantri
 * Date : 4/13/19
 * Project : BasicDataStructures
 **/

package BasicDataStructures;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public void print() {
        System.out.print(this.val);
        if (this.next != null) {
            this.next.print();
        }
    }
}
