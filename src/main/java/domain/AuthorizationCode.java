package domain;

public class AuthorizationCode {

    private String code;
    private long expirationTime;

    public AuthorizationCode(String code, long expirationTime) {
        this.code = "Bearer " + code;
        this.expirationTime = expirationTime;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public long getTimeUntilExpiration() {
        long currentTime = System.currentTimeMillis();
        if (currentTime >= expirationTime) {
            return 0L;
        }
        return System.currentTimeMillis() - expirationTime;
    }

    public boolean isExpired() {
        return getTimeUntilExpiration() == 0L;
    }

    public String getCode() {
        return code;
    }

}
