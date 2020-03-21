const { maskMoney } = require('./src/components/MaskMoney')

describe('Hurb tests', () => {
  test('check mask function', () => {
    const input = 900.99
    const output = maskMoney(input)
    const expected = "R$ 900,99"
    expect(output).toBe(expected)
  });
});



 


