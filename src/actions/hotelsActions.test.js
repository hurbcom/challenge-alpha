import 'react-native';

import { getHotelsSuccess, getHotelsFail } from './hotelsActions';
import * as constants from '../constants/hotels';

describe('hotelsActions', () => {

    it('should dispatch fetch hotels success', () => {
        const hotels = [ 'hotelzinho suave' ];
        const result = getHotelsSuccess(hotels);

        expect(result.type).toBe(constants.FETCH_HOTELS_SUCCESS);
        expect(result.hotels).toBe(hotels);
    });

    it('should dispatch fetch hotels fail', () => {
        const result = getHotelsFail();

        expect(result.type).toBe(constants.FETCH_HOTELS_FAIL);
    });
});