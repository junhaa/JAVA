import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// BOJ #15899 트리와 색깔 
public class Main {

	private static class FastIO {
		final private int BUFFER_SIZE = 1 << 16;
		final private int INT_SIZE = 10;
		private DataInputStream din;
		private DataOutputStream dout;

		private byte[] inbuffer;
		private int inputbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outputbufferpointer;
		private byte[] bytebuffer;
		
		FastIO() {
			din = new DataInputStream(System.in);
			dout = new DataOutputStream(System.out);
			inputbufferpointer = bytesread = outputbufferpointer = 0;
			bytebuffer = new byte[INT_SIZE];
			inbuffer = new byte[BUFFER_SIZE];
			outbuffer = new byte[BUFFER_SIZE];
			
			
		}
		
		public int nextInt() throws IOException { 
            int ret = 0; 
            byte b = read(); 
            while (b <= ' ') 
                b = read(); 
            boolean isneg = (b == '-'); 
            if (isneg) 
                b = read(); 
            do { 
                ret = ret * 10 + b - '0';
            } while ((b = read()) >= '0' && b <= '9'); 
  
            if (isneg) 
                return -ret; 
            return ret; 
        }
		
		private byte read() throws IOException { 
            if (inputbufferpointer == bytesread) 
                fillbuffer(); 
            return inbuffer[inputbufferpointer++]; 
        }
		
		private void fillbuffer() throws IOException { 
            bytesread = din.read(inbuffer, inputbufferpointer = 0, BUFFER_SIZE); 
            if (bytesread == -1) 
                inbuffer[0] = -1; 
        }
		
		public void write(int i) {
			if (i == 0) {
				writebuffer((byte) '0');
				return;
			}

			if (i < 0) {
				writebuffer((byte) '-');
				i = -i;
			}

			int index = 0;
			while (i > 0) {
				bytebuffer[index++] = (byte) ((i % 10) + '0');
				i /= 10;
			}

			while (index-- > 0)
				writebuffer(bytebuffer[index]);
		}
		
		public void writels(int i) {
			write(i);
			writebuffer((byte)' ');
		}
		
		private void writebuffer(byte b) {
			if (outputbufferpointer == outbuffer.length) {
				flushbuffer();
			}
			outbuffer[outputbufferpointer++] = b;
		}
		
		private void flushbuffer() {
			if (outputbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outputbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			outputbufferpointer = 0;
		}		
	}



	static int[] color, startIdx, endIdx;
	static boolean[] visited;
	static ArrayList<Integer>[] tree, list;
	static int size = 2 ,idx = -1;
	
	
	static void DFS(int i) {
		startIdx[i] = ++idx;
		visited[i] = true;
		for(int next : list[i]) {
			if(!visited[next]) {
				DFS(next);
			}
		}
		endIdx[i] = idx;
	}
	
	static int upper_bound(int nodeNum, int val) {
		ArrayList<Integer> list = tree[nodeNum];
		int lt = 0;
		int rt = list.size();
		int mid;
		while(lt < rt) {
			mid = (lt + rt) / 2;
			if(val >= list.get(mid)) {
				lt = mid + 1;
			}
			else {
				rt = mid;
			}
		}
		return rt;
	}
	
	/*
	 * static void construct() { for(int i = size / 2 - 1 ; i > 0 ; i --) { int lt =
	 * 0; int rt = 0; while(lt < tree[i * 2].size() || rt < tree[i * 2 + 1].size())
	 * { if(rt >= tree[i * 2 + 1].size() || (lt < tree[i * 2].size() && tree[i *
	 * 2].get(lt) < tree[i * 2 + 1].get(rt))) { tree[i].add(tree[i * 2].get(lt++));
	 * } else { tree[i].add(tree[i * 2 + 1].get(rt++)); } } } }
	 */
	
	static void construct() {
		for(int i = size / 2 - 1 ; i > 0 ; i --) {
			ArrayList<Integer> c = tree[i], lt = tree[i * 2], rt = tree[i * 2 + 1];
			for(int j = 0, p = 0, q = 0 ; j < lt.size() + rt.size() ; j ++) {
				if(q == rt.size() || (p < lt.size() && lt.get(p) < rt.get(q))) c.add(lt.get(p++));
				else c.add(rt.get(q++));
			}
		}
	}
	
	
	static long findNum(int L, int R, int nodeNum, int nodeL, int nodeR, int val) {
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return upper_bound(nodeNum, val);
		int mid = (nodeL + nodeR) / 2;
		return findNum(L, R, nodeNum * 2, nodeL, mid, val) + findNum(L, R, nodeNum * 2 + 1, mid + 1, nodeR, val);
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		FastIO myIO = new FastIO();
		int N = myIO.nextInt();
		int M = myIO.nextInt();
		int C = myIO.nextInt();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new ArrayList[size];
		list = new ArrayList[N];
	
		for(int i = 0 ; i < size ; i ++) {
			if(i < N) {
				list[i] = new ArrayList<>();
			}
			tree[i] = new ArrayList<>();
		}
		color = new int[N];
		startIdx = new int[N];
		endIdx = new int[N];
		visited = new boolean[N];
		
		for(int i = 0 ; i < N ; i ++) {
			color[i] = myIO.nextInt();
		}
		
		for(int i = 1 ; i < N ; i ++) {
			int start = myIO.nextInt() - 1;
			int end = myIO.nextInt() - 1;
			list[start].add(end);
			list[end].add(start);
		}
		long answer = 0;
		T.DFS(0);
		
		for(int i = 0 ; i < N ; i ++) {
			tree[startIdx[i] + size / 2].add(color[i]);
		}
		T.construct();
		for(int i = 0 ; i < M ; i ++) {
			int node = myIO.nextInt() - 1;
			int cost = myIO.nextInt();
			answer += T.findNum(startIdx[node], endIdx[node], 1, 0, size / 2 - 1, cost);
			answer %= 1000000007;
		}
		bw.write(String.valueOf(answer));
		bw.flush();
	}
}
