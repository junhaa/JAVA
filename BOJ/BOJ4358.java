import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// BOJ #4358 생태학
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		HashMap<String, Integer> map = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		int n = 0;
		//while((input = br.readLine()) != null) {
		int test = 29;
		while(test -- > 0) {
			input = br.readLine();
			n ++ ;
			map.put(input, map.getOrDefault(input, 0) + 1);
		}
		ArrayList<String> list = new ArrayList<>();
		for(String key : map.keySet()) {
			list.add(key);
		}
		Collections.sort(list);
		for(String name : list) {
			sb.append(name + " ");
			sb.append(String.format("%.4f",  (double)map.get(name) / n * 100) + "\n");
		}
		System.out.println(sb);
	}
}
