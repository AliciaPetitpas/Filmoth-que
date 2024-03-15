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

function addAvis() {
    let formAvis = document.getElementById("form-avis");
    let buttonAvis = document.getElementById("add-avis");

    if (formAvis.classList.contains("hidden")) {
        formAvis.classList.replace("hidden", "flex")
        buttonAvis.classList.add("hidden");
    } else {
        formAvis.classList.replace("flex", "hidden");
        buttonAvis.classList.remove("hidden")
    }
}
