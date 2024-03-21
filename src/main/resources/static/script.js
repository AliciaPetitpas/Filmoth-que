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

function backgroundColor() {
    let body = document.getElementById("body");

    if (body.classList.contains('bg-default')) {
        body.classList.replace('bg-default', 'bg-pink');
    } else {
        body.classList.replace('bg-pink', 'bg-default');
    }
}

function addNote(note) {
    let stars = document.getElementById("stars-note").children;
    let starNote = document.getElementById("star-" + note);

    for (const star of stars) {
        if (note === 1) {
            if (starNote.firstElementChild.classList.contains("full-star")) {
                starNote.firstElementChild.classList.remove("full-star");
            } else {
                starNote.firstElementChild.classList.add("full-star");
            }
        } else {
            if (star !== starNote) {
                if (star.firstElementChild.classList.contains("full-star")) {
                    star.firstElementChild.classList.remove("full-star");
                    starNote.firstElementChild.classList.remove("full-star");
                } else {
                    star.firstElementChild.classList.add("full-star");
                    starNote.firstElementChild.classList.add("full-star");
                }
            } else {
                return;
            }
        }
    }
}
