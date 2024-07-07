document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('login-btn');
    const registerBtn = document.getElementById('register-btn');

    const loginUsername = document.getElementById('login-username');
    const loginPassword = document.getElementById('login-password');

    const registerUsername = document.getElementById('register-username');
    const registerPassword = document.getElementById('register-password');
    const registerEmail = document.getElementById('register-email');
    const registerReferralCode = document.getElementById('register-referralCode');

    function validateLogin() {
        let valid = true;

        if (!loginUsername.value.trim()) {
            document.getElementById('login-username-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('login-username-error').style.display = 'none';
        }

        if (!loginPassword.value.trim()) {
            document.getElementById('login-password-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('login-password-error').style.display = 'none';
        }

        loginBtn.disabled = !valid;
    }

    function validateRegister() {
        let valid = true;

        if (!registerUsername.value.trim()) {
            document.getElementById('register-username-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('register-username-error').style.display = 'none';
        }

        if (!registerPassword.value.trim()) {
            document.getElementById('register-password-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('register-password-error').style.display = 'none';
        }

        if (!registerEmail.value.trim() || !registerEmail.checkValidity()) {
            document.getElementById('register-email-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('register-email-error').style.display = 'none';
        }

        if (!registerReferralCode.value.trim()) {
            document.getElementById('register-referralCode-error').style.display = 'block';
            valid = false;
        } else {
            document.getElementById('register-referralCode-error').style.display = 'none';
        }

        registerBtn.disabled = !valid;
    }

    loginUsername.addEventListener('input', validateLogin);
    loginPassword.addEventListener('input', validateLogin);
    registerUsername.addEventListener('input', validateRegister);
    registerPassword.addEventListener('input', validateRegister);
    registerEmail.addEventListener('input', validateRegister);
    registerReferralCode.addEventListener('input', validateRegister);

    validateLogin();
    validateRegister();
});
