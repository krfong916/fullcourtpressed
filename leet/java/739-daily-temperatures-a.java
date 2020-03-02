class Solution {
  public int[] dailyTemperatures(int[] T) {
    int startOfWindow = 0; int endOfWindow = 0;
    int currentTemp = 0;
    int daysTillWarmer = 0;
    int[] daysTillWarmerTemps = new int[T.length];

    for (int i = 0; i < T.length; i++) {
      currentTemp = T[i];
      startOfWindow = i; endOfWindow = i;
      daysTillWarmer = 0;
      while (endOfWindow < T.length) {
        if (T[endOfWindow] > currentTemp) {
          daysTillWarmer = endOfWindow - startOfWindow;
          break;
        } else {
          endOfWindow++;
        }
      }
      T[i] = daysTillWarmer;
    }
    return T;
  }
}