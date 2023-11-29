const default_boundary_value = 10

async function doRequest() {
    let boundary = validateBoundary()
    updatePrimesLabel(boundary)
    let url = "http://localhost:8080/sieve?boundary=" + boundary
    await fetch(url, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    }).then(response => {
        if (response.ok) {
            return response.json()
        } else {
            throw new Error(`Error while retrieving primes! Status: ${response.status}.`)
        }
    }).then(primes => {
        displayPrimes(primes)
    }).catch(error => {
        console.log(error)
    })
}

function updatePrimesLabel(boundary) {
    let primesLabel = document.getElementById("primes_label")
    primesLabel.textContent = `Prime numbers lesser than ${boundary} are:`
}

function validateBoundary() {
    let input = document.getElementById("boundary_input")
    if (!input.value) input.value = default_boundary_value.toString()
    let numeric = isNumeric(input.value)
    if (!numeric || parseInt(input.value) < 0) input.value = default_boundary_value.toString();
    return input.value
}

function isNumeric(value) {
    for (let index = 0, len = value.length; index < len; index++) {
        let code = value.charCodeAt(index)
        if (!(code > 47 && code < 58)) return false
    }
    return true
}

function displayPrimes(primes) {
    let primesColumn = document.getElementById("primes_column")
    if (primes.length === 0) {
        primesColumn.innerHTML = "<div class='row'>No primes lesser that given boundary found!</div>"
        return
    }
    primesColumn.innerHTML = ""
    for (let index = 0; index < primes.length; index++) {
        let rowIndex = Math.floor(index / 10)
        if (index % 10 === 0) {
            primesColumn.innerHTML += `<div class="row with_top_margin" id="row_${rowIndex}"></div>`
        }
        let primesRow = document.getElementById(`row_${rowIndex}`)
        primesRow.innerHTML += `<div class="col_10 center_content">${primes[index]}</div>`
    }
}