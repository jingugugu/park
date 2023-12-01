async function getList({rno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${rno}?page=${page}`, {params: {page, size}})

    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))

        return getList({rno:rno, page:lastPage, size:size})
    }
    return result.data
}