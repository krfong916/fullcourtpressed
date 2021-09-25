import React from 'react';
import './index.css';

const initBoardState = [Array(9).fill(null)];

// we remove the detail of where 'persistent' state exists
// from the board function component itself
function resetBoardState(state, setState) {
  setState(initBoardState);
  window.localStorage.removeItem('board');
}

// custom hook for board state
// could substitute localstorage for API calls to a backend to get board state
function useBoardState() {
  const [state, setState] = React.useState(() => {
    let board = window.localStorage.getItem('board');
    if (board) {
      return JSON.parse(board);
    } else {
      return initBoardState;
    }
  });

  React.useEffect(() => {
    window.localStorage.setItem('board', JSON.stringify(state));
  }, [state]);

  return [state, setState];
}

export default function App() {
  return (
    <div className="game">
      <Board />
    </div>
  );
}

function Board({ onClick, board, isFinalState }) {
  const [state, setState] = useBoardState();
  const [gameOver, setGameOver] = React.useState(calculateOutcome(state) === 'win'); // state for indicating when the game is over
  const [cats, setCats] = React.useState(calculateOutcome(state) === 'cats'); // state for indicating a cats game
  let moveCounter = state.length - 1; // maintains a count of all moves placed, useful for deriving the desired board state
  let playerOneTurn = calculateTurn() == 1 ? true : false; // true == player1's turn, false == player2's turn

  /**
   * Checks if the user action is a valid move
   * if the action is a valid move
   * then we need to create a new board state that reflects that valid move
   * to do so,
   * save the current board state
   * create a new board state from the existing board state
   * add the move to the new board state
   * append the new board state to the list of all states
   * increment the move counter
   */
  function selectSquare(id) {
    if (canPlace(id) && !gameOver && !cats) {
      let allBoardStates = [...state]; // [[original board state],[board state + 1],[board state + 2],...,[board state + k]]
      let playersAction = playerOneTurn == true ? 'X' : 'O'; // calculate the correct symbol to place on the board
      let newBoardState = [...state[moveCounter]]; // new board state will be updated with the playersAction

      newBoardState[id] = playersAction;
      allBoardStates.push(newBoardState);

      setState(allBoardStates);
      moveCounter++; // a move has been placed, increment the current counter of all actions by 1

      // calculate the outcome based on the user's action
      let outcome = calculateOutcome(allBoardStates);
      if (outcome === 'win') {
        setGameOver(true);
      } else if (outcome === 'cats') {
        setCats(true);
      }
    }
  }

  function canPlace(id) {
    return state[moveCounter][id] === null ? true : false;
  }

  function reset() {
    setGameOver(false);
    setCats(false);
    resetBoardState(state, setState);
  }

  // returns a number
  // 1 == player 1's turn
  // 2 == player 2's turn
  function calculateTurn() {
    let moveCount = state[moveCounter].reduce((accum, currentVal) => {
      if (currentVal !== null) accum++;
      return accum;
    }, 0);
    console.log(moveCount);
    return moveCount % 2 == 0 ? 1 : 2;
  }

  function undoMove(index) {
    if (gameOver || cats) return;
    let prevState = index;
    let backtrackToPriorState = [...state];

    // reset to the desired state
    while (backtrackToPriorState.length !== prevState) {
      backtrackToPriorState.pop();
      moveCounter--;
    }

    setState(backtrackToPriorState);
  }

  function renderSquare(id) {
    return (
      <button role="button" className="board-cell" onClick={() => selectSquare(id)}>
        {state[moveCounter][id]}
      </button>
    );
  }

  return (
    <div className="game-board-container">
      <div className="game-board">
        {gameOver ? (
          <span className="turn-title">Player {playerOneTurn} is the winner!</span>
        ) : cats ? (
          <span className="turn-title">Cat's Game! Reset to play again</span>
        ) : (
          <span className="turn-title">
            Player {playerOneTurn == true ? 1 : 2}'s Turn
          </span>
        )}
        <div className="board">
          <div className="board-row">
            {renderSquare(0)}
            {renderSquare(1)}
            {renderSquare(2)}
          </div>
          <div className="board-row">
            {renderSquare(3)}
            {renderSquare(4)}
            {renderSquare(5)}
          </div>
          <div className="board-row">
            {renderSquare(6)}
            {renderSquare(7)}
            {renderSquare(8)}
          </div>
        </div>
        <button role="button" onClick={reset} className="board-reset">
          Reset Game
        </button>
      </div>
      <div className="undo-states">
        <span className="undo-states-title">Rewind to Previous Board State</span>
        <ul>
          {state.map((state, index) => {
            if (index > 0) {
              return (
                <li key={index}>
                  <button role="button" onClick={() => undoMove(index)}>
                    Move {index}
                  </button>
                </li>
              );
            }
          })}
        </ul>
      </div>
    </div>
  );
}

function calculateOutcome(nextState) {
  const winningConfig = [
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 4, 8],
    [2, 4, 6]
  ];

  for (let i = 0; i < winningConfig.length; i++) {
    let one = nextState[nextState.length - 1][winningConfig[i][0]];
    let two = nextState[nextState.length - 1][winningConfig[i][1]];
    let three = nextState[nextState.length - 1][winningConfig[i][2]];
    if (
      one == two &&
      one == three &&
      two == three &&
      one !== null &&
      two !== null &&
      three !== null
    ) {
      return 'win';
    }
  }
  return nextState[nextState.length - 1].every((cell) => cell != null)
    ? 'cats'
    : 'continue';
}
