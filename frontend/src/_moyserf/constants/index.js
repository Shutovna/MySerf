export const BACKEND_BASE_URL = 'http://localhost:8080';
export const API_BASE_URL = `${BACKEND_BASE_URL}/api`;
export const ACCESS_TOKEN = 'accessToken';
export const USER_INFO  = 'userInfo';

export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

export const GOOGLE_AUTH_URL = BACKEND_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = BACKEND_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = BACKEND_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;


export const VERIFICATION_TOKEN_INVALID = "invalidToken";
export const VERIFICATION_TOKEN_EXPIRED = "expired";
export const VERIFICATION_TOKEN_VALID = "valid";

//для websocket
window.global = window;