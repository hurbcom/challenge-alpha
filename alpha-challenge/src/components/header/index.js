import React from 'react';
import logo from '../../assets/logo.png';
import { Container, HeaderView, Title, LogoImage } from './styles';

export default function Header() {
    return (
        <Container>
            <HeaderView>
                <Title> Hoteis </Title>
                <LogoImage source={logo} />
            </HeaderView>
        </Container>
    );
}
