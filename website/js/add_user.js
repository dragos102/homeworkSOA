document.addEventListener('DOMContentLoaded', function () {
    let loginForm = document.getElementById("addUserForm");
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        let firstName = document.getElementById("firstName");
        let lastName = document.getElementById("lastName");
        let email = document.getElementById("email");

        let user = {
            first_name: firstName.value,
            last_name: lastName.value,
            email: email.value,
            password: " "
        };


        // Make a POST request using fetch
        fetch('http://localhost:8080/v1/users/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if(response.ok){
            alert('User added succesfully');
            document.getElementById("firstName").value="";
            document.getElementById("lastName").value="";
            document.getElementById("email").value="";
            // Handle success response
            }
            else {
                alert('User not added ')
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle error
        });
    });
});