async function getMarkers(){
    const result = await axios.get(`/enter/map/list`)
    return result.data
}

async function getMarkerOne(type,facility_no){
    const result = await axios.get(`/enter/map/${type}/${facility_no}`)
    console.log(result);
    return result.data
}