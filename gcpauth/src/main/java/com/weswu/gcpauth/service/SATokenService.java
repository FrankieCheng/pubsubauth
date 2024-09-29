package com.weswu.gcpauth.service;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ImpersonatedCredentials;
import com.weswu.gcpauth.base.GAException;
import com.weswu.gcpauth.base.ResStatus;
import com.weswu.gcpauth.base.TokenCache;
import com.weswu.gcpauth.config.Configuration;
import com.weswu.gcpauth.entity.TokenReq;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author weswu
 * @date 2021/5/23
 */
public class SATokenService{

    private final TokenCache tokenCache = TokenCache.getInstance();
    private static final Logger log = LoggerFactory.getLogger(SATokenService.class);

    public void validateReq(String gcpAuthToken, TokenReq tokenreq) throws GAException {
        String bearerToken = null;
        if (gcpAuthToken != null && gcpAuthToken.startsWith("Bearer")) {
            bearerToken = gcpAuthToken.substring(7, gcpAuthToken.length());
            if (!DigestUtils.md5Hex(Configuration.SECRET_KEY).equals(bearerToken)){
                throw new GAException(ResStatus.FORBIDDEN, "Invalid gcpauth token");
            }
        } else {
            throw new GAException(ResStatus.FORBIDDEN, "Invalid gcpauth token");
        }
        if (null == tokenreq.getScope()){
            throw new GAException(ResStatus.FAIL, "Scope can't be null or empty");
        }
        if (!Configuration.ALLOWED_SCOPES.contains(tokenreq.getScope())){
            throw new GAException(ResStatus.FORBIDDEN, "Scope: " + tokenreq.getScope() + " is not allowed");
        }
    }

    public AccessToken getAccessToken(String gcpAuthToken, TokenReq tokenreq) throws IOException, GAException{

        String impersonatedServiceAccount = Configuration.IMPERSONATED_SERVICE_ACCOUNT;
        String scope = tokenreq.getScope();
        Integer lifeTime = Configuration.TOKEN_EXPIRES_SEC;

        AccessToken tokenRes = null;
        validateReq(gcpAuthToken, tokenreq);
        // read from cache
        
        if (null != tokenCache.get(scope)){
            log.info(tokenCache.get(scope).toString());
            tokenRes = (AccessToken)tokenCache.get(scope);
            return tokenRes;
        }

        log.info("Begin to call the GCP Auth API...");


        // Construct the GoogleCredentials object which obtains the default configuration from your
        // working environment.
        GoogleCredentials googleCredentials = GoogleCredentials.getApplicationDefault();

        log.info("Get service account cred.");

        // delegates: The chained list of delegates required to grant the final accessToken.
        // For more information, see:
        // https://cloud.google.com/iam/docs/create-short-lived-credentials-direct#sa-credentials-permissions
        // Delegate is NOT USED here.
        List<String> delegates = null;

        // Create the impersonated credential.
        ImpersonatedCredentials impersonatedCredentials =
            ImpersonatedCredentials.newBuilder()
                .setSourceCredentials(googleCredentials)
                .setTargetPrincipal(impersonatedServiceAccount)
                .setScopes(Arrays.asList(scope))
                .setLifetime(lifeTime)
                .setDelegates(delegates)
                .build();

        log.info("Begin to refresh the Auth token.");
        // Get the OAuth2 token.
        // Once you've obtained the OAuth2 token, you can use it to make an authenticated call.
        impersonatedCredentials.refresh();
        log.info("End refresh the Auth token.");
        AccessToken accessToken = impersonatedCredentials.getAccessToken();
        log.info("Generated access token and setup the cache.");
        tokenCache.put(scope, accessToken);
        return accessToken;
    }

}
