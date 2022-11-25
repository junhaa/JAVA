import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2309 일곱 난쟁이
public class Main {

	public static void main(String[] args) throws IOException  {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		int sum = 0;
		for(int i = 0 ; i < 9 ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			list.add(tmp);
			sum += tmp;
		}
		Collections.sort(list);
		boolean flag = false;
		for(int i = 0 ; i < 8 ; i ++) {
			for(int k = i + 1 ; k < 9 ; k ++) {
				if(sum - (list.get(i) + list.get(k)) == 100) {
					flag = true;
					list.remove(k);
					list.remove(i);
					break;
				}
			}
			if(flag) break;
		}
		for(int i = 0 ; i < list.size() ; i ++) {
			System.out.println(list.get(i));
		}
	}
}
