import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import api from '../../src/services/api';

const apiMock = MockAdapter(api);

describe('test', () => {
    it('fetches successfully data from an API', async () => {



        apiMock.onGet('/').reply(200, {hotel.name = 'Teste'});

        axios.get.mockImplementationOnce(() => Promise.resolve(data));
        await expect(fetchData('react')).resolves.toEqual(data);
        expect(axios.get).toHaveBeenCalledWith(`${API}/search?query=react`);
    });
});
