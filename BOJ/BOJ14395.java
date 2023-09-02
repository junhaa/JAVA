import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #14395 4연산
public class Main {
    static HashMap<Long, Long> map = new HashMap<>();
    static boolean BFS(int start, int end){
        Queue<Long> Q = new LinkedList<>();
        map.put((long)start, (long)-1);
        Q.offer((long)start);
        while(!Q.isEmpty()){
            long tmp = Q.poll();
            if(tmp == end) return true;
            long cur = tmp + tmp;
            if(cur <= (long)1e18 && !map.containsKey(cur)){
                map.put(cur, tmp);
                Q.offer(cur);
            }
            cur = tmp * tmp;
            if(cur <= (long)1e18 && !map.containsKey(cur)){
                map.put(cur, tmp);
                Q.offer(cur);
            }
            cur = 1;
            if(!map.containsKey(cur)){
                map.put(cur, tmp);
                Q.offer(cur);
            }
        }
        return false;
    }

    static Stack<Character> getSeq(long cur, Stack<Character> stack, int start){
        if(cur == start){
            return stack;
        }
        if(Math.sqrt(cur) == (double)map.get(cur)){
            stack.push('*');
            getSeq((long)Math.sqrt(cur), stack, start);
        }
        else if((cur / 2 == map.get(cur))){
            stack.push('+');
            getSeq(cur / 2, stack, start);
        }
        else if(cur == 1){
            stack.push('/');
            getSeq(start, stack, start);
        }
        return stack;
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        if(s == t){
            System.out.println(0);
            return;
        }
        if(!BFS(s, t)){
            System.out.println(-1);
            return;
        }
        Stack<Character> stack = new Stack<>();
        getSeq(t, stack, s);
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}
