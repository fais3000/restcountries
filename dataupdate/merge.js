'use strict'

const fs = require('fs');

const lcc = JSON.parse(fs.readFileSync('lcc.json'));
const orig = JSON.parse(fs.readFileSync('../src/main/resources/countriesV2.json'));
const upd = JSON.parse(fs.readFileSync('source.json'));

const lcc3to2 = (lcc3) => {
  const language = lcc.find(l => l['alpha3-t'] === lcc3 || l['alpha3-b'] === lcc3);
  if (!language || !language.alpha2) {
    console.warn('Can\'t convert LCC3 ot LCC2', lcc3);
    return(lcc3);
  } else {
    return language.alpha2;
  }
}

upd.forEach(country => {
  const oC = orig.find(c => c.alpha2Code === country.cca2);
  if (!oC) {
    console.log('Skipped new Country: ', country.cca2);
  }
  Object.keys(country.translations).forEach(lcc3 => {
    const updTranslation = country.translations[lcc3];
    const lcc2 = lcc3to2(lcc3);
    if (!oC.translations[lcc2] || oC.translations[lcc2] !== updTranslation.common) {
      console.log('Updating country ' + oC.alpha2Code + ', language ' + lcc2 + ' \''+ oC.translations[lcc2] + '\' => \'' + updTranslation.common + '\'');
      oC.translations[lcc2] = updTranslation.common;
    }
  })
});

fs.writeFileSync('../src/main/resources/countriesV2.json', JSON.stringify(orig));
