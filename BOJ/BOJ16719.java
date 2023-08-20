import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
//BOJ #16719 ZOAC
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<String> stack = new Stack<>();
        int len = input.length();
        boolean[] ch = new boolean[len];
        for(int i = 0 ; i < len; i ++){
            String tmp = "";
            for(int j = 0 ; j < len ; j ++){
                if(!ch[j]) tmp += input.charAt(j);
            }
            stack.push(tmp);
            int last = -1;
            boolean flag = false;
            for(int j = 0 ; j <len ; j ++){
                if(ch[j]) continue;;
                if(last != -1 && input.charAt(last) > input.charAt(j)){
                    ch[last] = true;
                    flag = true;
                    break;
                }
                last = j;
            }
            if(!flag){
                ch[last] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop() + "\n");
        }
        System.out.println(sb);
    }
}
