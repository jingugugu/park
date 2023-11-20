
async function isConfirmEmail(mailTo) {
    const result = await axios.get(`/member_rest/sendConfirmMail/${mailTo}`);
    return result.data;
}

