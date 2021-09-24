package com.mvp.mvpmodule.util.country;

import android.content.Context;


import com.mvp.mvpmodule.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 国家
 *
 * @author fence
 * @date 2019-09-05
 */
public class CountryRepo {

    private String defaultCountryDomain="ng";//尼日利亚
    private Map<String, Country> mCountries;

    public CountryRepo() {
        createCountries();
    }

    private void createCountries() {
        mCountries = new LinkedHashMap<>();
        mCountries.put("af", new Country("+93", "AF", "Afghanistan", R.drawable.country_af,"af"));
        mCountries.put("al", new Country("+355", "AL", "Albania", R.drawable.country_al,"al"));
        mCountries.put("dz", new Country("+213", "DZ", "Algeria", R.drawable.country_dz,"dz"));
        mCountries.put("ad", new Country("+376", "AD", "Andorra", R.drawable.country_ad,"ad"));
        mCountries.put("ao", new Country("+244", "AO", "Angola", R.drawable.country_ao,"ao"));
        mCountries.put("ai", new Country("+1264", "AI", "Anguilla", R.drawable.country_ai,"ai"));
        mCountries.put("ag", new Country("+1268", "AG", "Antigua and Barbuda", R.drawable.country_ag,"ag"));
        mCountries.put("ar", new Country("+54", "AR", "Argentina", R.drawable.country_ar,"ar"));
        mCountries.put("am", new Country("+374", "AM", "Armenia", R.drawable.country_am,"am"));
        mCountries.put("ac", new Country("+247", "AC", "Ascension", R.drawable.country_ac,"ac"));
        mCountries.put("au", new Country("+61", "AU", "Australia", R.drawable.country_au,"au"));
        mCountries.put("at", new Country("+43", "AT", "Austria", R.drawable.country_at,"at"));
        mCountries.put("az", new Country("+994", "AZ", "Azerbaijan", R.drawable.country_az,"az"));
        mCountries.put("bs", new Country("+1242", "BS", "Bahamas", R.drawable.country_bs,"bs"));
        mCountries.put("bh", new Country("+973", "BH", "Bahrain", R.drawable.country_bh,"bh"));
        mCountries.put("bd", new Country("+880", "BD", "Bangladesh", R.drawable.country_bd,"bd"));
        mCountries.put("bb", new Country("+1246", "BB", "Barbados", R.drawable.country_bb,"bb"));
        mCountries.put("by", new Country("+375", "BY", "Belarus", R.drawable.country_by,"by"));
        mCountries.put("be", new Country("+32", "BE", "Belgium", R.drawable.country_be,"be"));
        mCountries.put("bz", new Country("+501", "BZ", "Belize", R.drawable.country_bz,"bz"));
        mCountries.put("bj", new Country("+229", "BJ", "Benin", R.drawable.country_bj,"bj"));
        mCountries.put("bm", new Country("+1441", "BM", "Bermuda Is.", R.drawable.country_bm,"bm"));
        mCountries.put("bo", new Country("+591", "BO", "Bolivia", R.drawable.country_bo,"bo"));
        mCountries.put("bw", new Country("+267", "BW", "Botswana", R.drawable.country_bw,"bw"));
        mCountries.put("br", new Country("+55", "BR", "Brazil", R.drawable.country_br,"br"));
        mCountries.put("bn", new Country("+673", "BN", "Brunei", R.drawable.country_bn,"bn"));
        mCountries.put("bg", new Country("+359", "BG", "Bulgaria", R.drawable.country_bg,"bg"));
        mCountries.put("bf", new Country("+226", "BF", "Burkina-faso", R.drawable.country_bf,"bf"));
        mCountries.put("mm", new Country("+95", "MM", "Burma", R.drawable.country_mm,"mm"));
        mCountries.put("bi", new Country("+257", "BI", "Burundi", R.drawable.country_bi,"bi"));
        mCountries.put("kh", new Country("+855", "KH", "Cambodia", R.drawable.country_kh,9,"kh"));
        mCountries.put("cm", new Country("+237", "CM", "Cameroon", R.drawable.country_cm,"cm"));
        mCountries.put("ca", new Country("+1", "CA", "Canada", R.drawable.country_ca,"ca"));
        mCountries.put("ky", new Country("+1345", "KY", "Cayman Is.", R.drawable.country_ky,"ky"));
        mCountries.put("cf", new Country("+236", "CF", "Central African Republic", R.drawable.country_cf,"cf"));
        mCountries.put("td", new Country("+235", "TD", "Chad", R.drawable.country_td,"td"));
        mCountries.put("cl", new Country("+56", "CL", "Chile", R.drawable.country_cl,"cl"));
        mCountries.put("cn", new Country("+86", "CN", "China", R.drawable.country_cn,"cn"));
        mCountries.put("co", new Country("+57", "CO", "Colombia", R.drawable.country_co,"co"));
        mCountries.put("cg", new Country("+242", "CG", "Congo", R.drawable.country_cg,"cg"));
        mCountries.put("ck", new Country("+682", "CK", "Cook Is.", R.drawable.country_ck,"ck"));
        mCountries.put("cr", new Country("+506", "CR", "Costa Rica", R.drawable.country_cr,"cr"));
        mCountries.put("cu", new Country("+53", "CU", "Cuba", R.drawable.country_cu,"cu"));
        mCountries.put("cy", new Country("+357", "CY", "Cyprus", R.drawable.country_cy,"cy"));
        mCountries.put("cz", new Country("+420", "CZ", "Czech Republic", R.drawable.country_cz,"cz"));
        mCountries.put("dk", new Country("+45", "DK", "Denmark", R.drawable.country_dk,"dk"));
        mCountries.put("dj", new Country("+253", "DJ", "Djibouti", R.drawable.country_dj,"dj"));
        mCountries.put("do", new Country("+1890", "DO", "Dominica Rep.", R.drawable.country_do,"do"));
        mCountries.put("ec", new Country("+593", "EC", "Ecuador", R.drawable.country_ec,"ec"));
        mCountries.put("eg", new Country("+20", "EG", "Egypt", R.drawable.country_eg,"eg"));
        mCountries.put("sv", new Country("+503", "SV", "EI Salvador", R.drawable.country_sv,"sv"));
        mCountries.put("ee", new Country("+372", "EE", "Estonia", R.drawable.country_ee,"ee"));
        mCountries.put("et", new Country("+251", "ET", "Ethiopia", R.drawable.country_et,"et"));
        mCountries.put("fj", new Country("+679", "FJ", "Fiji", R.drawable.country_fj,"fj"));
        mCountries.put("fi", new Country("+358", "FI", "Finland", R.drawable.country_fi,"fi"));
        mCountries.put("fr", new Country("+33", "FR", "France", R.drawable.country_fr,"fr"));
        mCountries.put("gf", new Country("+594", "GF", "French Guiana", R.drawable.country_gf,"gf"));
        mCountries.put("pf", new Country("+689", "PF", "French Polynesia", R.drawable.country_pf,"pf"));
        mCountries.put("ga", new Country("+241", "GA", "Gabon", R.drawable.country_ga,"ga"));
        mCountries.put("gm", new Country("+220", "GM", "Gambia", R.drawable.country_gm,"gm"));
        mCountries.put("ge", new Country("+995", "GE", "Georgia", R.drawable.country_ge,"ge"));
        mCountries.put("de", new Country("+49", "DE", "Germany", R.drawable.country_de,"de"));
        mCountries.put("gh", new Country("+233", "GH", "Ghana", R.drawable.country_gh,"gh"));
        mCountries.put("gi", new Country("+350", "GI", "Gibraltar", R.drawable.country_gi,"gi"));
        mCountries.put("gr", new Country("+30", "GR", "Greece", R.drawable.country_gr,"gr"));
        mCountries.put("gd", new Country("+1809", "GD", "Grenada", R.drawable.country_gd,"gd"));
        mCountries.put("gu", new Country("+1671", "GU", "Guam", R.drawable.country_gu,"gu"));
        mCountries.put("gt", new Country("+502", "GT", "Guatemala", R.drawable.country_gt,"gt"));
        mCountries.put("gn", new Country("+224", "GN", "Guinea", R.drawable.country_gn,"gn"));
        mCountries.put("gy", new Country("+592", "GY", "Guyana", R.drawable.country_gy,"gy"));
        mCountries.put("ht", new Country("+509", "HT", "Haiti", R.drawable.country_ht,"ht"));
        mCountries.put("hn", new Country("+504", "HN", "Honduras", R.drawable.country_hn,"hn"));
        mCountries.put("hk", new Country("+852", "HK", "Hongkong", R.drawable.country_hk,"hk"));
        mCountries.put("hu", new Country("+36", "HU", "Hungary", R.drawable.country_hu,"hu"));
        mCountries.put("is", new Country("+354", "IS", "Iceland", R.drawable.country_is,"is"));
        mCountries.put("in", new Country("+91", "IN", "India", R.drawable.country_in,10,"in"));
        mCountries.put("id", new Country("+62", "ID", "Indonesia", R.drawable.country_id,11,"id"));
        mCountries.put("ir", new Country("+98", "IR", "Iran", R.drawable.country_ir,"ir"));
        mCountries.put("iq", new Country("+964", "IQ", "Iraq", R.drawable.country_iq,"iq"));
        mCountries.put("ie", new Country("+353", "IE", "Ireland", R.drawable.country_ie,"ie"));
        mCountries.put("il", new Country("+972", "IL", "Israel", R.drawable.country_il,"il"));
        mCountries.put("it", new Country("+39", "IT", "Italy", R.drawable.country_it,"it"));
        mCountries.put("ci", new Country("+225", "CI", "Ivory Coast", R.drawable.country_ci,"ci"));
        mCountries.put("jm", new Country("+1876", "JM", "Jamaica", R.drawable.country_jm,"jm"));
        mCountries.put("jp", new Country("+81", "JP", "Japan", R.drawable.country_jp,"jp"));
        mCountries.put("jo", new Country("+962", "JO", "Jordan", R.drawable.country_jo,"jo"));
        mCountries.put("kz", new Country("+327", "KZ", "Kazakstan", R.drawable.country_kz,"kz"));
        mCountries.put("ke", new Country("+254", "KE", "Kenya", R.drawable.country_ke,"ke"));
        mCountries.put("kr", new Country("+82", "KR", "Korea", R.drawable.country_kr,"kr"));
        mCountries.put("kw", new Country("+965", "KW", "Kuwait", R.drawable.country_kw,"kw"));
        mCountries.put("kg", new Country("+331", "KG", "Kyrgyzstan", R.drawable.country_kg,"kg"));
        mCountries.put("la", new Country("+856", "LA", "Laos", R.drawable.country_la,10,"la"));
        mCountries.put("lv", new Country("+371", "LV", "Latvia", R.drawable.country_lv,"lv"));
        mCountries.put("lb", new Country("+961", "LB", "Lebanon", R.drawable.country_lb,"lb"));
        mCountries.put("ls", new Country("+266", "LS", "Lesotho", R.drawable.country_ls,"ls"));
        mCountries.put("lr", new Country("+231", "LR", "Liberia", R.drawable.country_lr,"lr"));
        mCountries.put("ly", new Country("+218", "LY", "Libya", R.drawable.country_ly,"ly"));
        mCountries.put("li", new Country("+423", "LI", "Liechtenstein", R.drawable.country_li,"li"));
        mCountries.put("lt", new Country("+370", "LT", "Lithuania", R.drawable.country_lt,"lt"));
        mCountries.put("lu", new Country("+352", "LU", "Luxembourg", R.drawable.country_lu,"lu"));
        mCountries.put("mo", new Country("+853", "MO", "Macao", R.drawable.country_mo,"mo"));
        mCountries.put("mg", new Country("+261", "MG", "Madagascar", R.drawable.country_mg,"mg"));
        mCountries.put("mw", new Country("+265", "MW", "Malawi", R.drawable.country_mw,"mw"));
        mCountries.put("my", new Country("+60", "MY", "Malaysia", R.drawable.country_my,9,"my"));
        mCountries.put("mv", new Country("+960", "MV", "Maldives", R.drawable.country_mv,"mv"));
        mCountries.put("ml", new Country("+223", "ML", "Mali", R.drawable.country_ml,"ml"));
        mCountries.put("mt", new Country("+356", "MT", "Malta", R.drawable.country_mt,"mt"));
        mCountries.put("mp", new Country("+1670", "MP", "Mariana Is", R.drawable.country_mp,"mp"));
        mCountries.put("mq", new Country("+596", "MQ", "Martinique", R.drawable.country_mq,"mq"));
        mCountries.put("mu", new Country("+230", "MU", "Mauritius", R.drawable.country_mu,"mu"));
        mCountries.put("mx", new Country("+52", "MX", "Mexico", R.drawable.country_mx,"mx"));
        mCountries.put("md", new Country("+373", "MD", "Moldova", R.drawable.country_md,"md"));
        mCountries.put("mc", new Country("+377", "MC", "Monaco", R.drawable.country_mc,"mc"));
        mCountries.put("mn", new Country("+976", "MN", "Mongolia", R.drawable.country_mn,"mn"));
        mCountries.put("ms", new Country("+1664", "MS", "Montserrat Is", R.drawable.country_ms,"ms"));
        mCountries.put("ma", new Country("+212", "MA", "Morocco", R.drawable.country_ma,"ma"));
        mCountries.put("mz", new Country("+258", "MZ", "Mozambique", R.drawable.country_mz,"mz"));
        mCountries.put("na", new Country("+264", "NA", "Namibia", R.drawable.country_na,"na"));
        mCountries.put("nr", new Country("+674", "NR", "Nauru", R.drawable.country_nr,"nr"));
        mCountries.put("np", new Country("+977", "NP", "Nepal", R.drawable.country_np,"np"));
        mCountries.put("nl", new Country("+31", "NL", "Netherlands", R.drawable.country_nl,"nl"));
        mCountries.put("an", new Country("+599", "AN", "Netherlands Antilles", R.drawable.country_an,"an"));
        mCountries.put("nz", new Country("+64", "NZ", "New Zealand", R.drawable.country_nz,"nz"));
        mCountries.put("ni", new Country("+505", "NI", "Nicaragua", R.drawable.country_ni,"ni"));
        mCountries.put("ne", new Country("+227", "NE", "Niger", R.drawable.country_ne,"ne"));
        mCountries.put("ng", new Country("+234", "NG", "Nigeria", R.drawable.country_ng,"ng"));
        mCountries.put("kp", new Country("+850", "KP", "North Korea", R.drawable.country_kp,"kp"));
        mCountries.put("no", new Country("+47", "NO", "Norway", R.drawable.country_no,"no"));
        mCountries.put("om", new Country("+968", "OM", "Oman", R.drawable.country_om,"om"));
        mCountries.put("pk", new Country("+92", "PK", "Pakistan", R.drawable.country_pk,"pk"));
        mCountries.put("pa", new Country("+507", "PA", "Panama", R.drawable.country_pa,"pa"));
        mCountries.put("pg", new Country("+675", "PG", "Papua New Cuinea", R.drawable.country_pg,"pg"));
        mCountries.put("py", new Country("+595", "PY", "Paraguay", R.drawable.country_py,"py"));
        mCountries.put("pe", new Country("+51", "PE", "Peru", R.drawable.country_pe,"pe"));
        mCountries.put("ph", new Country("+63", "PH", "Philippines", R.drawable.country_ph,10,"ph"));
        mCountries.put("pl", new Country("+48", "PL", "Poland", R.drawable.country_pl,"pl"));
        mCountries.put("pt", new Country("+351", "PT", "Portugal", R.drawable.country_pt,"pt"));
        mCountries.put("pr", new Country("+1787", "PR", "Puerto Rico", R.drawable.country_pr,"pr"));
        mCountries.put("qa", new Country("+974", "QA", "Qatar", R.drawable.country_qa,"qa"));
        mCountries.put("re", new Country("+262", "RE", "Reunion", R.drawable.country_re,"re"));
        mCountries.put("ro", new Country("+40", "RO", "Romania", R.drawable.country_ro,"ro"));
        mCountries.put("ru", new Country("+7", "RU", "Russia", R.drawable.country_ru,"ru"));
        mCountries.put("as", new Country("+684", "AS", "Samoa Eastern", R.drawable.country_as,"as"));
        mCountries.put("ws", new Country("+685", "WS", "Samoa Western", R.drawable.country_ws,"ws"));
        mCountries.put("sm", new Country("+378", "SM", "San Marino", R.drawable.country_sm,"sm"));
        mCountries.put("st", new Country("+239", "ST", "Sao Tome and Principe", R.drawable.country_st,"st"));
        mCountries.put("sa", new Country("+966", "SA", "Saudi Arabia", R.drawable.country_sa,"sa"));
        mCountries.put("sn", new Country("+221", "SN", "Senegal", R.drawable.country_sn,"sn"));
        mCountries.put("sc", new Country("+248", "SC", "Seychelles", R.drawable.country_sc,"sc"));
        mCountries.put("sl", new Country("+232", "SL", "Sierra Leone", R.drawable.country_sl,"sl"));
        mCountries.put("sg", new Country("+65", "SG", "Singapore", R.drawable.country_sg,8,"sg"));
        mCountries.put("sk", new Country("+421", "SK", "Slovakia", R.drawable.country_sk,"sk"));
        mCountries.put("si", new Country("+386", "SI", "Slovenia", R.drawable.country_si,"si"));
        mCountries.put("sb", new Country("+677", "SB", "Solomon Is", R.drawable.country_sb,"sb"));
        mCountries.put("so", new Country("+252", "SO", "Somalia", R.drawable.country_so,"so"));
        mCountries.put("za", new Country("+27", "ZA", "South Africa", R.drawable.country_za,"za"));
        mCountries.put("es", new Country("+34", "ES", "Spain", R.drawable.country_es,"es"));
        mCountries.put("lk", new Country("+94", "LK", "Sri Lanka", R.drawable.country_lk));
        mCountries.put("lc", new Country("+1758", "LC", "St.Lucia", R.drawable.country_lc,"lc"));
        mCountries.put("vc", new Country("+1784", "VC", "St.Vincent", R.drawable.country_vc,"vc"));
        mCountries.put("sd", new Country("+249", "SD", "Sudan", R.drawable.country_sd,"sd"));
        mCountries.put("sr", new Country("+597", "SR", "Suriname", R.drawable.country_sr,"sr"));
        mCountries.put("sz", new Country("+268", "SZ", "Swaziland", R.drawable.country_sz,"sz"));
        mCountries.put("se", new Country("+46", "SE", "Sweden", R.drawable.country_se,"se"));
        mCountries.put("ch", new Country("+41", "CH", "Switzerland", R.drawable.country_ch,"ch"));
        mCountries.put("sy", new Country("+963", "SY", "Syria", R.drawable.country_sy,"sy"));
        mCountries.put("tw", new Country("+886", "TW", "Taiwan", R.drawable.country_tw,"tw"));
        mCountries.put("tj", new Country("+992", "TJ", "Tajikstan", R.drawable.country_tj,"tj"));
        mCountries.put("tz", new Country("+255", "TZ", "Tanzania", R.drawable.country_tz,"tz"));
        mCountries.put("th", new Country("+66", "TH", "Thailand", R.drawable.country_th,10,"th"));
        mCountries.put("tg", new Country("+228", "TG", "Togo", R.drawable.country_tg,"tg"));
        mCountries.put("to", new Country("+676", "TO", "Tonga", R.drawable.country_to,"to"));
        mCountries.put("tt", new Country("+1809", "TT", "Trinidad and Tobago", R.drawable.country_tt,"tt"));
        mCountries.put("tn", new Country("+216", "TN", "Tunisia", R.drawable.country_tn,"tn"));
        mCountries.put("tr", new Country("+90", "TR", "Turkey", R.drawable.country_tr,"tr"));
        mCountries.put("tm", new Country("+993", "TM", "Turkmenistan", R.drawable.country_tm,"tm"));
        mCountries.put("ug", new Country("+256", "UG", "Uganda", R.drawable.country_ug,"ug"));
        mCountries.put("ua", new Country("+380", "UA", "Ukraine", R.drawable.country_ua,"ua"));
        mCountries.put("ae", new Country("+971", "AE", "United Arab Emirates", R.drawable.country_ae,"ae"));
        mCountries.put("uk", new Country("+44", "UK", "United Kiongdom", R.drawable.country_uk,"uk"));
        mCountries.put("us", new Country("+1", "US", "United States of America", R.drawable.country_us,"us"));
        mCountries.put("uy", new Country("+598", "UY", "Uruguay", R.drawable.country_uy,"uy"));
        mCountries.put("uz", new Country("+233", "UZ", "Uzbekistan", R.drawable.country_uz,"uz"));
        mCountries.put("ve", new Country("+58", "VE", "Venezuela", R.drawable.country_ve,"ve"));
        mCountries.put("vn", new Country("+84", "VN", "Vietnam", R.drawable.country_vn,9,"vn"));
        mCountries.put("ye", new Country("+967", "YE", "Yemen", R.drawable.country_ye,"ye"));
        mCountries.put("yu", new Country("+381", "YU", "Yugoslavia", R.drawable.country_yu,"yu"));
        mCountries.put("cd", new Country("+243", "CD", "Zaire", R.drawable.country_cd,"cd"));
        mCountries.put("zm", new Country("+260", "ZM", "Zambia", R.drawable.country_zm,"zm"));
        mCountries.put("zw", new Country("+263", "ZW", "Zimbabwe", R.drawable.country_zw,"zw"));
    }

