// LOADING IMAGES
// - Load images from 3rd party source instead of hard-code
// - If there is one image, then we don't assign dots or arrows
// - If there is more than one image, assign dots and arrows
let images = [
  { source: 'img/naru-fan-art-1.jpeg', alt: 'Vignette of Naruto characters' },
  {
    source: 'img/naru-fan-art-2.jpeg',
    alt:
      'Jiraiya grinning, Minato pointing and explaining, and Naruto laughing while they all look at a scrapbook of photos'
  },
  {
    source: 'img/nd68ak3x17w61.jpg',
    alt:
      'Kakashi and Naruto in their Hokage outfit smiling in front of the Hokage Rock'
  }
];

//
// Variables for Carousel track and slides
//
let imageFrag = document.createDocumentFragment();
let carouselContainer = document.querySelector('.carousel__track-container');
let track = document.createElement('ul');

//
// Variables for Carousel nav
//
let numImages = images.length;
let navFrag = document.createDocumentFragment();
let carousel = document.querySelector('.carousel');
let carouselNav = document.querySelector('.carousel__nav');

//
// Create Carousel Component
//
createCarouselTrack(imageFrag, carouselContainer, track);
createCarouselNav(numImages, navFrag, carousel, carouselNav);

let slides = Array.from(track.children);
let navMarks = Array.from(carouselNav.children);

//
// Position carousel slides
//
positionSlides(slides);

function positionSlides(slides) {
  function assignWidth(slide, index) {
    slide.style.left = slide.getBoundingClientRect().width * index + 'px';
  }
  slides.forEach(assignWidth);
}

function createCarouselTrack(imageFrag, carouselContainer, track) {
  imageFrag.appendChild(track);

  track.classList.add('carousel__track');
  images.forEach((image, index) => {
    // create dom nodes
    let trackItem = document.createElement('li');
    trackItem.classList.add('carousel__track-item');
    let pic = document.createElement('img');
    pic.classList.add('carousel__track-image');

    // if the image is the first image, it is considered the current image displayed
    if (index == 0) {
      trackItem.classList.add('current-carousel-slide');
    }

    // assign attributes
    pic.setAttribute('src', image.source);
    pic.setAttribute('alt', image.alt);

    // append the image to the list element
    trackItem.appendChild(pic);

    // append the list element to the carousel track
    track.appendChild(trackItem);
  });
  carouselContainer.appendChild(imageFrag);
}

// - If there is one image, then we only assign one dot and no arrows
// - If there is more than one image, assign n-many dots and L/R arrows
function createCarouselNav(numImages, navFrag, carousel, carouselNav) {
  for (let i = 0; i < numImages; i++) {
    let carouselNavButton = document.createElement('button');
    carouselNavButton.addEventListener('click', updateNav);
    carouselNavButton.addEventListener('onkeydown', updateNav);
    carouselNavButton.classList.add('carousel__nav-mark');

    if (i == 0) {
      carouselNavButton.classList.add('carousel__nav--current');
    }
    navFrag.appendChild(carouselNavButton);
  }
  carouselNav.appendChild(navFrag);
  if (numImages > 1) {
    // assign left and right arrows
    let prevButton = document.createElement('button');
    let nextButton = document.createElement('button');
    let leftArrIcon = document.createElement('img');
    let rightArrIcon = document.createElement('img');

    const LEFT_CAROUSEL_ARROW = 'LEFT_CAROUSEL_ARROW';
    const RIGHT_CAROUSEL_ARROW = 'RIGHT_CAROUSEL_ARROW';

    prevButton.classList.add('carousel__button--left');
    nextButton.classList.add('carousel__button--right');

    prevButton.setAttribute('id', LEFT_CAROUSEL_ARROW);
    nextButton.setAttribute('id', RIGHT_CAROUSEL_ARROW);

    leftArrIcon.setAttribute('type', 'image/svg+xml');
    leftArrIcon.setAttribute('src', 'img/left-arrow.svg');

    rightArrIcon.setAttribute('type', 'image/svg+xml');
    rightArrIcon.setAttribute('src', 'img/right-arrow.svg');

    prevButton.appendChild(leftArrIcon);
    nextButton.appendChild(rightArrIcon);

    carousel.appendChild(prevButton);
    carousel.appendChild(nextButton);

    prevButton.addEventListener('keydown', slidePrev);
    prevButton.addEventListener('click', slidePrev);

    nextButton.addEventListener('keydown', slideNext);
    nextButton.addEventListener('click', slideNext);
  }
}

const LEFT_ARROW = 37;
const RIGHT_ARROW = 39;
const SPACEBAR = 32;
const ENTER = 13;

function slidePrev(e) {
  if (
    e.type == 'click' ||
    e.keyCode == LEFT_ARROW ||
    e.keyCode == SPACEBAR ||
    e.keyCode == ENTER
  ) {
    e.preventDefault();
    let currentSlide = document.querySelector('.current-carousel-slide');
    let prevSlide = currentSlide.previousSibling;
    if (prevSlide !== null) {
      let currentNavMark = document.querySelector('.carousel__nav--current');
      let targetIndex = navMarks.findIndex((mark) => currentNavMark == mark);
      let targetNavMark = navMarks[targetIndex - 1];
      moveSlide(track, currentSlide, prevSlide);
      moveNav(currentNavMark, targetNavMark);
    }
  }
}

function slideNext(e) {
  if (
    e.type == 'click' ||
    e.keyCode == RIGHT_ARROW ||
    e.keyCode == SPACEBAR ||
    e.keyCode == ENTER
  ) {
    e.preventDefault();
    let currentSlide = document.querySelector('.current-carousel-slide');
    let nextSlide = currentSlide.nextSibling;
    if (nextSlide !== null) {
      let currentNavMark = document.querySelector('.carousel__nav--current');
      let targetIndex = navMarks.findIndex((mark) => currentNavMark == mark);
      let targetNavMark = navMarks[targetIndex + 1];
      moveSlide(track, currentSlide, nextSlide);
      moveNav(currentNavMark, targetNavMark);
    }
  }
}

function moveSlide(track, currentSlide, targetSlide) {
  track.style.transform = 'translateX(-' + targetSlide.style.left + ')';
  currentSlide.classList.remove('current-carousel-slide');
  targetSlide.classList.add('current-carousel-slide');
}

function moveNav(currentNavMark, targetNavMark) {
  currentNavMark.classList.remove('carousel__nav--current');
  targetNavMark.classList.add('carousel__nav--current');
}

function updateNav(e) {
  let targetNavMark = e.target.closest('button');
  if (targetNavMark == null) return;
  if (e.type == 'click' || e.keyCode == SPACEBAR || e.keyCode == ENTER) {
    let currentSlide = document.querySelector('.current-carousel-slide');
    let currentNavMark = document.querySelector('.carousel__nav--current');
    let targetIndex = navMarks.findIndex((mark) => targetNavMark == mark);
    let targetSlide = slides[targetIndex];

    moveSlide(track, currentSlide, targetSlide);
    moveNav(currentNavMark, targetNavMark);
  }
}

// ARROW
// keyboard events:
//   - when we click
//     - the left arrow, we move the slide left
//     - the right arrow, we move the slide right
// ui
//   - if there are no more images to the left then don't display the left arrow
//   - if there are no more images to the right then don't display the right arrow

// CAROUSEL NAV BUTTONS
// keyboard events:
//   - when we click
//     - changes to the image that corresponds with the dot
// ui
//   - the dot that contains the active image is black

// UX: touch-enable the carousel
