REST Countries
=======

Forked from https://restcountries.eu 

Differences from original
* Packaged as a standalone micronaut service application
* Removed /v1 API 
* Removed /contribute API
* Added `unicodeFlag` attribute

Default port 5055

API Endpoints
=======

Below are described the REST endpoints available that you can use to search for countries

Health Check
--------------

```
/health
```

All
---------------

``` html
/v2/all
```

Name
---------------

Search by country name. It can be the native name or partial name

``` javascript
/v2/name/{name}
```

``` html
/v2/name/eesti
```

``` html
/v2/name/united
```

Full Name
---------------

Search by country full name

``` javascript
/v2/name/{name}?fullText=true
```

``` html
/v2/name/aruba?fullText=true
```

Code
---------------

Search by ISO 3166-1 2-letter or 3-letter country code

``` javascript
/v2/alpha/{code}
```

``` html
/v2/alpha/co
```

``` html
/v2/alpha/col
```

List of codes
---------------

Search by list of ISO 3166-1 2-letter or 3-letter country codes

``` javascript
/v2/alpha?codes={code};{code};{code}
```

``` html
/v2/alpha?codes=col;no;ee
```

Currency
---------------

Search by ISO 4217 currency code

``` javascript
/v2/currency/{currency}
```
``` html
/v2/currency/cop
```

Language
---------------

Search by ISO 639-1 language code

``` javascript
/v2/lang/{et}
```
``` html
/v2/lang/es
```

Capital city
---------------

Search by capital city

``` javascript
/v2/capital/{capital}
```
``` html
/v2/capital/tallinn
```

Calling code
---------------

Search by calling code

``` javascript
/v2/callingcode/{callingcode}
```
``` html
/v2/callingcode/372
```

Region
---------------

Search by region: Africa, Americas, Asia, Europe, Oceania

``` javascript
/v2/region/{region}
```
``` html
/v2/region/europe
```

Regional Bloc
---------------

Search by regional bloc:

- EU (European Union)
- EFTA (European Free Trade Association)
- CARICOM (Caribbean Community)
- PA (Pacific Alliance)
- AU (African Union)
- USAN (Union of South American Nations)
- EEU (Eurasian Economic Union)
- AL (Arab League)
- ASEAN (Association of Southeast Asian Nations)
- CAIS (Central American Integration System)
- CEFTA (Central European Free Trade Agreement)
- NAFTA (North American Free Trade Agreement)
- SAARC (South Asian Association for Regional Cooperation)

``` javascript
/v2/regionalbloc/{regionalbloc}
```
``` html
/v2/regionalbloc/eu
```

Response Example
---------------

``` html
/v2/alpha/col
```

``` json
[[{
	"name": "Colombia",
	"topLevelDomain": [".co"],
	"alpha2Code": "CO",
	"alpha3Code": "COL",
	"callingCodes": ["57"],
	"capital": "Bogotá",
	"altSpellings": ["CO", "Republic of Colombia", "República de Colombia"],
	"region": "Americas",
	"subregion": "South America",
	"population": 48759958,
	"latlng": [4.0, -72.0],
	"demonym": "Colombian",
	"area": 1141748.0,
	"gini": 55.9,
	"timezones": ["UTC-05:00"],
	"borders": ["BRA", "ECU", "PAN", "PER", "VEN"],
	"nativeName": "Colombia",
	"numericCode": "170",
	"currencies": [{
		"code": "COP",
		"name": "Colombian peso",
		"symbol": "$"
	}],
	"languages": [{
		"iso639_1": "es",
		"iso639_2": "spa",
		"name": "Spanish",
		"nativeName": "Español"
	}],
	"translations": {
		"de": "Kolumbien",
		"es": "Colombia",
		"fr": "Colombie",
		"ja": "コロンビア",
		"it": "Colombia",
		"br": "Colômbia",
		"pt": "Colômbia"
	},
	"flag": "/data/col.svg",
    "unicodeFlag": "🇨🇴",
	"regionalBlocs": [{
		"acronym": "PA",
		"name": "Pacific Alliance",
		"otherAcronyms": [],
		"otherNames": ["Alianza del Pacífico"]
	}, {
		"acronym": "USAN",
		"name": "Union of South American Nations",
		"otherAcronyms": ["UNASUR", "UNASUL", "UZAN"],
		"otherNames": ["Unión de Naciones Suramericanas", "União de Nações Sul-Americanas", "Unie van Zuid-Amerikaanse Naties", "South American Union"]
	}]
}]
```

Filter Response
=======

You can filter the output of your request to include only the specified fields.

``` javascript
/v2/{service}?fields={field};{field};{field}
```
``` html
/v2/all?fields=name;capital;currencies
```

Update Tool
=======

Use scripts in `dataupdate` directory download updated country data from  [https://github.com/mledoze/countries] and language code mapping data from [https://datahub.io/core/language-codes]. Requires Node.js and cUrl.

Currently it updates only country name translations.

 ```bash
cd dataupdate
./update.sh 
 ```
 
Review the changes in `src/main/java/resources/countriesV2.json` and commit changes.
 

Sources
=======
* [@mledoze]
* [List of countries]
* [Languages]
* [Currencies]
* [Area]

Similar projects
=======
* [Countries of the world]
* [REST Countries Node.js]
* [REST Countries Ruby]
* [REST Countries Go]
* [REST Countries Python]
* [world-currencies]

License
=======
[Mozilla Public License] MPL 2.0

[dist]: https://github.com/fayder/restcountries/
[Twitter]: https://twitter.com/restcountries
[mailing list]: http://eepurl.com/cC-h2v
[Donate]: https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=V5AJAEMKE6A3E
[@mledoze]: https://github.com/mledoze/countries
[List of countries]: https://en.wikipedia.org/wiki/ISO_3166-1#Current_codes
[Languages]: https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
[Currencies]: https://en.wikipedia.org/wiki/List_of_circulating_currencies
[Area]: https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_area
[Population]: https://en.wikipedia.org/wiki/List_of_countries_by_population
[Gini coefficient]: http://en.wikipedia.org/wiki/List_of_countries_by_income_equality
[Mozilla Public License]: https://www.mozilla.org/en-US/MPL/2.0/
[world-currencies]: https://github.com/wiredmax/world-currencies
[REST Countries Node.js]: https://github.com/aredo/restcountries
[REST Countries Ruby]: https://github.com/davidesantangelo/restcountry
[REST Countries Go]: https://github.com/alediaferia/gocountries
[REST Countries Python]: https://github.com/SteinRobert/python-restcountries
[Countries of the world]: http://countries.petethompson.net
[TTÜ]: https://www.ttu.ee/studying/tut_admission/programmes-in-tut/ask-us/
[Spotify International Pricing Index]: http://mts.io/2014/05/07/spotify-pricing-index/
[Gorillaz]: http://www.gorillaz.com/
[Wanderlust]: https://wanderlust.com/
[Xero]: https://www.xero.com/
[FxPro]: http://www.fxpro.com/
[onefinestay]: https://www.onefinestay.com/
[Much Better Adventures]: https://www.muchbetteradventures.com
[SKROSS]: http://www.skross.com/en
