import { createStore, combineReducers } from 'redux'
import { AsyncStorage } from 'react-native'
import { persistStore, persistReducer } from 'redux-persist'

import hotelReducer from './reducer/hotelReducer'
import favoriteReducer from './reducer/favoriteReducer'

const reducers = combineReducers({hotelRdc: hotelReducer, favoriteRdc: favoriteReducer})

const persistConfig = {
    key: 'root',
    storage: AsyncStorage,
    blacklist: ['hotelRdc']
};

const persistedReducer = persistReducer(persistConfig, reducers);

const store = createStore(persistedReducer)
let persistor = persistStore(store);

export { store, persistor }
