package io.kangov.stix.v21.enums;

import java.util.*;

/**
 * STIX 2.1 WD04 open vocabularies
 */
public class Vocabs {

    public enum Containing {
        SHOULD,
        MUST
    }

    public enum Vocab implements Iterable<String> {

        // VOCABS
        ACCOUNT_TYPE                ( ACCOUNT_TYPE_OV ),
        ATTACK_MOTIVATION           ( ATTACK_MOTIVATION_OV ),
        ATTACK_RESOURCE_LEVEL       ( ATTACK_RESOURCE_LEVEL_OV ),
        GROUPING_CONTEXT            ( GROUPING_CONTEXT_OV),
        IDENTITY_CLASS              ( IDENTITY_CLASS_OV ),
        IMPLEMENTATION_LANGUAGES    ( IMPLEMENTATION_LANGUAGES_OV ),
        INDICATOR_TYPE              ( INDICATOR_TYPE_OV ),
        INDICATOR_PATTERN           ( INDICATOR_PATTERN_OV ),
        INDUSTRY_SECTORS            ( INDUSTRY_SECTOR_OV ),
        INFRASTRUCTURE_TYPE         ( INFRASTRUCTURE_TYPE_OV) ,
        MALWARE_CAPABILITIES        ( MALWARE_CAPABILITIES_OV ),
        MALWARE_RESULT              ( MALWARE_RESULT_OV ),
        MALWARE_TYPE                ( MALWARE_TYPE_OV ),
        PROCESSOR_ARCHITECTURE      ( PROCESSOR_ARCHITECTURE_OV ),
        REGION                      ( REGION_OV ),
        REPORT_TYPE                 ( REPORT_TYPE_OV ),
        THREAT_ACTOR_ROLE           ( THREAT_ACTOR_ROLE_OV ),
        THREAT_ACTOR_SOPHISTICATION ( THREAT_ACTOR_SOPHISTICATION_OV ),
        THREAT_ACTOR_TYPE           ( THREAT_ACTOR_TYPE_OV ),
        TOOL_TYPE                   ( TOOL_TYPE_OV ),
        // ENUMS
        ENCRYPTION_ALGORITHM        ( ENCRYPTION_ALGORITHM_ENUM ),
        WINDOWS_REGISTRY_DATATYPE   ( WINDOWS_REGISTRY_DATATYPE_ENUM ),
        // LOOKUPS
        MIME_TYPE                   ( MIME_TYPE_LOOKUP ),
        COUNTRY_CODE_2              ( COUNTRY_ISO3166_ALPHA2 )
        ;

        private final Set<String> entries;
        private final Containing containing;

        Vocab(Set<String> entries) {
            this(entries, Containing.SHOULD);
        }

        Vocab(Set<String> entries, Containing containing) {
            this.entries = entries;
            this.containing = containing;
        }

        public Set<String> entries() {
            return this.entries;
        }

        public Containing containing() {
            return this.containing;
        }

        @Override
        public String toString() {
            return this.toString().toLowerCase().replace("_", "-") + "-ov";
        }

        public Set<String> dedup(Iterable<String> actuals) {
            var result = new HashSet<String>();
            for (String actual : actuals) {
                if (!contains(actual)) {
                    result.add(actual);
                }
            }
            return result;
        }

        public Set<String> dedup(String ... actuals) {
            var result = new HashSet<String>();
            for (String actual : actuals) {
                if (!contains(actual)) {
                    result.add(actual);
                }
            }
            return result;
        }

        public boolean contains(String entry) {
            return entries.contains(entry);
        }

        /**
         * Returns an iterator over elements of type {@code T}.
         *
         * @return an Iterator.
         */
        @Override
        public Iterator<String> iterator() {
            return entries().iterator();
        }
    }

    // -- LOOKUPS (non-spec vocabs)

    public static final Set<String> MIME_TYPE_LOOKUP = Set.of(
        "application",
        "audio",
        "font",
        "example",
        "image",
        "message",
        "model",
        "multipart",
        "text",
        "video"
    );

    // -- ENUMS

    public static final Set<String> ENCRYPTION_ALGORITHM_ENUM = Set.of(
        "AES-256-GCM",
        "ChaCha20-Poly1305",
        "mime-type-indicated"
    );

