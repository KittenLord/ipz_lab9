function book() {
    const urlParams = new URLSearchParams(window.location.search);
    const roomId = urlParams.get("id");

    location.href = `/book?id=${roomId}`;
}
