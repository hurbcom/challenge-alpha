import Reactotron from 'reactotron-react-native'; // ferramenta utilizada para auxiliar no 'debug' da aplicação

if (__DEV__) {
    const tron = Reactotron.configure({ host: '192.168.0.16' })
        .useReactNative()
        .connect();

    tron.clear();

    console.tron = tron;
}
