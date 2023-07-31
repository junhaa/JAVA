import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// BOJ #6443 애너그램 
public class Main {
    static char[] str, cur;
    static boolean[] ch;
    static int len;
    static StringBuilder sb = new StringBuilder();

    static void backtracking(int L){
        if(L == len){
            for(int i = 0 ; i < len ; i ++){
                sb.append(cur[i]);
            }
            sb.append("\n");
            return;
        }
        char last = '0';
        for(int i = 0 ; i < len ; i ++){
            if(str[i] == last || ch[i]) continue;
            last = str[i];
            ch[i] = true;
            cur[L] = str[i];
            backtracking(L + 1);
            ch[i] = false;
        }
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        while(N -- > 0){
            String input = br.readLine();
            len = input.length();
            ch = new boolean[len];
            cur = new char[len];
            str = input.toCharArray();
            Arrays.sort(str);
            backtracking(0);
        }
        System.out.println(sb);
    }
}
