package com.radian.cuwbilling.common.bo.codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.radian.foundation.bo.codes.BaseEnumType;

/**
 * This class realizes the type-safe enum pattern.
 * 
 * The enumerated type is BranchTypes, which is a domain of values to describe
 * the possible types of customer branches with which we do business. Branch
 * type is relevant to Customer Branches.
 * 
 * @author MSM
 * @see Type-Safe Enum Pattern
 * @version 1.0
 */
public class Country extends BaseEnumType
{

    /**
     * State: definition of possible values
     */
    // template is: public static final State <statename> = new State(new
    // Long(<ID>, "<desc>", "<abbr>");
    public static final Country ANDORRA = new Country(new Long(18001), "Andorra", "AD");

    public static final Country UNITED_ARAB_EMIRATES = new Country(new Long(18002), "United Arab Emirates", "AE");

    public static final Country AFGHANISTAN = new Country(new Long(18003), "Afghanistan", "AF");

    public static final Country ANTIGUA_AND_BARBUDA = new Country(new Long(18004), "Antigua and Barbuda", "AG");

    public static final Country ANGUILLA = new Country(new Long(18005), "Anguilla", "AI");

    public static final Country ALBANIA = new Country(new Long(18006), "Albania", "AL");

    public static final Country ARMENIA = new Country(new Long(18007), "Armenia", "AM");

    public static final Country NETHERLANDS_ANTILLES = new Country(new Long(18008), "Netherlands Antilles", "AN");

    public static final Country ANGOLA = new Country(new Long(18009), "Angola", "AO");

    public static final Country ANTARCTICA = new Country(new Long(18010), "Antarctica", "AQ");

    public static final Country ARGENTINA = new Country(new Long(18011), "Argentina", "AR");

    public static final Country AMERICAN_SAMOA = new Country(new Long(18013), "American Samoa", "AS");

    public static final Country AUSTRIA = new Country(new Long(18014), "Austria", "AT");

    public static final Country AUSTRALIA = new Country(new Long(18015), "Australia", "AU");

    public static final Country ARUBA = new Country(new Long(18016), "Aruba", "AW");

    public static final Country AZERBAIDJAN = new Country(new Long(18017), "Azerbaidjan", "AZ");

    public static final Country BOSNIA_HERZEGOVINA = new Country(new Long(18018), "Bosnia-Herzegovina", "BA");

    public static final Country BARBADOS = new Country(new Long(18019), "Barbados", "BB");

    public static final Country BANGLADESH = new Country(new Long(18020), "Bangladesh", "BD");

    public static final Country BELGIUM = new Country(new Long(18021), "Belgium", "BE");

    public static final Country BURKINA_FASO = new Country(new Long(18022), "Burkina Faso", "BF");

    public static final Country BULGARIA = new Country(new Long(18023), "Bulgaria", "BG");

    public static final Country BAHRAIN = new Country(new Long(18024), "Bahrain", "BH");

    public static final Country BURUNDI = new Country(new Long(18025), "Burundi", "BI");

    public static final Country BENIN = new Country(new Long(18026), "Benin", "BJ");

    public static final Country BERMUDA = new Country(new Long(18027), "Bermuda", "BM");

    public static final Country BRUNEI_DARUSSALAM = new Country(new Long(18028), "Brunei Darussalam", "BN");

    public static final Country BOLIVIA = new Country(new Long(18029), "Bolivia", "BO");

    public static final Country BRAZIL = new Country(new Long(18030), "Brazil", "BR");

    public static final Country BAHAMAS = new Country(new Long(18031), "Bahamas", "BS");

    public static final Country BHUTAN = new Country(new Long(18032), "Bhutan", "BT");

    public static final Country BOUVET_ISLAND = new Country(new Long(18033), "Bouvet Island", "BV");

    public static final Country BOTSWANA = new Country(new Long(18034), "Botswana", "BW");

    public static final Country BELARUS = new Country(new Long(18035), "Belarus", "BY");

