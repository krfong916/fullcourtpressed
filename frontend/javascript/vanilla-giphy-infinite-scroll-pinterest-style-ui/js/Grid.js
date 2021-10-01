// Calculates the grid column property based on screen width
// Receives a "container" DOM node, and assigns grid css properties based on the column calculations
// This function also registers a ResizeObserver to adjust column calculations based on screen resize
export class Grid {
  resizeObserver;
  columnWidth = parseFloat('210px');
  columnGap = parseFloat('10px');
  constructor(element) {
    this.element = element;
    this.resizeObserver = new ResizeObserver(() => {
      this.organizeGridColumns();
    });
    this.setupObserver();
  }

  get columns() {
    return Math.floor(
      (this.gridWidth + this.columnGap) / (this.columnWidth + this.columnGap)
    );
  }

  get gridWidth() {
    let gridStyle = getComputedStyle(this.element);
    let paddingLeft = parseFloat(gridStyle.getPropertyValue('padding-left'));
    let paddingRight = parseFloat(gridStyle.getPropertyValue('padding-right'));
    return (
      parseFloat(gridStyle.getPropertyValue('width')) - (paddingLeft + paddingRight)
    );
  }

  setupObserver() {
    this.resizeObserver.observe(this.element);
  }

  organizeGridColumns() {
    this.element.style.gridTemplateColumns = `repeat(${this.columns}, ${this.columnWidth}px)`;
    this.element.style.gridColumnGap = `${this.columnGap}px`;
  }
}
