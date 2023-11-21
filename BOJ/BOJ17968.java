import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// BOJ #17968 Fire on Field
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[1001];
        Arrays.fill(arr, 1);

        for(int i = 2 ; i <= 1000 ; i ++){
            while(true){
                boolean flag = false;
                for(int j = 1 ; j <= 500 ; j ++){
                    if(i - 2 * j < 0) break;
                    if(arr[i] - arr[i - j] == arr[i - j] - arr[i - 2 * j]){
                        arr[i] ++;
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
            }
        }
        System.out.println(arr[Integer.parseInt(br.readLine())]);
    }
}
