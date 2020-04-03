import React, { useState, useEffect } from 'react';
import { FlatList, Linking } from 'react-native';
import * as MailComposer from 'expo-mail-composer';
import { useRoute } from '@react-navigation/native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import Header from '../../components/header';
import NoImgLogo from '../../assets/hurbLogo.jpg';
import {
    Container,
    HeadImage,
    GalleryImage,
    ImagesView,
    ContactView,
    ContactButton,
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
    NameText,
    ContactHelpText,
    LoadingIndicator,
    ContactHelpView,
} from './styles';

export default function HotelDetails() {
    const route = useRoute();
    const [secondImage, setSecondImage] = useState(NoImgLogo);
    const [galleryImage1, setGalleryImage1] = useState(NoImgLogo);
    const [galleryImage2, setGalleryImage2] = useState(NoImgLogo);
    const { hotel } = route.params;
    const [loading, setLoading] = useState(false);
    const message = `Olá, estou com algumas duvidas sobre o ${hotel.name}, poderia me ajudar? `;

    function sendMail() {
        // função para enviar e-mail
        MailComposer.composeAsync({
            subject: 'Duvida',
            recipients: ['contato@hurb.com'],
            body: message,
        });
    }
    function sendWhatsapp() {
        // função para enviar whatsapp
        Linking.openURL(`whatsapp://send?phone=+5521969189874&text=${message}`);
    }

    async function loadSuggestions() {
        setLoading(true); // auxilia o loading da galeria imagens
        try {
            setSecondImage(hotel.gallery[1].url);
            setGalleryImage1(hotel.gallery[2].url);
            setGalleryImage2(hotel.gallery[3].url);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        loadSuggestions();
    }, []);

    return (
        <Container>
            <Header />
            <HotelView>
                <ImagesView>
                    <HeadImage source={{ uri: `${hotel.image}` }} />
                    <SecondView>
                        {loading ? (
                            <LoadingIndicator color="#0101DF" size="large" />
                        ) : (
                            <SecondImage source={{ uri: `${secondImage}` }} />
                        )}
                        <GaleryView>
                            {loading ? (
                                <LoadingIndicator
                                    color="#0101DF"
                                    size="large"
                                />
                            ) : (
                                <GalleryImage
                                    source={
                                        {
                                            uri: `${galleryImage1}`,
                                        } || NoImgLogo
                                    }
                                />
                            )}
                            {loading ? (
                                <LoadingIndicator
                                    color="#0101DF"
                                    size="large"
                                />
                            ) : (
                                <GalleryImage
                                    source={
                                        {
                                            uri: `${galleryImage2}`,
                                        } || NoImgLogo
                                    }
                                />
                            )}
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
                                <AmenitiesText> {amenities.name}</AmenitiesText>
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
                    <NameText>{hotel.name}</NameText>
                    <DescritionText> {hotel.description} </DescritionText>
                </DetailsView>
                <ContactHelpView>
                    <ContactHelpText> Ficou alguma duvida? </ContactHelpText>
                    <ContactView>
                        <TouchOpacityButton onPress={() => sendMail()}>
                            <ContactButton>E-mail</ContactButton>
                        </TouchOpacityButton>
                        <TouchOpacityButton onPress={() => sendWhatsapp()}>
                            <ContactButton>Whattsapp</ContactButton>
                        </TouchOpacityButton>
                    </ContactView>
                </ContactHelpView>
            </HotelView>
        </Container>
    );
}
