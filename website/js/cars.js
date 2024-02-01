const xhr = new XMLHttpRequest();
xhr.open("GET", "http://localhost:8080/v1/vehicles");
xhr.send();
xhr.responseType = "json";

xhr.onload = () => {
    if (xhr.readyState == 4 && xhr.status == 200) {
        const responseData = xhr.response;
        populateTable(responseData);
    } else {
        console.log(`Error: ${xhr.status}`);
    }
};

function populateTable(data) {
    const tableBody = document.querySelector("#data-table tbody");

    // Clear existing table rows
    tableBody.innerHTML = "";

    // Iterate through the response data and add rows to the table
    data.forEach(item => {
        const row = document.createElement("tr");
        row.innerHTML = `<td>${item.model}</td><td>${item.year}</td><td>${item.price}</td>`;
        // Add other cells based on your response structure

        tableBody.appendChild(row);
    });
}
