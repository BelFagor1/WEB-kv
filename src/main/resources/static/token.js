const but = document.getElementById('create-token');
but.addEventListener('click', setTokenToField);
const copyButton = document.getElementById("copyButton");
const copyButton1 = document.getElementById("copyButton1");
copyButton.addEventListener("click", copyToClipboard);
copyButton1.addEventListener("click", copyToClipboard);


function generateToken(length = 16) {
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    let token = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        token += characters[randomIndex];
    }
    return token;
}

function setTokenToField() {
    const token = generateToken();
    const inputField = document.getElementById('field');
    if (inputField) {
        inputField.value = token;
    }
}
function copyToClipboard() {
    const inputField = document.getElementById('field');
    inputField.select();
    document.execCommand("copy");

}

