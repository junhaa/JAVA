import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #20187 종이접기
public class Main {
    static int[][] map;
    static char[] arr;
    static int H, k;
    static int getUD(int num){
        return (num + 2) % 4;
    }
    static int getRL(int num){
        return num % 2 == 0 ? num + 1 : num - 1;
    }
    static void recursive(int y, int x, int w, int h, int idx){
        if(idx == k * 2){
            map[y][x] = H;
            return;
        }
        char curQ = arr[idx];
        if(curQ == 'U'){
            recursive(y, x, w, h / 2, idx + 1);
            y += h / 2;
            for(int i = 0 ; i < h / 2 ; i ++){
                for(int j = 0 ; j < w ; j ++){
                    map[y + i][x + j] = getUD(map[y + i - (i * 2 + 1)][x + j]);
                }
            }
        }
        else if(curQ == 'D'){
            recursive(y + h / 2, x, w, h / 2, idx + 1);
            for(int i = 0 ; i < h / 2 ; i ++){
                for(int j = 0 ; j < w ; j ++){
                    map[y + i][x + j] = getUD(map[y + i + ((h / 2 - i) * 2 - 1)][x + j]);
                }
            }
        }
        else if(curQ == 'R'){
            recursive(y, x + w / 2, w / 2, h, idx + 1);
            for(int i = 0 ; i < w / 2 ; i ++){
                for(int j = 0 ; j < h ; j ++){
                    map[y + j][x + i] = getRL(map[y + j][x + i + ((w / 2 - i) * 2 - 1)]);
                }
            }
        }
        else {
            recursive(y, x, w / 2, h, idx + 1);
            x += w / 2;
            for (int i = 0; i < w / 2; i++) {
                for (int j = 0; j < h; j++) {
                    map[y + j][x + i] = getRL(map[y + j][x + i - (i * 2 + 1)]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = (int)Math.pow(2, k);

        map = new int[len][len];
        arr = new char[2 * k];
        for(int i = 0 ; i < 2 * k ; i ++){
            arr[i] = st.nextToken().charAt(0);
        }
        H = Integer.parseInt(br.readLine());
        recursive(0, 0, len, len, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j < len ; j ++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
