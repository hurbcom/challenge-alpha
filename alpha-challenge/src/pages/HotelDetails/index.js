import React, { useState, useEffect } from 'react';
import { FlatList } from 'react-native';
import { useRoute } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import Header from '../../components/header';
import {
    Container,
    HeadImage,
    GalleryImage,
    ImagesView,
    ContactView,
    ContactText,
    HotelView,
    GaleryView,
    SecondImage,
    SecondView,
    TouchOpacityButton,
    DetailsView,
    DescritionText,
    AmenitiesView,
    AmenitiesFlatListView,
    AmenitiesText,
    CancelView,
    CancelText,
} from './styles';
import api from '../../services/suggestionsApi';

export default function HotelDetails() {
    const route = useRoute();
    const [suggestions, setSuggestions] = useState([]);

    const { hotel } = route.params;

    async function loadSuggestions(locationName) {
        const response = await api.get('', {
            params: {
                q: `${locationName}`,
            },
        });
        setSuggestions(response.data.suggestions);
        console.tron.log(suggestions);
    }

    useEffect(() => {
        loadSuggestions(hotel.address.city);
    }, []);

    return (
        <Container>
            <Header />
            <HotelView>
                <ImagesView>
                    <HeadImage source={{ uri: `${hotel.image}` }} />
                    <SecondView>
                        <SecondImage source={{ uri: `${hotel.image}` }} />
                        <GaleryView>
                            <GalleryImage source={{ uri: `${hotel.image}` }} />
                            <GalleryImage source={{ uri: `${hotel.image}` }} />
                        </GaleryView>
                    </SecondView>
                </ImagesView>
                <AmenitiesView>
                    <FlatList
                        data={hotel.amenities}
                        keyExtractor={amenities => amenities.name}
                        showsVerticalScrollIndicator={false}
                        renderItem={({ item: amenities }) => (
                            <AmenitiesFlatListView>
                                <Icon name="check" color="#166DFF" size={12} />
                                <AmenitiesText>{amenities.name} </AmenitiesText>
                            </AmenitiesFlatListView>
                        )}
                    />

                    {hotel.hu_free_cancellation ? (
                        <CancelView>
                            <Icon
                                name="event-available"
                                color="#166DFF"
                                size={40}
                            />
                            <CancelText>
                                Cancelamento Gratis 24 Horas
                            </CancelText>
                        </CancelView>
                    ) : null}
                </AmenitiesView>
                <DetailsView>
                    <DescritionText> {hotel.description} </DescritionText>
                </DetailsView>
                <ContactView>
                    <TouchOpacityButton>
                        <ContactText>E-mail</ContactText>
                    </TouchOpacityButton>
                    <TouchOpacityButton>
                        <ContactText>Whattsapp</ContactText>
                    </TouchOpacityButton>
                </ContactView>
            </HotelView>
        </Container>
    );
}
