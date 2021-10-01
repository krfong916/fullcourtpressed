// api service that calls the Giphy API
export class GiphyService {
  static URL = 'https://api.giphy.com/v1/gifs/search';
  static API_KEY = '8fPElqNMrBVZU2ae7tNqNOUkUE6MV6Fe';

  // fetch gifs based on the offset
  static async search(term = 'naruto', numberOfGIFS = 30, offset) {
    // for testing: let url = 'http://localhost:3000/data';
    let url = `${this.URL}?api_key=${this.API_KEY}&q=${term}&limit=${numberOfGIFS}&offset=${offset}&rating=g&lang=en`;
    let res = await fetch(url, {
      mode: 'cors'
    });
    res = await res.json();
    return res.data;
  }
}
