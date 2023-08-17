import javax.print.attribute.standard.Finishings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
// BOJ #16638 괄호 추가하기 2
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
        Deque<Long> dQ = new LinkedList<>();
        boolean[] finished = new boolean[oprLen];
        dQ.offerLast((long)num[0]);
        for(int i = 1 ; i <= oprLen ; i ++){ // 괄호 계산
            dQ.offerLast((long)num[i]);
            if(usePrt[i - 1]) {
                long rt = dQ.pollLast();
                dQ.offerLast(operation(dQ.pollLast(), rt, opr[i - 1]));
                finished[i - 1] = true;
            }
        }

        int len = dQ.size();
        int oprPt = 0;
        dQ.offerLast(dQ.pollFirst());
        for(int i = 0 ; i < len - 1 ; i ++){
            long tmp = dQ.pollFirst();
            while(finished[oprPt]) oprPt ++;
            if(opr[oprPt] == '*'){
                tmp *= dQ.pollLast();
                finished[oprPt] = true;
            }
            else oprPt ++;
            dQ.offerLast(tmp);
        }

        len = dQ.size();
        oprPt = 0;
        dQ.offerLast(dQ.pollFirst());
        for(int i = 0 ; i < len - 1 ; i ++){
            long lt = dQ.pollLast();
            long rt = dQ.pollFirst();
            long tmp = 0;
            while(finished[oprPt]) oprPt ++;
            if(opr[oprPt] == '+'){
                tmp = lt + rt;
                finished[oprPt] = true;
            }
            else {
                tmp = lt - rt;
                finished[oprPt] = true;
            }
            dQ.offerLast(tmp);
        }

        if(dQ.size() != 1){
            System.out.println("dQsize ERROR");
            System.exit(0);
        }

        return dQ.pollFirst().intValue();
        /*
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
         */
    }
    static int DFS(int L){
        if(L == oprLen) {
            int res = getSum();
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