    public static final Set<String> WINDOWS_REGISTRY_DATATYPE_ENUM = Set.of(
        "REG_NONE",
        "REG_SZ",
        "REG_EXPAND_SZ",
        "REG_BINARY",
        "REG_DWORD",
        "REG_DWORD_BIG_ENDIAN",
        "REG_DWORD_LITTLE_ENDIAN",
        "REG_LINK, REG_MULTI_SZ",
        "REG_RESOURCE_LIST",
        "REG_FULL_RESOURCE_DESCRIPTION",
        "REG_RESOURCE_REQUIREMENTS_LIST",
        "REG_QWORD",
        "REG_INVALID_TYPE"
    );

    // -- VOCABS

    public static final Set<String> ACCOUNT_TYPE_OV = Set.of(
        "facebook",
        "ldap",
        "nis",
        "openid",
        "radius",
        "skype",
        "tacacs",
        "twitter",
        "unix",
        "windows-local",
        "windows-domain"
    );

    public static final Set<String> ATTACK_MOTIVATION_OV = Set.of(
        "accidental",
        "coercion",
        "dominance",
        "ideology",
        "notoriety",
        "organizational-gain",
        "personal-gain",
        "personal-satisfaction",
        "revenge",
        "unpredictable"
    );


    public static final Set<String> ATTACK_RESOURCE_LEVEL_OV = Set.of(
        "individual",
        "club",
        "contest",
        "team",
        "organization",
        "government"
    );


    public static final Set<String> GROUPING_CONTEXT_OV = Set.of(
        "suspicious-activity",
        "malware-analysis",
        "unspecified"
    );


    public static final Set<String> IDENTITY_CLASS_OV = Set.of(
        "individual",
        "group",
        "system",
        "organization",
        "class",
        "unknown"
    );


    public static final Set<String> IMPLEMENTATION_LANGUAGES_OV = Set.of(
        "applescript",
        "bash",
        "c",
        "c++",
        "c#",
        "go",
        "java",
        "javascript",
        "lua",
        "objective-c",
        "perl",
        "php",
        "powershell",
        "python",
        "ruby",
        "scala",
        "swift",
        "typescript",
        "visual-basic",
        "x86-32",
        "x86-64"
    );


    public static final Set<String> INDICATOR_TYPE_OV = Set.of(
        "anomalous-activity",
        "anonymization",
        "benign",
        "compromised",
        "malicious-activity",
        "attribution",
        "unknown"
    );


    public static final Set<String> INDUSTRY_SECTOR_OV = Set.of(
        "agriculture",
        "aerospace",
        "automotive",
        "chemical",
        "commercial",
        "communications",
        "construction",
        "defense",
        "education",
        "energy",
        "entertainment",
        "financial-services",
        "government",
        "emergency-services",
        "government-local",
        "government-national",
        "government-public-services",
        "government-regional",
        "healthcare",
        "hospitality-leisure",
        "infrastructure",
        "dams",
        "nuclear",
        "water",
        "insurance",
        "manufacturing",
        "mining",
        "non-profit",
        "pharmaceuticals",
        "retail",
        "technology",
        "telecommunications",
        "transportation",
        "utilities"
    );


    public static final Set<String> INFRASTRUCTURE_TYPE_OV = Set.of(
        "amplification",
        "anonymization",
        "botnet",
        "command-and-control",
        "control-system",
        "exfiltration",
        "firewall",
        "hosting-malware",
        "hosting-target-lists",
        "phishing",
        "reconnaissance",
        "routers-switches",
        "staging",
        "workstation",
        "unknown"
    );


    public static final Set<String> MALWARE_RESULT_OV = Set.of(
        "malicious",
        "suspicious",
        "benign",
        "unknown"
    );


    public static final Set<String> MALWARE_TYPE_OV = Set.of(
        "adware",
        "backdoor",
        "bot",
        "bootkit",
        "ddos",
        "downloader",
        "dropper",
        "exploit-kit",
        "keylogger",
        "ransomware",
        "remote-access-trojan",
        "resource-exploitation",
        "rogue-security-software",
        "rootkit",
        "screen-capture",
        "spyware",
        "trojan",
        "unknown",
        "virus",
        "webshell",
        "wiper",
        "worm"
    );


