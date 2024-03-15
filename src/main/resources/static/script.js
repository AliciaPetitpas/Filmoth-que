function openMenu() {
    let menu = document.getElementById('menu');
    let bars = document.getElementById("bars");
    let xmark = document.getElementById("xmark")

    if (menu.classList.contains("hidden")) {
        menu.classList.replace("hidden", "flex");
        xmark.classList.replace("hidden", "flex");
        bars.classList.replace("svg-secondary", "svg-hidden");
    } else {
        menu.classList.replace("flex", "hidden");
        xmark.classList.replace("flex", "hidden");
        bars.classList.replace("svg-hidden", "svg-secondary");
    }
}

