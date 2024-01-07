import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ #2916 자와 각도기 
public class Main {
    static boolean[] check = new boolean[360];
    static int getGcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    // 만들어 질 수 있는 각도 모두 check
    static void checkAll(int a){
        if(check[a]) return;
        int cur = a;
        while(cur < 360){
            check[cur] = true;
            cur += a;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            int cur = Integer.parseInt(st.nextToken());
            list.add(cur);
            checkAll(cur);
            int gcd = getGcd(cur, 360);
            list.add(gcd);
            checkAll(gcd);
        }

        for(int i = 0 ; i < list.size() ; i ++){
            for(int j = i + 1 ; j < list.size() ; j ++){
                checkAll(getGcd(list.get(i), list.get(j)));
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i ++){
            if(check[Integer.parseInt(st.nextToken())]) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }
}
