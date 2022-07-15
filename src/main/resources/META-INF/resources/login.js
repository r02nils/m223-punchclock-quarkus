const URL = 'http://localhost:8080';
let entries = [];

const login = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['username'] = formData.get('username');
    user['password'] = formData.get('password');

    fetch(`${URL}/users/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            console.log(user.headers);
            if(user == 1){
                window.localStorage.setItem("token",result.headers.get("token"));
                window.location = "index.html";
            }
            else if(user == -1){
                document.getElementById('error').innerText = "wrong password";
            }
            else if(user == -2){
                document.getElementById('error').innerText = "user does not exist";
            }
            else{
                document.getElementById('error').innerText = "error";
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', function(){
    const loginForm = document.querySelector('#loginForm');
    loginForm.addEventListener('submit', login);
});