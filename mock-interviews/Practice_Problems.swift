class Solution {
    func climbStairs(_ n: Int) -> Int {
        guard n > 1 else { return n }
        var total = ["\(n)": 1]
        return climb(&total, 0, n).count
    }

    func climb(_ totalVals: inout [String:Int],_ num: Int,_ target: Int) -> [String:Int] {
        guard num <= target else { 
            return 0
        }

        if num == target { 
            totalVals += 1
            return 0
        }

        
        climb(&totalVals, num+1, target)
        climb(&totalVals, num+2, target)



        return totalVals
    }
}

// 

public int climb(int n) {
    if (n <= 0) return 0;
    int[] memo = new int[n+1]; // [0,0,0,0,0,0]
    climbHelper(n, memo);
    return memo[n];
    
}

n = 3
n = 5, max = 8

n = 6

step(6) = step(5) + step(4)

step(1) = 1
step(2) = 2
step(3) = step(1) + step(2)
.
.
.
step(4) = step(3) + step(2)
.
.
.
step(i) = step(i-1) + step(i-2)

Set<String>
give me all configurations of this alphabet
size of the alphabet


// Recurrence relation: Step(n) = step(n-1) + step(n-2)
// top-down w/ memoization
private void climbHelper(int currentStep, int[] memo) {
    if (memo[i] > 0) return memo[i];
    if (currentStep == 1) return 1;
    if (currentStep == 2) return 2;
    memo[currentStep] = climbHelper(currentStep - 1, memo) + climbHelper(currentStep - 2, memo);
}

class Solution {
  public int rob(int[] nums) {
    // pre: number of houses must be defined
    if (nums.length == 0) return 0;

    int[] memo = new int[nums.length + 1];
    memo[0] = 0;
    memo[1] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      memo[i + 1] = Math.max(nums[i] + memo[i - 1], memo[i]);
    }

    return memo[nums.length];
  }
}

// bottom-up: the result of solving the smallest problems optimally will yield a globally optimal 
// ("nth-optimal") solution 
public int climb(int n) {
    if (n <= 2) return n;
    int[] totalWaysOfPreviousSteps = new int[n+1];
    totalWaysOfPreviousSteps[1] = 1;
    totalWaysOfPreviousSteps[2] = 2;
    for (int i = 3; i <= n; i++) {
        totalWaysOfPreviousSteps[i] = totalWaysOfPreviousSteps[i-1] + totalWaysOfPreviousSteps[i-2];
    }
    return totalWaysOfPreviousSteps[n];
}
O(1): observe: we only that we only need to reference one step below and two steps below to get a our answer for n steps
public int climb(int n) {
    if (n <= 2) return n;
    int oneStepBelow = 2; // 3-1
    int twoStepsBelow = 1; // 3-2
    int totalWays = 0;
    for (int i = 3; i <= n; i++) {
        // this will be the value of one step after adding the current total ways
        int prevTotalWays = totalWays;

        // recurrence relation
        totalways = oneStepBelow + twoStepsBelow;
        
        // update steps
        twoStepsBelow = oneStepBelow;
        oneStepBelow = prevTotalWays
    }
    return totalWays;
}

given currentStep = oneStepBelow + twoStepsBelow



n = 3

n = 2, max = 2
n = 1, max = 1






3
111 : totalVal = 1
21 : totalVal = 2
12 : 1
21 : 1


0
1
2
3

// memoize: perform smart recursion
// we will not repeat duplicate fn() calls in our recursive call tree
// what should we store? how do we know we've already performed computation on a call
// key: ,