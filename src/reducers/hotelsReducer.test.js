import hotelsReducer from './hotelsReducer';

describe('hotelsReducer', () => {
    it('should reduce to previous state correctly', () => {

        const dispatch = {
            type: 'type',
            hotels: [ 'hotelzinho suave' ],
        };
        const previousState = 'previousState';

        const result = hotelsReducer(previousState, dispatch);
        expect(result).toBe(previousState);
    });
});