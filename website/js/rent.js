// Funcție pentru a încărca opțiunile dropdown-ului
function loadDropdownOptions_User() {
    let dropdown_user = document.getElementById("dropdown_user");

    // Face o cerere GET către server pentru a obține lista de string-uri
    fetch('http://localhost:8080/v1/users') // Actualizează URL-ul cu ruta corectă către server
        .then(response => response.json())
        .then(data => {
            // Iterează prin lista de string-uri și adaugă fiecare ca opțiune în dropdown
            data.forEach(option => {
                let optionElement = document.createElement("option");
                optionElement.value = option.userId ;
                optionElement.text = option.first_name+ " " + option.last_name;
                dropdown_user.appendChild(optionElement);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Funcție pentru a încărca opțiunile dropdown-ului
function loadDropdownOptions_Car() {
    let dropdown = document.getElementById("dropdown_car");

    // Face o cerere GET către server pentru a obține lista de string-uri
    fetch('http://localhost:8080/v1/vehicles') // Actualizează URL-ul cu ruta corectă către server
        .then(response => response.json())
        .then(data => {
            // Iterează prin lista de string-uri și adaugă fiecare ca opțiune în dropdown
            data.forEach(option => {
                let optionElement = document.createElement("option");
                optionElement.value = option.model + " "+ option.year ;
                optionElement.text = option.model + " "+ option.year ;
                dropdown.appendChild(optionElement);
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


document.addEventListener('DOMContentLoaded', function () {
    // Your JavaScript code here
    loadDropdownOptions_User();
    loadDropdownOptions_Car();

    let loginForm = document.getElementById("rentCarForm");
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        let username = document.getElementById("dropdown_user").value;
        let car = document.getElementById("dropdown_car").value;

        const url = `http://localhost:8080/v1/vehicles/${username}/users/${car}`;

        // Make a POST request
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
                // You may need to include other headers if required by your backend
            },
            body: JSON.stringify({
                    username: username,
                    car: car
                
            })
        })
        .then(response => {
            if (response.ok) {
                alert('Request successful');
                loadDropdownOptions_User();
                loadDropdownOptions_Car();
                // Handle success response if needed
            } else {
                console.error(`Error: ${response.status} - ${response.statusText}`);
                // Handle error response if needed
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle other errors if needed
        });


    });

});