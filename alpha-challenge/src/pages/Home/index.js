import React, { useEffect, useState } from 'react';
import { Text } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import { Container, HeaderView, SearchInput, Title } from './styles';
import api from '../../services/api';

export default function Home() {
    const [hotels, setHotels] = useState([]);

    async function loadHotels() {
        const response = await api.get('', {
            params: {
                q: 'buzios',
                page: 1,
            },
        });
        setHotels(response.data);
    }

    useEffect(() => {
        loadHotels();
    }, []);

    return (
        <Container>
            <Title> Hoteis </Title>
            <HeaderView>
                <Icon name="search" color="#2E2E2E" size={40} />
                <SearchInput placeholder="Vai para onde?" />
            </HeaderView>
        </Container>
    );
}
