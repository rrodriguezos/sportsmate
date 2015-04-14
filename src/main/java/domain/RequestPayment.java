package domain;

public class RequestPayment
{
    private Transaction[] transactions;

    private Payer payer;

    private RedirectUrls redirect_urls;

    private String intent;

    public Transaction[] getTransactions ()
    {
        return transactions;
    }

    public void setTransactions (Transaction[] transactions)
    {
        this.transactions = transactions;
    }

    public Payer getPayer ()
    {
        return payer;
    }

    public void setPayer (Payer payer)
    {
        this.payer = payer;
    }

    public RedirectUrls getRedirect_urls ()
    {
        return redirect_urls;
    }

    public void setRedirect_urls (RedirectUrls redirect_urls)
    {
        this.redirect_urls = redirect_urls;
    }

    public String getIntent ()
    {
        return intent;
    }

    public void setIntent (String intent)
    {
        this.intent = intent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [transactions = "+transactions+", payer = "+payer+", redirect_urls = "+redirect_urls+", intent = "+intent+"]";
    }
}
