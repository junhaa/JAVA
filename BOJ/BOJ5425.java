import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #5425 자리합
public class Main {


    static Long zeroSum(int num){
        return (long)(num * (num + 1) / 2);
    }
    static Long getSum(Long num){
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int numSum = 0;
        long sum = 0;
        for(int i = len ; i > 0 ; i --){
            int cur = numStr.charAt(len - i) - '0';
            if(i == 1) cur ++;
            long curSum = 0;
            curSum += cur * ((i - 1) * Math.pow(10, i - 2)) * 45;
                if(cur != 0) {
                    curSum += (Math.pow(10, i - 1) * zeroSum(cur - 1));
                }
                if(cur != 0) {
                    curSum += Math.pow(10, i - 1) * (cur) * numSum;
                }
                numSum += cur;
                sum += curSum;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            if(start != 0) start --;
            long end = Long.parseLong(st.nextToken());
            sb.append(getSum(end) - getSum(start) + "\n");
        }
        System.out.println(sb);
    }
}
