import kamarad.*;
import org.omg.CORBA.*;

public class OnlineKamaradImplementation implements OnlineKamaradOperations {
    private ORB orb;
    
    
    public OnlineKamaradImplementation (ORB orb) {
        this.orb = orb;
    }
    
    public void register (kamarad.KamaradAccountDetails accountDetails, org.omg.CORBA.AnyHolder anyUniqueIdHolder) {
        Any anyUniqueId = orb.create_any();
        // TODO
        // Add logic to allocate a new unique id for this registration and store the new account
        // for subsequent retrieval
        anyUniqueId.insert_longlong(1);
        anyUniqueIdHolder.value = anyUniqueId;
    }
    
    public void getAccountDetails (int uniqueId, kamarad.KamaradAccountDetailsHolder accountDetailsHolder) {
        // TODO
        // Look up and retrieve the account details for this user
        KamaradAccountDetails accountDetails = new KamaradAccountDetails();
        accountDetails.name = " <retrieved customer name> ";
        accountDetails.address = " <retrieved customer address> ";
        accountDetails.phoneNumber = " <retrieved mobile number> ";
        accountDetails.credit = 0.0; // Retrieved credit balance
        accountDetails.uniqueId = 1; // Retrieved unique identifier

        // Put the account into a holder object for returning
        accountDetailsHolder.value = accountDetails;
    }
    
    public void topupAccount (int uniqueId, double topup) {
        // TODO
        // Look up the account details for this user and update the credit by adding the
        // specified top-up to the existing balance
    }
}
