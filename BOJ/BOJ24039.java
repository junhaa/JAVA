import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// BOJ #24039 2021은 무엇이 특별할까 ?
public class Main {

	static boolean[] ch = new boolean[151];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 2 ; i < 150 ; i ++) {
			for(int k = i * 2 ; k < 150 ; k += i) {
				ch[k] = true;
			}
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		ch[0] = true;
		ch[1] = true;
		int last = 2;
		for(int i = 3 ; i < 150 ; i ++) {
			if(!ch[i]) {
				list.add(i * last);
				last = i;
			}
		}
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < list.size(); i ++) {
			if(list.get(i) > N) {
				System.out.println(list.get(i));
				return;
			}
		}
		System.out.println(-1);
	}
}
