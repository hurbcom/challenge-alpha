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
    background: #e6e6e6;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    margin-top: 15px;
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
    background: #e6e6e6;
    height: 50px;
    width: 70%;
    border-radius: 5px;
`;

export const Title = styled.Text`
    font-weight: bold;
    font-size: 26px;
`;

export const UITableView = styled.View`
    background: #ffffff;
    margin-top: 10px;
    flex-direction: row;
    border-radius: 4px;
    max-height: 160px;
    border-bottom-width: 1px;
    border-bottom-color: #e6e6e6;
`;

export const HotelHeadImage = styled.Image`
    width: 80px;
    height: 100%;
    background: #ffffff;
`;
export const HotelGalleryImage = styled.Image`
    width: 40px;
    height: 40px;
    background: #ffffff;
`;
export const HotelImagesView = styled.View`
    height: 100%;
    max-height: 180px;
    width: 100%;
    max-width: 80px;
    background: #fafafa;
`;

export const HotelGalleryView = styled.View`
    flex-direction: row;
`;

export const HotelLocationText = styled.Text`
    font-weight: bold;
    font-size: 12px;
    margin-left: 4px;
    margin-top: 4px;
`;
export const HotelNameText = styled.Text.attrs({
    numberOfLines: 1,
})`
    font-size: 16px;
    margin-left: 4px;
`;
export const HotelDescriptionText = styled.Text.attrs({
    numberOfLines: 2,
})`
    color: #999;
    font-size: 10px;
    margin-top: 6px;
    margin-left: 4px;
    text-align: left;
`;
export const HotelPriceText = styled.Text`
    font-size: 20px;
    margin-left: 55%;
    margin-top: auto;
    font-weight: bold;
    color: #daa520;
`;
export const HotelDetailView = styled.View`
    width: 72%;
    padding-left: 10px;
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
    font-size: 18px;
`;
export const HotelAmenitiesText = styled.Text.attrs({
    numberOfLines: 1,
})`
    font-size: 10px;
`;

export const SectionHeaderView = styled.View`
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 6px;
`;
export const SubmitButton = styled(RectButton)`
    align-items: center;
    justify-content: center;
    background: #166dff;
    height: 100%;
    width: 60px;
    border-radius: 4px;
`;
