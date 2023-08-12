import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #19644 좀비 떼가 기관총 진지에도 오다니
public class Main {

    static int[] prefix;

    static int getSum(int i, int ml){
        int rt = i + 1;
        int lt = rt - ml >= 0 ? rt - ml : 0;
        return prefix[rt] - prefix[lt];
    }

    static boolean canUseMG(int health, int ml, int mk, int i){
        if(getSum(i - 1, ml - 1) + mk >= health) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ml = Integer.parseInt(st.nextToken()); // 유효 사거리
        int mk = Integer.parseInt(st.nextToken()); // 살상력
        int C = Integer.parseInt(br.readLine());
        prefix = new int[L + 1];
        int first = Integer.parseInt(br.readLine());
        if(canUseMG(first, ml, mk, 1)){
            prefix[1] = mk;
        }
        else{
            if(C == 0) {
                System.out.println("NO");
                return;
            }
            C--;
        }
        for(int i = 1 ; i < L ; i ++){
            int health = Integer.parseInt(br.readLine());
            prefix[i + 1] = prefix[i];
            if(canUseMG(health, ml, mk, i)){
                prefix[i + 1] += mk;
            }
            else {
                if(C == 0){
                    System.out.println("NO");
                    return;
                }
                C--;
            }
        }
        System.out.println("YES");
    }
}
