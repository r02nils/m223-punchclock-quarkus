const URL = 'http://localhost:8080';
let entries = [];

const signup = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['username'] = formData.get('username');
    user['email'] = formData.get('email');
    user['password'] = formData.get('password');

    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then((result) => {
        result.json().then((user) => {
            console.log(user);
            if(user == 1){
                window.location = "login.html";

            }
            else if(user == -1){
                document.getElementById('error').innerText = "password must have atleast 8 characters";
            }
            else if(user == -2){
                document.getElementById('error').innerText = "username must have atleast 3 characters";
            }
            else{
                document.getElementById('error').innerText = "error";
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', function(){
    const loginForm = document.querySelector('#signupForm');
    loginForm.addEventListener('submit', signup);
});