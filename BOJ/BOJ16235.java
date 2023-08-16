import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #16235 나무 재테크
public class Main {
    static int N, M, K;
    static ArrayList<Integer>[][] map;
    static int[][] arr, food;
    static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 }, dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static void SpringSummer(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                ArrayList<Integer> tmpList = map[i][j];
                Collections.sort(tmpList);
                int foodSum = 0;
                int k;
                for(k = 0 ; k < tmpList.size() ; k ++){
                    int tmp = tmpList.get(k);
                    if(food[i][j] >= tmp){
                        food[i][j] -= tmp;
                        tmpList.set(k, tmp + 1);
                    }
                    else{
                        break;
                    }
                }
                int len = tmpList.size() - k;
                for(int l = 0 ; l < len ; l ++){
                    foodSum += tmpList.get(k) / 2;
                    tmpList.remove(k);
                }
                food[i][j] += foodSum;
            }
        }
    }

    static void Fall(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                for(int k = 0 ; k < map[i][j].size() ; k ++){
                    if(map[i][j].get(k) % 5 == 0){
                        for(int l = 0 ; l < 8 ; l ++){
                            int ny = i + dy[l];
                            int nx = j + dx[l];
                            if(!OOB(ny, nx)) map[ny][nx].add(1);
                        }
                    }
                }
            }
        }
    }

    static void Winter(){
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                food[i][j] += arr[i][j];
            }
        }
    }

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        else return true;
    }

    static int getSum(){
        int answer = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                answer += map[i][j].size();
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        arr = new int[N][N];
        food = new int[N][N];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(food[i], 5);
            for(int j = 0 ; j < N ; j ++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<Integer>();
            }
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()));
        }

        while(K -- > 0){
            SpringSummer();
            Fall();
            Winter();
        }

        System.out.println(getSum());
    }
}