    public static final Country BELIZE = new Country(new Long(18036), "Belize", "BZ");

    public static final Country CANADA = new Country(new Long(18037), "Canada", "CA");

    public static final Country COCOS_KEELING_ISLANDS = new Country(new Long(18038), "Cocos (Keeling) Islands", "CC");

    public static final Country CENTRAL_AFRICAN_REPUBLIC = new Country(new Long(18039), "Central African Republic", "CF");

    public static final Country CONGO = new Country(new Long(18040), "Congo", "CG");

    public static final Country SWITZERLAND = new Country(new Long(18041), "Switzerland", "CH");

    public static final Country IVORY_COAST_COTE_DIVOIRE = new Country(new Long(18042), "Ivory Coast (Cote D'Ivoire)", "CI");

    public static final Country COOK_ISLANDS = new Country(new Long(18043), "Cook Islands", "CK");

    public static final Country CHILE = new Country(new Long(18044), "Chile", "CL");

    public static final Country CAMEROON = new Country(new Long(18045), "Cameroon", "CM");

    public static final Country CHINA = new Country(new Long(18046), "China", "CN");

    public static final Country COLOMBIA = new Country(new Long(18047), "Colombia", "CO");

    public static final Country COSTA_RICA = new Country(new Long(18049), "Costa Rica", "CR");

    public static final Country FORMER_CZECHOSLOVAKIA = new Country(new Long(18050), "Former Czechoslovakia", "CS");

    public static final Country CUBA = new Country(new Long(18051), "Cuba", "CU");

    public static final Country CAPE_VERDE = new Country(new Long(18052), "Cape Verde", "CV");

    public static final Country CHRISTMAS_ISLAND = new Country(new Long(18053), "Christmas Island", "CX");

    public static final Country CYPRUS = new Country(new Long(18054), "Cyprus", "CY");

    public static final Country CZECH_REPUBLIC = new Country(new Long(18055), "Czech Republic", "CZ");

    public static final Country GERMANY = new Country(new Long(18056), "Germany", "DE");

    public static final Country DJIBOUTI = new Country(new Long(18057), "Djibouti", "DJ");

    public static final Country DENMARK = new Country(new Long(18058), "Denmark", "DK");

    public static final Country DOMINICA = new Country(new Long(18059), "Dominica", "DM");

    public static final Country DOMINICAN_REPUBLIC = new Country(new Long(18060), "Dominican Republic", "DO");

    public static final Country ALGERIA = new Country(new Long(18061), "Algeria", "DZ");

    public static final Country ECUADOR = new Country(new Long(18062), "Ecuador", "EC");

    public static final Country ESTONIA = new Country(new Long(18064), "Estonia", "EE");

    public static final Country EGYPT = new Country(new Long(18065), "Egypt", "EG");

    public static final Country WESTERN_SAHARA = new Country(new Long(18066), "Western Sahara", "EH");

    public static final Country ERITREA = new Country(new Long(18067), "Eritrea", "ER");

    public static final Country SPAIN = new Country(new Long(18068), "Spain", "ES");

    public static final Country ETHIOPIA = new Country(new Long(18069), "Ethiopia", "ET");

    public static final Country FINLAND = new Country(new Long(18070), "Finland", "FI");

    public static final Country FIJI = new Country(new Long(18071), "Fiji", "FJ");

    public static final Country FALKLAND_ISLANDS = new Country(new Long(18072), "Falkland Islands", "FK");

    public static final Country MICRONESIA = new Country(new Long(18073), "Micronesia", "FM");

    public static final Country FAROE_ISLANDS = new Country(new Long(18074), "Faroe Islands", "FO");

    public static final Country FRANCE = new Country(new Long(18075), "France", "FR");

    public static final Country FRANCE_EUROPEAN_TERRITORY = new Country(new Long(18076), "France (European Territory)", "FX");

    public static final Country GABON = new Country(new Long(18077), "Gabon", "GA");

    public static final Country GREAT_BRITAIN = new Country(new Long(18078), "Great Britain", "GB");

