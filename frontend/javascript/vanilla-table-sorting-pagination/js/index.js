(function PokemonResults() {
  let resultsContainer;
  let resultsInput;
  let resultsFilters;
  let resultsPagination;
  let pokemonTable;
  let pokemonTableBody;
  let loader;
  let pokemonResults;
  let textContent;
  let pokemonList;
  let currentPokemon;
  let offset;

  // filters
  const ASCENDING = 'ID (Ascending)';
  const DESCENDING = 'ID (Descending)';
  const ALPHA_A_Z = 'Alphabetical (A-Z)';
  const ALPHA_Z_A = 'Alphabetical (Z-A)';
  const filters = [ASCENDING, DESCENDING, ALPHA_A_Z, ALPHA_Z_A];

  if (document.readyState != 'complete') {
    document.addEventListener('DOMContentLoaded', init, false);
  } else {
    init();
  }

  async function init() {
    resultsContainer = document.querySelector('.results-container');
    resultsInput = document.querySelector("input[name='results-search']");
    resultsFilters = document.querySelector('.results-filters');
    resultsPagination = document.querySelector('.results-pagination');
    pokemonResults = document.querySelector('.pokemon-results');
    pokemonTable = document.querySelector('.pokemon-table');
    loader = document.querySelector('.pokemon-data-loading');

    loader.innerHTML = 'Loading pokemon';

    const result = await getPokemon();
    loader.style.display = 'none';
    if (result.ok) {
      offset = 0;
      createPokemonList(result.data);
      createPokemonTableHeader(['id', 'name']);
      createPokemonTableData(offset, pokemonList.pokemon);
      updatePokemonTableInformation(pokemonList.count);
      createPokemonFilters();
    } else {
      displayPokemonError(result.error);
    }

    resultsInput.addEventListener('input', debounce(fetchSinglePokemon, 500));
  }

  function handleSinglePokemon(e) {
    console.log(e.target);
  }

  function sortPokemon(e) {
    const filterBy = this.value;
    // we're going to sort the pokemon rows
    const pokemon = Array.from(document.querySelectorAll('tbody tr:nth-child(n)'));
    switch (filterBy) {
      case ASCENDING: {
        pokemon.sort(ascending('id'));
        break;
      }
      case DESCENDING: {
        pokemon.sort(descending('id'));
        break;
      }
      case ALPHA_A_Z: {
        pokemon.sort(alphaAscending('name'));
        break;
      }
      case ALPHA_Z_A: {
        pokemon.sort(alphaDescending('name'));
        break;
      }
      default:
        throw new TypeError(`Unhandled filter, received ${filterBy}`);
    }

    pokemon.forEach((poke) => pokemonTableBody.appendChild(poke));
  }
  const ascending = (key) => (a, b) => {
    return (
      a.firstChild.getAttribute(`data-${key}`) -
      b.firstChild.getAttribute(`data-${key}`)
    );
  };

  const descending = (key) => (a, b) => {
    return (
      b.firstChild.getAttribute(`data-${key}`) -
      a.firstChild.getAttribute(`data-${key}`)
    );
  };

  const alphaAscending = (key) => (a, b) => {
    return a.firstChild
      .getAttribute(`data-${key}`)
      .localeCompare(b.firstChild.getAttribute(`data-${key}`));
  };

  const alphaDescending = (key) => (a, b) => {
    return b.firstChild
      .getAttribute(`data-${key}`)
      .localeCompare(a.firstChild.getAttribute(`data-${key}`));
  };

  function createPokemonList(results) {
    pokemonList = {};
    pokemonList.count = results.count;
    pokemonList.pokemon = results.results.map((pokemon) => pokemon);
  }

  function createPokemonTableHeader(headings) {
    const tableHeader = document.createElement('thead');
    const topLevelRow = document.createElement('tr');
    headings
      .map((name) => {
        const header = document.createElement('th');
        header.classList.add('data-header');
        header.setAttribute('id', `header-${name}`);
        header.innerHTML = `${capitalizeString(name)}`;
        return header;
      })
      .forEach((header) => {
        topLevelRow.appendChild(header);
      });
    tableHeader.appendChild(topLevelRow);
    pokemonTable.appendChild(tableHeader);
  }

  function createPokemonTableData(offset, pokemon) {
    pokemonTableBody = document.createElement('tbody');
    pokemon
      .map(({ name: pokeName, url }, index) => {
        const pokeID = (index += offset);
        const dataRow = document.createElement('tr');
        const id = document.createElement('td');
        const name = document.createElement('td');

        id.setAttribute('data-name', pokeName);
        name.setAttribute('data-name', pokeName);

        id.setAttribute('data-id', pokeID);
        name.setAttribute('data-id', pokeID);

        id.setAttribute('data-url', url);
        name.setAttribute('data-url', url);

        id.innerHTML = pokeID;
        name.innerHTML = pokeName;

        dataRow.appendChild(id);
        dataRow.appendChild(name);

        return dataRow;
      })
      .forEach((data) => pokemonTableBody.appendChild(data));
    pokemonTable.appendChild(pokemonTableBody);
    pokemonResults.appendChild(pokemonTable);
    pokemonTable.addEventListener('click', handleSinglePokemon);
  }

  function updatePokemonTableInformation() {}

  function createPokemonFilters() {
    const filterInfo = document.createElement('span');
    const select = document.createElement('select');
    filterInfo.innerHTML = 'Sort Pokemon By: ';
    filters.forEach((filter) => {
      const option = document.createElement('option');
      option.innerHTML = filter;
      select.appendChild(option);
    });

    resultsFilters.appendChild(filterInfo);
    resultsFilters.appendChild(select);
    select.addEventListener('change', sortPokemon);
  }

  function displayPokemonError(err) {
    const error = document.createElement('span');
    error.innerHTML = 'Error fetching pokemon';
    error.classList.add('pokemon-error');
    pokemonResults.appendChild(error);
  }

  function getPokemon(limit = 50, offset = 0) {
    const url = `https://pokeapi.co/api/v2/pokemon?limit=${limit}&offset=${offset}`;
    const apiResult = Promise.resolve(fetchData(url));
    const pokemonResult = {};
    return apiResult.then(
      (results) => {
        pokemonResult.ok = true;
        pokemonResult.data = results;
        return pokemonResult;
      },
      (error) => {
        pokemonResult.ok = false;
        pokemonResult.error = error;
        return pokemonResult;
      }
    );
  }

  function fetchSinglePokemon(e) {
    textContent = e.target.value;
    const url = `https://pokeapi.co/api/v2/pokemon/${textContent}`;
    const apiResult = Promise.resolve(fetchData(url));
    apiResult.then(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  /*************************************************************************************/

  function debounce(callback, delay, options = {}) {
    let timerID;
    const { leading = false, trailing = true } = options;
    let isLeading = leading;
    let isTrailing = trailing;

    return function(...args) {
      clearTimeout(timerID);

      if (isLeading) {
        callback(...args);
      }

      timerID = setTimeout(() => {
        if (isTrailing) {
          callback(...args);
        }
      }, delay);
    };
  }

  async function fetchData(url, method) {
    const response = await fetch(url, {
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      }
    });

    try {
      if (response.ok) {
        const results = await response.json();
        return Promise.resolve(results);
      } else {
        return Promise.reject('error fetching data');
      }
    } catch (error) {
      return new Error(error);
    }
  }

  function capitalizeString(str) {
    return str
      .charAt(0)
      .toUpperCase()
      .concat(str.slice(1));
  }
})();
