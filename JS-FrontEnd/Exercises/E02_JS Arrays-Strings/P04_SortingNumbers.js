function sortNumbers(arr) {
  arr.sort((a, b) => a - b);
  const result = [];
  let left = 0;
  let right = arr.length - 1;

  while (left <= right) {
    if (left === right) {
      result.push(arr[left]);
    } else {
      result.push(arr[left]);
      result.push(arr[right]);
    }
    left++;
    right--;
  }

  return result;
}
