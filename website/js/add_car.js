document.addEventListener('DOMContentLoaded', function () {
    let loginForm = document.getElementById("addCarForm");
    loginForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission behavior

        let model = document.getElementById("model");
        let year = document.getElementById("year");
        let price = document.getElementById("price");

        let user = {
            model: model.value,
            year: year.value,
            price: price.value
        };


        // Make a POST request using fetch
        fetch('http://localhost:8080/v1/vehicles/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if(response.ok){
            alert('Car added succesfully');
            document.getElementById("model").value="";
            document.getElementById("year").value="";
            document.getElementById("price").value="";
            // Handle success response
            }
            else {
                alert('Car not added ')
            }
        })
        .catch(error => {
            console.error('Error:', error);
            // Handle error
        });
    });
});