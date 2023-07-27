import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #1593 암호 해독
public class Main {
    static int[] chArr = new int[52];
    static int[] Warr = new int[52];

    static int ctoi(char C){
        if(C >= 'a') return C - 'a' + 26;
        else return C - 'A';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        String W = br.readLine();
        String S = br.readLine();
        for(int i = 0 ; i < g ; i ++){
            Warr[ctoi(W.charAt(i))] ++;
            chArr[ctoi(S.charAt(i))] ++;
        }

        if(Arrays.equals(Warr, chArr)) {
            answer++;
        }
        for(int i = g ; i < s ; i ++){
            chArr[ctoi(S.charAt(i))] ++;
            chArr[ctoi(S.charAt(i - g))] --;
            if(Arrays.equals(Warr, chArr)) answer ++;
        }
        System.out.println(answer);
    }
}