    public static final Country GRENADA = new Country(new Long(18079), "Grenada", "GD");

    public static final Country GEORGIA = new Country(new Long(18080), "Georgia", "GE");

    public static final Country FRENCH_GUYANA = new Country(new Long(18081), "French Guyana", "GF");

    public static final Country GHANA = new Country(new Long(18082), "Ghana", "GH");

    public static final Country GIBRALTAR = new Country(new Long(18083), "Gibraltar", "GI");

    public static final Country GREENLAND = new Country(new Long(18084), "Greenland", "GL");

    public static final Country GAMBIA = new Country(new Long(18085), "Gambia", "GM");

    public static final Country GUINEA = new Country(new Long(18086), "Guinea", "GN");

    public static final Country GUADELOUPE_FRENCH = new Country(new Long(18088), "Guadeloupe (French)", "GP");

    public static final Country EQUATORIAL_GUINEA = new Country(new Long(18089), "Equatorial Guinea", "GQ");

    public static final Country GREECE = new Country(new Long(18090), "Greece", "GR");

    public static final Country S_GEORGIA_S_SANDWICH_ISLS = new Country(new Long(18091), "S. Georgia & S. Sandwich Isls.", "GS");

    public static final Country GUATEMALA = new Country(new Long(18092), "Guatemala", "GT");

    public static final Country GUAM_USA = new Country(new Long(18093), "Guam (USA)", "GU");

    public static final Country GUINEA_BISSAU = new Country(new Long(18094), "Guinea Bissau", "GW");

    public static final Country GUYANA = new Country(new Long(18095), "Guyana", "GY");

    public static final Country HONG_KONG = new Country(new Long(18096), "Hong Kong", "HK");

    public static final Country HEARD_AND_MCDONALD_ISLANDS = new Country(new Long(18097), "Heard and McDonald Islands", "HM");

    public static final Country HONDURAS = new Country(new Long(18098), "Honduras", "HN");

    public static final Country CROATIA = new Country(new Long(18099), "Croatia", "HR");

    public static final Country HAITI = new Country(new Long(18100), "Haiti", "HT");

    public static final Country HUNGARY = new Country(new Long(18101), "Hungary", "HU");

    public static final Country INDONESIA = new Country(new Long(18102), "Indonesia", "ID");

    public static final Country IRELAND = new Country(new Long(18103), "Ireland", "IE");

    public static final Country ISRAEL = new Country(new Long(18104), "Israel", "IL");

    public static final Country INDIA = new Country(new Long(18105), "India", "IN");

    public static final Country BRITISH_INDIAN_OCEAN_TERRITORY = new Country(new Long(18107), "British Indian Ocean Territory", "IO");

    public static final Country IRAQ = new Country(new Long(18108), "Iraq", "IQ");

    public static final Country IRAN = new Country(new Long(18109), "Iran", "IR");

    public static final Country ICELAND = new Country(new Long(18110), "Iceland", "IS");

    public static final Country ITALY = new Country(new Long(18111), "Italy", "IT");

    public static final Country JAMAICA = new Country(new Long(18112), "Jamaica", "JM");

    public static final Country JORDAN = new Country(new Long(18113), "Jordan", "JO");

    public static final Country JAPAN = new Country(new Long(18114), "Japan", "JP");

    public static final Country KENYA = new Country(new Long(18115), "Kenya", "KE");

    public static final Country KYRGYZSTAN = new Country(new Long(18116), "Kyrgyzstan", "KG");

    public static final Country CAMBODIA = new Country(new Long(18117), "Cambodia", "KH");

    public static final Country KIRIBATI = new Country(new Long(18118), "Kiribati", "KI");

    public static final Country COMOROS = new Country(new Long(18119), "Comoros", "KM");

    public static final Country SAINT_KITTS_NEVIS_ANGUILLA = new Country(new Long(18120), "Saint Kitts & Nevis Anguilla", "KN");

