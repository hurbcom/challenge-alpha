import styled from 'styled-components/native';
import { RectButton } from 'react-native-gesture-handler';

export const Container = styled.View`
    flex: 1;
    padding-top: 30px;
    background: #fff;
    padding: 14px;
    margin-top: 20px;
`;

export const InputView = styled.View`
    background: #a4a4a4;
    flex-direction: row;
    align-items: center;
    margin-top: 10px;
    width: 100%;
    height: 50px;
    border-radius: 4px;
`;
export const HeaderView = styled.View`
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
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

export const UITableView = styled.View`
    background: #d8d8d8;
    margin-top: 10px;
    flex-direction: row;
    border-radius: 4px;
    max-height: 200px;
`;

export const HotelHeadImage = styled.Image`
    width: 80px;
    height: 100%;
    background: #d8d8d8;
`;
export const HotelGalleryImage = styled.Image`
    width: 40px;
    height: 40px;
    background: #d8d8d8;
`;
export const HotelImagesView = styled.View`
    height: 100%;
    max-height: 180px;
    width: 100%;
    max-width: 80px;
    background: #d8d8d8;
`;

export const HotelGalleryView = styled.View`
    flex-direction: row;
`;

export const HotelLocationText = styled.Text`
    font-weight: bold;
    font-size: 14px;
    margin-left: 4px;
    margin-top: 4px;
`;
export const HotelNameText = styled.Text.attrs({
    numberOfLines: 1,
})`
    font-size: 18px;
    margin-left: 4px;
`;
export const HotelDescriptionText = styled.Text.attrs({
    numberOfLines: 2,
})`
    color: #999;
    font-size: 12px;
    margin-top: 6px;
    margin-left: 4px;
    text-align: right;
`;
export const HotelPriceText = styled.Text`
    font-size: 16px;
    margin-left: 55%;
    margin-top: auto;
    font-weight: bold;
    color: #daa520;
`;
export const HotelDetailView = styled.View`
    width: 72%;
`;
export const LoadingView = styled.View`
    height: 80%;
    align-items: center;
    justify-content: center;
`;
export const LogoImage = styled.Image`
    width: 35px;
    height: 35px;
`;
export const HotelStarsText = styled.Text``;
export const HotelAmenitiesView = styled.View`
    flex-direction: column;
    margin-left: 6px;
    margin-top: 4px;
`;
export const SectionHeaderText = styled.Text`
    align-items: center;
    text-align: center;
    font-weight: bold;
    font-size: 20px;
`;
export const HotelAmenitiesText = styled.Text.attrs({
    numberOfLines: 1,
})``;

export const SectionHeaderView = styled.View`
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 6px;
`;
export const SubmitButton = styled(RectButton)`
    align-items: center;
    justify-content: center;
    background: #7159c1;
    height: 100%;
    width: 50px;
    border-radius: 4px;
`;
