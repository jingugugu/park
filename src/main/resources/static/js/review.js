document.querySelector(".pagination").addEventListener("click",function (e) {
    e.preventDefault();
    e.stopPropagation();

    const target = e.target;

    if (target.tagName !== 'A') {
        return;
    }
    const num = target.getAttribute("data-num");

    const formObj = document.querySelector("form");

    formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`;

    formObj.submit();
},false)