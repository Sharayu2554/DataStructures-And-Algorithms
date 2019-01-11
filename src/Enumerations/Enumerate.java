/**
 * Class to implement
 *
 * @author Sharayu Sharad Mantri
 * Date : 1/9/2019
 * Project : Enumerations
 */

package Enumerations;

public class Enumerate<T> {
    T[] arr;
    int k;
    int count;
    Approver<T> app;

    // Class to decide whether to extend a permutation with a selected item
    // Extend this class in algorithms that need to enumerate permutations with precedence constraints
    public static class Approver<T> {
        /* Extend permutation by item? */
        public boolean select(T item) { return true; }

        public void clear(){
            return ;
        }

        /* Backtrack selected item */
        public void unselect(T item) { }

        /* Visit a permutation or combination */
        public void visit(T[] array, int k) {
            for (int i = 0; i < k; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    public Enumerate(T[] arr, int k, Approver<T> app) {
        this.arr = arr;
        this.k = k;
        this.count = 0;
        this.app = app;
    }

    public Enumerate(T[] arr, Approver<T> app) {
        this(arr, arr.length, app);
    }

    public Enumerate(T[] arr, int k) {
        this(arr, k, new Approver<T>());
    }

    public Enumerate(T[] arr) {
        this(arr, arr.length, new Approver<T>());
    }

    public void visit(T[] array) {
        count++;
        app.visit(array, k);
    }

    // n = arr.length, choose k things, d elements arr[0..d-1] done
    // c more elements are needed from arr[d..n-1].  d = k-c.
    public void permute(int k, int c) {
        if ( c == 0) {
            count++;
            app.visit(this.arr, k);
        }
        else {
            int d = k -c;
            T temp;
            for (int i = d; i < arr.length; i++) {
                if (app.select(this.arr[i])){
                    temp = arr[d];
                    arr[d] = arr[i];
                    arr[i] = temp;

                    permute(k, c-1);

                    temp = arr[d];
                    arr[d] = arr[i];
                    arr[i] = temp;

                    app.unselect(arr[i]);
                }
            }
        }
    }

    // Enumerate permutations of k items out of n = arr.length
    public static<T> Enumerate<T> permute(T[] arr, int k) {
        Enumerate<T> e = new Enumerate<>(arr, k);
        e.permute(k,  k);
        return e;
    }

    public void combine(int k, int i, int c) {
        if(c == 0) {
            count++;
            app.visit(arr, k);
        }
        else{
            T temp = arr[k-c];
            arr[k-c] = arr[i];
            arr[i] = temp;

            combine(k, i+1, c-1);

            temp = arr[k-c];
            arr[k-c] = arr[i];
            arr[i] = temp;

            if (arr.length - i > c) {
                combine(k, i+1, c);
            }
        }
    }

    // Enumerate combinations of k items out of n = arr.length
    public static<T> Enumerate<T> combine(T[] arr, int k) {
        Enumerate<T> e = new Enumerate<>(arr, k);
        e.combine(k, 0, k);
        return e;
    }

    public static void main(String args[]) {
        int n = 5;
        int k = 3;
        if(args.length > 0) { n = Integer.parseInt(args[0]);  k = n; }
        if(args.length > 1) { k = Integer.parseInt(args[1]); }
        Integer[] arr = new Integer[n];
//        Integer[] arr = {1,3,2,4};
        for (int i = 0; i < n; i++) {
            arr[i] = i+1;
        }

        System.out.println("Permutations: " + n + " " + k);
        Enumerate<Integer> e = permute(arr, k);
        System.out.println("Count: " + e.count + "\n_________________________");

        System.out.println("combinations: " + n + " " + k);
        e = combine(arr, k);
        System.out.println("Count: " + e.count + "\n_________________________");
    }
}
