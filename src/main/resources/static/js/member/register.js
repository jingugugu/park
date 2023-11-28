
async function isConfirmEmail(mailTo) {
    const result = await axios.get(`/member_rest/sendConfirmMail/${mailTo}`);
    return result.data;
}

async function isConfirmEmail_id(email_id) {
    const result = await axios.get(`/member_rest/emailCheck/${email_id}`);
    return result.data;
}

