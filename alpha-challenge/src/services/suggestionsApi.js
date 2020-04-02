import axios from 'axios';

const api = axios.create({
    baseURL: `https://www.hurb.com/search/api/suggestion?`,
});

export default api;
