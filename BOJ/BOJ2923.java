import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2923 숫자 게임
public class Main {
    static int[] idxA = new int[101], idxB = new int[101];

    static int findIdx(boolean plus, int cur, int[] arr){
        while(arr[cur] == 0){
            if(plus) cur ++;
            else cur --;
        }
        return cur;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            idxA[a] ++;
            idxB[b] ++;
            int[] copyA = new int[101];
            int[] copyB = new int[101];
            System.arraycopy(idxA, 1, copyA, 1, 100);
            System.arraycopy(idxB, 1, copyB, 1, 100);
            int cnt = 0;
            int tmpA = 1;
            int tmpB = 100;
            int max = Integer.MIN_VALUE;
            while(true){
                tmpA = findIdx(true, tmpA, copyA);
                tmpB = findIdx(false, tmpB, copyB);
                int dif = Math.min(copyA[tmpA], copyB[tmpB]);
                max = Math.max(tmpA + tmpB, max);
                copyA[tmpA] -= dif;
                copyB[tmpB] -= dif;
                cnt += dif;
                if(cnt == i) break;
            }
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }
}
