let newPasswordForm = document.getElementById("new-password-form");

newPasswordForm .onsubmit = function () {
    let input = document.querySelectorAll(".new-password-input");
    return validatePassword(input.item(0).value) &&
        confirmPassword(input.item(0).value, input.item(1).value);
}