    public List<Country> getCountries() {
        if (mCountries == null) {
            createCountries();
        }

        return new ArrayList<>(mCountries.values());
    }

    public Map<String, Integer> getSectionIndexs(List<Country> countries) {
        int size = countries.size();
        Map<String, Integer> sectionIndexs = new HashMap<>();

        for (int index = 0; index < size; index++) {
            String key = countries.get(index).getFullName().substring(0, 1).toUpperCase();

            if (!sectionIndexs.containsKey(key)) {
                sectionIndexs.put(key, index);
            }
        }

        return sectionIndexs;
    }

    public Country getCountry(String countryName) {
        return mCountries.get(countryName);
    }
    public Country getCountryShort(String countryid) {
        return mCountries.get(countryid);
    }


    public List<Country> getCountry(List<String> countryList) {
        List<Country> countries=new ArrayList<>();
        for(String ipToCountry:countryList){
            Country country=mCountries.get(ipToCountry);
            countries.add(country);
        }
        return countries;
    }
    public String getCurrentCountryCode(Context context) {
//        if(!BuildConfig.DEBUG){
//            //正式包的时候直接通过SIM卡获取到国家顶级域名，从而获取到国家区号
//            TelephonyManager telManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//            String countryDomain=telManager.getSimCountryIso();
//            Country country=mCountries.get(countryDomain);
//            return country.getCode();
//        }else{
//            Country country=mCountries.get(defaultCountryDomain);
//            return country.getCode();
//        }
        Country country=mCountries.get(defaultCountryDomain);
        return country.getCode();
    }
}
