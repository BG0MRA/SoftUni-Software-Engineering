window.addEventListener("load", solve);

function solve() {
  const nameInputElement = document.getElementById("name");
  const phoneInputElement = document.getElementById("phone");
  const categoryInputElement = document.getElementById("category");
  const addButtonElement = document.getElementById("add-btn");
  const checkListElement = document.getElementById("check-list");
  const contactListElement = document.getElementById("contact-list");

  addButtonElement.addEventListener("click", () => {
    // Check if any of the input fields are empty
    if (
      nameInputElement.value === "" ||
      phoneInputElement.value === "" ||
      categoryInputElement.value === ""
    ) {
      return; // Exit the function without doing anything
    }
    // Get input information
    const name = nameInputElement.value;
    const phone = phoneInputElement.value;
    const category = categoryInputElement.value;

    // Create contact element
    const contactLiElement = createContactElement(name, phone, category);

    // Add to contact list
    checkListElement.appendChild(contactLiElement);

    // Clear input fields
    clearInputs();
  });

  function createContactElement(name, phone, category) {
    const editButtonElement = document.createElement("button");
    editButtonElement.classList.add("edit-btn");

    const saveButtonElement = document.createElement("button");
    saveButtonElement.classList.add("save-btn");

    const buttonsDivElement = document.createElement("div");
    buttonsDivElement.classList.add("buttons");
    buttonsDivElement.appendChild(editButtonElement);
    buttonsDivElement.appendChild(saveButtonElement);

    const namePElement = document.createElement("p");
    namePElement.textContent = `name:${name}`;
    const phonePElement = document.createElement("p");
    phonePElement.textContent = `phone:${phone}`;
    const categoryPElement = document.createElement("p");
    categoryPElement.textContent = `category:${category}`;

    const articleElement = document.createElement("article");
    articleElement.appendChild(namePElement);
    articleElement.appendChild(phonePElement);
    articleElement.appendChild(categoryPElement);

    const contactLiElement = document.createElement("li");
    //contactLiElement.classList.add("contact");
    contactLiElement.appendChild(articleElement);
    contactLiElement.appendChild(buttonsDivElement);

    // Event listeners
    editButtonElement.addEventListener("click", () => {
      // send to inputs
      nameInputElement.value = name;
      phoneInputElement.value = phone;
      categoryInputElement.value = category;

      // Remove the contact from the list
      contactLiElement.remove();
    });

    saveButtonElement.addEventListener("click", () => {
      // Remove the buttons
      buttonsDivElement.remove();

      // Remove the contact from the list and add button del-btn
      contactLiElement.remove();
      const deleteButtonElement = document.createElement("button");
      deleteButtonElement.classList.add("del-btn");
      contactLiElement.appendChild(deleteButtonElement);

      // add to contact-list
      contactListElement.appendChild(contactLiElement);

      // Event listener for delete button
      deleteButtonElement.addEventListener("click", () => {
        contactLiElement.remove();
      });
    });

    return contactLiElement;
  }

  function clearInputs() {
    nameInputElement.value = "";
    phoneInputElement.value = "";
    categoryInputElement.value = "";
  }
}
