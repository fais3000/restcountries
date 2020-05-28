/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.v2.domain;

import eu.fayder.restcountries.domain.BaseCountry;
import io.micronaut.core.annotation.Introspected;

import java.util.List;
import java.util.Map;

@Introspected
public class Country extends BaseCountry {
    private static final int  UNICODE_OFFSET = 127397;
    private List<Currency> currencies;
    private List<Language> languages;
    private Map<String, String> translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs;
    private String cioc;

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public String getFlag() {
        return flag;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public String getUnicodeFlag() {
        String cc = getAlpha2Code();
        if (null == cc || cc.length() != 2) {
            return "";
        }
        cc = cc.equalsIgnoreCase("uk") ? "gb" : cc;
        StringBuilder unicodeStr = new StringBuilder();
        cc.chars().forEach(c -> unicodeStr.appendCodePoint(c + UNICODE_OFFSET));
        return unicodeStr.toString();
    }
}
