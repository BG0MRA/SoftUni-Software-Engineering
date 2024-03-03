function splitPascalCase(str) {
  const words = str
    .replace(/([A-Z])/g, " $1")
    .trim()
    .split(" ");
  return words.join(", ");
}
