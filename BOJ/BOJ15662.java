// BOJ #15662 톱니바퀴 (2)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] gear; //  0 2 6
    static boolean[] visited;
    static int N;
    static void turnC(int num){
        int tmp = gear[num].get(7);
        gear[num].add(0, tmp);
        gear[num].remove(8);
    }

    static void turnAC(int num){
        int tmp = gear[num].get(0);
        gear[num].add(tmp);
        gear[num].remove(0);
    }

    static void turnGear(int num, int dir){ // dir 0 시계방향 1 반시계
        visited[num] = true;
        if(num != 0 && !visited[num - 1]){
            if(gear[num].get(6) + gear[num - 1].get(2) == 1) {
                turnGear(num - 1, 1 - dir);
            }
        }
        if(num != N - 1 && !visited[num + 1]){
            if(gear[num].get(2) + gear[num + 1].get(6) == 1){
                turnGear(num + 1, 1 - dir);
            }
        }
        if(dir == 1){
            turnAC(num);
        }
        else turnC(num);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        gear = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            gear[i] = new ArrayList<>();
            String input = br.readLine(); // N극은 0 S극은 1
            for(int j = 0 ; j < 8 ; j ++){
                gear[i].add(input.charAt(j) - '0');
            }
        }
        //Query
        int K = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < K ; i ++){
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            if(dir == -1) dir = 1;
            else dir = 0;
            turnGear(num, dir);
        }
        int answer = 0;
        for(int i = 0 ; i < N ; i ++ ) {
            if(gear[i].get(0) == 1) answer ++;
        }
        System.out.println(answer);
    }
}
