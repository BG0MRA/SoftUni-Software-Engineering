function arrayRotation(arr, rotations) {
  for (let i = 0; i < rotations; i++) {
    const firstElement = arr.shift();
    arr.push(firstElement);
  }
  console.log(arr.join(" "));
}
