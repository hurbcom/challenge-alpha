import styled from 'styled-components/native';

export const Container = styled.View`
    flex: 1;
    padding-top: 30px;
    background: #08088a;
`;

export const HeaderView = styled.View`
    background: #a4a4a4;
    flex-direction: row;
    align-items: center;
    margin-top: 10px;
    width: 100%;
    height: 50px;
`;
export const SearchInput = styled.TextInput`
    margin-left: 5px;
    background: #a4a4a4;
    height: 50px;
    width: 70%;
    border-radius: 5px;
`;

export const Title = styled.Text`
    font-weight: bold;
    font-size: 20px;
`;
