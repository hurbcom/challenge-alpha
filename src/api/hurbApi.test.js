import 'react-native';
import * as hurbApi from './hurbApi';

describe('hurbApi', () => {
    it('should fetch results correctly', async () => {
        hurbApi.getHotels('Rio de Janeiro').then(response => {
            expect(typeof response).to.be.equal('object');
        })
    });
});
