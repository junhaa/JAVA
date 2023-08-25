import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #14595 동방 프로젝트 (large)
class Break implements Comparable<Break> {
    int start, end;
    public Break(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Break O){
        if(this.start == O.start) return O.end - this.end;
        else return this.start - O.start;
    }
}
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Break> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());
        if(M == 0) {
            System.out.println(N);
            return;
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            list.add(new Break(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }
        Collections.sort(list);
        int start = list.get(0).start;
        int end = list.get(0).end;
        int cnt = 0;
        for(int i = 1 ; i < M ; i ++){
            Break tmp = list.get(i);
            if(tmp.start <= end) end = Math.max(end, tmp.end);
            else{
                cnt += end - start;
                start = tmp.start;
                end = tmp.end;
            }
        }
        cnt += end - start;
        System.out.println(N - cnt);
    }
}
