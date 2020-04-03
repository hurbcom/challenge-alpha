import styled from 'styled-components/native';

export const Container = styled.View`
    flex: 1;
    background: #fff;
    padding: 14px;
    margin-top: 20px;
`;
export const HotelView = styled.View`
    width: 100%;
    height: 100%;
    margin-top: 15px;
    border-radius: 4px;
`;
export const ImagesView = styled.View`
    background: #eaecee;
    flex-direction: row;
    max-height: 200px;
`;
export const ContactView = styled.View`
    flex-direction: row;
    justify-content: space-between;
    margin-top: 8px;
`;
export const ContactButton = styled.Text`
    font-weight: bold;
    font-size: 15px;
    color: #fff;
`;
export const LoadingIndicator = styled.ActivityIndicator`
    color: #0101df;
`;
export const TouchOpacityButton = styled.TouchableOpacity`
    background-color: #0101df;
    border-radius: 8px;
    height: 50px;
    width: 48%;
    justify-content: center;
    align-items: center;
`;

export const GaleryView = styled.View`
    flex-direction: row;
    width: 24%;
    height: 50%;
`;
export const HeadImage = styled.Image`
    width: 200px;
    height: 200px;
`;
export const SecondImage = styled.Image`
    width: 48%;
    height: 50%;
`;
export const GalleryImage = styled.Image`
    width: 100%;
    height: 100%;
`;
export const SecondView = styled.View`
    flex-direction: column;
    width: 100%;
`;
export const DetailsView = styled.View`
    margin-top: 10px;
    border-bottom-width: 3px;
    border-bottom-color: #e6e6e6;
    border-bottom-right-radius: 40px;
    border-bottom-left-radius: 40px;
`;
export const DescritionText = styled.Text.attrs({
    numberOfLines: 10,
})`
    font-size: 10px;
    margin-top: 4px;
    border-bottom-width: 1px;
    border-bottom-color: #e6e6e6;
    color: #919393;
    text-align: justify;
`;
export const AmenitiesView = styled.View`
    flex-direction: row;
    height: 50px;
    margin-top: 10px;
    border-bottom-width: 1px;
    border-bottom-color: #e6e6e6;
`;
export const AmenitiesFlatListView = styled.View`
    flex-direction: row;
`;
export const CancelView = styled.View`
    flex-direction: row;
    align-items: center;
`;
export const CancelText = styled.Text`
    font-size: 10px;
    font-weight: bold;
`;
export const AmenitiesText = styled.Text`
    font-size: 10px;
    font-weight: bold;
`;
export const NameText = styled.Text`
    font-size: 14px;
    font-weight: bold;
`;
export const ContactHelpText = styled.Text`
    font-size: 14px;
    font-weight: bold;
    margin-top: 20px;
    align-self: center;
`;
export const ContactHelpView = styled.View``;
