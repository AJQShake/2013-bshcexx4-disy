import kamarad.*;
import org.omg.CORBA.*;
import java.io.* ;
import java.util.* ;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

public class OnlineKamaradAccount {
    public static void main(String args[]) {
        try{
            ORB orb = ORB.init(args, null);

            //Obtaining the object reference to the Name Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            //Narrowing the object reference of the Name Service to the correct Type
            NamingContext rootCtx = NamingContextHelper.narrow(objRef);
            //Creating a name of the desired object that we want to get the object reference from the name server
            NameComponent[] path = new NameComponent[2];
            path[0] = new NameComponent("Kamarad", "Context");
            path[1]    = new NameComponent("OnlineKamarad", "Object");
            
            org.omg.CORBA.Object objRefKamarad = rootCtx.resolve(path);

            // Converting the retireved object reference from the Name Service to the correct type
            OnlineKamarad kamaradRef = OnlineKamaradHelper.narrow (objRefKamarad);
      
            // Creating an any holder that will hold the account details
            KamaradAccountDetailsHolder anyDetailsHolder = new KamaradAccountDetailsHolder();

            // Get the user account details
            kamaradRef.getAccountDetails(1, anyDetailsHolder);
      
            // Extract the account details from the holder object
            KamaradAccountDetails accountDetails = anyDetailsHolder.value;

            // Display user account information
            System.out.println("Unique Id: " + accountDetails.uniqueId);
            System.out.println("Name: " + accountDetails.name);
            System.out.println("Address: " + accountDetails.address);
            System.out.println("Phone: " + accountDetails.phoneNumber);
            System.out.println("Credit: " + accountDetails.credit);

        } catch (Exception e) {
                System.out.println("ERROR : " + e) ;
                e.printStackTrace(System.out);
        }
    }
}
