import * as constants from '../constants/hotels';
import * as hurbApi from '../api/hurbApi';
import * as ramda from 'ramda';

export const getHotelsSuccess = (hotels = []) =>
  ({ type: constants.FETCH_HOTELS_SUCCESS, hotels });

export const getHotelsFail = () =>
  ({ type: constants.FETCH_HOTELS_FAIL });

export const getHotelsByLocation = (location = '') => {
  return(dispatch) => {
    return hurbApi.getHotels(location).then(response => {
        const hotels = ramda.path(
            ['results'], response);
        if (hotels) {
            return dispatch(getHotelsSuccess(hotels));
        }
        return dispatch(getHotelsFail());
    }).catch(err => {
        return dispatch(getHotelsFail());
    })
  }
}