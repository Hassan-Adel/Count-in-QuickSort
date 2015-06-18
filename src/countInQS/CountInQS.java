//http://a2oj.com/Submission.jsp?ID=69046
package countinqs;
import java.util.Scanner;
import java.util.Vector;

public class CountInQS {
    static int comparisons = 0;
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int K = scan.nextInt();
        //Vector <Integer> finalC = new Vector<Integer> ();
        
        for (int i=0; i < K; i++){
            int N = scan.nextInt();
            int arr1[] = new int[N];
            int arr2[] = new int[N];
            int arr3[] = new int[N];
            for (int j=0; j < N; j++){
                int tmp = scan.nextInt();
                arr1[j] = tmp;
                arr2[j] = tmp;
                arr3[j] = tmp;
            }

            System.out.print(QS1(arr1, 0, N) + " ");
            //finalC.add(QS1(arr1, 0, N));
            comparisons = 0;
            System.out.print(QS2(arr2, 0, N) + " ");
            //finalC.add(QS2(arr2, 0, N));
            comparisons = 0;
            System.out.println(QS3(arr3, 0, N));
            //finalC.add(QS3(arr3, 0, N));
            comparisons = 0;
        }
//        for(int i=0; i<(K*K); i++){
//            System.out.println(finalC.get(i)+" "+finalC.get(i+1)+" "+finalC.get(i+2));
//            i = i+2;
//        }
    }

    public static int QS1(int arr1[], int left, int right) {
        if (left == right)
            return comparisons;
        int i = arr1[left];
        int j = Partition(arr1, left, right, i);
        
        QS1(arr1, left, j - 1);
        QS1(arr1, j, right);
        
        return comparisons;
    }

    public static int QS2(int arr2[], int left, int right) {
        if (left == right)
            return comparisons;
        
        int temp = arr2[left];
        arr2[left] = arr2[right - 1];
        arr2[right - 1] = temp;
        int i = arr2[left];
        int j = Partition(arr2, left, right, i);
        
        QS2(arr2, left, j - 1);
        QS2(arr2, j, right);
        
        return comparisons;
    }

    public static int QS3(int arr3[], int left, int right) {
        if (left == right)
            return comparisons;

        int arr[] = new int[3];
        arr[0] = arr3[left];
        arr[1] = arr3[(left + right - 1) / 2];
        arr[2] = arr3[right - 1];
        for (int i = 0; i < 2; i++) {
            for (int j = i + 1; j < 3; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        if (arr[1] == arr3[(left + right - 1) / 2]) {
            int temp = arr3[left];
            arr3[left] = arr3[(left + right - 1) / 2];
            arr3[(left + right - 1) / 2] = temp;

        } else if (arr[1] == arr3[right - 1]) {
            int temp = arr3[left];
            arr3[left] = arr3[right - 1];
            arr3[right - 1] = temp;

        }
        int i = arr3[left];
        int j = Partition(arr3, left, right, i);
        QS3(arr3, left, j - 1);
        QS3(arr3, j, right);
        
        return comparisons;
    }

    public static int Partition(int arr[], int start, int end, int pivot) {
        int i = start + 1;
        for (int j = start + 1; j < end; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                i++;

            }
            comparisons++;
        }
        int tmp = arr[start];
        arr[start] = arr[i - 1];
        arr[i - 1] = tmp;
        
        return i;
    }
}