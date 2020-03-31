import React, { useEffect, useState } from 'react';
import { SectionList, ActivityIndicator, Button } from 'react-native';
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
    SectionHeaderText,
    HotelAmenitiesText,
    HotelAmenitiesView,
    HotelGalleryView,
    SectionHeaderView,
    HotelDetailView,
    LoadingView,
    LogoImage,
    HeaderView,
    SubmitButton,
} from './styles';
import api from '../../services/api';

export default function Home() {
    const [hotels, setHotels] = useState([]);
    const [hotels5Stars, setHotels5Stars] = useState([]);
    const [hotels4Stars, setHotels4Stars] = useState([]);
    const [hotels3Stars, setHotels3Stars] = useState([]);
    const [hotels2Stars, setHotels2Stars] = useState([]);
    const [hotels1Stars, setHotels1Stars] = useState([]);
    const [packet, sePacket] = useState([]);
    const [loading, setLoading] = useState(null);

    async function loadHotels() {
        setLoading(true);
        const response = await api.get('', {
            params: {
                q: 'buzios',
                page: 1,
            },
        });
        setHotels(response.data.results);
        console.tron.log(response.data);
        setHotels5Stars(hotels.filter(hotel => hotel.stars === 5));
        setHotels4Stars(hotels.filter(hotel => hotel.stars === 4));
        setHotels3Stars(hotels.filter(hotel => hotel.stars === 3));
        setHotels2Stars(hotels.filter(hotel => hotel.stars === 2));
        setHotels1Stars(hotels.filter(hotel => hotel.stars === 1));
        sePacket(hotels.filter(hotel => hotel.stars === 0));
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
                <SubmitButton>
                    <Icon
                        name="keyboard-arrow-right"
                        color="#2E2E2E"
                        size={40}
                    />
                </SubmitButton>
            </InputView>

            <SectionList
                sections={[
                    {
                        title: '5',
                        data: hotels5Stars,
                    },
                    {
                        title: '4',
                        data: hotels4Stars,
                    },
                    {
                        title: '3',
                        data: hotels3Stars,
                    },
                    {
                        title: '2',
                        data: hotels2Stars,
                    },
                    {
                        title: '1',
                        data: hotels1Stars,
                    },
                ]}
                keyExtrator={item => item.id}
                showsVerticalScrollIndicator={false}
                renderSectionHeader={({ section }) =>
                    loading || (
                        <SectionHeaderView>
                            <SectionHeaderText>
                                {section.title}
                            </SectionHeaderText>
                            <Icon name="star" color="#daa520" size={25} />
                        </SectionHeaderView>
                    )
                }
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
                            </HotelImagesView>
                            <HotelDetailView>
                                <HotelLocationText>
                                    {`${hotel.address.city}/ ${hotel.address.state}`}
                                </HotelLocationText>
                                <HotelNameText>{hotel.name}</HotelNameText>
                                <HotelDescriptionText>
                                    {hotel.smallDescription}
                                </HotelDescriptionText>
                                <HotelAmenitiesView>
                                    <HotelAmenitiesText>
                                        {hotel.amenities[0] ? (
                                            <>
                                                <Icon
                                                    name="check"
                                                    color="#2E2E2E"
                                                    size={10}
                                                />
                                                <HotelAmenitiesText>
                                                    {hotel.amenities[0].name}
                                                </HotelAmenitiesText>
                                            </>
                                        ) : null}
                                    </HotelAmenitiesText>
                                    <HotelAmenitiesText>
                                        {hotel.amenities[1] ? (
                                            <>
                                                <Icon
                                                    name="check"
                                                    color="#2E2E2E"
                                                    size={10}
                                                />
                                                <HotelAmenitiesText>
                                                    {hotel.amenities[1].name}
                                                </HotelAmenitiesText>
                                            </>
                                        ) : null}
                                    </HotelAmenitiesText>
                                    <HotelAmenitiesText>
                                        {hotel.amenities[2] ? (
                                            <>
                                                <Icon
                                                    name="check"
                                                    color="#2E2E2E"
                                                    size={10}
                                                />
                                                <HotelAmenitiesText>
                                                    {hotel.amenities[2].name}
                                                </HotelAmenitiesText>
                                            </>
                                        ) : null}
                                    </HotelAmenitiesText>
                                </HotelAmenitiesView>
                                <HotelPriceText>
                                    {Intl.NumberFormat('pt-BR', {
                                        style: 'currency',
                                        currency: 'BRL',
                                    }).format(hotel.price.amount)}
                                </HotelPriceText>
                            </HotelDetailView>
                        </UITableView>
                    )
                }
            />
        </Container>
    );
}
