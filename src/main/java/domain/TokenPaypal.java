package domain;

public class TokenPaypal
{
    private String scope;

    private String expires_in;

    private String app_id;

    private String token_type;

    private String access_token;

    public String getScope ()
    {
        return scope;
    }

    public void setScope (String scope)
    {
        this.scope = scope;
    }

    public String getExpires_in ()
    {
        return expires_in;
    }

    public void setExpires_in (String expires_in)
    {
        this.expires_in = expires_in;
    }

    public String getApp_id ()
    {
        return app_id;
    }

    public void setApp_id (String app_id)
    {
        this.app_id = app_id;
    }

    public String getToken_type ()
    {
        return token_type;
    }

    public void setToken_type (String token_type)
    {
        this.token_type = token_type;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [scope = "+scope+", expires_in = "+expires_in+", app_id = "+app_id+", token_type = "+token_type+", access_token = "+access_token+"]";
    }
}

