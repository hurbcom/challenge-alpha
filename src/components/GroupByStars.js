 //agrupa por estrelas ou pacotes
 export const groupStarsPackages = (lista) => {
    let grupos = []

    if(lista) {
        for(let i=5; i>=1; i--) {
            let hotels = lista.filter(hotel => hotel.stars === i)
            if(hotels.length > 0) grupos.push({ data: hotels, titulo: `${i} estrelas`})
          }
      
          let packages = lista.filter(pack => pack.stars === null)
          if(packages.length > 0) grupos.push({ data: packages, titulo: 'Pacotes'})
    }
    
    return grupos
  }