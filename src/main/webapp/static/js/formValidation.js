var regExpressionNickname = /^[A-Za-z ]{5,30}$/i;
var regExpressionEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/i;
var regExpressionPassword = /^[A-Za-z0-9]{6,20}$/i;

function validateInputs() {
    const nicknameID = "#nickname";
    const emailID = "#email";
    const passwordID = "#password";

    validateNickname(nicknameID);
    validateEmail(emailID);
    validatePassword(passwordID);
}

const validateNickname = (nicknameID) => {
    let nicknameValue = $(nicknameID).val().trim();
    let validator = true;

    if (nicknameValue.length < 5 || nicknameValue.length > 30) {
        validator = false;
    }

    if (!regExpressionNickname.test(nicknameValue)) {
        validator = false;
    }

    if (validator == false) {
        alert("Wrong format for NICKNAME!\n+ Minimum: 5 letters\n+ Maximum: 30 letters\n+ Don't accepted Special characters");
        $(nicknameID).val("");
    }
}

const validateEmail = (emailID) => {
    let emailValue = $(emailID).val().trim();
    let validator = true;

    if (emailValue.length < 5 || emailValue.length > 30) {
        validator = false;
    }

    if (!regExpressionEmail.test(emailValue)) {
        validator = false;
    }

    if (validator == false) {
        alert("Wrong format for email!\n+ Minimum: 5 letters\n+ Maximum: 30 letters\n+ Don't accepted special characters");
        $(emailID).val("");
    }
}

const validatePassword = (passwordID) => {
    let passwordValue = $(passwordID).val().trim();
    let validator = true;

    if (passwordValue.length < 5 || passwordValue.length > 30) {
        validator = false;
    }

    if (!regExpressionPassword.test(passwordValue)) {
        validator = false;
    }

    if (validator == false) {
        alert("Wrong format for PASSWORD!\n+ Minimum: 6 letters\n+ Maximum: 20 letters\n+ Don't accepted special characters");
        $(passwordID).val("");
    }
}