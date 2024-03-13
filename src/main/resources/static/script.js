function openMenu() {
    let menu = document.getElementById('menu');

    if (menu.classList.contains("hidden")) {
        menu.classList.replace("hidden", "flex");
    } else {
        menu.classList.replace("flex", "hidden");
    }
}