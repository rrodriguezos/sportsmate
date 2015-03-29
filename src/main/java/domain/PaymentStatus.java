package domain;

public class PaymentStatus
{
    private String id;

    private Transactions[] transactions;

    private String update_time;

    private Payer payer;

    private String state;

    private String create_time;

    private Links[] links;

    private String intent;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Transactions[] getTransactions ()
    {
        return transactions;
    }

    public void setTransactions (Transactions[] transactions)
    {
        this.transactions = transactions;
    }

    public String getUpdate_time ()
    {
        return update_time;
    }

    public void setUpdate_time (String update_time)
    {
        this.update_time = update_time;
    }

    public Payer getPayer ()
    {
        return payer;
    }

    public void setPayer (Payer payer)
    {
        this.payer = payer;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getCreate_time ()
    {
        return create_time;
    }

    public void setCreate_time (String create_time)
    {
        this.create_time = create_time;
    }

    public Links[] getLinks ()
    {
        return links;
    }

    public void setLinks (Links[] links)
    {
        this.links = links;
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
        return "ClassPojo [id = "+id+", transactions = "+transactions+", update_time = "+update_time+", payer = "+payer+", state = "+state+", create_time = "+create_time+", links = "+links+", intent = "+intent+"]";
    }
}