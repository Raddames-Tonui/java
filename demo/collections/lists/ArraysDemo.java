import java.util.Arrays;


public class ArraysDemo {
    public static void main(String[] args) {
    arrayDemo();
    sorted();
}

    public  static void arrayDemo(){

//         | Task       | Example                        |
// | ---------- | ------------------------------ |
// | Declare    | `int[] arr = new int[5];`      |
// | Initialize | `String[] names = {"A", "B"};` |
// | Access     | `names[0]`                     |
// | Modify     | `arr[1] = 10;`                 |
// | Iterate    | `for (int i : arr)`            |
// | Sort       | `Arrays.sort(arr)`             |

        String[] names = new String[4];
        names[0] = "Raddames";
        names[2] = "Tony";
        System.out.println("First name is : "  + names[0]);

        for (String nums : names){
            System.out.println(nums );
        }

        for ( int j= 0; j < names.length; j++ ){
            System.out.println("Name at index "+j+ "is " + names[j]);
        }

    }

    public static int[] sorted(){
        int[] nums = {3,4,62,1,3,6,14,6,3,3};
        Arrays.sort(nums);
        return  nums;
    }
    
    
}