    public static final Set<String> MALWARE_CAPABILITIES_OV = Set.of(
        "accesses-remote-machines",
        "anti-debugging",
        "anti-disassembly",
        "anti-emulation",
        "anti-memory-forensics",
        "anti-sandbox",
        "anti-vm",
        "captures-input-peripherals",
        "captures-output-peripherals",
        "captures-system-state-data",
        "cleans-traces-of-infection",
        "commits-fraud",
        "communicates-with-c2",
        "compromises-data-availability",
        "compromises-data-integrity",
        "compromises-system-availability",
        "controls-local-machine",
        "degrades-security-software",
        "degrades-system-updates",
        "determines-c2-server",
        "emails-spam",
        "escalates-privileges",
        "evades-av",
        "exfiltrates-data",
        "fingerprints-host",
        "hides-artifacts",
        "hides-executing-code",
        "infects-files",
        "infects-remote-machines",
        "installs-other-components",
        "persists-after-system-reboot",
        "prevents-artifact-access",
        "prevents-artifact-deletion",
        "probes-network-environment",
        "self-modifies",
        "steals-authentication-credentials",
        "violates-system-operational-integrity"
    );


    public static final Set<String> INDICATOR_PATTERN_OV = Set.of(
        "stix",
        "pcre",
        "sigma",
        "snort",
        "suricata",
        "yara"
    );


    public static final Set<String> PROCESSOR_ARCHITECTURE_OV = Set.of(
        "alpha",
        "arm",
        "ia-64",
        "mips",
        "powerpc",
        "sparc",
        "x86",
        "x86-64"
    );


    public static final Set<String> REGION_OV = Set.of(
        "africa",
        "eastern-africa",
        "middle-africa",
        "northern-africa",
        "southern-africa",
        "western-africa",
        "americas",
        "latin-america-caribbean",
        "south-america",
        "caribbean",
        "central-america",
        "northern-america",
        "asia",
        "central-asia",
        "eastern-asia",
        "southern-asia",
        "south-eastern-asia",
        "western-asia",
        "europe",
        "eastern-europe",
        "northern-europe",
        "southern-europe",
        "western-europe",
        "oceania",
        "australia-new-zealand",
        "melanesia",
        "micronesia",
        "polynesia",
        "antarctica"
    );


    public static final Set<String> REPORT_TYPE_OV = Set.of(
        "threat-report",
        "attack-pattern",
        "campaign",
        "identity",
        "indicator",
        "intrusion-set",
        "malware",
        "observed-data",
        "threat-actor",
        "tool",
        "vulnerability"
    );


    public static final Set<String> THREAT_ACTOR_TYPE_OV = Set.of(
        "activist",
        "competitor",
        "crime-syndicate",
        "criminal",
        "hacker",
        "insider-accidental",
        "insider-disgruntled",
        "nation-state",
        "sensationalist",
        "spy",
        "terrorist",
        "unknown"
    );


    public static final Set<String> THREAT_ACTOR_ROLE_OV = Set.of(
        "agent",
        "director",
        "independent",
        "infrastructure-architect",
        "infrastructure-operator",
        "malware-author",
        "sponsor"
    );


    public static final Set<String> THREAT_ACTOR_SOPHISTICATION_OV = Set.of(
        "none",
        "minimal",
        "intermediate",
        "advanced",
        "expert",
        "innovator",
        "strategic"
    );


    public static final Set<String> TOOL_TYPE_OV = Set.of(
        "denial-of-service",
        "exploitation",
        "information-gathering",
        "network-capture",
        "credential-exploitation",
        "remote-access",
        "vulnerability-scanning",
        "unknown"
    );


    public static final Set<String> HASH_ALGO_OV = Set.of(
        "MD5",
        "SHA-1",
        "SHA-256",
        "SHA-512",
        "SHA3-256",
        "SHA3-512",
        "SSDEEP",
        "TLSH"
    );


    public static final Set<String> WINDOWS_PEBINARY_TYPE_OV = Set.of(
        "exe",
        "dll",
        "sys"
    );