    public static final Country NORTH_KOREA = new Country(new Long(18121), "North Korea", "KP");

    public static final Country SOUTH_KOREA = new Country(new Long(18122), "South Korea", "KR");

    public static final Country KUWAIT = new Country(new Long(18123), "Kuwait", "KW");

    public static final Country CAYMAN_ISLANDS = new Country(new Long(18124), "Cayman Islands", "KY");

    public static final Country KAZAKHSTAN = new Country(new Long(18125), "Kazakhstan", "KZ");

    public static final Country LAOS = new Country(new Long(18126), "Laos", "LA");

    public static final Country LEBANON = new Country(new Long(18127), "Lebanon", "LB");

    public static final Country SAINT_LUCIA = new Country(new Long(18128), "Saint Lucia", "LC");

    public static final Country LIECHTENSTEIN = new Country(new Long(18129), "Liechtenstein", "LI");

    public static final Country SRI_LANKA = new Country(new Long(18130), "Sri Lanka", "LK");

    public static final Country LIBERIA = new Country(new Long(18131), "Liberia", "LR");

    public static final Country LESOTHO = new Country(new Long(18132), "Lesotho", "LS");

    public static final Country LITHUANIA = new Country(new Long(18133), "Lithuania", "LT");

    public static final Country LUXEMBOURG = new Country(new Long(18134), "Luxembourg", "LU");

    public static final Country LATVIA = new Country(new Long(18135), "Latvia", "LV");

    public static final Country LIBYA = new Country(new Long(18136), "Libya", "LY");

    public static final Country MOROCCO = new Country(new Long(18137), "Morocco", "MA");

    public static final Country MONACO = new Country(new Long(18138), "Monaco", "MC");

    public static final Country MOLDAVIA = new Country(new Long(18139), "Moldavia", "MD");

    public static final Country MADAGASCAR = new Country(new Long(18140), "Madagascar", "MG");

    public static final Country MARSHALL_ISLANDS = new Country(new Long(18141), "Marshall Islands", "MH");

    public static final Country MACEDONIA = new Country(new Long(18143), "Macedonia", "MK");

    public static final Country MALI = new Country(new Long(18144), "Mali", "ML");

    public static final Country MYANMAR = new Country(new Long(18145), "Myanmar", "MM");

    public static final Country MONGOLIA = new Country(new Long(18146), "Mongolia", "MN");

    public static final Country MACAU = new Country(new Long(18147), "Macau", "MO");

    public static final Country NORTHERN_MARIANA_ISLANDS = new Country(new Long(18148), "Northern Mariana Islands", "MP");

    public static final Country MARTINIQUE_FRENCH = new Country(new Long(18149), "Martinique (French)", "MQ");

    public static final Country MAURITANIA = new Country(new Long(18150), "Mauritania", "MR");

    public static final Country MONTSERRAT = new Country(new Long(18151), "Montserrat", "MS");

    public static final Country MALTA = new Country(new Long(18152), "Malta", "MT");

    public static final Country MAURITIUS = new Country(new Long(18153), "Mauritius", "MU");

    public static final Country MALDIVES = new Country(new Long(18154), "Maldives", "MV");

    public static final Country MALAWI = new Country(new Long(18155), "Malawi", "MW");

    public static final Country MEXICO = new Country(new Long(18156), "Mexico", "MX");

    public static final Country MALAYSIA = new Country(new Long(18157), "Malaysia", "MY");

    public static final Country MOZAMBIQUE = new Country(new Long(18158), "Mozambique", "MZ");

    public static final Country NAMIBIA = new Country(new Long(18159), "Namibia", "NA");

    public static final Country NEW_CALEDONIA_FRENCH = new Country(new Long(18161), "New Caledonia (French)", "NC");

    public static final Country NIGER = new Country(new Long(18162), "Niger", "NE");

    public static final Country NORFOLK_ISLAND = new Country(new Long(18164), "Norfolk Island", "NF");

    public static final Country NIGERIA = new Country(new Long(18165), "Nigeria", "NG");

