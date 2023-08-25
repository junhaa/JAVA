import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #2258 정육점
class Meat implements Comparable<Meat> {
    int cost, weight;
    public Meat(int weight, int cost){
        this.weight = weight;
        this.cost = cost;
    }
    @Override
    public int compareTo(Meat O){
        if(this.cost == O.cost) return O.weight - this.weight;
        else return this.cost - O.cost;
    }

}
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Meat> list = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            list.add(new Meat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        long min = Long.MAX_VALUE;
        int sum = 0;
        int costSum = 0;
        int curCost = -1;

        for(int i = 0 ; i < N ; i ++){
            Meat tmp = list.get(i);
            if(curCost != tmp.cost){
                costSum = tmp.cost;
                curCost = tmp.cost;
            }
            else{
                costSum += tmp.cost;
            }
            sum += tmp.weight;
            if(sum >= M){
                min = Math.min(min, costSum);
            }
        }

        if(min == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}
