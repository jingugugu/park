
async function isConfirmPassword(pw) {
    const result = await axios.get(`/member_rest/sendConfirmPassword/${pw}`);
    console.log(result);
    return result.data;
}