    public static final Country NICARAGUA = new Country(new Long(18166), "Nicaragua", "NI");

    public static final Country NETHERLANDS = new Country(new Long(18167), "Netherlands", "NL");

    public static final Country NORWAY = new Country(new Long(18168), "Norway", "NO");

    public static final Country NEPAL = new Country(new Long(18169), "Nepal", "NP");

    public static final Country NAURU = new Country(new Long(18170), "Nauru", "NR");

    public static final Country NEUTRAL_ZONE = new Country(new Long(18171), "Neutral Zone", "NT");

    public static final Country NIUE = new Country(new Long(18172), "Niue", "NU");

    public static final Country NEW_ZEALAND = new Country(new Long(18173), "New Zealand", "NZ");

    public static final Country OMAN = new Country(new Long(18174), "Oman", "OM");

    public static final Country PANAMA = new Country(new Long(18176), "Panama", "PA");

    public static final Country PERU = new Country(new Long(18177), "Peru", "PE");

    public static final Country POLYNESIA_FRENCH = new Country(new Long(18178), "Polynesia (French)", "PF");

    public static final Country PAPUA_NEW_GUINEA = new Country(new Long(18179), "Papua New Guinea", "PG");

    public static final Country PHILIPPINES = new Country(new Long(18180), "Philippines", "PH");

    public static final Country PAKISTAN = new Country(new Long(18181), "Pakistan", "PK");

    public static final Country POLAND = new Country(new Long(18182), "Poland", "PL");

    public static final Country SAINT_PIERRE_AND_MIQUELON = new Country(new Long(18183), "Saint Pierre and Miquelon", "PM");

    public static final Country PITCAIRN_ISLAND = new Country(new Long(18184), "Pitcairn Island", "PN");

    public static final Country PUERTO_RICO = new Country(new Long(18185), "Puerto Rico", "PR");

    public static final Country PORTUGAL = new Country(new Long(18186), "Portugal", "PT");

    public static final Country PALAU = new Country(new Long(18187), "Palau", "PW");

    public static final Country PARAGUAY = new Country(new Long(18188), "Paraguay", "PY");

    public static final Country QATAR = new Country(new Long(18189), "Qatar", "QA");

    public static final Country REUNION_FRENCH = new Country(new Long(18190), "Reunion (French)", "RE");

    public static final Country ROMANIA = new Country(new Long(18191), "Romania", "RO");

    public static final Country RUSSIAN_FEDERATION = new Country(new Long(18192), "Russian Federation", "RU");

    public static final Country RWANDA = new Country(new Long(18193), "Rwanda", "RW");

    public static final Country SAUDI_ARABIA = new Country(new Long(18194), "Saudi Arabia", "SA");

    public static final Country SOLOMON_ISLANDS = new Country(new Long(18195), "Solomon Islands", "SB");

    public static final Country SEYCHELLES = new Country(new Long(18196), "Seychelles", "SC");

    public static final Country SUDAN = new Country(new Long(18197), "Sudan", "SD");

    public static final Country SWEDEN = new Country(new Long(18198), "Sweden", "SE");

    public static final Country SINGAPORE = new Country(new Long(18199), "Singapore", "SG");

    public static final Country SAINT_HELENA = new Country(new Long(18200), "Saint Helena", "SH");

    public static final Country SLOVENIA = new Country(new Long(18201), "Slovenia", "SI");

    public static final Country SVALBARD_AND_JAN_MAYEN_ISLANDS = new Country(new Long(18202), "Svalbard and Jan Mayen Islands", "SJ");

    public static final Country SLOVAK_REPUBLIC = new Country(new Long(18203), "Slovak Republic", "SK");

    public static final Country SIERRA_LEONE = new Country(new Long(18204), "Sierra Leone", "SL");

    public static final Country SAN_MARINO = new Country(new Long(18205), "San Marino", "SM");

    public static final Country SENEGAL = new Country(new Long(18206), "Senegal", "SN");

