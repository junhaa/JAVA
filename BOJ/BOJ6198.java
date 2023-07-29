import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// BOJ #6198 옥상 정원 꾸미기
class Building{
    int h, cnt = 0;
    public Building(int h){
        this.h = h;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Building> stack = new Stack<>();
        long answer = 0;
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(br.readLine());
            if(stack.isEmpty() || stack.peek().h > tmp){
                stack.push(new Building(tmp));
            }
            else{
                while(!stack.isEmpty() && stack.peek().h <= tmp){
                    Building cur = stack.pop();
                    answer += cur.cnt;
                    if(!stack.isEmpty()) stack.peek().cnt += cur.cnt + 1;
                }
                stack.push(new Building(tmp));
            }
        }
        while(!stack.isEmpty()){
            Building cur = stack.pop();
            answer += cur.cnt;
            if(!stack.isEmpty()) stack.peek().cnt += cur.cnt + 1;
        }
        System.out.println(answer);
    }
}
