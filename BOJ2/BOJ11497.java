import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
// BOJ #11497 통나무 건너뛰기
public class Main {
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(T -- > 0){
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<>();
			for(int i = 0 ; i < N ; i ++){
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list);
			int[] arr = new int[N];
			for(int i = 0 ; i < N ; i ++){
				if(i % 2 == 0){
					arr[i / 2] = list.get(i);
				}
				else{
					arr[N - 1 - i / 2] = list.get(i);
				}
			}
			sb.append(main.getDifficult(arr, N) + "\n");
		}
		System.out.println(sb);
	}

	private int getDifficult(int[] arr, int size){
		int max = Math.abs(arr[size - 1] - arr[0]);
		for(int i = 1 ; i < size ; i ++){
			max = Math.max(Math.abs(arr[i] - arr[i - 1]), max);
		}
		return max;
	}
}
