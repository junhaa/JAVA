import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
// BOJ #3005 크로스워드 퍼즐 쳐다보기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        List<String> list = new ArrayList<>();
        for(int i = 0 ; i < R ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < C ; j ++){
                map[i][j] = input.charAt(j);
            }
        }

        for(int i = 0 ; i < R ; i ++){
            String cur = "";
            for(int j = 0 ; j < C ; j ++){
                if(map[i][j] != '#'){
                    cur += map[i][j];
                }
                else{
                    if(cur.length() > 1){
                        list.add(cur);
                    }
                    cur = "";
                }
            }
            if(cur.length() > 1) list.add(cur);
        }

        for(int i = 0 ; i < C ; i ++){
            String cur = "";
            for(int j = 0 ; j < R ; j ++){
                if(map[j][i] != '#'){
                    cur += map[j][i];
                }
                else{
                    if(cur.length() > 1){
                        list.add(cur);
                    }
                    cur = "";
                }
            }
            if(cur.length() > 1) list.add(cur);
        }

        Collections.sort(list);
        System.out.println(list.get(0));
    }
}
