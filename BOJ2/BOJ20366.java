import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #20366 같이 눈사람 만들래?
public class Main {
	static int N, arr[], answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i = 0 ; i < N ; i ++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		for(int i = 0 ; i < N ; i ++){
			for(int j = i + 3 ; j < N ; j ++){
				main.twoPointer(i, j);
				if(answer == 0){
					System.out.println(answer);
					return;
				}
			}
		}
		System.out.println(answer);
	}

	private void twoPointer(int p1, int p2){
		int lt = p1 + 1;
		int rt = p2 - 1;
		int pSum = arr[p1] + arr[p2];

		while(lt < rt){
			int curSum = arr[lt] + arr[rt];
			if(Math.abs(curSum - pSum) < answer){
				answer = Math.abs(curSum - pSum);
			}
			if(curSum > pSum){
				rt --;
			}
			else{
				lt ++;
			}
		}
	}

}
