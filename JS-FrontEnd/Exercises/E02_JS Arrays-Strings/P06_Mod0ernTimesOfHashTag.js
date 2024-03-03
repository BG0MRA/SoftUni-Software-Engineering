function findSpecialWords(input) {
  const words = input.split(" ");
  const specialWords = [];

  for (let word of words) {
    if (word.startsWith("#")) {
      const specialWord = word.slice(1);
      if (/^[a-zA-Z]+$/.test(specialWord)) {
        specialWords.push(specialWord);
      }
    }
  }

  return specialWords.join("\n"); // Separate words line by line
}

// Example usage:
const input = "I love #programming and #coding!";
const specialWords = findSpecialWords(input);
for (let word of specialWords) {
  console.log(word);
}
