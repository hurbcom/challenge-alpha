import React, { useEffect, useState } from 'react';
import { useNavigation } from '@react-navigation/native';
import { SectionList, ActivityIndicator, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import Header from '../../components/header';
import {
    Container,
    SearchInput,
    PaginationButton,
    SubmitButton,
    HotelHeadImage,
    InputView,
    HotelImagesView,
    HotelAmenitiesView,
    HotelDetailView,
    SectionHeaderView,
    HotelListView,
    UITableView,
    LoadingView,
    PaginationView,
    HotelLocationText,
    HotelNameText,
    HotelPriceText,
    HotelDescriptionText,
    HotelAmenitiesText,
    SectionHeaderText,
    PaginationText,
} from './styles';
import api from '../../services/api';

export default function HotelSearchList() {
    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(false); // variavel para auxiliar o loading dos componentes
    const [pagination, setPagination] = useState({
        total: 2,
        count: 1,
    });
    const [meta, setMeta] = useState();
    const [searchText, setSearchText] = useState('');
    const navigation = useNavigation();

    function navigateToHotelDetails(hotel) {
        navigation.navigate('HotelDetails', { hotel }); // chama a pagina hotelDetails e passa o hotel em questão.
    }

    async function loadHotels(locationName, page = 1) {
        setLoading(true);
        if (page > pagination.total) {
            // garante que o usuario não vai passar do limite de paginas
            setLoading(false);
            return;
        }
        try {
            const response = await api.get('', {
                params: {
                    q: `${locationName}`,
                    page,
                },
            });
            setHotels(response.data.results);
            setMeta(response.data.meta);
            setPagination({
                total: response.data.pagination.count,
                count: page,
            });
            console.log(response.data); // foi solicitado um log pelo desafio dos dados recebidos pela API
        } catch (err) {
            console.log('Error na busca');
        } finally {
            setLoading(false);
        }
    }

    function handleSubmit(locationName) {
        loadHotels(locationName);
    }

    useEffect(() => {
        loadHotels('Buzios', pagination.count); // Buzios pois foi solicitado pelo desafio
    }, []);

    return (
        <Container>
            <Header />
            <InputView>
                <Icon name="search" color="#166DFF" size={40} />
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
                <HotelListView>
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
                                    <Icon
                                        name="star"
                                        color="#daa520"
                                        size={25}
                                    />
                                </SectionHeaderView>
                            ) : null
                        }
                        renderItem={({ item: hotel }) => (
                            <TouchableOpacity
                                pressDelay={1.5}
                                onPress={() => navigateToHotelDetails(hotel)}
                            >
                                <UITableView>
                                    <HotelImagesView>
                                        <HotelHeadImage
                                            source={{ uri: `${hotel.image}` }}
                                        />
                                    </HotelImagesView>
                                    <HotelDetailView>
                                        <HotelLocationText>
                                            {`${hotel.address.city}/${hotel.address.state}`}
                                        </HotelLocationText>
                                        <HotelNameText>
                                            {hotel.name}
                                        </HotelNameText>
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
                                                            {
                                                                hotel
                                                                    .amenities[0]
                                                                    .name
                                                            }
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
                                                            {
                                                                hotel
                                                                    .amenities[1]
                                                                    .name
                                                            }
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
                                                            {
                                                                hotel
                                                                    .amenities[2]
                                                                    .name
                                                            }
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
                            </TouchableOpacity>
                        )}
                    />
                    <PaginationView>
                        {pagination.count > 1 ? ( // previne que o usuario va para paginas antes de 1
                            <PaginationButton
                                onPress={() =>
                                    loadHotels(meta.query, pagination.count - 1)
                                }
                            >
                                <Icon
                                    name="keyboard-arrow-left"
                                    color="#166DFF"
                                    size={40}
                                />
                            </PaginationButton>
                        ) : null}
                        {pagination.count > 1 ? (
                            <>
                                <PaginationText>
                                    {pagination.count}
                                </PaginationText>
                            </>
                        ) : (
                            <PaginationText />
                        )}
                        <PaginationButton
                            onPress={() =>
                                loadHotels(meta.query, pagination.count + 1)
                            }
                        >
                            <Icon
                                name="keyboard-arrow-right"
                                color="#166DFF"
                                size={40}
                            />
                        </PaginationButton>
                    </PaginationView>
                </HotelListView>
            )}
        </Container>
    );
}
