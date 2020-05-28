package eu.fayder.restcountries.v2;

import eu.fayder.restcountries.v2.domain.Country;
import eu.fayder.restcountries.v2.domain.Currency;
import eu.fayder.restcountries.v2.domain.Language;
import eu.fayder.restcountries.v2.domain.RegionalBloc;
import eu.fayder.restcountries.v2.rest.CountryService;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@MicronautTest
public class CountryServiceTest {

    @Inject
    private CountryService countryService;
    
    @Test
    public void getAll() throws Exception {
        List<Country> countries = countryService.getAll();
        Assertions.assertFalse(countries.isEmpty());
        System.out.println("TOTAL Countries " + countries.size());
    }

    @Test
    public void getByAlpha2() throws Exception {
        Country country = countryService.getByAlpha("CO");
        Assertions.assertNotNull(country);
        Assertions.assertEquals("CO", country.getAlpha2Code());
    }

    @Test
    public void getByAlpha3() throws Exception {
        Country country = countryService.getByAlpha("COL");
        Assertions.assertNotNull(country);
        Assertions.assertEquals("COL", country.getAlpha3Code());
    }

    @Test
    public void getByCodeList() throws Exception {
        List<Country> countries = countryService.getByCodeList("CO;NOR;EE");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals(3, countries.size());

        for (Country country : countries) {
            Assertions.assertTrue(country.getAlpha2Code().equals("CO") || country.getAlpha2Code().equals("NO") || country.getAlpha2Code().equals("EE"));
        }
    }

    @Test
    public void getByCurrency() throws Exception {
        List<Country> countries = countryService.getByCurrency("EUR");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (Currency currency : country.getCurrencies()) {
                currency.getCode().equals("EUR");
            }
        }
    }

    @Test
    public void getByName() throws Exception {
        List<Country> countries = countryService.getByName("Norway", false);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals("Norway", countries.get(0).getName());
    }

    @Test
    public void getByNamePriority() throws Exception {
        List<Country> countries = countryService.getByName("Iran", false);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals("Iran (Islamic Republic of)", countries.get(0).getName());

        countries = countryService.getByName("United", false);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals("United States Minor Outlying Islands", countries.get(0).getName());
    }

    @Test
    public void getByNameAlt() throws Exception {
        List<Country> countries = countryService.getByName("Norge", false);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals("Norway", countries.get(0).getName());
    }

    @Test
    public void getByNameFullText() throws Exception {
        List<Country> countries = countryService.getByName("Russian Federation", true);
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals("Russian Federation", countries.get(0).getName());
    }

    @Test
    public void getByNameFullTextNotFound() throws Exception {
        List<Country> countries = countryService.getByName("Russian Fed", true);
        Assertions.assertNotNull(countries);
        Assertions.assertTrue(countries.isEmpty());
    }

    @Test
    public void getByCallingCode() throws Exception {
        List<Country> countries = countryService.getByCallingCode("57");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals(1, countries.size());
        Assertions.assertEquals("CO", countries.get(0).getAlpha2Code());
    }

    @Test
    public void getByCapital() throws Exception {
        List<Country> countries = countryService.getByCapital("Tallinn");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        Assertions.assertEquals(1, countries.size());
        Assertions.assertEquals("EE", countries.get(0).getAlpha2Code());
        Assertions.assertEquals("Eesti", countries.get(0).getNativeName());
    }

    @Test
    public void getByRegion() throws Exception {
        List<Country> countries = countryService.getByRegion("Europe");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            Assertions.assertEquals("Europe", country.getRegion());
        }
    }

    @Test
    public void getByLanguageCode() throws Exception {
        List<Country> countries = countryService.getByLanguage("es");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (Language language : country.getLanguages()) {
                if (language.getIso639_1().equals("es")) {
                    return;
                }
            }
        }
        Assertions.fail();
    }

    @Test
    public void getByDemonym() throws Exception {
        List<Country> countries = countryService.getByDemonym("french");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            Assertions.assertEquals("french", country.getDemonym().toLowerCase());
        }
    }

    @Test
    public void getByRegionalBloc() throws Exception {
        List<Country> countries = countryService.getByRegionalBloc("eu");
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());
        for (Country country : countries) {
            for (RegionalBloc regionalBloc : country.getRegionalBlocs()) {
                if (regionalBloc.getAcronym().toLowerCase().equals("eu")) {
                    return;
                }
            }
        }
        Assertions.fail();
    }

    @Test
    public void translations() throws Exception {
        Country country = countryService.getByAlpha("COL");
        Assertions.assertNotNull(country);
        Map<String, String> translations = country.getTranslations();
        Assertions.assertEquals("Kolumbien", translations.get("de"));
        Assertions.assertEquals("Colombia", translations.get("es"));
        Assertions.assertEquals("Colombie", translations.get("fr"));
        Assertions.assertEquals("コロンビア", translations.get("ja"));
        Assertions.assertEquals("Colombia", translations.get("it"));
        Assertions.assertEquals("Colômbia", translations.get("br"));
        Assertions.assertEquals("Colômbia", translations.get("pt"));
    }
}
