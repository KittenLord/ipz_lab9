let minPrice = undefined;
let maxPrice = undefined;

let minStars = undefined;
let maxStars = undefined;

let city = undefined;
let id = undefined; // room id, ignore all other args

let page = localStorage.getItem("page");
if(page === null) page = 0;

let order = undefined; // sorting order

function populateInputs() {
    minPrice = document.getElementById("minPrice").value;
    if(isNaN(parseFloat(minPrice))) minPrice = undefined;

    maxPrice = document.getElementById("maxPrice").value;
    if(isNaN(parseFloat(maxPrice))) maxPrice = undefined;

    minStars = document.getElementById("minStars").value;
    if(isNaN(parseFloat(minStars))) minStars = undefined;

    maxStars = document.getElementById("maxStars").value;
    if(isNaN(parseFloat(maxStars))) maxStars = undefined;

    city = document.getElementById("city").value;
    if(city.length === 0) city = undefined;

    id = document.getElementById("roomId").value;
    if(isNaN(parseInt(id))) id = undefined;
}

function applyFilters() {
    populateInputs();

    let link = "/search?";
    if(id !== undefined) link += `id=${id}`;
    else {
        if(minPrice !== undefined) link += `minPrice=${minPrice}&`;
        if(maxPrice !== undefined) link += `maxPrice=${maxPrice}&`;

        if(minStars !== undefined) link += `minStars=${minStars}&`;
        if(maxStars !== undefined) link += `maxStars=${maxStars}&`;

        if(city !== undefined) link += `city=${city}&`;
        if(page !== undefined) link += `page=${page}&`;
        if(order !== undefined) link += `order=${order}&`;
    }

    location.href = link;
}

function search() {
    applyFilters();
    localStorage.setItem("page", 0);
}

function nextPage() {
    page++;
    localStorage.setItem("page", page);
    applyFilters();
}

function prevPage() {
    page--;
    if(page < 0) page = 0;
    localStorage.setItem("page", page);
    applyFilters();
}
