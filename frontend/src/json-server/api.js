
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
export const fetchPatch = (url, id, data) => {
    fetch(`${url}${id}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'Application/json' },
      body: JSON.stringify(data),
    })
      .then(() => {
        window.location.reload();
      })
      .catch((error) => {
        console.error('Error', error);
      });
};