import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #28448 광기의 PS
class PS implements Comparable<PS>{
    int K, T;
    long healing;
    public PS(int K, int T){
        this.K = K;
        this.T = T;
        this.healing = Math.min(K * T, 5 * K);
    }
    @Override
    public int compareTo(PS o){
        if(this.healing - o.healing > 0) return -1;
        else if(this.healing == o.healing) return 0;
        else return 1;
        /*
        long fatigue1 = (long)this.K * (long)this.T - this.healing;
        long fatigue2 = (long)o.K * (long)o.T - o.healing;
        if(fatigue1 < fatigue2) return -1;
        else if(fatigue1 == fatigue2) return 0;
        else return 1;
         */
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ArrayList<PS> list = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            list.add(new PS(K, T));
        }
        Collections.sort(list);
        long curFatigue = 0;
        long time = 0;
        for(int i = 0 ; i < N ; i ++){
            PS tmp = list.get(i);
            if(curFatigue + ((long)tmp.K * tmp.T) > L){
                time += (curFatigue + ((long)tmp.K * tmp.T)) - L;
                time += tmp.T;
                curFatigue = L;
                curFatigue -= tmp.healing;
            }
            else{
                time += tmp.T;
                curFatigue += tmp.K * tmp.T;
                curFatigue -= tmp.healing;
            }
        }
        System.out.println(time);
    }
}
