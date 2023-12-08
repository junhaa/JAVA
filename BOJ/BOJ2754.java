import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #2754 학점계산
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cur = br.readLine();
        if(cur.equals("F")){
            System.out.println("0.0");
            return;
        }
        double score = 0;
        score += (4 - (cur.charAt(0) - 'A'));
        if(cur.charAt(1) == '+'){
            score += 0.3;
        }
        else if(cur.charAt(1) == '-'){
            score -= 0.3;
        }
        System.out.println(score);
    }
}
