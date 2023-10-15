import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
// BOJ #16499 동일한 단어 그룹화하기
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<HashMap<Character, Integer>> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            HashMap<Character, Integer> tmp = new HashMap<>();
            for(int j = 0 ; j < input.length(); j ++){
                char tmpChar = input.charAt(j);
                tmp.put(tmpChar, tmp.getOrDefault(tmpChar, 0) + 1);
            }
            boolean flag = false;
            for(HashMap<Character, Integer> o : list){
                if(o.equals(tmp)){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list.add(tmp);
            }
        }
        System.out.println(list.size());
    }
}
