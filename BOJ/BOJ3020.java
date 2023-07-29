import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #3020 개똥벌레 
public class Main {

    static int lower_bound(int h, int[] arr){
        int lt = 0;
        int rt = arr.length;
        while(lt < rt){
            int mid = (lt + rt) / 2;
            if(arr[mid] < h){
                lt = mid + 1;
            }
            else{
                rt = mid;
            }
        }
        return lt;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[N / 2];
        int[] arr2 = new int[N / 2];
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(br.readLine());
            if(i % 2 == 0){
                arr1[i / 2] = tmp - 1;
            }
            else{
                arr2[i / 2] = tmp - 1;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int minN = Integer.MAX_VALUE;
        int minC = -1;
        for(int i = 0 ; i < H ; i ++){
            int cur = N - (lower_bound(i, arr1) + lower_bound(H - i - 1, arr2));
            if(minN > cur){
                minC = 1;
                minN = cur;
            }
            else if(minN == cur){
                minC ++;
            }
        }


        /*
        //prefix - Sum
        int[] arr1 = new int[H];
        int[] arr2 = new int[H];
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(br.readLine()) - 1;
            if(i % 2 == 0){
                arr1[tmp] ++;
            }
            else {
                arr2[tmp]++;
            }
        }
        for(int i = H - 2 ; i >= 0 ; i --){
            arr1[i] = arr1[i] + arr1[i + 1];
            arr2[i] = arr2[i] + arr2[i + 1];
        }
        for(int i = 0 ; i < H ; i ++){
            arr1[H - 1 - i] += arr2[i];
        }
        int minN = Integer.MAX_VALUE;
        int minC = -1;
        for(int i = 0 ; i < H ; i ++){
            int cur = arr1[i];
            if(minN > cur){
                minC = 1;
                minN = cur;
            }
            else if(minN == cur){
                minC ++;
            }
        }
        */
        System.out.println((minN) + " " + minC);
    }
}
