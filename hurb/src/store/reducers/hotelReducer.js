import {
  SET_ALL_HOTELS,
} from '../actions/actionTypes';

const initialState = {
  hoteis: []
}

const reducer = (state = initialState, action) => {
  switch (action.type) {

    case SET_ALL_HOTELS:
      return {
        ...state,
        hoteis: action.hotels
      };
      break;

    default:
      return state;
  }
}

export default reducer;