function replaceWords(words, template) {
  const wordArray = words.split(", ");
  const templateArray = template.split(" ");

  for (let i = 0; i < templateArray.length; i++) {
    if (templateArray[i].includes("*")) {
      const wordLength = templateArray[i].length;
      const matchingWord = wordArray.find((word) => word.length === wordLength);
      if (matchingWord) {
        templateArray[i] = matchingWord;
      }
    }
  }

  return templateArray.join(" ");
}

const words = "great, learning, ";
const template =
  "softuni is ***** place for ******** new programming languages";

const result = replaceWords(words, template);
console.log(result);
