import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static void write(ArrayList<String> list, int word, int idx){
        for(int i = 0 ; i < list.size() ; i ++){
            String cur = list.get(i);
            if(word == i) {
                for(int j = 0 ; j < cur.length() ; j ++){
                    if(j == idx){
                        sb.append("[" + cur.charAt(j) + "]");
                    }
                    else{
                        sb.append(cur.charAt(j));
                    }
                }
                sb.append(" ");
            }
            else{
                sb.append(cur + " ");
            }
        }
        sb.append("\n");
    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashSet<Character> set = new HashSet<>();
        for(int i = 0 ; i < N ; i ++){
            boolean flag = false;
            ArrayList<String> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int len = st.countTokens();
            for(int j = 0 ; j < len ; j ++){
                list.add(st.nextToken());
            }
            for(int j = 0 ; j < list.size() ; j ++){
                if(!set.contains(Character.toUpperCase(list.get(j).charAt(0)))){
                    flag = true;
                    set.add(Character.toUpperCase(list.get(j).charAt(0)));
                    write(list, j, 0);
                    break;
                }
            }
            if(flag) continue;
            for(int j = 0 ; j < list.size() ; j ++){
                String cur = list.get(j);
                for(int k = 1 ; k < cur.length() ; k ++){
                    if(!set.contains(Character.toUpperCase(list.get(j).charAt(k)))){
                        flag = true;
                        set.add(Character.toUpperCase(list.get(j).charAt(k)));
                        write(list, j, k);
                        break;
                    }
                }
                if(flag) break;
            }
            if(!flag){
                for(String s : list) {
                    sb.append(s + " ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
