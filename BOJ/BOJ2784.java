import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// BOJ #2784 가로 세로 퍼즐
public class Main {
    static boolean[] visited = new boolean[6];
    static String[] arr = new String[6];
    static char[][] map = new char[3][3];
    static boolean check(int x){
        if(x == 3) return true;
        String cur = "";
        boolean flag = false;
        for(int i = 0 ; i < 3 ; i ++){
            cur += map[i][x];
        }

        for(int i = 0 ; i < 6 ; i ++){
            if(!visited[i] && arr[i].equals(cur)){
                visited[i] = true;
                flag = check(x + 1);
                visited[i] = false;
                return flag;
            }
        }
        return flag;
    }

    static StringBuilder printMap(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 3 ; i ++){
            for(int j = 0 ; j < 3 ; j ++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0 ; i < 6 ; i ++){
            arr[i] = br.readLine();
        }

        for(int i = 0 ; i < 6 ; i ++){
            if(visited[i]) continue;
            visited[i] = true;
            for(int j = 0 ; j < 3 ; j ++){
                map[0][j] = arr[i].charAt(j);
            }
            for(int j = 0; j < 6 ; j ++){
                if(visited[j]) continue;
                visited[j] = true;
                for(int k = 0 ; k < 3 ; k ++){
                    map[1][k] = arr[j].charAt(k);
                }
                for(int k = 0 ; k < 6 ; k ++){
                    if(visited[k]) continue;
                    visited[k] = true;
                    for(int l = 0 ; l < 3 ; l ++){
                        map[2][l] = arr[k].charAt(l);
                    }
                    if(check(0)){
                        System.out.println(printMap());
                        System.exit(0);

                    }
                    visited[k] = false;
                }
                visited[j] = false;
            }
            visited[i] = false;
        }
        System.out.println("0");
    }
}
