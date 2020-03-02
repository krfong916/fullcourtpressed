class Solution {
  public int[] dailyTemperatures(int[] temperatures) {
    Stack<Integer> stack = new Stack<Integer>();
    int[] daysTillWarmerTemps = new int[temperatures.length];

    for (int currentDay = 0; currentDay < temperatures.length; currentDay++) {
      while (!stack.isEmpty() && temperatures[currentDay] > temperatures[stack.peek()]) {
        int day = stack.pop();
        daysTillWarmerTemps[day] = currentDay - day;
      }
      stack.push(currentDay);
    }
    return daysTillWarmerTemps;
  }
}
