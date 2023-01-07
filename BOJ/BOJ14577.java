import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #14577 일기예보
public class Main {

	static String[] q;
	static int[] tree;
	static long[] arr, idx,h;
	static ArrayList<Long> list;
	static int size;
	static HashSet<Long> set = new HashSet<>();
	static HashMap<Long, Integer> map = new HashMap<>();
	
	
	static int lowerBound(ArrayList<Long> data, long target) {
	    int begin = 0;
	    int end = data.size();
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data.get(mid) >= target) {
	        	end = mid;
	        }
	        else {
	        	begin = mid + 1;
	        }
	    }
	    return end;
	}
	
	static int upperBound(ArrayList<Long> data, long target) {
	    int begin = 0;
	    int end = data.size();
	    
	    while(begin < end) {
	    	int mid = (begin + end) / 2;
	        
	        if(data.get(mid) <= target) {
	        	begin = mid + 1;
	        }
	        else {
	        	end = mid;
	        }
	    }
	    return end;
	}
	
	static int findSum(int L, int R, int nodeNum, int nodeL, int nodeR) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return tree[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return findSum(L, R, nodeNum * 2, nodeL, mid) + findSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
	}
	
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int findNum(int num, int nodeNum) {
		if(nodeNum >= size / 2) {
			return nodeNum - size / 2;
		}
		if(num > tree[nodeNum * 2 + 1]) {
			return findNum(num - tree[nodeNum * 2 + 1], nodeNum * 2);
		}
		else {
			return findNum(num, nodeNum * 2 + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		h = new long[N];
		arr = new long[N];
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(st.nextToken());
			h[i] = tmp;
			arr[i] = tmp;
			set.add((long)tmp);
		}
		q = new String[M];
		for(int i = 0 ; i < M ; i ++) {
			q[i] = br.readLine();
		}
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(q[i]);
			int menu = Integer.parseInt(st.nextToken());
			if(menu == 1) {
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				arr[tmp] += Integer.parseInt(st.nextToken());
				set.add(arr[tmp]);
			}
			else if(menu == 2){
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				arr[tmp] -= Integer.parseInt(st.nextToken());
				set.add(arr[tmp]);
			}
			else if(menu == 3) {
				set.add(Long.parseLong(st.nextToken()));
				set.add(Long.parseLong(st.nextToken()));
			}
		}
		list = new ArrayList<>(set);
		idx = new long[list.size()];
		Collections.sort(list);
		for(int i = 0 ; i < list.size() ; i ++) {
			map.put(list.get(i), i);
			idx[i] = list.get(i);
		}
		size = 2;
		while(true) {
			if(size >= list.size()) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		for(int i = 0 ; i < h.length ; i ++) {
			tree[size / 2 + map.get(h[i])] ++;
		}
		T.construct();
		
		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(q[i]);
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				int tmp = Integer.parseInt(st.nextToken()) - 1;
				T.update(map.get(h[tmp]), -1);
				h[tmp] += Integer.parseInt(st.nextToken());
				T.update(map.get(h[tmp]), 1);
				break;
				
			case 2:
				int tmp1 = Integer.parseInt(st.nextToken()) - 1;
				T.update(map.get(h[tmp1]), -1);
				h[tmp1] -= Integer.parseInt(st.nextToken());
				T.update(map.get(h[tmp1]), 1);
				break;
				
			case 3:
				sb.append(T.findSum(map.get(Long.parseLong(st.nextToken())), map.get(Long.parseLong(st.nextToken())), 1, 0, size / 2 - 1) + "\n");
				break;
				
			case 4:
				sb.append(idx[T.findNum(Integer.parseInt(st.nextToken()), 1)] + "\n");
				break;
			}
		}
		System.out.println(sb);
	}
}
