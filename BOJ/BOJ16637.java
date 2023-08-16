import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #16637 괄호 추가하기
public class Main {
    static int[] num;
    static char[] opr;
    static boolean[] usePrt;
    static int N, numLen, oprLen;

    static long operation(long lt, long rt, char opr){
        switch (opr){
            case '+':
                return lt + rt;
            case '-':
                return lt - rt;
            case '*':
                return lt * rt;
        }
        return -1;
    }
    static int getSum(){
        long sum = num[0];
        for(int i = 0 ; i < oprLen ; i ++){
            if(i != oprLen - 1 && usePrt[i + 1]){
                sum = operation(sum, operation(num[i + 1], num[i + 2], opr[i + 1]), opr[i]);
                i ++;
            }
            else{
                sum = operation(sum, num[i + 1], opr[i]);
            }
        }
        return (int)sum;
    }
    static int DFS(int L){
        if(L == oprLen) {
            return getSum();
        }
        int max = Integer.MIN_VALUE;
        if(L == 0 || !usePrt[L - 1]){
            usePrt[L] = true;
            max = Math.max(max, DFS(L + 1));
            usePrt[L] = false;
        }
        max = Math.max(DFS(L + 1), max);
        return max;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numLen = N / 2 + 1;
        oprLen = N / 2;
        num = new int[numLen];
        opr = new char[oprLen];
        usePrt = new boolean[oprLen];
        String input = br.readLine();
        for(int i = 0 ; i < N ; i ++){
            if(i % 2 == 0){
                num[i / 2] = (int)(input.charAt(i) - '0');
            }
            else opr[i / 2] = input.charAt(i);
        }
        System.out.println(DFS(0));
    }
}
