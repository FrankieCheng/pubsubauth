package com.weswu.gcpauth.config;

import com.weswu.gcpauth.base.Const;
import com.weswu.gcpauth.base.GAException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author weswu
 * @date 2021/05/23
 */
public class Configuration {
    static IniReader iniReader;
    public static String GOOGLE_CREDENTIALS = null;
    public static String SECRET_KEY = null;
    public static String GOOGLE_AUTH_ENDPOINT = null;
    public static int TOKEN_EXPIRES_SEC = 0;
    public static int CACHED_TOKENS_TTL = 0;
    public static Map<?,?> GOOGLE_CREDENTIALS_JSON =  null;
    public static List<String> ALLOWED_SCOPES = null;
    public static String ALLOWED_SCOPES_URL_PREFIX = null;
    public static String IMPERSONATED_SERVICE_ACCOUNT = null;
    /* custom configurable options */
    public static void load() throws Exception {
        iniReader = IniReader.getInstance(Const.CONG_FILE);
        GOOGLE_CREDENTIALS = iniReader.getProperty("google_credentials");
        SECRET_KEY = iniReader.getProperty("secret_key");
        GOOGLE_AUTH_ENDPOINT = iniReader.getProperty("google_auth_endpoint");
        TOKEN_EXPIRES_SEC = Integer.parseInt(iniReader.getProperty("token_expires_sec"));
        CACHED_TOKENS_TTL = Integer.parseInt(iniReader.getProperty("cached_tokens_ttl"));
        ALLOWED_SCOPES_URL_PREFIX = iniReader.getProperty("allowed_scope_url_prefix");
        ALLOWED_SCOPES = loadAllowedScopes(iniReader.getProperty("allowed_scopes"));
        IMPERSONATED_SERVICE_ACCOUNT = iniReader.getProperty("impersonated_service_account");
    }
    public static List<String> loadAllowedScopes(String allowedScopesStr) throws GAException {
        List<String> allowedScopes = new ArrayList<>();
        if (null ==  allowedScopesStr){
            throw new GAException("Configuration Error", "'allowed scopes' can't be null or empty.");
        }
        List<String> tmp_scopes = Arrays.asList(allowedScopesStr.split(","));
        for (String scope : tmp_scopes) {
            if (null != scope && scope.trim() != "") {
                allowedScopes.add(Configuration.ALLOWED_SCOPES_URL_PREFIX + "/" + scope.trim());
            }
        }
        return  allowedScopes;
    }
}
