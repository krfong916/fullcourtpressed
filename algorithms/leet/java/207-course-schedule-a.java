class Solution {

  private int[] color;
  private boolean hasCycle = false;

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites.length == 0) return true;

    // build adj list
    ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(numCourses);
    for (int i = 0; i < numCourses; i++) {
      adjList.add(new ArrayList<Integer>());
    }
    for (int[] course : prerequisites) {
      adjList.get(course[1]).add(course[0]);
    }

    // allocate colors array
    color = new int[numCourses];

    // call dfs to detect a cycle
    for (int i = 0; i < adjList.size(); i++) {
      if (color[i] == 0) {
        canFinishHelper(adjList, i);
      }
    }

    if (hasCycle == false) {
      return true;
    } else {
      return false;
    }
  }

  private void canFinishHelper(ArrayList<ArrayList<Integer>> graph, int u) {
    color[u] = 1;
    for (int i = 0; i < graph.get(u).size(); i++) {
      int v = graph.get(u).get(i);
      if (color[v] == 0) {
        canFinishHelper(graph, v);
      } else if (color[v] == 1) {
        hasCycle = true;
      }
    }
    color[u] = 2;
    return;
  }
}
