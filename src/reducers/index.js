import { combineReducers } from 'redux';

import hotelsReducer from './hotelsReducer';

const rootReducer = combineReducers({
    hotels: hotelsReducer
});

export default rootReducer;