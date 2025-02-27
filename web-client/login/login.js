document.getElementById('loginForm').addEventListener('submit', function (event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    if (username) {
        window.location.href = `../chat/chat.html?username=${encodeURIComponent(username)}`;
    }
});