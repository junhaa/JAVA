import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #9519 졸
public class Main {
    static char[] input, tmp1, tmp2;

    static void shuffleArray(){
        for(int i = 0 ; i < (double)tmp1.length / 2 ; i ++){
            tmp2[i * 2] = tmp1[i];
        }
        int cnt = 0;
        for(int i = tmp1.length - 1 ; i > (double)tmp1.length / 2 ; i --){
            tmp2[cnt * 2 + 1] = tmp1[i];
            cnt ++;
        }
        if(tmp1.length % 2 == 0){
            tmp2[tmp1.length - 1] = tmp1[tmp1.length / 2];
        }
        char[] tmp = tmp1;
        tmp1 = tmp2;
        tmp2 = tmp;
    }

    static boolean isEquals(char[] arr1, char[] arr2){
        for(int i = 0 ; i < arr1.length ; i ++){
            if(arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
        tmp1 = new char[input.length];
        tmp2 = new char[input.length];
        System.arraycopy(input, 0, tmp1, 0, input.length);
        int cnt = 0;
        do {
            shuffleArray();
            cnt ++;
        } while(!isEquals(input, tmp1));
        int len = cnt - (X % cnt);
        for(int i = 0 ; i < len ; i ++){
            shuffleArray();
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < tmp1.length ; i ++){
            sb.append(tmp1[i]);
        }
        System.out.println(sb);
    }
}
