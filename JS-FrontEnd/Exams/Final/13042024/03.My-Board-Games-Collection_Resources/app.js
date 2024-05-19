const baseUrl = "http://localhost:3030/jsonstore/games";

const loadGamesButton = document.getElementById("load-games");
const gamesListDiv = document.getElementById("games-list");
const addGameButton = document.getElementById("add-game");
const editGameButton = document.getElementById("edit-game");
const gameName = document.getElementById("g-name");
const gameType = document.getElementById("type");
const maxPlayers = document.getElementById("players");

let currentGameId = null; // This will hold the ID of the game being edited

loadGamesButton.addEventListener("click", function () {
  fetch(baseUrl)
    .then((response) => response.json())
    .then((games) => {
      while (gamesListDiv.firstChild) {
        gamesListDiv.removeChild(gamesListDiv.firstChild);
      }

      Object.values(games).forEach((game) => {
        const gameDiv = createGameDiv(game);
        gamesListDiv.appendChild(gameDiv);
      });
    })
    .catch((error) => {
      console.error("Error loading games:", error);
    });
});

// Event listener for the "Add Game" button
addGameButton.addEventListener("click", function () {
  // Create a new game object to be sent to the server
  const newGame = {
    name: gameName.value,
    type: gameType.value,
    players: maxPlayers.value,
  };

  // Send a POST request to create a new game
  fetch(baseUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newGame),
  }).then((response) => {
    if (!response.ok) {
      return;
    }
    // Clear the input fields after game creation
    clearInputFields();

    // Reload the list of games to include the new game
    loadGames();
  });
});

// Event listener for the "Edit Game" button

editGameButton.addEventListener("click", editGame);

// Function to load games, can be called after adding a new game
async function loadGames() {
  const response = await fetch(baseUrl);
  const games = await response.json();

  gamesListDiv.innerHTML = "";

  const gamesFragment = document.createDocumentFragment();

  Object.values(games).forEach((game) => {
    gamesFragment.appendChild(createGameDiv(game));
  });
  gamesListDiv.appendChild(gamesFragment);
}

async function editGame() {
  const gameData = {
    name: gameName.value,
    type: gameType.value,
    players: maxPlayers.value,
  };

  const response = await fetch(`${baseUrl}/${currentGameId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      _id: currentGameId,
      name: gameData.name,
      players: gameData.players,
      type: gameData.type,
    }),
  });

  if (!response.ok) {
    console.error("Error editing game");
    return;
  }

  // Reload the games to reflect changes
  await loadGames();

  //deactivate edit button
  editGameButton.setAttribute("disabled", "disabled");

  //activate add button
  addGameButton.removeAttribute("disabled");

  // Clear the input fields and reset the state
  clearInputFields();
  currentGameId = null;
}

function createGameDiv(game) {
  const gameDiv = document.createElement("div");
  gameDiv.className = "board-game";

  const contentDiv = document.createElement("div");
  contentDiv.className = "content";

  const nameP = document.createElement("p");
  nameP.textContent = game.name;
  const playersP = document.createElement("p");
  playersP.textContent = game.players;
  const typeP = document.createElement("p");
  typeP.textContent = game.type;

  contentDiv.appendChild(nameP);
  contentDiv.appendChild(playersP);
  contentDiv.appendChild(typeP);

  const buttonsContainerDiv = document.createElement("div");
  buttonsContainerDiv.className = "buttons-container";

  const changeBtn = document.createElement("button");
  changeBtn.className = "change-btn";
  changeBtn.textContent = "Change";
  const deleteBtn = document.createElement("button");
  deleteBtn.className = "delete-btn";
  deleteBtn.textContent = "Delete";

  buttonsContainerDiv.appendChild(changeBtn);
  buttonsContainerDiv.appendChild(deleteBtn);

  gameDiv.appendChild(contentDiv);
  gameDiv.appendChild(buttonsContainerDiv);

  gameDiv.setAttribute("data-id", game._id);

  // Add event listeners for the change and delete buttons
  changeBtn.addEventListener("click", () => {
    currentGameId = game._id;

    gameName.value = game.name;
    maxPlayers.value = game.players;
    gameType.value = game.type;

    editGameButton.removeAttribute("disabled");
    addGameButton.setAttribute("disabled", "disabled");

    gameDiv.remove();
  });

  //delete button
  deleteBtn.addEventListener("click", async () => {
    await fetch(`${baseUrl}/${game._id}`, {
      method: "DELETE",
    });

    gameDiv.remove();
  });

  return gameDiv;
}

// Function to clear the input fields after submitting the form
function clearInputFields() {
  gameName.value = "";
  gameType.value = "";
  maxPlayers.value = "";
}
