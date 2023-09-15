import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #18353 병사 배치하기
public class Main {
    static int[] arr;

    static int LIS(int N){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Integer.MAX_VALUE);
        for(int i = 0 ; i < N ; i ++){
            int tmp = arr[i];
            if(tmp > list.get(list.size() - 1)) list.add(tmp);
            else{
                int idx = Collections.binarySearch(list, tmp);
                if(idx < 0){
                    idx ++;
                    idx *= -1;
                    list.set(idx, tmp);
                }
            }
        }
        return list.size();
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= N ; i ++){

            arr[N - i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(N - LIS(N));
    }
}
