import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #1713 후보 추천하기 
class Vote implements Comparable<Vote>{
	int candidate;
	int cnt;
	public Vote(int candidate, int cnt) {
		this.candidate = candidate;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Vote o) {
		return this.candidate - o.candidate;
	}
}
public class Main {
	public static void solution(int N, int M, int[] arr) {
		ArrayList<Vote> list = new ArrayList<>();
		for(int i = 0 ; i < M ; i ++) {
				boolean flag = false; // list에 X
				for(int k = 0 ; k < list.size() ; k ++) {
					if(list.get(k).candidate == arr[i] ) {
						list.get(k).cnt ++;
						flag = true;
						break;
					}
				}
				if(!flag) {
					if(list.size() < N) { // 사진틀이 비어있을 때
						list.add(new Vote(arr[i], 1));
					}
					else { // 사진틀이 모두 찼을 때
						int index = -1;
						int min = Integer.MAX_VALUE;
						for(int k = 0 ; k < N ; k ++) {
							if(list.get(k).cnt < min) {
								min = list.get(k).cnt;
								index = k;
							}
						}
						list.remove(index);
						list.add(new Vote(arr[i], 1));
					}
				}
		}
		Collections.sort(list);
		for(Vote x : list) {
			System.out.print(x.candidate + " ");
		}
		
	}

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[M];
		for(int i = 0 ; i < M ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		T.solution(N, M, arr);
	}
}
