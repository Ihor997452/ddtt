let loginBlock = document.getElementById("login");
let registerBlock = document.getElementById("register");
let restoreBlock = document.getElementById("restore");

document.getElementById("signIn").onclick = function () {
    change(loginBlock, restoreBlock, registerBlock);
}
document.getElementById("signUp").onclick = function () {
    change(registerBlock, loginBlock, restoreBlock);
}
document.getElementById("forgot-password").onclick = function () {
    change(restoreBlock, loginBlock, registerBlock);
}

function change(blockToShow, blockToHideOne, blockToHideTwo) {

    if (blockToHideOne.classList.contains("visible")) {
        blockToHideOne.classList.toggle("visible");
        blockToHideOne.classList.toggle("invisible");
    }

    if (blockToHideTwo.classList.contains("visible")) {
        blockToHideTwo.classList.toggle("visible");
        blockToHideTwo.classList.toggle("invisible");
    }

    blockToShow.classList.toggle("invisible");
    blockToShow.classList.toggle("visible");
}

let registerForm = document.getElementById("register-form");

registerForm.onsubmit = function () {
    let registerInput = document.querySelectorAll(".register-input");
    return validatePassword(registerInput.item(4).value) &&
        confirmPassword(registerInput.item(4).value, registerInput.item(5).value)
        && validateRole(registerInput.item(1).value);
}