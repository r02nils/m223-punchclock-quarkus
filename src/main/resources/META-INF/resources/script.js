const URL = 'http://localhost:8080';
let entries = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

const updateEntry = (id) => {
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(document.getElementById("checkIn").value, document.getElementById("checkInTime").value);
    entry['checkOut'] = dateAndTimeToDate(document.getElementById("checkOut").value, document.getElementById("checkOutTime").value);
    entry['id'] = id;
    fetch(`${URL}/entries/update`,{
        method:'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then(() => {
        entries.splice((id-1),1);
        entries.splice((id-1),0,entry);
        renderEntries();
    })
};

const deleteEntry = (id) => {
    fetch(`${URL}/entries/${id}`,{
        method:'DELETE'
    }).then(() => {
        entries = entries.filter((entry) => entry.id !== id);
        renderEntries();
    })
};


const createDeleteButtonCell = (id) => {
    const cell = document.createElement("td");
    const button = document.createElement("button");
    button.addEventListener("click", () => {
        deleteEntry(id);
    });
    button.innerText = "Delete";
    cell.appendChild(button);
    return cell;
}

const createUpdateButtonCell = (id) => {
    const cell = document.createElement("td");
    const button = document.createElement("button");
    button.addEventListener("click", () => {
        updateEntry(id);
    });
    button.innerText = "Update";
    cell.appendChild(button);
    return cell;
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createDeleteButtonCell(entry.id));
        row.appendChild(createUpdateButtonCell(entry.id));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});