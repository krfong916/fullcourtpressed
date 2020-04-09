class MedianFinder {

  enum SIZES {
    LEFT_HAS_MORE,
    RIGHT_HAS_MORE,
    EQUAL_SIZE,
    INITIAL
  }

  PriorityQueue<Integer> lessThan;
  PriorityQueue<Integer> greaterThan;

  public MedianFinder() {
    lessThan = new PriorityQueue<Integer>((a, b) -> b - a); // setup max-heap i.e. "left"-heap
    greaterThan = new PriorityQueue<Integer>(); // setup min-heap i.e. "right"-heap
  }

  public void addNum(int num) {
    SIZES size = getSizesOfHeaps(this.lessThan, this.greaterThan);
    switch (size) {
    case LEFT_HAS_MORE:
      addToRight(num);
      break;
    case RIGHT_HAS_MORE:
      addToLeft(num);
      break;
    case EQUAL_SIZE:
      addToEither(num);
      break;
    case INITIAL:
      addToInitial(num);
      break;
    default: // initial
      throw new Error("must insert into a heap");
    }
  }

  private static SIZES getSizesOfHeaps(PriorityQueue<Integer> lessThan, PriorityQueue<Integer> greaterThan) {
    if (lessThan.size() == 0 || greaterThan.size() == 0) {
      return SIZES.INITIAL;

      // 1: less.s > greater.s
    } else if (lessThan.size() > greaterThan.size()) {
      return SIZES.LEFT_HAS_MORE;

      // 2: less.s == greater.s
    } else if (lessThan.size() == greaterThan.size() && lessThan.size() > 0 && greaterThan.size() > 0) {
      return SIZES.EQUAL_SIZE;

      // 3: less.s < greater.s
    } else if (lessThan.size() < greaterThan.size()) {
      return SIZES.RIGHT_HAS_MORE;

    } else {
      return SIZES.LEFT_HAS_MORE;
    }
  }

  private void addToInitial(int num) {
    if (this.lessThan.size() == 0) {
      this.lessThan.add(num);
    } else if (this.lessThan.size() == 1 && this.greaterThan.size() == 0) {
      if (num < this.lessThan.peek()) {
        int max = this.lessThan.poll();
        this.greaterThan.add(max);
        this.lessThan.add(num);
      } else {
        this.greaterThan.add(num);
      }
    }

  }

  // "left"-heap has more elements than "right"-heap
  private void addToRight(int num) {
    // num <= less[max]
    if (num <= this.lessThan.peek()) {
      int max = this.lessThan.poll();
      this.greaterThan.add(max);
      this.lessThan.add(num);

      // num > less[max] && num < greater[min]
    } else if (num > this.lessThan.peek() && num <= this.greaterThan.peek()) {
      this.greaterThan.add(num);

      // num > greater[min]
    } else if (num > this.greaterThan.peek()) {
      this.greaterThan.add(num);
    }
  }

  // "left"-heap has less elements than "right"-heap
  private void addToLeft(int num) {
    // num <= less[max]
    if (num <= this.lessThan.peek()) {
      this.lessThan.add(num);

      // num > less[max] && num < greater[min]
    } else if (num > this.lessThan.peek() && num <= this.greaterThan.peek()) {
      this.lessThan.add(num);

      // num > greater[min]
    } else if (num > this.greaterThan.peek()) {
      int min = this.greaterThan.poll();
      this.lessThan.add(min);
      this.greaterThan.add(num);
    }
  }

  // "left"-heap and "right"-heap have equal number of elements
  private void addToEither(int num) {
    // num <= less[max]
    if (num <= this.lessThan.peek()) {
      this.lessThan.add(num);

      // num > less[max] && num < greater[min]
    } else if (num > this.lessThan.peek() && num <= this.greaterThan.peek()) {
      this.lessThan.add(num);

      // num > greater[min]
    } else if (num > this.greaterThan.peek()) {
      this.greaterThan.add(num);
    }
  }

  /**
   * TODO: check double boxing
   */
  public double findMedian() {
    // 1: less.s > greater.s
    if (this.lessThan.size() > this.greaterThan.size()) {
      return (double) this.lessThan.peek();

      // 2: less.s == greater.s
    } else if (this.lessThan.size() == this.greaterThan.size()) {
      return (double) (this.lessThan.peek() + this.greaterThan.peek()) / 2;

      // 3: less.s < greater.s
    } else {
      return (double) this.greaterThan.peek();
    }
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
