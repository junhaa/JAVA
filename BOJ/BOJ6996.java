import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #6996 애너그램
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		while(test -- > 0) {
			boolean flag = false;
			st = new StringTokenizer(br.readLine());
			HashMap<Character, Integer> map = new HashMap<>();
			String input1 = st.nextToken();
			String input2 = st.nextToken();
			for(int i = 0 ; i < input1.length() ; i ++) {
				map.put(input1.charAt(i), map.getOrDefault(input1.charAt(i), 0) + 1); 
			}
			for(int i = 0 ; i < input2.length() ; i ++) {
				if(!map.containsKey(input2.charAt(i))) {
					flag = true;
					break;
				}
				map.put(input2.charAt(i), map.get(input2.charAt(i)) - 1);
				if(map.get(input2.charAt(i)) == 0) map.remove(input2.charAt(i));
				
			}
			if(flag) sb.append(input1 + " & " + input2 + " are NOT anagrams." + "\n");
			else if(!map.isEmpty()) {
				sb.append(input1 + " & " + input2 + " are NOT anagrams." + "\n");
			}
			else sb.append(input1 + " & " + input2 + " are anagrams." + "\n");
		}
		System.out.println(sb);
	}
}
