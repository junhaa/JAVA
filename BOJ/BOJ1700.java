import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1700 멀티탭 스케줄링

public class Main{
	static int[] arr; // 순서
	static int[] plug; // 멀티탭
	static int solution(int K, int N) {
		if(K <= N) return 0;
		int answer = 0;
		for(int i = 0 ; i < K ; i ++) {
			int tmp = arr[i];
			boolean flag = true;; // 플러그를 뽑아야 하는지 
			for(int j = 0 ; j < N ; j ++) {
				if(plug[j] == tmp) {
					flag =false;
					break;
				}
				else if(plug[j] == 0) {
					plug[j] = tmp;
					flag =false;
					break;
				}
			}
			if(!flag) continue;
			int[] idx = new int[K + 1]; // 다음에 오는 인덱스 저장
			for(int k = i ; k < K ; k ++) {
				if(idx[arr[k]] == 0) {
					idx[arr[k]] = k;
				}
			}
			int maxIdx = -1;
			int maxNum = Integer.MIN_VALUE;
			for(int k = 0 ; k < N ; k ++) {
				if(idx[plug[k]] == 0) {
					maxIdx = k;
					break;
				}
				if(idx[plug[k]] > maxNum) {
					maxNum = idx[plug[k]];
					maxIdx = k;
				}
			}
			plug[maxIdx] = arr[i];
			answer ++;
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수
		int K = Integer.parseInt(st.nextToken()); // 전기 용품의 총 사용횟수
		arr = new int[K];
		plug = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < K ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(K, N));
	}
}

/*public class Main { // WA
	static int[] arr; // 순서
	static int[] freq; // 멀티탭 수까지 사용 빈도
	static int[] freqAll; // 전체 사용
	static int[] plug;

	static int solution(int K, int N) {
		if (K <= N)	return 0;
		int answer = 0;
		int idx = 0;
		boolean freqF = false; // freq 배열
		for (int k = 0; k < K; k++) {
			int tmp = arr[k];
			boolean flag = false; // 이미 플러그가 꼽혀 있는지 
			for (int i = 0; i < N; i++) {
				if(plug[i] == tmp) { 
					if(freqF) {
						if(idx != K) {
							freq[arr[idx]] ++;
							idx ++;
						}
						freq[arr[k]] --;
					}
					flag = true;
					freqAll[tmp] --;
					break;
				}
				else if(plug[i] == 0) {
					flag = true;
					plug[i] = tmp;
					freqAll[tmp] --;
					break;
				}
			}
			if(flag) continue;
			// 플러그를 뽑아야 할 때
			if(!freqF) {
				freqF = true;
				idx = k;
				while(idx - k < N) {
					if(idx == K) break;
					int ftmp = arr[idx];
					freq[ftmp] ++;
					idx ++;
				}
			}
			int minIdx = -1;
			int minNum = Integer.MAX_VALUE;
			int minFreq = Integer.MAX_VALUE;
			for(int j = 0 ; j < N ; j ++) {
				if(freq[plug[j]] < minNum) {
					minIdx = j;
					minFreq = freqAll[plug[j]];
					minNum = freq[plug[j]];
				}
				else if(freq[plug[j]] == minNum && minFreq > freqAll[plug[j]]) {
					minIdx = j;
					minFreq = freqAll[plug[j]];
				}
			}
			plug[minIdx] = arr[k];
			freqAll[arr[k]] --;
			if(idx != K) {
				freq[arr[idx]] ++;
				idx ++;
			}
			freq[arr[k]] --;
			answer ++;
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍의 개수
		int K = Integer.parseInt(st.nextToken()); // 전기 용품의 총 사용횟수
		arr = new int[K];
		plug = new int[N];
		freq = new int[K + 1];
		freqAll = new int[K + 1];
		String input = br.readLine();
		st = new StringTokenizer(input);
		for (int i = 0; i < K; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			arr[i] = tmp;
			freqAll[tmp] ++;
		}
		System.out.println(T.solution(K, N));
	}
}*/
