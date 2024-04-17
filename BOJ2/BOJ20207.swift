// BOJ #20207 - 달력

import Foundation

func fillPrefix(N : Int, prefix: [Int]) -> [Int] {
    var curPrefix = prefix;
    for i in 1...366{
        curPrefix[i] += curPrefix[i - 1];
    }
    return curPrefix;
}

func getMax(a : Int, b : Int) -> Int{
    if(a > b){
        return a;
    }
    else{
      return b;  
    } 
}

func getSum(N : Int, prefix : [Int]) -> Int {
    var curSum : Int = 0;
    var seq : Int = 0;
    var max : Int = 0;
    for i in 1...366{ // 0-based
        if(prefix[i] > 0){
            seq += 1
            max = getMax(a :max, b :prefix[i])
        }
        else{
            curSum += seq * max;
            max = 0
            seq = 0
        }
    }
    return curSum;
}


let N = Int(readLine()!)!

var prefix = [Int](repeating: 0, count : 367) // 0-based


for _ in 1...N {
    let input:[Int] = readLine()!.split(separator: " ").map { Int(String($0))! }
    
    let start = input[0] 
    let end = input[1]
    
    prefix[start] += 1
    prefix[end + 1] -= 1
}


prefix = fillPrefix(N : N, prefix: prefix);
    
print(getSum(N: N, prefix: prefix));










