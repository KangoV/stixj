package io.kangov.stix.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * STIX 2.1 WD04 open vocabularies
 */
public class Vocabs {

    public enum Vocab {

        ACCOUNT_TYPE(ACCOUNT_TYPE_OV),
        ATTACK_MOTIVATION(ATTACK_MOTIVATION_OV),
        ATTACK_RESOURCE_LEVEL(ATTACK_RESOURCE_LEVEL_OV),
        IDENTITY_CLASS(IDENTITY_CLASS_OV),
        IMPLEMENTATION_LANGUAGES(IMPLEMENTATION_LANGUAGES_OV),
        INDUSTRY_SECTORS(INDUSTRY_SECTOR_OV),
        INFRASTRUCTURE_TYPE(INFRASTRUCTURE_TYPE_OV),
        MALWARE_CAPABILITIES(MALWARE_CAPABILITIES_OV),
        MALWARE_RESULT(MALWARE_RESULT_OV),
        MALWARE_TYPE(MALWARE_TYPE_OV),
        PROCESSOR_ARCHITECTURE(PROCESSOR_ARCHITECTURE_OV),
        REGION(REGION_OV),
        REPORT_TYPE(REPORT_TYPE_OV),
        THREAT_ACTOR_ROLE(THREAT_ACTOR_ROLE_OV),
        THREAT_ACTOR_SOPHISTICATION(THREAT_ACTOR_SOPHISTICATION_OV),
        THREAT_ACTOR_TYPE(THREAT_ACTOR_TYPE_OV),
        TOOL_TYPE(TOOL_TYPE_OV)
        ;

        private final Set<String> entries;

        Vocab(Set<String> entries) {
            this.entries = entries;
        }

        public Set<String> entries() {
            return this.entries;
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

    }

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

}


