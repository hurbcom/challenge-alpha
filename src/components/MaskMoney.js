export const maskMoney = (_numero) => {
  var numero;
  numero = _numero.toString()
  numero = numero.replace(",",".")
  numero = parseFloat(numero)
  numero = numero.toFixed(2).split(".")
  numero[0] = numero[0].split(/(?=(?:...)*$)/).join(".")
  numero = `R$ ${numero[0]},${numero[1]}`
  return String(numero)
}