    public static final Set<String> LANG_CODES = Set.of(
        "af", "af-ZA", "ar", "ar-AE", "ar-BH", "ar-DZ", "ar-EG", "ar-IQ", "ar-JO",
        "ar-KW", "ar-LB", "ar-LY", "ar-MA", "ar-OM", "ar-QA", "ar-SA", "ar-SY",
        "ar-TN", "ar-YE", "az", "az-AZ", "az-Cyrl-AZ", "be", "be-BY", "bg", "bg-BG",
        "bs-BA", "ca", "ca-ES", "cs", "cs-CZ", "cy", "cy-GB", "da", "da-DK", "de",
        "de-AT", "de-CH", "de-DE", "de-LI", "de-LU", "dv", "dv-MV", "el", "el-GR",
        "en", "en-AU", "en-BZ", "en-CA", "en-CB", "en-GB", "en-IE", "en-JM",
        "en-NZ", "en-PH", "en-TT", "en-US", "en-ZA", "en-ZW", "eo", "es", "es-AR",
        "es-BO", "es-CL", "es-CO", "es-CR", "es-DO", "es-EC", "es-ES", "es-GT",
        "es-HN", "es-MX", "es-NI", "es-PA", "es-PE", "es-PR", "es-PY", "es-SV",
        "es-UY", "es-VE", "et", "et-EE", "eu", "eu-ES", "fa", "fa-IR", "fi",
        "fi-FI", "fo", "fo-FO", "fr", "fr-BE", "fr-CA", "fr-CH", "fr-FR", "fr-LU",
        "fr-MC", "gl", "gl-ES", "gu", "gu-IN", "he", "he-IL", "hi", "hi-IN", "hr",
        "hr-BA", "hr-HR", "hu", "hu-HU", "hy", "hy-AM", "id", "id-ID", "is",
        "is-IS", "it", "it-CH", "it-IT", "ja", "ja-JP", "ka", "ka-GE", "kk",
        "kk-KZ", "kn", "kn-IN", "ko", "ko-KR", "kok", "kok-IN", "ky", "ky-KG",
        "lt", "lt-LT", "lv", "lv-LV", "mi", "mi-NZ", "mk", "mk-MK", "mn", "mn-MN",
        "mr", "mr-IN", "ms", "ms-BN", "ms-MY", "mt", "mt-MT", "nb", "nb-NO", "nl",
        "nl-BE", "nl-NL", "nn-NO", "ns", "ns-ZA", "pa", "pa-IN", "pl", "pl-PL",
        "ps", "ps-AR", "pt", "pt-BR", "pt-PT", "qu", "qu-BO", "qu-EC", "qu-PE",
        "ro", "ro-RO", "ru", "ru-RU", "sa", "sa-IN", "se", "se-FI", "se-NO",
        "se-SE", "sk", "sk-SK", "sl", "sl-SI", "sq", "sq-AL", "sr-BA", "sr-Cyrl-BA",
        "sr-SP", "sr-Cyrl-SP", "sv", "sv-FI", "sv-SE", "sw", "sw-KE", "syr",
        "syr-SY", "ta", "ta-IN", "te", "te-IN", "th", "th-TH", "tl", "tl-PH", "tn",
        "tn-ZA", "tr", "tr-TR", "tt", "tt-RU", "ts", "uk", "uk-UA", "ur", "ur-PK",
        "uz", "uz-UZ", "uz-Cyrl-UZ", "vi", "vi-VN", "xh", "xh-ZA", "zh", "zh-CN",
        "zh-HK", "zh-MO", "zh-SG", "zh-TW", "zu", "zu-ZA"
        );

