import Numeral from 'numeral';
import 'numeral/locales/pt-br';

export const formatPrice = rawPrice => {
  rawPrice = parseFloat(rawPrice);
  Numeral.locale('pt-br');
  return Numeral(rawPrice).format('$ 0,0[.]00');
};