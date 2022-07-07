import edu.princeton.cs.algs4.StdOut;

public class test {
    public void a() {
        int[] array = new int[4];
        for (int i = 0; i < 4; i++) {
            array[i] = i;
        }
        
        int[] array2 = array;
        array[0] = 100;

        showMe(array);
        showMe(array2);
    }
    
    private void showMe(int[] array){
        for (int i = 0; i < array.length; i++) {
            StdOut.println(array +"["+i +"] :" +array[i]);
        }
    }


    public static void main(String[] args) {
        test test = new test();
        test.a();
    }
}