    public static final Set<String> SOFTWARE_LANG_CODES = Set.of(
        "aar", "abk", "ace", "ach", "ada", "ady", "afa", "afh", "afr", "ain",
        "aka", "akk", "ale", "alg", "alt", "amh", "ang", "anp", "apa", "ara",
        "arc", "arg", "arn", "arp", "art", "arw", "asm", "ast", "ath", "aus",
        "ava", "ave", "awa", "aym", "aze", "bad", "bai", "bak", "bal", "bam",
        "ban", "bas", "bat", "bej", "bel", "bem", "ben", "ber", "bho", "bih",
        "bik", "bin", "bis", "bla", "bnt", "bos", "bra", "bre", "btk", "bua",
        "bug", "bul", "byn", "cad", "cai", "car", "cat", "cau", "ceb", "cel",
        "cze", "ces", "cha", "chb", "che", "chg", "chk", "chm", "chn", "cho",
        "chp", "chr", "chu", "chv", "chy", "cmc", "cop", "cor", "cos", "cpe",
        "cpf", "cpp", "cre", "crh", "crp", "csb", "cus", "dak", "dan", "dar",
        "day", "del", "den", "ger", "deu", "dgr", "din", "div", "doi", "dra",
        "dsb", "dua", "dum", "dyu", "dzo", "efi", "egy", "eka", "elx", "eng",
        "enm", "epo", "est", "baq", "eus", "ewe", "ewo", "fan", "fao", "fat",
        "fij", "fil", "fin", "fiu", "fon", "fre", "fra", "frm", "fro", "frr",
        "frs", "fry", "ful", "fur", "gaa", "gay", "gba", "gem", "gez", "gil",
        "gla", "gle", "glg", "glv", "gmh", "goh", "gon", "gor", "got", "grb",
        "grc", "gre", "ell", "grn", "gsw", "guj", "gwi", "hai", "hat", "hau",
        "haw", "heb", "her", "hil", "him", "hin", "hit", "hmn", "hmo", "hrv",
        "hsb", "hun", "hup", "arm", "hye", "iba", "ibo", "ido", "iii", "ijo",
        "iku", "ile", "ilo", "ina", "inc", "ind", "ine", "inh", "ipk", "ira",
        "iro", "ice", "isl", "ita", "jav", "jbo", "jpn", "jpr", "jrb", "kaa",
        "kab", "kac", "kal", "kam", "kan", "kar", "kas", "geo", "kat", "kau",
        "kaw", "kaz", "kbd", "kha", "khi", "khm", "kho", "kik", "kin", "kir",
        "kmb", "kok", "kom", "kon", "kor", "kos", "kpe", "krc", "krl", "kro",
        "kru", "kua", "kum", "kur", "kut", "lad", "lah", "lam", "lao", "lat",
        "lav", "lez", "lim", "lin", "lit", "lol", "loz", "ltz", "lua", "lub",
        "lug", "lui", "lun", "luo", "lus", "mad", "mag", "mah", "mai", "mak",
        "mal", "man", "map", "mar", "mas", "mdf", "mdr", "men", "mga", "mic",
        "min", "mis", "mac", "mkd", "mkh", "mlg", "mlt", "mnc", "mni", "mno",
        "moh", "mon", "mos", "mao", "mri", "may", "msa", "mul", "mun", "mus",
        "mwl", "mwr", "bur", "mya", "myn", "myv", "nah", "nai", "nap", "nau",
        "nav", "nbl", "nde", "ndo", "nds", "nep", "new", "nia", "nic", "niu",
        "dut", "nld", "nno", "nob", "nog", "non", "nor", "nqo", "nso", "nub",
        "nwc", "nya", "nym", "nyn", "nyo", "nzi", "oci", "oji", "ori", "orm",
        "osa", "oss", "ota", "oto", "paa", "pag", "pal", "pam", "pan", "pap",
        "pau", "peo", "per", "fas", "phi", "phn", "pli", "pol", "pon", "por",
        "pra", "pro", "pus", "qaa-qtz", "que", "raj", "rap", "rar", "roa", "roh",
        "rom", "rum", "ron", "run", "rup", "rus", "sad", "sag", "sah", "sai",
        "sal", "sam", "san", "sas", "sat", "scn", "sco", "sel", "sem", "sga",
        "sgn", "shn", "sid", "sin", "sio", "sit", "sla", "slo", "slk", "slv",
        "sma", "sme", "smi", "smj", "smn", "smo", "sms", "sna", "snd", "snk",
        "sog", "som", "son", "sot", "spa", "alb", "sqi", "srd", "srn", "srp",
        "srr", "ssa", "ssw", "suk", "sun", "sus", "sux", "swa", "swe", "syc",
        "syr", "tah", "tai", "tam", "tat", "tel", "tem", "ter", "tet", "tgk",
        "tgl", "tha", "tib", "bod", "tig", "tir", "tiv", "tkl", "tlh", "tli",
        "tmh", "tog", "ton", "tpi", "tsi", "tsn", "tso", "tuk", "tum", "tup",
        "tur", "tut", "tvl", "twi", "tyv", "udm", "uga", "uig", "ukr", "umb",
        "und", "urd", "uzb", "vai", "ven", "vie", "vol", "vot", "wak", "wal",
        "war", "was", "wel", "cym", "wen", "wln", "wol", "xal", "xho", "yao",
        "yap", "yid", "yor", "ypk", "zap", "zbl", "zen", "zgh", "zha", "chi",
        "zho", "znd", "zul", "zun", "zxx", "zza"
    );

