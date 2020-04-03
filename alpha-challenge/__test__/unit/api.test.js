import axios from '../mocks/axios';

export default async () => {
    const response = await axios.get(`https://www.hurb.com/search/api`, {
        params: {
            q: 'Buzios',
            page: 1,
        },
    });

    return response.data.results;
};
