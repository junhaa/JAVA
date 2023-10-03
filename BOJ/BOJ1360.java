import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;
// BOJ #1360 되돌리기
class Command{
    String com;
    int time;

    public Command(String com, int time) {
        this.com = com;
        this.time = time;
    }
}

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Stack<Command> stack = new Stack<>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            if(st.nextToken().equals("type")){
                stack.push(new Command(st.nextToken(), Integer.parseInt(st.nextToken())));
            }
            else{
                stack.push(new Command(st.nextToken(), Integer.parseInt(st.nextToken())));
            }
        }

        Stack<Character> rs = new Stack<>();
        while(!stack.isEmpty()){
            Command cur = stack.pop();
            if(Character.isAlphabetic(cur.com.charAt(0))){
                rs.push(cur.com.charAt(0));
            }
            else{
                int time = cur.time - Integer.parseInt(cur.com);
                while(!stack.isEmpty()){
                    if(stack.peek().time >= time){
                        stack.pop();
                    }
                    else break;
                }
            }
        }
        while(!rs.isEmpty()){
            sb.append(rs.pop());
        }
        System.out.println(sb);
    }
}
