import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// BOJ #2493 탑
class Tower{
    int idx, h;
    public Tower(int idx, int h){
        this.idx = idx;
        this.h = h;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Tower> stack = new Stack<>();
        for(int i = 1 ; i <= N ; i ++){
            int tmp = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()) stack.push(new Tower(i, tmp));
            else if(stack.peek().h <= tmp){
                while(true){
                    if(stack.isEmpty()) {
                        stack.push(new Tower(i, tmp));
                        break;
                    }
                    else if(stack.peek().h > tmp){
                        answer[i] = stack.peek().idx;
                        stack.push(new Tower(i, tmp));
                        break;
                    }
                    else stack.pop();
                }
            }
            else{
                answer[i] = stack.peek().idx;
                stack.push(new Tower(i, tmp));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i <= N ; i ++){
            if(answer[i] != 0) {
                sb.append(answer[i] + " ");
            }
            else sb.append(0 + " ");
        }
        System.out.println(sb);
    }
}