    public static final Set<String> COUNTRY_ISO3166_ALPHA2 = Set.of(
        "AC", "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AP", "AQ",
        "AR", "AS", "AT", "AU", "AW", "AX", "AZ", "BA", "BB", "BD", "BE", "BF",
        "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS", "BT",
        "BU", "BV", "BW", "BX", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH",
        "CI", "CK", "CL", "CM", "CN", "CO", "CP", "CR", "CS", "CT", "CU", "CV",
        "CW", "CX", "CY", "CZ", "DD", "DE", "DG", "DJ", "DK", "DM", "DO", "DY",
        "DZ", "EA", "EC", "EE", "EF", "EG", "EH", "EM", "EP", "ER", "ES", "ET",
        "EU", "EV", "EW", "EZ", "FI", "FJ", "FK", "FL", "FM", "FO", "FQ", "FR",
        "FX", "GA", "GB", "GC", "GD", "GE", "GF", "GG", "GH", "GI", "GL", "GM",
        "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM", "HN",
        "HR", "HT", "HU", "HV", "IB", "IC", "ID", "IE", "IL", "IM", "IN", "IO",
        "IQ", "IR", "IS", "IT", "JA", "JE", "JM", "JO", "JP", "JT", "KE", "KG",
        "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC",
        "LF", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD",
        "ME", "MF", "MG", "MH", "MI", "MK", "ML", "MM", "MN", "MO", "MP", "MQ",
        "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA", "NC", "NE",
        "NF", "NG", "NH", "NI", "NL", "NO", "NP", "NQ", "NR", "NT", "NU", "NZ",
        "OA", "OM", "PA", "PC", "PE", "PF", "PG", "PH", "PI", "PK", "PL", "PM",
        "PN", "PR", "PS", "PT", "PU", "PW", "PY", "PZ", "QA", "RA", "RB", "RC",
        "RE", "RH", "RI", "RL", "RM", "RN", "RO", "RP", "RS", "RU", "RW", "SA",
        "SB", "SC", "SD", "SE", "SF", "SG", "SH", "SI", "SJ", "SK", "SL", "SM",
        "SN", "SO", "SR", "SS", "ST", "SU", "SV", "SX", "SY", "SZ", "TA", "TC",
        "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO", "TP", "TR",
        "TT", "TV", "TW", "TZ", "UA", "UG", "UK", "UM", "UN", "US", "UY", "UZ",
        "VA", "VC", "VD", "VE", "VG", "VI", "VN", "VU", "WF", "WG", "WK", "WL",
        "WO", "WS", "WV", "YD", "YE", "YT", "YU", "YV", "ZA", "ZM", "ZR", "ZW"
    );

    public static final Set<String> HTTP_REQUEST_HEADERS = Set.of(
        "Accept",
        "Accept-Charset",
        "Accept-Encoding",
        "Accept-Language",
        "Accept-Datetime",
        "Authorization",
        "Cache-Control",
        "Connection",
        "Cookie",
        "Content-Length",
        "Content-MD5",
        "Content-Type",
        "Date",
        "Expect",
        "Forwarded",
        "From",
        "Host",
        "If-Match",
        "If-Modified-Since",
        "If-None-Match",
        "If-Range",
        "If-Unmodified-Since",
        "Max-Forwards",
        "Origin",
        "Pragma",
        "Proxy-Authorization",
        "Range",
        "Referer",
        "TE",
        "User-Agent",
        "Upgrade",
        "Via",
        "Warning",
        "X-Requested-With",
        "DNT",
        "X-Forwarded-For",
        "X-Forwarded-Host",
        "X-Forwarded-Proto",
        "Front-End-Https",
        "X-Http-Method-Override",
        "X-ATT-DeviceId",
        "X-Wap-Profile",
        "Proxy-Connection",
        "X-UIDH",
        "X-Csrf-Token",
        "X-Request-ID",
        "X-Correlation-ID"
    );

