import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// BOJ #2504 괄호의 값 
class Bracket{
    int sum;
    char ch;
    public Bracket(int sum, char ch){
        this.sum = sum;
        this.ch = ch;
    }
}
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        Stack<Bracket> stack = new Stack<>();
        int answer = 0;
        for(int i = 0 ; i < input.length() ; i ++){
            char tmp = input.charAt(i);
            if(tmp == '(' || tmp == '['){
                stack.push(new Bracket(0, tmp));
            }
            else if(tmp == ')' || tmp == ']'){
                if(stack.isEmpty()){
                    System.out.println(0);
                    return;
                }
                if(tmp == ')'){
                    if(stack.peek().ch == '('){
                        int curSum = stack.pop().sum;
                        if(curSum == 0) curSum = 1;
                        if(stack.isEmpty()){
                            answer += curSum * 2;
                        }
                        else stack.peek().sum += curSum * 2;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }
                else if(tmp == ']'){
                    if(stack.peek().ch == '['){
                        int curSum = stack.pop().sum;
                        if(curSum == 0) curSum = 1;
                        if(stack.isEmpty()){
                            answer += curSum * 3;
                        }
                        else stack.peek().sum += curSum * 3;
                    }
                    else {
                        System.out.println(0);
                        return;
                    }
                }

            }
            else {
                System.out.println(0);
                 return;
            }
        }
        if(!stack.isEmpty()) System.out.println(0);
        else System.out.println(answer);
    }
}
