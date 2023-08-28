import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// BOJ #28278 스택 2 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            switch (q){
                case 1:
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case 2:
                    if(stack.isEmpty()){
                        sb.append(-1);
                    }
                    else{
                        sb.append(stack.pop());
                    }
                    break;
                case 3:
                    sb.append(stack.size());
                    break;
                case 4:
                    if(stack.isEmpty()){
                        sb.append(1);
                    }
                    else{
                        sb.append(0);
                    }
                    break;
                case 5:
                    if(stack.isEmpty()){
                        sb.append(-1);
                    }
                    else{
                        sb.append(stack.peek());
                    }
            }

            if(q != 1) sb.append("\n");
        }
        System.out.println(sb);
    }
}
