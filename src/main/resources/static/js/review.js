async function getList({rno, page, size, goLast}) {
    const result = await axios.get(`/replies/list/${rno}?page=${page}`, {params: {page, size}})

    if (goLast) {
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total / size))

        return getList({rno: rno, page: lastPage, size: size})
    }
    return result.data
}

async function replyLike({rno,mno}){
    const result = await axios.get(`/like/add/${rno}/${mno}`)
    return result.data;
}
// <a className="reply-like"
//    th:href="@{/like/add(rno=${review.rno}, mno=${review.mno}, facility_no=${review.facility_no}, type=${review.type})}"
//    th:if="${review.liked} > 0">
//     <i className="fa-solid fa-thumbs-up" style="font-size: 25px;"></i>
// </a>