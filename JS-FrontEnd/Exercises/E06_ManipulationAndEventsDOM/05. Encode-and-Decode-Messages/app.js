function encodeAndDecodeMessages() {
  // Select the necessary elements from the DOM
  const textAreas = document.querySelectorAll("textarea");
  const buttons = document.querySelectorAll("button");

  // The first textarea is for sending messages
  const senderTextarea = textAreas[0];

  // The second textarea is for receiving messages
  const receiverTextarea = textAreas[1];

  // Attach event listener to the 'Encode and send it' button
  buttons[0].addEventListener("click", function () {
    // Get the message from sender textarea
    const message = senderTextarea.value;

    // Encode the message by shifting each character's ASCII code by +1
    let encodedMessage = "";
    for (let char of message) {
      encodedMessage += String.fromCharCode(char.charCodeAt(0) + 1);
    }

    // Clear the sender textarea and put the encoded message in the receiver textarea
    senderTextarea.value = "";
    receiverTextarea.value = encodedMessage;
  });

  // Attach event listener to the 'Decode and read it' button
  buttons[1].addEventListener("click", function () {
    // Get the encoded message from receiver textarea
    const encodedMessage = receiverTextarea.value;

    // Decode the message by shifting each character's ASCII code by -1
    let decodedMessage = "";
    for (let char of encodedMessage) {
      decodedMessage += String.fromCharCode(char.charCodeAt(0) - 1);
    }

    // Replace the encoded message with the decoded message in the receiver textarea
    receiverTextarea.value = decodedMessage;
  });
}
