import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
// BOJ #2374 같은 수로 만들기
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        long answer = 0;
        for(int i = 0 ; i < n ; i ++){
            int tmp = Integer.parseInt(br.readLine());
            if(stack.isEmpty()){
                stack.push(tmp);
            }
            else if(stack.peek() == tmp) continue;
            else if(stack.peek() > tmp) stack.push(tmp);
            else {
                long diff = 0;
                while(!stack.isEmpty() && stack.peek() <= tmp){
                    diff = Math.max(tmp - stack.pop(), diff);
                }
                answer += diff;
                stack.push(tmp);
            }
        }
        int first = stack.pop();
        int max = first;
        while(!stack.isEmpty()){
            max = stack.pop();
        }
        answer += max - first;
        System.out.println(answer);
    }
}
