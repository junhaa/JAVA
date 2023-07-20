import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// BOJ #5568 카드 놓기
public class Main {

    static int n, k;
    static int[] arr;
    static boolean[] ch;
    static HashSet<String> set = new HashSet<>();
    static void back_tracking(int L, String tmp){
        if(L == k){
            set.add(tmp);
            return;
        }
        for(int i = 0 ; i < n ; i ++){
            if(ch[i]) continue;
            ch[i] = true;
            String ctmp = "";
            ctmp += tmp;
            ctmp += arr[i];
            back_tracking(L + 1, ctmp);
            ch[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        arr = new int[n];
        ch = new boolean[n];
        for(int i = 0 ; i < n ; i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        back_tracking(0, "");
        System.out.println(set.size());
    }
}
