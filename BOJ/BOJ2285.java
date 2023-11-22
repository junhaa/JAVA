import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #2285 우체국
class Village implements Comparable<Village>{
    long x, a;
    public Village(long x, long a){
        this.x = x;
        this.a = a;
    }
    @Override
    public int compareTo(Village v){
        if(this.x - v.x < 0) return -1;
        else if(this.x == v.x) return 0;
        else return 1;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
        ArrayList<Village> list = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            cnt += a;
            list.add(new Village(x, a));
        }
        Collections.sort(list);
        long curSum = 0;

        for(int i = 0 ; i < N ; i ++){
            curSum += list.get(i).a;
            if((double)curSum >= (double)cnt / 2){
                System.out.println(list.get(i).x);
                return;
            }
        }
        System.out.println("ERROR");
    }
}
