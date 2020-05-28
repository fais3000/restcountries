/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.v2.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.fayder.restcountries.domain.ICountryRestSymbols;
import eu.fayder.restcountries.v2.domain.Country;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller("/v2")
@Produces(MediaType.APPLICATION_JSON)
public class CountryRest {
    private final CountryService countryService;
    private final ObjectMapper objectMapper;

    public CountryRest(CountryService countryService, ObjectMapper objectMapper) {
        this.countryService = countryService;
        this.objectMapper = objectMapper;
    }

    @Get("all")
    public JsonNode getAllCountries(@QueryValue("fields") @Nullable String fields) {
        return this.getCountries(fields);
    }

    @Get
    public JsonNode getCountries(@QueryValue("fields") @Nullable String fields) {
        log.info("Getting all");
        List<Country> countries = this.countryService.getAll();
        return parsedCountries(countries, fields);
    }

    @Get("alpha/{alphacode}")
    public HttpResponse<JsonNode> getByAlpha(@PathVariable("alphacode") String alpha, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by alpha " + alpha);
        if (isEmpty(alpha) || alpha.length() < 2 || alpha.length() > 3) {
            return HttpResponse.badRequest();
        }
        Country country = this.countryService.getByAlpha(alpha);
        if (country != null) {
            return HttpResponse.ok(parsedCountry(country, fields));
        }
        return HttpResponse.notFound();
    }

    @Get("alpha/")
    public HttpResponse<JsonNode> getByAlphaList(@QueryValue("codes") String codes, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by list " + codes);
        if (isEmpty(codes) || codes.length() < 2 || (codes.length() > 3 && !codes.contains(";"))) {
            return HttpResponse.badRequest();
        }
        try {
            List<Country> countries = this.countryService.getByCodeList(codes);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("currency/{currency}")
    public HttpResponse<JsonNode> getByCurrency(@PathVariable("currency") String currency, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by currency " + currency);
        if (isEmpty(currency) || currency.length() != 3) {
            return HttpResponse.badRequest();
        }
        try {
            List<Country> countries = this.countryService.getByCurrency(currency);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("name/{name}")
    public Object getByName(@PathVariable("name") String name, @QueryValue("fullText") @Nullable Boolean fullText, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by name " + name);
        try {
            List<Country> countries = this.countryService.getByName(name, fullText);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("callingcode/{callingcode}")
    public HttpResponse<JsonNode> getByCallingCode(@PathVariable("callingcode") String callingcode, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = this.countryService.getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("capital/{capital}")
    public HttpResponse<JsonNode> getByCapital(@PathVariable("capital") String capital, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by capital " + capital);
        try {
            List<Country> countries = this.countryService.getByCapital(capital);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("region/{region}")
    public HttpResponse<JsonNode>  getByRegion(@PathVariable("region") String region, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by region " + region);
        try {
            List<Country> countries = this.countryService.getByRegion(region);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("subregion/{subregion}")
    public HttpResponse<JsonNode>  getBySubRegion(@PathVariable("subregion") String subregion, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by sub region " + subregion);
        try {
            List<Country> countries = this.countryService.getBySubRegion(subregion);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("lang/{lang}")
    public HttpResponse<JsonNode>  getByLanguage(@PathVariable("lang") String language, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by language " + language);
        try {
            List<Country> countries = this.countryService.getByLanguage(language);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("demonym/{demonym}")
    public HttpResponse<JsonNode>  getByDemonym(@PathVariable("demonym") String demonym, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by demonym " + demonym);
        try {
            List<Country> countries = this.countryService.getByDemonym(demonym);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    @Get("regionalbloc/{regionalbloc}")
    public HttpResponse<JsonNode>  getByRegionalBloc(@PathVariable("regionalbloc") String regionalBlock, @QueryValue("fields") @Nullable String fields) {
        log.info("Getting by regional bloc " + regionalBlock);
        try {
            List<Country> countries = this.countryService.getByRegionalBloc(regionalBlock);
            if (!countries.isEmpty()) {
                return HttpResponse.ok(parsedCountries(countries, fields));
            }
            return HttpResponse.notFound();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResponse.serverError();
        }
    }

    private JsonNode parsedCountry(Country country, String fields) {
        if (fields == null || fields.isEmpty()) {
            return objectMapper.valueToTree(country);
        } else {
            return getCountryJson(country, Arrays.asList(fields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private JsonNode parsedCountries(List<Country> countries, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return objectMapper.valueToTree(countries);
        } else {
            return getCountriesJson(countries, Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private JsonNode getCountryJson(Country country, List<String> fields) {
        JsonNode countryNode = objectMapper.valueToTree(country);
        List<String> excludedFields = getExcludedFields(fields);
        excludedFields.forEach(field -> ((ObjectNode)countryNode).remove(field));
        return countryNode;
    }

    private JsonNode getCountriesJson(List<Country> countries, List<String> fields) {
        ArrayNode result = objectMapper.createArrayNode();
        countries.forEach(country -> {
            JsonNode countryJson = getCountryJson(country, fields);
            result.add(countryJson);
        });
        return result;
    }

    private List<String> getExcludedFields(List<String> fields) {
        List<String> excludedFields = new ArrayList<>(Arrays.asList(COUNTRY_FIELDS));
        excludedFields.removeAll(fields);
        return excludedFields;
    }

    private static final String[] COUNTRY_FIELDS = new String[]{
            "name",
            "topLevelDomain",
            "alpha2Code",
            "alpha3Code",
            "callingCodes",
            "capital",
            "altSpellings",
            "region",
            "subregion",
            "translations",
            "population",
            "latlng",
            "demonym",
            "area",
            "gini",
            "timezones",
            "borders",
            "nativeName",
            "numericCode",
            "currencies",
            "languages",
            "flag",
            "regionalBlocs",
            "cioc",
            "unicodeFlag"
    };

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
