import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #1422 숫자의 신
class Num implements Comparable<Num>{
    int length;
    String num;
    public Num(String num) {
        length = num.length();
        this.num = num;
        int idx = 0;
        while(this.num.length() < 10) {
            this.num += num.charAt(idx % num.length());
            idx ++;
        }
    }
    @Override
    public int compareTo(Num o) {
        if(this.num == o.num) {
            return this.length - o.length;
        }
        else {
            long oNum = Long.parseLong(o.num);
            long thisNum = Long.parseLong(this.num);
            if(oNum > thisNum) return 1;
            else if(oNum < thisNum) return -1;
            else return 0;
        }
    }
}

public class Main {

    static PriorityQueue<Num> pQ = new PriorityQueue<>();

    static BigInteger solution(int max, int rep) {
        String answer = "";
        boolean flag = false;
        while(!pQ.isEmpty()) {
            Num tmp = pQ.poll();
            if(!flag && tmp.length == max){
                for(int i = 0 ; i < rep ; i ++){
                    for(int j = 0 ; j < tmp.length ; j ++){
                        answer += tmp.num.charAt(j);
                    }
                }
                flag = true;
            }
            for(int i = 0 ; i < tmp.length ; i ++){
                answer += tmp.num.charAt(i);
            }
        }
        return new BigInteger(answer);
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < K ; i ++) {
            st = new StringTokenizer(br.readLine());
            String cur = st.nextToken();
            max = Math.max(max, cur.length());
            pQ.offer(new Num(cur));
        }
        System.out.println(T.solution(max, N - K));
    }
}
