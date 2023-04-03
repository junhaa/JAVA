import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #3078 좋은 친구 
public class Main {

	static Queue<Integer>[] Qlist = new Queue[20];
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < 20 ; i ++) {
			Qlist[i] = new LinkedList<Integer>();
		}
		long answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = br.readLine().length() - 1;
			if(!Qlist[tmp].isEmpty()) {
				while(!Qlist[tmp].isEmpty() && Qlist[tmp].peek() + K < i) {
					Qlist[tmp].poll();
				}
				answer += Qlist[tmp].size();
			}
			Qlist[tmp].add(i);
		}
		System.out.println(answer);
	}
}
