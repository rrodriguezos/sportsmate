package domain;

public class RequestPayment
{
    private Transactions[] transactions;

    private Payer payer;

    private Redirect_urls redirect_urls;

    private String intent;

    public Transactions[] getTransactions ()
    {
        return transactions;
    }

    public void setTransactions (Transactions[] transactions)
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

    public Redirect_urls getRedirect_urls ()
    {
        return redirect_urls;
    }

    public void setRedirect_urls (Redirect_urls redirect_urls)
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
