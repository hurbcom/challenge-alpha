import api from './api.test';

it("calls api", async () => {
    const images = await api("teste");
    console.log(images);
})