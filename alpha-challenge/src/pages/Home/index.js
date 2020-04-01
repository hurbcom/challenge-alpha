import React, { useEffect, useState } from 'react';
import { SectionList, ActivityIndicator } from 'react-native';
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
    HotelLocationText,
    HotelNameText,
    HotelPriceText,
    HotelDescriptionText,
    SectionHeaderText,
    HotelAmenitiesText,
    HotelAmenitiesView,
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
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const [searchText, setSearchText] = useState('');

    async function loadHotels(locationName) {
        setLoading(true);
        try {
            const response = await api.get('', {
                params: {
                    q: `${locationName}`,
                    page,
                },
            });
            setHotels(response.data.results);
            setPage(response.data.pagination);
            console.log(response.data); // foi solicitado um log pelo desafio dos dados recebidos pela API
        } finally {
            setLoading(false);
        }
    }

    function handleSubmit(locationName) {
        loadHotels(locationName);
    }

    useEffect(() => {
        loadHotels('Buzios');
    }, []);

    return (
        <Container>
            <HeaderView>
                <Title> Hoteis </Title>
                <LogoImage source={logo} />
            </HeaderView>
            <InputView>
                <Icon name="search" color="#2E2E2E" size={40} />
                <SearchInput
                    id="locationInput"
                    onChangeText={text => setSearchText(text)}
                    placeholder="Vai para onde?"
                    returnKeyType="send"
                    onSubmitEditing={() => handleSubmit(searchText)}
                />
                <SubmitButton onPress={() => handleSubmit(searchText)}>
                    <Icon
                        name="keyboard-arrow-right"
                        color="#2E2E2E"
                        size={40}
                    />
                </SubmitButton>
            </InputView>
            {loading ? (
                <LoadingView>
                    <ActivityIndicator color="#0101DF" size="large" />
                </LoadingView>
            ) : (
                <SectionList
                    sections={[
                        {
                            title: '5',
                            data: hotels.filter(hotel => hotel.stars === 5),
                        },
                        {
                            title: '4',
                            data: hotels.filter(hotel => hotel.stars === 4),
                        },
                        {
                            title: '3',
                            data: hotels.filter(hotel => hotel.stars === 3),
                        },
                        {
                            title: '2',
                            data: hotels.filter(hotel => hotel.stars === 2),
                        },
                        {
                            title: hotels.filter(hotel => hotel.stars === 1)
                                ? '1'
                                : null,
                            data: hotels.filter(hotel => hotel.stars === 1),
                        },
                        {
                            // caso existam hoteis sem estrelas, vão ser inseridos em uma
                            // lista (pacotes) como soliticado
                            title: hotels.filter(hotel => hotel.stars === 0)
                                ? 'Pacotes'
                                : null,
                            data: hotels.filter(hotel => hotel.stars === 0),
                        },
                    ]}
                    keyExtrator={item => item.id}
                    showsVerticalScrollIndicator={false}
                    renderSectionHeader={({ section }) =>
                        section.data.length !== 0 ? (
                            <SectionHeaderView>
                                <SectionHeaderText>
                                    {section.title}
                                </SectionHeaderText>
                                <Icon name="star" color="#daa520" size={25} />
                            </SectionHeaderView>
                        ) : null
                    }
                    renderItem={({ item: hotel }) => (
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
                    )}
                />
            )}
        </Container>
    );
}
