// const xhr = new XMLHttpRequest();
// xhr.open("GET", "http://localhost:8080/v1/users");
// xhr.send();
// xhr.responseType = "json";

// xhr.onload = () => {
//     if (xhr.readyState == 4 && xhr.status == 200) {
//         const responseData = xhr.response;
//         populateTable(responseData);
//     } else {
//         console.log(`Error: ${xhr.status}`);
//     }
// };

// function populateTable(data) {
//     const tableBody = document.querySelector("#data-table tbody");

//     // Clear existing table rows
//     tableBody.innerHTML = "";

//     // Iterate through the response data and add rows to the table
//     data.forEach(item => {
//         const row = document.createElement("tr");
//         row.innerHTML = `<td>${item.first_name}</td><td>${item.last_name}</td>`;
//         // Add other cells based on your response structure

//         tableBody.appendChild(row);
//     });
// }

document.addEventListener('DOMContentLoaded', function () {
    let loginForm = document.getElementById("loginUserForm");
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        let email = document.getElementById("email");
        let password = document.getElementById("password");

        let user = {
            userId:0,
            first_name:" ",
            last_name:" ",
            password: password.value,
            email: email.value
        };


        // Make a POST request using fetch
        fetch('http://localhost:8080/v1/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if(response.ok){
            alert("You are logged into the application");
            document.getElementById("email").value="";
            document.getElementById("password").value="";
            // Handle success response
            }
            else {
                alert('You are not logged into the application')
            }
        })
        .catch(error => {
           alert(error);
            // Handle error
        });
    });
});