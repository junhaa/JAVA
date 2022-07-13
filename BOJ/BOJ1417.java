import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1417 국회의원 선거
public class Main {

	public static int solution(int[] arr) {
		int ds = arr[1];
		int index = 0;
		int answer = 0 ;
		while(true) {
			int max = Integer.MIN_VALUE;
			for(int i = 2 ; i < arr.length ; i ++) {
				if(arr[i] > max) {
					max = arr[i];
					index = i;
				}
			}
			if(max >= ds) {
				ds++;
				arr[index] --;
				answer ++;
			}
			else return answer;
		}
	}
	
	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		for(int i = 1 ; i <= N ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(T.solution(arr));
	}
}
