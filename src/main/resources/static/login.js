const signIn = document.getElementById('sign-in');
const signUp = document.getElementById('sign-up');
const login = document.getElementById('login-container');
const reg = document.getElementById('registration-container');
const code = document.getElementById('code');
const hide = document.getElementById('hide');
const codeInput = document.getElementById('code-input');

signIn.addEventListener('click', signInFun);
signUp.addEventListener('click', signUpFun);
code.addEventListener('click',sendFun)

function signInFun() {
    signIn.style.borderBottom = '3px solid #1d6ff2';
    signUp.style.borderBottom = 'none';
    login.style.display = 'block';
    reg.style.display = 'none';
}
function signUpFun() {
    signUp.style.borderBottom = '3px solid #1d6ff2';
    signIn.style.borderBottom = 'none';
    login.style.display = 'none';
    reg.style.display = 'block';
}
function sendFun(){
    const email = document.getElementById('email').value;
    fetch("/api/send-email", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ email: email }),
    })
    hide.style.display = 'block';
    code.style.display = 'none';
    codeInput.style.display = 'flex';
}