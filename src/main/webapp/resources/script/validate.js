function validateRole(role) {
    if (role === "0" || role === "1") {
        return true;
    } else {
        insertMessage( "Wrong Role Value");
        return false;
    }
}

function validatePassword(password) {
    if (password.length > 6) {
        return true;
    } else {
        insertMessage("Password should contain al least 6 characters");
        return false;
    }
}

function confirmPassword(password, confirmPassword) {
    if (password === confirmPassword) {
        return true;
    } else {
        insertMessage("Passwords should be the same");
        return false;
    }
}

function validateDescription(desc) {
    if (desc < 999) {
        return true;
    } else {
        insertMessage("Description too long");
        return false;
    }
}
