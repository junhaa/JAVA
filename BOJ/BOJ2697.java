import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
// BOJ #2697 다음수 구하기
public class Main {
    static String getNextNum(String input){
        String ans = "";
        int len = input.length();
        HashMap<Integer, Integer> map = new HashMap<>();
        int last = -1;
        boolean flag = false;
        int idx = len - 1;
        for(idx = len - 1 ; idx >= 0 ; idx --){
            int cur = input.charAt(idx) - '0';
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if(cur < last){
                last = cur;
                flag = true;
                break;
            }
            last = cur;
        }
        if(!flag) return "BIGGEST";
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(int i = 0 ; i < list.size() ; i ++){
            if(list.get(i) > last){
                ans += list.get(i);
                map.put(list.get(i), map.get(list.get(i)) - 1);
                if(map.get(list.get(i)) == 0){
                    map.remove(list.get(i));
                    list.remove(list.get(i));
                }
                break;
            }
        }
        for(int i = 0 ; i < list.size() ; i ++){
            int cur = list.get(i);
            for(int j = 0 ; j < map.get(cur) ; j ++){
                ans += cur;
            }
        }
        return input.substring(0, idx) + ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String input = br.readLine();
            sb.append(getNextNum(input) + "\n");
        }
        System.out.println(sb);
    }
}
