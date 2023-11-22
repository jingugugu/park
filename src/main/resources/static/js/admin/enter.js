async function uploadToServer(formObj){
    console.log("upload to server...");
    console.log(formObj);

    const response = await axios({
        method: 'post',
        url: '/upload',
        data: formObj,
        headers: {
            'Content-Type' : 'multipart/form-data',
        },
    });

    return response.data;
}

async function removeFileToServer(uuid, fileName){
    const response = await axios.delete(`/remove/${uuid}_${fileName}`);
    return response.data;
}

async function removeDefaultFileToServer(fileName,type,folderName){
    const response = await axios.delete(`/remove/${fileName}/${type}/${folderName}`);
    return response.data;
}