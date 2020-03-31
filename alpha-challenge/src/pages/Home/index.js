import React, { useEffect, useState } from 'react';
import { SectionList, FlatList, ActivityIndicator, Text } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import logo from '../../assets/logo.png';
import {
    Container,
    InputView,
    SearchInput,
    Title,
    UITableView,
    HotelHeadImage,
    HotelImagesView,
    HotelGalleryImage,
    HotelLocationText,
    HotelNameText,
    HotelPriceText,
    HotelDescriptionText,
    HotelStarsText,
    HotelStarsView,
    HotelGalleryView,
    HotelDetailView,
    LoadingView,
    LogoImage,
    HeaderView,
    SafeAreaView,
} from './styles';
import api from '../../services/api';

export default function Home() {
    const [hotels, setHotels] = useState([]);
    const [hotels5Stars, setHotels5Stars] = useState([]);
    const [hotels4Stars, setHotels4Stars] = useState([]);
    const [hotels3Stars, setHotels3Stars] = useState([]);
    const [hotels2Stars, setHotels2Stars] = useState([]);
    const [hotels1Stars, setHotels1Stars] = useState([]);
    const [loading, setLoading] = useState(false);

    async function loadHotels() {
        setLoading(true);
        const response = await api.get('', {
            params: {
                q: 'buzios',
                page: 1,
            },
        });
        setHotels(response.data.results);
        setHotels5Stars({
            title: '5 Stars',
            data: hotels.filter(hotel => hotel.stars === 5),
        });
        setHotels4Stars({
            title: '4 Stars',
            data: hotels.filter(hotel => hotel.stars === 4),
        });
        setHotels3Stars({
            title: '3 Stars',
            data: hotels.filter(hotel => hotel.stars === 3),
        });
        setHotels2Stars({
            title: '2 Stars',
            data: hotels.filter(hotel => hotel.stars === 2),
        });
        setHotels1Stars({
            title: '1 Stars',
            data: hotels.filter(hotel => hotel.stars === 1),
        });

        console.tron.log(hotels);
        console.tron.log(hotels5Stars);
        console.tron.log(hotels4Stars);
        console.tron.log(hotels3Stars);
        console.tron.log(hotels2Stars);
        console.tron.log(hotels1Stars);
        setLoading(false);
    }

    useEffect(() => {
        loadHotels();
    }, []);

    return (
        <Container>
            <HeaderView>
                <Title> Hoteis </Title>
                <LogoImage source={logo} />
            </HeaderView>
            <InputView>
                <Icon name="search" color="#2E2E2E" size={40} />
                <SearchInput placeholder="Vai para onde?" />
            </InputView>

            <FlatList
                data={hotels.sort(function orderByStars(a, b) {
                    if (a.stars > b.stars) {
                        return -1;
                    }
                    if (a.stars < b.stars) {
                        return 1;
                    }
                    // a must be equal to b
                    return 0;
                })}
                keyExtrator={hotel => String(hotel.id)}
                showsVerticalScrollIndicator={false}
                renderItem={({ item: hotel }) =>
                    loading ? (
                        <LoadingView>
                            <ActivityIndicator color="#0101DF" size="large" />
                        </LoadingView>
                    ) : (
                        <UITableView>
                            <HotelImagesView>
                                <HotelHeadImage
                                    source={{ uri: `${hotel.image}` }}
                                />
                                <HotelGalleryView>
                                    <HotelGalleryImage
                                        source={{
                                            uri: `${hotel.gallery[1].url}`,
                                        }}
                                    />
                                    <HotelGalleryImage
                                        source={{
                                            uri: `${hotel.gallery[2].url}`,
                                        }}
                                    />
                                </HotelGalleryView>
                            </HotelImagesView>
                            <HotelDetailView>
                                <HotelLocationText>
                                    {`${hotel.address.city}/ ${hotel.address.state}`}
                                </HotelLocationText>
                                <HotelNameText> {hotel.name}</HotelNameText>
                                <HotelDescriptionText>
                                    {hotel.smallDescription}
                                </HotelDescriptionText>
                                <HotelStarsView>
                                    <HotelStarsText>
                                        {hotel.stars}
                                    </HotelStarsText>
                                    <Icon
                                        name="star"
                                        color="#daa520"
                                        size={15}
                                    />
                                </HotelStarsView>
                                <HotelPriceText>
                                    {hotel.price.amount}
                                </HotelPriceText>
                            </HotelDetailView>
                        </UITableView>
                    )
                }
            />
        </Container>
    );
}
