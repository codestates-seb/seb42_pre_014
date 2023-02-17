
export const fetchCreate = (url, data) => {
    fetch(url, {
        method: "POST",
        headers: {"Content-Type" : "application/json"},
        body: JSON.stringify(data)
    })
    .catch((error) => {
        console.error('Error', error);
    })
}