function checkWord(word, text) {
  const lowerCaseWord = word.toLowerCase();
  const lowerCaseText = text.toLowerCase();
  const wordsArray = lowerCaseText.split(" ");

  if (wordsArray.includes(lowerCaseWord)) {
    console.log(word);
  } else {
    console.log(`${word} not found!`);
  }
}

// Example usage
checkWord("javascript", "JavaScript is the best programming language");
checkWord("python", "JavaScript is the best programming language");
