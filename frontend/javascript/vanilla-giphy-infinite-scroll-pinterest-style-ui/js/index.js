// Implement a global progress bar at the top of the page, indicating APIs are in progress.
// Share some thoughts on natural animation
// - multiple APIs, how do you display a loading bar for that?
//   - (promise.all and bump up the loading bar)
// - error case
// - timeout
// - fast & slow request
// - state management
// - modularization
// - API cancellation .etc

// when to catch in a promise chain?
// how to handle null gif images

import '../css/index.css';
import { GiphyService } from './GiphyService';
import { Grid } from './Grid';

let el = document.querySelector('.gif-grid');
const grid = new Grid(el);
const GiphyResponse = GiphyService.search();
CreateGridItems(GiphyResponse);

/* *************************
 *
 * CreateGridItems
 * Create grid items based on giphy response object
 * Order matters here,
 * Download the images first, and append to DOM ->
 * then calculate the CSS Grid layout
 * If we calculate the Grid Layout before the images are appended to the DOM
 * then we'll see images overlap each other
 * i.e. download image, then calculate layout
 * *************************
 */
function CreateGridItems(GiphyResponse) {
  GiphyResponse.then((giphyData) => {
    return loadGifImages(giphyData);
  })
    .then((gifImages) => {
      return gifImages.map((gifImage) => CreateGifDOMNode(gifImage));
    })
    .then((gifNodes) => {
      return gifNodes.map((gifNode) => new GridItem(gifNode));
    })
    .catch((error) => console.error(error));
}

/* *************************
 *
 * Load Gifs
 * Returns: GIFImages[]
 *
 * *************************
 */
function loadGifImages(gifsData) {
  return Promise.all(gifsData.map((gifData) => loadGIFImage(gifData))).catch(
    (error) => {
      console.error(error);
    }
  );
}

// When we create an image tag, even if the HTMLElement Node hasn't been appended to the DOM
// we preload the image. This serves a form of 'lazy-loading' i.e. preload
function loadGIFImage(gifData) {
  let url = gifData.images.fixed_width.url;
  return new Promise((resolve, reject) => {
    let image = new Image();
    image.onload = () => resolve(image);
    const msg = `Could not load image at ${url}`;
    image.onerror = () => reject(new Error(msg));
    image.alt = gifData.title;
    image.src = url;
  });
}

function CreateGifDOMNode(gifImage) {
  const gifContainer = document.createElement('div');
  const description = document.createElement('span');

  // Assign container props
  gifContainer.classList.add('gif-container');

  // Assign description
  description.textContent = gifImage.alt;
  description.classList.add('gif-description');
  gifContainer.appendChild(gifImage);
  gifContainer.appendChild(description);

  // we append the current dom node to the grid layout
  // because we will need to calculate the scrollheight later
  // if we calculate the scrollHeight first, and then append
  // the scrollHeight will be 0
  grid.element.appendChild(gifContainer);
  return gifContainer;
}

class GridItem {
  gridGap = '5';
  constructor(element) {
    this.element = element;
    this.calculateRowSpan();
  }

  calculateRowSpan() {
    let numRows = parseFloat(this.element.scrollHeight) + parseFloat(this.gridGap);
    this.element.style.gridRowEnd = `span ${numRows}`;
  }
}