    public static final Country SOMALIA = new Country(new Long(18207), "Somalia", "SO");

    public static final Country SURINAME = new Country(new Long(18208), "Suriname", "SR");

    public static final Country SAINT_TOME_SAO_TOME_AND_PRINCIPE = new Country(new Long(18209), "Saint Tome (Sao Tome) and Principe", "ST");

    public static final Country FORMER_USSR = new Country(new Long(18210), "Former USSR", "SU");

    public static final Country EL_SALVADOR = new Country(new Long(18211), "El Salvador", "SV");

    public static final Country SYRIA = new Country(new Long(18212), "Syria", "SY");

    public static final Country SWAZILAND = new Country(new Long(18213), "Swaziland", "SZ");

    public static final Country TURKS_AND_CAICOS_ISLANDS = new Country(new Long(18214), "Turks and Caicos Islands", "TC");

    public static final Country CHAD = new Country(new Long(18215), "Chad", "TD");

    public static final Country FRENCH_SOUTHERN_TERRITORIES = new Country(new Long(18216), "French Southern Territories", "TF");

    public static final Country TOGO = new Country(new Long(18217), "Togo", "TG");

    public static final Country THAILAND = new Country(new Long(18218), "Thailand", "TH");

    public static final Country TADJIKISTAN = new Country(new Long(18219), "Tadjikistan", "TJ");

    public static final Country TOKELAU = new Country(new Long(18220), "Tokelau", "TK");

    public static final Country TURKMENISTAN = new Country(new Long(18221), "Turkmenistan", "TM");

    public static final Country TUNISIA = new Country(new Long(18222), "Tunisia", "TN");

    public static final Country TONGA = new Country(new Long(18223), "Tonga", "TO");

    public static final Country EAST_TIMOR = new Country(new Long(18224), "East Timor", "TP");

    public static final Country TURKEY = new Country(new Long(18225), "Turkey", "TR");

    public static final Country TRINIDAD_AND_TOBAGO = new Country(new Long(18226), "Trinidad and Tobago", "TT");

    public static final Country TUVALU = new Country(new Long(18227), "Tuvalu", "TV");

    public static final Country TAIWAN = new Country(new Long(18228), "Taiwan", "TW");

    public static final Country TANZANIA = new Country(new Long(18229), "Tanzania", "TZ");

    public static final Country UKRAINE = new Country(new Long(18230), "Ukraine", "UA");

    public static final Country UGANDA = new Country(new Long(18231), "Uganda", "UG");

    public static final Country UNITED_KINGDOM = new Country(new Long(18232), "United Kingdom", "UK");

    public static final Country USA_MINOR_OUTLYING_ISLANDS = new Country(new Long(18233), "USA Minor Outlying Islands", "UM");

    public static final Country UNITED_STATES = new Country(new Long(18234), "United States", "USA");

    public static final Country URUGUAY = new Country(new Long(18235), "Uruguay", "UY");

    public static final Country UZBEKISTAN = new Country(new Long(18236), "Uzbekistan", "UZ");

    public static final Country VATICAN_CITY_STATE = new Country(new Long(18237), "Vatican City State", "VA");

    public static final Country SAINT_VINCENT_GRENADINES = new Country(new Long(18238), "Saint Vincent & Grenadines", "VC");

    public static final Country VENEZUELA = new Country(new Long(18239), "Venezuela", "VE");

    public static final Country VIRGIN_ISLANDS_BRITISH = new Country(new Long(18240), "Virgin Islands (British)", "VG");

    public static final Country VIRGIN_ISLANDS_USA = new Country(new Long(18241), "Virgin Islands (USA)", "VI");

    public static final Country VIETNAM = new Country(new Long(18242), "Vietnam", "VN");

    public static final Country VANUATU = new Country(new Long(18243), "Vanuatu", "VU");

    public static final Country WALLIS_AND_FUTUNA_ISLANDS = new Country(new Long(18244), "Wallis and Futuna Islands", "WF");

