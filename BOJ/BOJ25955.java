import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #25955 APC는 쉬운 난이도 순일까, 아닐까?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] sarr = new String[N];
        int[] iarr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            String cur = st.nextToken();
            sarr[i] = cur;
            int score = 0;
            switch (cur.charAt(0)){
                case 'B':
                    score += 50000;
                    break;
                case 'S':
                    score += 40000;
                    break;
                case 'G':
                    score += 30000;
                    break;
                case 'P':
                    score += 20000;
                    break;
                case 'D':
                    score += 10000;
                    break;
            }
            score += Integer.parseInt(cur.substring(1, cur.length()));
            iarr[i] = score;
        }
        int[] narr = new int[N];
        System.arraycopy(iarr, 0, narr, 0, N);
        Arrays.sort(iarr);
        boolean flag = true;
        int idx1 = -1;
        int idx2 = -1;
        for(int i = 0 ; i < N ; i ++){
            if(narr[i] != iarr[N - i - 1]){
                if(!flag){
                    idx2 = i;
                    break;
                }
                else {
                    flag = false;
                    idx1 = i;
                }
            }
        }
        if(flag){
            System.out.println("OK");
        }
        else{
            System.out.println("KO\n" + sarr[idx2] + " " + sarr[idx1]);
        }
    }
}