    /*
     * If you have a Socket Option not present in this list
     * for SO|ICMP|ICMP6|IP|IPV6|MCAST|TCP|IRLMP please open an issue/PR
     * in https://github.com/oasis-open/cti-stix-validator/ to include it.
     * Include a reference (link) to where its defined.
     */
    public static final Set<String> SOCKET_OPTIONS = Set.of(
        "ICMP6_FILTER",
        "IP_ADD_MEMBERSHIP",
        "IP_ADD_SOURCE_MEMBERSHIP",
        "IP_BIND_ADDRESS_NO_PORT",
        "IP_BLOCK_SOURCE",
        "IP_DONTFRAGMENT",
        "IP_DROP_MEMBERSHIP",
        "IP_DROP_SOURCE_MEMBERSHIP",
        "IP_FREEBIND",
        "IP_HDRINCL",
        "IP_MSFILTER",
        "IP_MTU",
        "IP_MTU_DISCOVER",
        "IP_MULTICAST_ALL",
        "IP_MULTICAST_IF",
        "IP_MULTICAST_LOOP",
        "IP_MULTICAST_TTL",
        "IP_NODEFRAG",
        "IP_OPTIONS",
        "IP_ORIGINAL_ARRIVAL_IF",
        "IP_PKTINFO",
        "IP_RECEIVE_BROADCAST",
        "IP_RECVDSTADDR",
        "IP_RECVERR",
        "IP_RECVIF",
        "IP_RECVOPTS",
        "IP_RECVORIGDSTADDR",
        "IP_RECVTOS",
        "IP_RECVTTL",
        "IP_RETOPTS",
        "IP_ROUTER_ALERT",
        "IP_TOS",
        "IP_TRANSPARENT",
        "IP_TTL",
        "IP_UNBLOCK_SOURCE",
        "IP_UNICAST_IF",
        "IP_WFP_REDIRECT_CONTEXT",
        "IP_WFP_REDIRECT_RECORDS",
        "IPV6_ADD_MEMBERSHIP",
        "IPV6_CHECKSUM",
        "IPV6_DONTFRAG",
        "IPV6_DROP_MEMBERSHIP",
        "IPV6_DSTOPTS",
        "IPV6_HDRINCL",
        "IPV6_HOPLIMIT",
        "IPV6_HOPOPTS",
        "IPV6_JOIN_GROUP",
        "IPV6_LEAVE_GROUP",
        "IPV6_MTU",
        "IPV6_MTU_DISCOVER",
        "IPV6_MULTICAST_HOPS",
        "IPV6_MULTICAST_IF",
        "IPV6_MULTICAST_LOOP",
        "IPV6_NEXTHOP",
        "IPV6_PATHMTU",
        "IPV6_PKTINFO",
        "IPV6_PROTECTION_LEVEL",
        "IPV6_RECVDSTOPTS",
        "IPV6_RECVHOPLIMIT",
        "IPV6_RECVHOPOPTS",
        "IPV6_RECVIF",
        "IPV6_RECVPATHMTU",
        "IPV6_RECVPKTINFO",
        "IPV6_RECVRTHDR",
        "IPV6_RECVTCLASS",
        "IPV6_RTHDR",
        "IPV6_TCLASS",
        "IPV6_UNICAST_HOPS",
        "IPV6_UNICAST_IF",
        "IPV6_UNICAT_HOPS",
        "IPV6_USE_MIN_MTU",
        "IPV6_V6ONLY",
        "IRLMP_9WIRE_MODE",
        "IRLMP_DISCOVERY_MODE",
        "IRLMP_ENUMDEVICES",
        "IRLMP_EXCLUSIVE_MODE",
        "IRLMP_IAS_QUERY",
        "IRLMP_IAS_SET",
        "IRLMP_IRLPT_MODE",
        "IRLMP_PARAMETERS",
        "IRLMP_SEND_PDU_LEN",
        "IRLMP_SHARP_MODE",
        "IRLMP_TINYTP_MODE",
        "MCAST_BLOCK_SOURCE",
        "MCAST_JOIN_GROUP",
        "MCAST_JOIN_SOURCE_GROUP",
        "MCAST_LEAVE_GROUP",
        "MCAST_LEAVE_SOURCE_GROUP",
        "MCAST_UNBLOCK_SOURCE",
        "SO_ACCEPTCONN",
        "SO_ATTACH_BPF",
        "SO_ATTACH_FILTER",
        "SO_ATTACH_REUSEPORT_CBPF",
        "SO_BINDTODEVICE",
        "SO_BROADCAST",
        "SO_BSDCOMPAT",
        "SO_BSP_STATE",
        "SO_BUSY_POLL",
        "SO_CONDITIONAL_ACCEPT",
        "SO_CONFIRM_NAME",
        "SO_CONNDATA",
        "SO_CONNDATALEN",
        "SO_CONNECT_TIME",
        "SO_CONNOPT",
        "SO_CONNOPTLEN",
        "SO_DEBUG",
        "SO_DEREGISTER_NAME",
        "SO_DETACH_FILTER",
        "SO_DISCDATA",
        "SO_DISCDATALEN",
        "SO_DISCOPT",
        "SO_DISCOPTLEN",
        "SO_DOMAIN",
        "SO_DONTLINGER",
        "SO_DONTROUTE",
        "SO_ERROR",
        "SO_EXCLUSIVEADDRUSE",
        "SO_GETLOCALZONES",
        "SO_GETMYZONE",
        "SO_GETNETINFO",
        "SO_GETZONELIST",
        "SO_GROUP_ID",
        "SO_GROUP_PRIORITY",
        "SO_INCOMING_CPU",
        "SO_KEEPALIVE",
        "SO_LINGER",
        "SO_LOOKUP_MYZONE",
        "SO_LOOKUP_NAME",
        "SO_LOOKUP_NETDEF_ON_ADAPTER",
        "SO_LOOKUP_ZONES",
        "SO_LOOKUP_ZONES_ON_ADAPTER",
        "SO_MARK",
        "SO_MAX_MSG_SIZE",
        "SO_MAXDG",
        "SO_MAXPATHDG",
        "SO_OOBINLINE",
        "SO_OPENTYPE",
        "SO_PAP_GET_SERVER_STATUS",
        "SO_PAP_PRIME_READ",
        "SO_PAP_SET_SERVER_STATUS",
        "SO_PASSCRED",
        "SO_PASSSEC",
        "SO_PAUSE_ACCEPT",
        "SO_PEEK_OFF",
        "SO_PEERCRED",
        "SO_PORT_SCALABILITY",
        "SO_PRIORITY",
        "SO_PROTOCOL",
        "SO_PROTOCOL_INFO",
        "SO_PROTOCOL_INFOA",
        "SO_PROTOCOL_INFOW",
        "SO_RANDOMIZE_PORT",
        "SO_RCVBUF",
        "SO_RCVBUFFORCE",
        "SO_RCVLOWAT",
        "SO_RCVTIMEO",
        "SO_REGISTER_NAME",
        "SO_REMOVE_NAME",
        "SO_REUSE_MULTICASTPORT",
        "SO_REUSE_UNICASTPORT",
        "SO_REUSEADDR",
        "SO_REUSEPORT",
        "SO_RXQ_OVFL",
        "SO_SNDBUF",
        "SO_SNDBUFFORCE",
        "SO_SNDLOWAT",
        "SO_SNDTIMEO",
        "SO_TIMESTAMP",
        "SO_TYPE",
        "SO_UPDATE_ACCEPT_CONTEXT",
        "SO_UPDATE_CONNECT_CONTEXT",
        "SO_USELOOPBACK",
        "TCP_BSDURGENT",
        "TCP_CONGESTION",
        "TCP_CORK",
        "TCP_DEFER_ACCEPT",
        "TCP_EXPEDITED_1122",
        "TCP_FASTOPEN",
        "TCP_INFO",
        "TCP_KEEPCNT",
        "TCP_KEEPIDLE",
        "TCP_KEEPINTVL",
        "TCP_LINGER2",
        "TCP_MAXRT",
        "TCP_MAXSEG",
        "TCP_NODELAY",
        "TCP_QUICKACK",
        "TCP_SYNCNT",
        "TCP_TIMESTAMPS",
        "TCP_USER_TIMEOUT",
        "TCP_WINDOW_CLAMP"
        );
}


