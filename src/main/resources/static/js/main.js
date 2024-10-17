'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var jwtToken = null; // Token JWT

function connect(event) {
    username = document.querySelector('#name').value.trim();
    var password = document.querySelector('#password').value.trim();

    if (username && password) {
        // Gửi yêu cầu đăng nhập
        fetch('/api/auth/sign-in', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username: username, password: password })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid username or password');
            }
            return response.json();
        })
        .then(data => {
            if (data && data.data) {
                jwtToken = data.data.token; // Lưu token JWT
                usernamePage.classList.add('hidden');
                chatPage.classList.remove('hidden');
                connectWebSocket();
            }
        })
        .catch(error => {
            alert(error.message);
        });
    }
    event.preventDefault();
}

function connectWebSocket() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({ 'Authorization': 'Bearer ' + jwtToken }, onConnected, onError);
}

function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    var receiver = document.querySelector('#receiver').value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            receiver: receiver, // Thêm thông tin người nhận
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');
    messageElement.innerText = message.sender + ": " + message.content;
    messageArea.appendChild(messageElement);
}

usernameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', sendMessage, true);