    public static final Country SAMOA = new Country(new Long(18245), "Samoa", "WS");

    public static final Country YEMEN = new Country(new Long(18246), "Yemen", "YE");

    public static final Country MAYOTTE = new Country(new Long(18247), "Mayotte", "YT");

    public static final Country YUGOSLAVIA = new Country(new Long(18248), "Yugoslavia", "YU");

    public static final Country SOUTH_AFRICA = new Country(new Long(18249), "South Africa", "ZA");

    public static final Country ZAMBIA = new Country(new Long(18250), "Zambia", "ZM");

    public static final Country ZAIRE = new Country(new Long(18251), "Zaire", "ZR");

    public static final Country ZIMBABWE = new Country(new Long(18252), "Zimbabwe", "ZW");

    /**
     * System code domain
     */
    private static final Country[] domain = { ANDORRA, UNITED_ARAB_EMIRATES, AFGHANISTAN, ANTIGUA_AND_BARBUDA, ANGUILLA, ALBANIA, ARMENIA,
            NETHERLANDS_ANTILLES, ANGOLA, ANTARCTICA, ARGENTINA, AMERICAN_SAMOA, AUSTRIA, AUSTRALIA, ARUBA, AZERBAIDJAN, BOSNIA_HERZEGOVINA, BARBADOS,
            BANGLADESH, BELGIUM, BURKINA_FASO, BULGARIA, BAHRAIN, BURUNDI, BENIN, BERMUDA, BRUNEI_DARUSSALAM, BOLIVIA, BRAZIL, BAHAMAS, BHUTAN, BOUVET_ISLAND,
            BOTSWANA, BELARUS, BELIZE, CANADA, COCOS_KEELING_ISLANDS, CENTRAL_AFRICAN_REPUBLIC, CONGO, SWITZERLAND, IVORY_COAST_COTE_DIVOIRE, COOK_ISLANDS,
            CHILE, CAMEROON, CHINA, COLOMBIA, COSTA_RICA, FORMER_CZECHOSLOVAKIA, CUBA, CAPE_VERDE, CHRISTMAS_ISLAND, CYPRUS, CZECH_REPUBLIC, GERMANY, DJIBOUTI,
            DENMARK, DOMINICA, DOMINICAN_REPUBLIC, ALGERIA, ECUADOR, ESTONIA, EGYPT, WESTERN_SAHARA, ERITREA, SPAIN, ETHIOPIA, FINLAND, FIJI, FALKLAND_ISLANDS,
            MICRONESIA, FAROE_ISLANDS, FRANCE, FRANCE_EUROPEAN_TERRITORY, GABON, GREAT_BRITAIN, GRENADA, GEORGIA, FRENCH_GUYANA, GHANA, GIBRALTAR, GREENLAND,
            GAMBIA, GUINEA, GUADELOUPE_FRENCH, EQUATORIAL_GUINEA, GREECE, S_GEORGIA_S_SANDWICH_ISLS, GUATEMALA, GUAM_USA, GUINEA_BISSAU, GUYANA, HONG_KONG,
            HEARD_AND_MCDONALD_ISLANDS, HONDURAS, CROATIA, HAITI, HUNGARY, INDONESIA, IRELAND, ISRAEL, INDIA, BRITISH_INDIAN_OCEAN_TERRITORY, IRAQ, IRAN,
            ICELAND, ITALY, JAMAICA, JORDAN, JAPAN, KENYA, KYRGYZSTAN, CAMBODIA, KIRIBATI, COMOROS, SAINT_KITTS_NEVIS_ANGUILLA, NORTH_KOREA, SOUTH_KOREA,
            KUWAIT, CAYMAN_ISLANDS, KAZAKHSTAN, LAOS, LEBANON, SAINT_LUCIA, LIECHTENSTEIN, SRI_LANKA, LIBERIA, LESOTHO, LITHUANIA, LUXEMBOURG, LATVIA, LIBYA,
            MOROCCO, MONACO, MOLDAVIA, MADAGASCAR, MARSHALL_ISLANDS, MACEDONIA, MALI, MYANMAR, MONGOLIA, MACAU, NORTHERN_MARIANA_ISLANDS, MARTINIQUE_FRENCH,
            MAURITANIA, MONTSERRAT, MALTA, MAURITIUS, MALDIVES, MALAWI, MEXICO, MALAYSIA, MOZAMBIQUE, NAMIBIA, NEW_CALEDONIA_FRENCH, NIGER, NORFOLK_ISLAND,
            NIGERIA, NICARAGUA, NETHERLANDS, NORWAY, NEPAL, NAURU, NEUTRAL_ZONE, NIUE, NEW_ZEALAND, OMAN, PANAMA, PERU, POLYNESIA_FRENCH, PAPUA_NEW_GUINEA,
            PHILIPPINES, PAKISTAN, POLAND, SAINT_PIERRE_AND_MIQUELON, PITCAIRN_ISLAND, PUERTO_RICO, PORTUGAL, PALAU, PARAGUAY, QATAR, REUNION_FRENCH, ROMANIA,
            RUSSIAN_FEDERATION, RWANDA, SAUDI_ARABIA, SOLOMON_ISLANDS, SEYCHELLES, SUDAN, SWEDEN, SINGAPORE, SAINT_HELENA, SLOVENIA,
            SVALBARD_AND_JAN_MAYEN_ISLANDS, SLOVAK_REPUBLIC, SIERRA_LEONE, SAN_MARINO, SENEGAL, SOMALIA, SURINAME, SAINT_TOME_SAO_TOME_AND_PRINCIPE,
            FORMER_USSR, EL_SALVADOR, SYRIA, SWAZILAND, TURKS_AND_CAICOS_ISLANDS, CHAD, FRENCH_SOUTHERN_TERRITORIES, TOGO, THAILAND, TADJIKISTAN, TOKELAU,
            TURKMENISTAN, TUNISIA, TONGA, EAST_TIMOR, TURKEY, TRINIDAD_AND_TOBAGO, TUVALU, TAIWAN, TANZANIA, UKRAINE, UGANDA, UNITED_KINGDOM,
            USA_MINOR_OUTLYING_ISLANDS, UNITED_STATES, URUGUAY, UZBEKISTAN, VATICAN_CITY_STATE, SAINT_VINCENT_GRENADINES, VENEZUELA, VIRGIN_ISLANDS_BRITISH,
            VIRGIN_ISLANDS_USA, VIETNAM, VANUATU, WALLIS_AND_FUTUNA_ISLANDS, SAMOA, YEMEN, MAYOTTE, YUGOSLAVIA, SOUTH_AFRICA, ZAMBIA, ZAIRE, ZIMBABWE };

    /**
     * List of all possible BaseEnumTypes for the enumerated type
     */
    public static final List domainList = Collections.unmodifiableList(Arrays.asList(domain));

    /**
     * Restricted use constructor
     */
    public Country(Long id, String label, String codeValue)
    {
        super(id, label, codeValue);
    }

    public Country()
    {
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type key.
     * 
     * @param codeValue
     *            Enumerated type key - Country
     * @return Country for the enumerated type
     */
    public static final Country decodeValue(Long codeID)
    {
        return (Country) BaseEnumType.decodeValue(codeID, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated type
     * value.
     * 
     * @param codeValue
     *            Enumerated type code value - Country
     * @return Country for the enumerated type
     */
    public static final Country decodeValue(String codeValue)
    {
        return (Country) BaseEnumType.decodeValue(codeValue, domainList.iterator());
    }

    /**
     * Return the specific BaseEnumType for the respective enumerated code
     * label.
     * 
     * @param codeValue
     *            Enumerated type code value - BaseEnumType
     * @return Country for the enumerated type
     */
    public static final Country decodeLabel(String codeLabel)
    {
        return (Country) BaseEnumType.decodeLabel(codeLabel, domainList.iterator());
    }
}
